package de.gianttree.mc.chestsorter.commands

import de.gianttree.mc.chestsorter.commands.internal.ItemStackComparator
import de.gianttree.mc.chestsorter.commands.internal.SortingPlayer
import de.gianttree.mc.chestsorter.commands.internal.SortingView
import net.kyori.adventure.text.Component
import net.kyori.adventure.text.format.NamedTextColor
import org.bukkit.Bukkit
import org.bukkit.Location
import org.bukkit.Material
import org.bukkit.NamespacedKey
import org.bukkit.block.Block
import org.bukkit.block.Container
import org.bukkit.command.Command
import org.bukkit.command.CommandSender
import org.bukkit.command.TabExecutor
import org.bukkit.entity.LivingEntity
import org.bukkit.event.inventory.ClickType
import org.bukkit.event.inventory.InventoryAction
import org.bukkit.event.inventory.InventoryClickEvent
import org.bukkit.event.inventory.InventoryType
import org.bukkit.inventory.ItemStack

class ChestSorterCommand : TabExecutor {
    override fun onTabComplete(
        sender: CommandSender,
        command: Command,
        alias: String,
        args: Array<out String>
    ): MutableList<String>? {
        when (args.size) {
            1 -> return mutableListOf(
                "",
                this.targetBlockLocationToString(sender)
            )
                .filter { it.startsWith(args.last()) }
                .toMutableList()

            4 -> return Bukkit.getWorlds().map { it.key.toString() }.toMutableList()
        }
        return null
    }

    private fun targetBlockLocationToString(sender: CommandSender): String {
        if (sender is LivingEntity) {
            val targetBlock = sender.getTargetBlockExact(16) ?: return ""
            return "${targetBlock.x} ${targetBlock.y} ${targetBlock.z}"
        } else {
            return ""
        }
    }

    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<out String>): Boolean {
        val targetBlock = if (args.size == 4) {
            val key = NamespacedKey.fromString(args[3]) ?: return false
            Location(Bukkit.getWorld(key), args[0].toDouble(), args[1].toDouble(), args[2].toDouble()).block
        } else if (args.size == 3 && sender is LivingEntity) {
            Location(sender.world, args[0].toDouble(), args[1].toDouble(), args[2].toDouble()).block
        } else if (args.isEmpty() && sender is LivingEntity) {
            sender.getTargetBlockExact(16)
        } else {
            null
        }
        if (targetBlock?.state !is Container) {
            sender.sendMessage(
                Component.text("The specified block does not have a compatible inventory").color(NamedTextColor.RED)
            )
            return false
        }
        return sortContainer(sender, targetBlock)
    }

    private fun sortContainer(
        sender: CommandSender,
        targetBlock: Block
    ): Boolean {
        var container = targetBlock.state as Container
        var inventory = container.inventory

        val items = mutableListOf<ItemStack>()
        val indices = mutableListOf<Int>()

        val player = SortingPlayer.of(sender, targetBlock.location)
        val view = SortingView(
            inventory,
            player
        )

        for (slot in 0 until inventory.size) {
            if (view.getSlotType(slot) != InventoryType.SlotType.CONTAINER) {
                continue // Skip non-container slots
            }


            val event = InventoryClickEvent(
                view,
                InventoryType.SlotType.CONTAINER,
                slot,
                ClickType.LEFT,
                InventoryAction.PICKUP_ALL
            )

            if (event.callEvent()) {
                val cursor = event.cursor
                indices.add(slot)

                if (cursor.type != Material.AIR) {
                    items.add(cursor)
                    container = targetBlock.state as Container
                    inventory = container.inventory
                    continue
                }

                val inventoryItem = inventory.getItem(slot)
                if (inventoryItem != null
                    && inventoryItem.type != Material.AIR
                ) {
                    items.add(inventoryItem)
                    inventory.setItem(slot, emptyItem)
                    continue
                }
            } else {
                val cursor = event.cursor
                if (cursor.type != Material.AIR) {
                    indices.add(slot)
                    items.add(cursor)
                    container = targetBlock.location.block.state as Container
                    inventory = container.inventory
                    continue
                }
            }
        }

        val sortedItems = items.sortedWith(ItemStackComparator).toMutableList()
        mergeItems(sortedItems)
        sortedItems.removeIf { it.type == Material.AIR }

        indices.zip(sortedItems).forEach { (slot, item) ->
            player.cursor = item
            val event = InventoryClickEvent(
                view,
                InventoryType.SlotType.CONTAINER,
                slot,
                ClickType.LEFT,
                InventoryAction.PLACE_ALL
            )

            if (event.callEvent()) {
                val cursor = event.cursor
                if (cursor.type == Material.AIR) {
                    container = targetBlock.location.block.state as Container
                    inventory = container.inventory
                    // nothing to do, event did handle it
                } else {
                    inventory.setItem(slot, item)
                }
            } else {
                val cursor = event.cursor
                container = targetBlock.location.block.state as Container
                inventory = container.inventory
                if (cursor.type == Material.AIR) {
                    // even though the event was cancelled, it did handle placing the item
                } else {
                    sender.sendMessage(
                        Component.text("Could only retrieve, but not place item in slot $slot!")
                            .color(NamedTextColor.RED)
                    )
                }
            }
        }

        sender.sendMessage(
            Component.text("Successfully sorted chest!").color(NamedTextColor.GREEN)
                .append(Component.newline())
                .append(
                    Component.text("Handled ${items.size} item(s)!").color(NamedTextColor.GREEN)
                )
        )

        return true
    }

    companion object {
        val emptyItem = ItemStack(Material.AIR)

        private fun mergeItems(sortedItems: MutableList<ItemStack>) {
            var lastStack = sortedItems.firstOrNull()
            for (i in 1 until sortedItems.size) {
                val currentStack = sortedItems[i]
                if (lastStack != null && lastStack.amount < lastStack.maxStackSize && lastStack.isSimilar(currentStack)) {
                    val itemsToAdd = lastStack.maxStackSize - lastStack.amount
                    val itemsToAddToLastStack = itemsToAdd.coerceAtMost(currentStack.amount)
                    lastStack.amount += itemsToAddToLastStack
                    currentStack.amount -= itemsToAddToLastStack
                    if (currentStack.amount == 0) {
                        sortedItems[i] = emptyItem
                    } else {
                        lastStack = currentStack
                    }
                } else {
                    lastStack = currentStack
                }
            }
        }
    }

}
