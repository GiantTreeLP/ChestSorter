package de.gianttree.mc.chestsorter.commands.internal

import com.google.common.base.Preconditions
import org.bukkit.entity.HumanEntity
import org.bukkit.event.inventory.InventoryType
import org.bukkit.inventory.Inventory
import org.bukkit.inventory.InventoryView
import org.bukkit.inventory.InventoryView.OUTSIDE
import org.bukkit.inventory.ItemStack


class SortingView(
    private val inventory: Inventory,
    private val player: SortingPlayer
) : InventoryView {

    private val originalTitle = "Sorting inventory"

    override fun getTopInventory(): Inventory {
        return inventory
    }

    override fun getBottomInventory(): Inventory {
        return player.inventory
    }

    override fun getPlayer(): HumanEntity {
        return player
    }

    override fun getType(): InventoryType {
        return inventory.type
    }

    override fun setItem(slot: Int, item: ItemStack?) {
        val inventory = getInventory(slot)
        if (inventory != null) {
            inventory.setItem(convertSlot(slot), item)
        } else if (item != null) {
            getPlayer().world.dropItemNaturally(getPlayer().location, item)
        }
    }

    override fun getItem(slot: Int): ItemStack? {
        return getInventory(slot)?.getItem(convertSlot(slot))
    }

    override fun setCursor(item: ItemStack?) {
        getPlayer().setItemOnCursor(item)
    }

    override fun getCursor(): ItemStack {
        return getPlayer().itemOnCursor
    }

    override fun getInventory(rawSlot: Int): Inventory? {
        // Slot may be -1 if not properly detected due to client bug
        // e.g. dropping an item into part of the enchantment list section of an enchanting table
        if (rawSlot == OUTSIDE || rawSlot == -1) {
            return null;
        }
        Preconditions.checkArgument(rawSlot >= 0, "Negative, non outside slot %s", rawSlot);
        Preconditions.checkArgument(rawSlot < countSlots(), "Slot %s greater than inventory slot count", rawSlot);

        return if (rawSlot < topInventory.size) {
            topInventory;
        } else {
            bottomInventory;
        }

    }

    override fun convertSlot(rawSlot: Int): Int {
        val numInTop = topInventory.size

        // Index from the top inventory as having slots from [0,size]
        if (rawSlot < numInTop) {
            return rawSlot
        }


        // Move down the slot index by the top size
        var slot = rawSlot - numInTop


        // Player crafting slots are indexed differently. The matrix is caught by the first return.
        // Creative mode is the same, except that you can't see the crafting slots (but the IDs are still used)
        if (type == InventoryType.CRAFTING || type == InventoryType.CREATIVE) {
            /*
             * Raw Slots:
             *
             * 5             1  2     0
             * 6             3  4
             * 7
             * 8           45
             * 9  10 11 12 13 14 15 16 17
             * 18 19 20 21 22 23 24 25 26
             * 27 28 29 30 31 32 33 34 35
             * 36 37 38 39 40 41 42 43 44
             */

            /*
             * Converted Slots:
             *
             * 39             1  2     0
             * 38             3  4
             * 37
             * 36          40
             * 9  10 11 12 13 14 15 16 17
             * 18 19 20 21 22 23 24 25 26
             * 27 28 29 30 31 32 33 34 35
             * 0  1  2  3  4  5  6  7  8
             */

            if (slot < 4) {
                // Send [5,8] to [39,36]
                return 39 - slot
            } else if (slot > 39) {
                // Slot lives in the extra slot section
                return slot
            } else {
                // Reset index so 9 -> 0
                slot -= 4
            }
        }


        // 27 = 36 - 9
        if (slot >= 27) {
            // Put into hotbar section
            slot -= 27
        } else {
            // Take out of hotbar section
            // 9 = 36 - 27
            slot += 9
        }

        return slot
    }

    override fun getSlotType(slot: Int): InventoryType.SlotType {
        var type = InventoryType.SlotType.CONTAINER
        if (slot >= 0 && slot < this.topInventory.size) {
            when (this.type) {
                InventoryType.BLAST_FURNACE, InventoryType.FURNACE, InventoryType.SMOKER -> type = when (slot) {
                    1 -> {
                        InventoryType.SlotType.FUEL
                    }

                    2 -> {
                        InventoryType.SlotType.RESULT
                    }

                    else -> {
                        InventoryType.SlotType.CRAFTING
                    }
                }

                InventoryType.BREWING -> type = if (slot == 3) {
                    InventoryType.SlotType.FUEL
                } else {
                    InventoryType.SlotType.CRAFTING
                }

                InventoryType.ENCHANTING -> type = InventoryType.SlotType.CRAFTING
                InventoryType.WORKBENCH, InventoryType.CRAFTING -> type = if (slot == 0) {
                    InventoryType.SlotType.RESULT
                } else {
                    InventoryType.SlotType.CRAFTING
                }

                InventoryType.BEACON -> type = InventoryType.SlotType.CRAFTING
                InventoryType.ANVIL, InventoryType.CARTOGRAPHY, InventoryType.GRINDSTONE, InventoryType.MERCHANT -> type =
                    if (slot == 2) {
                        InventoryType.SlotType.RESULT
                    } else {
                        InventoryType.SlotType.CRAFTING
                    }

                InventoryType.STONECUTTER -> type = if (slot == 1) {
                    InventoryType.SlotType.RESULT
                } else {
                    InventoryType.SlotType.CRAFTING
                }

                InventoryType.LOOM, InventoryType.SMITHING, InventoryType.SMITHING_NEW -> type = if (slot == 3) {
                    InventoryType.SlotType.RESULT
                } else {
                    InventoryType.SlotType.CRAFTING
                }

                else -> {}
            }
        } else {
            if (slot < 0) {
                type = InventoryType.SlotType.OUTSIDE
            } else if (this.type == InventoryType.CRAFTING) { // Also includes creative inventory
                if (slot < 9) {
                    type = InventoryType.SlotType.ARMOR
                } else if (slot > 35) {
                    type = InventoryType.SlotType.QUICKBAR
                }
            } else if (slot >= (this.countSlots() - (9 + 4 + 1))) { // Quickbar, Armor, Offhand
                type = InventoryType.SlotType.QUICKBAR
            }
        }
        return type
    }

    override fun close() {
        getPlayer().closeInventory()
    }

    override fun countSlots(): Int {
        return topInventory.size + bottomInventory.size;
    }

    override fun setProperty(prop: InventoryView.Property, value: Int): Boolean {
        return getPlayer().setWindowProperty(prop, value)
    }

    @Deprecated("Deprecated in Java")
    override fun getTitle(): String {
        return "Sorting inventory"
    }

    override fun getOriginalTitle(): String {
        return originalTitle
    }

    override fun setTitle(title: String) {
        return // Ignored
    }

}
