package de.gianttree.mc.chestsorter.commands.internal

import net.kyori.adventure.text.serializer.plain.PlainTextComponentSerializer
import org.bukkit.inventory.ItemStack
import org.bukkit.inventory.meta.Damageable

object ItemStackComparator : Comparator<ItemStack> {

    override fun compare(o1: ItemStack?, o2: ItemStack?): Int {
        var order: Int
        if (o1 == null && o2 == null) {
            return 0
        }

        // Handle null values
        // If one of the items is null, we want to sort it to the end
        if (o1 == null) {
            return 1
        } else if (o2 == null) {
            return -1
        }
        if (o1 == o2) {
            return 0
        }
        // Compare the types of the items
        order = o1.type.ordinal.compareTo(o2.type.ordinal)
        if (order != 0) {
            return order
        }

        // Compare the names of the items
        val o1DisplayName = getDisplayNameOrNull(o1)
        val o2DisplayName = getDisplayNameOrNull(o2)

        if (o1DisplayName != null && o2DisplayName != null) {
            order = o1DisplayName.compareTo(o2DisplayName)
        } else if (o1DisplayName != null) {
            return -1
        } else if (o2DisplayName != null) {
            return 1
        }
        if (order != 0) {
            return order
        }

        // Compare the amounts of the items
        // Smaller stacks are sorted last
        order = o1.amount.compareTo(o2.amount)
        if (order != 0) {
            return -order
        }
        // Compare the damage value of the items
        val o1Damage = getItemDamage(o1)
        val o2Damage = getItemDamage(o2)

        return o1Damage.compareTo(o2Damage)
    }

    private fun getItemDamage(itemStack: ItemStack): Int {
        return if (itemStack.hasItemMeta() && itemStack.itemMeta is Damageable) {
            (itemStack.itemMeta as Damageable).damage
        } else {
            0
        }
    }

    private fun getDisplayNameOrNull(itemStack: ItemStack): String? {
        return if (itemStack.hasItemMeta()) {
            val meta = itemStack.itemMeta
            if (meta.hasDisplayName()) {
                meta.displayName()?.let(PlainTextComponentSerializer.plainText()::serialize)
            } else {
                null
            }
        } else {
            null
        }
    }
}
