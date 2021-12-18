package de.gianttree.mc.chestsorter.commands.internal

import org.bukkit.inventory.ItemStack
import org.bukkit.inventory.meta.Damageable
import org.bukkit.inventory.meta.EnchantmentStorageMeta

object ItemStackComparator : Comparator<ItemStack> {
    private val comparator = Comparator.nullsLast(
        Comparator.comparingInt<ItemStack> {
            it.type.ordinal
        }.thenComparingInt {
            -it.amount
        }.thenComparingInt {
            if (it.hasItemMeta()) {
                (it.itemMeta as Damageable).damage
            } else {
                0
            }
        }.thenComparingInt {
            if (it.hasItemMeta()) {
                it.itemMeta.enchants.keys.sumOf { enchantment ->
                    enchantment.rarity.weight
                }
            } else {
                0
            }
        }.thenComparingInt {
            // Handle enchanted books
            if (it.hasItemMeta() && it.itemMeta is EnchantmentStorageMeta) {
                val meta = it.itemMeta as EnchantmentStorageMeta
                meta.storedEnchants.keys.sumOf { enchantment ->
                    enchantment.rarity.weight
                }
            } else {
                0
            }
        }
    )

    override fun compare(o1: ItemStack?, o2: ItemStack?): Int {
        return comparator.compare(o1, o2)
    }
}
