package de.gianttree.mc.chestsorter.commands.internal

import org.bukkit.entity.HumanEntity
import org.bukkit.event.inventory.InventoryType
import org.bukkit.inventory.Inventory
import org.bukkit.inventory.InventoryView

class SortingView(
    private val inventory: Inventory,
    private val player: SortingPlayer
) : InventoryView() {

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

    override fun getTitle(): String {
        return "Sorting inventory"
    }

}
