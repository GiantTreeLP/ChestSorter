package de.gianttree.mc.chestsorter.commands.internal

import net.kyori.adventure.text.serializer.plain.PlainTextComponentSerializer
import org.bukkit.inventory.ItemStack
import org.bukkit.inventory.meta.Damageable
import org.bukkit.inventory.meta.EnchantmentStorageMeta
import java.util.function.Function

object ItemStackComparator : Comparator<ItemStack> {
    private val comparator = Comparator.nullsLast(
        Comparator.comparingInt<ItemStack> {
            it.type.ordinal
        }.thenComparing(Function<ItemStack, String> {
            return@Function if (it.hasItemMeta()) {
                val meta = it.itemMeta
                if (meta.hasDisplayName()) {
                    meta.displayName()?.let(PlainTextComponentSerializer.plainText()::serialize)
                } else {
                    null
                }
            } else {
                null
            }
        }, Comparator.nullsLast(Comparator.comparing(Function.identity()))).thenComparingInt {
            -it.amount
        }.thenComparingInt {
            if (it.hasItemMeta() && it.itemMeta is Damageable) {
                (it.itemMeta as Damageable).damage
            } else {
                0
            }
        }
    )

    override fun compare(o1: ItemStack?, o2: ItemStack?): Int {
        return comparator.compare(o1, o2)
    }
}
