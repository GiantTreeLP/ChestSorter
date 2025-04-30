package de.gianttree.mc.chestsorter.commands.internal

import org.bukkit.Location
import org.bukkit.Material
import org.bukkit.entity.HumanEntity
import org.bukkit.event.inventory.InventoryType
import org.bukkit.inventory.*

class SortingPlayerInventory(private val inventory: Inventory) : PlayerInventory {
    override fun iterator(): MutableListIterator<ItemStack> {
        return this.inventory.iterator()
    }

    override fun iterator(index: Int): MutableListIterator<ItemStack> {
        return this.inventory.iterator(index)
    }

    override fun getSize(): Int {
        return this.inventory.size
    }

    override fun getMaxStackSize(): Int {
        return this.inventory.maxStackSize
    }

    override fun setMaxStackSize(size: Int) {
        TODO("Not yet implemented")
    }

    override fun getItem(slot: EquipmentSlot): ItemStack {
        TODO("Not yet implemented")
    }

    override fun getItem(index: Int): ItemStack? {
        return this.inventory.getItem(index)
    }

    override fun setItem(index: Int, item: ItemStack?) {
        this.inventory.setItem(index, item)
    }

    override fun setItem(slot: EquipmentSlot, item: ItemStack?) {
        TODO("Not yet implemented")
    }

    override fun addItem(vararg items: ItemStack): HashMap<Int, ItemStack> {
        return this.inventory.addItem(*items)
    }

    override fun removeItem(vararg items: ItemStack): HashMap<Int, ItemStack> {
        return this.inventory.removeItem(*items)
    }

    override fun removeItemAnySlot(vararg items: ItemStack): HashMap<Int, ItemStack> {
        return this.inventory.removeItemAnySlot(*items)
    }

    override fun getContents(): Array<out ItemStack?> {
        return this.inventory.contents
    }

    override fun setContents(items: Array<out ItemStack?>) {
        this.inventory.contents = items
    }

    override fun getStorageContents(): Array<out ItemStack?> {
        return this.inventory.storageContents
    }

    override fun setStorageContents(items: Array<out ItemStack?>) {
        this.inventory.storageContents = items
    }

    override fun contains(material: Material): Boolean {
        return this.inventory.contains(material)
    }

    override fun contains(item: ItemStack?): Boolean {
        return this.inventory.contains(item)
    }

    override fun contains(material: Material, amount: Int): Boolean {
        return this.inventory.contains(material, amount)
    }

    override fun contains(item: ItemStack?, amount: Int): Boolean {
        return this.inventory.contains(item, amount)
    }

    override fun containsAtLeast(item: ItemStack?, amount: Int): Boolean {
        return this.inventory.containsAtLeast(item, amount)
    }

    override fun all(material: Material): HashMap<Int, out ItemStack> {
        return this.inventory.all(material)
    }

    override fun all(item: ItemStack?): HashMap<Int, out ItemStack> {
        return this.inventory.all(item)
    }

    override fun first(material: Material): Int {
        return this.inventory.first(material)
    }

    override fun first(item: ItemStack): Int {
        return this.inventory.first(item)
    }

    override fun firstEmpty(): Int {
        return this.inventory.firstEmpty()
    }

    override fun isEmpty(): Boolean {
        return this.inventory.isEmpty
    }

    override fun remove(material: Material) {
        this.inventory.remove(material)
    }

    override fun remove(item: ItemStack) {
        this.inventory.remove(item)
    }

    override fun clear(index: Int) {
        this.inventory.clear(index)
    }

    override fun clear() {
        this.inventory.clear()
    }

    override fun close(): Int {
        return this.inventory.close()
    }

    override fun getViewers(): MutableList<HumanEntity> {
        return this.inventory.viewers
    }

    override fun getType(): InventoryType {
        return this.inventory.type
    }

    override fun getHolder(): HumanEntity? {
        return null
    }

    override fun getHolder(useSnapshot: Boolean): InventoryHolder? {
        TODO("Not yet implemented")
    }

    override fun getLocation(): Location? {
        return this.inventory.location
    }

    override fun getArmorContents(): Array<ItemStack> {
        TODO("Not yet implemented")
    }

    override fun getExtraContents(): Array<ItemStack> {
        TODO("Not yet implemented")
    }

    override fun getHelmet(): ItemStack? {
        TODO("Not yet implemented")
    }

    override fun getChestplate(): ItemStack? {
        TODO("Not yet implemented")
    }

    override fun getLeggings(): ItemStack? {
        TODO("Not yet implemented")
    }

    override fun getBoots(): ItemStack? {
        TODO("Not yet implemented")
    }

    override fun setArmorContents(items: Array<out ItemStack?>) {
        TODO("Not yet implemented")
    }

    override fun setExtraContents(items: Array<out ItemStack?>) {
        TODO("Not yet implemented")
    }

    override fun setHelmet(helmet: ItemStack?) {
        TODO("Not yet implemented")
    }

    override fun setChestplate(chestplate: ItemStack?) {
        TODO("Not yet implemented")
    }

    override fun setLeggings(leggings: ItemStack?) {
        TODO("Not yet implemented")
    }

    override fun setBoots(boots: ItemStack?) {
        TODO("Not yet implemented")
    }

    override fun getItemInMainHand(): ItemStack {
        TODO("Not yet implemented")
    }

    override fun setItemInMainHand(item: ItemStack?) {
        TODO("Not yet implemented")
    }

    override fun getItemInOffHand(): ItemStack {
        TODO("Not yet implemented")
    }

    override fun setItemInOffHand(item: ItemStack?) {
        TODO("Not yet implemented")
    }

    @Deprecated("Deprecated in Java")
    override fun getItemInHand(): ItemStack {
        TODO("Not yet implemented")
    }

    @Deprecated("Deprecated in Java")
    override fun setItemInHand(stack: ItemStack?) {
        TODO("Not yet implemented")
    }

    override fun getHeldItemSlot(): Int {
        TODO("Not yet implemented")
    }

    override fun setHeldItemSlot(slot: Int) {
        TODO("Not yet implemented")
    }
}
