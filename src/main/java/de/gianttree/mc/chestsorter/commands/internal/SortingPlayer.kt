package de.gianttree.mc.chestsorter.commands.internal

import com.destroystokyo.paper.block.TargetBlockInfo
import com.destroystokyo.paper.entity.TargetEntityInfo
import net.kyori.adventure.text.Component
import org.bukkit.*
import org.bukkit.attribute.Attribute
import org.bukkit.attribute.AttributeInstance
import org.bukkit.block.Block
import org.bukkit.block.BlockFace
import org.bukkit.block.PistonMoveReaction
import org.bukkit.block.Sign
import org.bukkit.command.CommandSender
import org.bukkit.entity.*
import org.bukkit.entity.memory.MemoryKey
import org.bukkit.event.entity.CreatureSpawnEvent
import org.bukkit.event.entity.EntityDamageEvent
import org.bukkit.event.inventory.InventoryCloseEvent
import org.bukkit.event.inventory.InventoryType
import org.bukkit.event.player.PlayerTeleportEvent
import org.bukkit.inventory.*
import org.bukkit.metadata.MetadataValue
import org.bukkit.permissions.Permission
import org.bukkit.permissions.PermissionAttachment
import org.bukkit.permissions.PermissionAttachmentInfo
import org.bukkit.persistence.PersistentDataContainer
import org.bukkit.plugin.Plugin
import org.bukkit.potion.PotionEffect
import org.bukkit.potion.PotionEffectType
import org.bukkit.util.BoundingBox
import org.bukkit.util.RayTraceResult
import org.bukkit.util.Vector
import java.util.*

class SortingPlayer(
    private val sender: CommandSender,
    private val location: Location
) : HumanEntity {

    internal var cursor: ItemStack = ItemStack(Material.AIR)
    internal var inventory: PlayerInventory = SortingPlayerInventory(Bukkit.createInventory(null, InventoryType.PLAYER))

    override fun getAttribute(attribute: Attribute): AttributeInstance? {
        TODO("Not yet implemented")
    }

    override fun registerAttribute(attribute: Attribute) {
        TODO("Not yet implemented")
    }

    override fun setMetadata(metadataKey: String, newMetadataValue: MetadataValue) {
        TODO("Not yet implemented")
    }

    override fun getMetadata(metadataKey: String): MutableList<MetadataValue> {
        TODO("Not yet implemented")
    }

    override fun hasMetadata(metadataKey: String): Boolean {
        TODO("Not yet implemented")
    }

    override fun removeMetadata(metadataKey: String, owningPlugin: Plugin) {
        TODO("Not yet implemented")
    }

    override fun sendMessage(message: String) {
        this.sender.sendMessage(message)
    }

    override fun sendMessage(vararg messages: String) {
        this.sender.sendMessage(*messages)
    }

    override fun sendMessage(sender: UUID?, message: String) {
        this.sender.sendMessage(sender, message)
    }

    override fun sendMessage(sender: UUID?, vararg messages: String) {
        this.sender.sendMessage(sender, *messages)
    }

    override fun isOp(): Boolean {
        return this.sender.isOp
    }

    override fun setOp(value: Boolean) {
        this.sender.isOp = value
    }

    override fun isPermissionSet(name: String): Boolean {
        return this.sender.isPermissionSet(name)
    }

    override fun isPermissionSet(perm: Permission): Boolean {
        return this.sender.isPermissionSet(perm)
    }

    override fun hasPermission(name: String): Boolean {
        return this.sender.hasPermission(name)
    }

    override fun hasPermission(perm: Permission): Boolean {
        return this.sender.hasPermission(perm)
    }

    override fun addAttachment(plugin: Plugin, name: String, value: Boolean): PermissionAttachment {
        return this.sender.addAttachment(plugin, name, value)
    }

    override fun addAttachment(plugin: Plugin): PermissionAttachment {
        return this.sender.addAttachment(plugin)
    }

    override fun addAttachment(plugin: Plugin, name: String, value: Boolean, ticks: Int): PermissionAttachment? {
        return this.sender.addAttachment(plugin, name, value, ticks)
    }

    override fun addAttachment(plugin: Plugin, ticks: Int): PermissionAttachment? {
        return this.sender.addAttachment(plugin, ticks)
    }

    override fun removeAttachment(attachment: PermissionAttachment) {
        return this.sender.removeAttachment(attachment)
    }

    override fun recalculatePermissions() {
        this.sender.recalculatePermissions()
    }

    override fun getEffectivePermissions(): MutableSet<PermissionAttachmentInfo> {
        return this.sender.effectivePermissions
    }

    override fun getServer(): Server {
        return Bukkit.getServer()
    }

    override fun getName(): String {
        return this.sender.name
    }

    override fun spigot(): Entity.Spigot {
        TODO("Not yet implemented")
    }

    override fun name(): Component {
        return this.sender.name()
    }

    override fun customName(): Component? {
        TODO("Not yet implemented")
    }

    override fun customName(customName: Component?) {
        TODO("Not yet implemented")
    }

    override fun getCustomName(): String? {
        TODO("Not yet implemented")
    }

    override fun setCustomName(name: String?) {
        TODO("Not yet implemented")
    }

    override fun getPersistentDataContainer(): PersistentDataContainer {
        TODO("Not yet implemented")
    }

    override fun getLocation(): Location {
        return this.location
    }

    override fun getLocation(loc: Location?): Location? {
        loc?.set(this.location.x, this.location.y, this.location.z)
        loc?.direction = this.location.direction
        return loc
    }

    override fun setVelocity(velocity: Vector) {
        TODO("Not yet implemented")
    }

    override fun getVelocity(): Vector {
        TODO("Not yet implemented")
    }

    override fun getHeight(): Double {
        TODO("Not yet implemented")
    }

    override fun getWidth(): Double {
        TODO("Not yet implemented")
    }

    override fun getBoundingBox(): BoundingBox {
        TODO("Not yet implemented")
    }

    override fun isOnGround(): Boolean {
        TODO("Not yet implemented")
    }

    override fun isInWater(): Boolean {
        TODO("Not yet implemented")
    }

    override fun getWorld(): World {
        return this.location.world
    }

    override fun setRotation(yaw: Float, pitch: Float) {
        TODO("Not yet implemented")
    }

    override fun teleport(location: Location): Boolean {
        TODO("Not yet implemented")
    }

    override fun teleport(location: Location, cause: PlayerTeleportEvent.TeleportCause): Boolean {
        TODO("Not yet implemented")
    }

    override fun teleport(destination: Entity): Boolean {
        TODO("Not yet implemented")
    }

    override fun teleport(destination: Entity, cause: PlayerTeleportEvent.TeleportCause): Boolean {
        TODO("Not yet implemented")
    }

    override fun getNearbyEntities(x: Double, y: Double, z: Double): MutableList<Entity> {
        TODO("Not yet implemented")
    }

    override fun getEntityId(): Int {
        TODO("Not yet implemented")
    }

    override fun getFireTicks(): Int {
        TODO("Not yet implemented")
    }

    override fun getMaxFireTicks(): Int {
        TODO("Not yet implemented")
    }

    override fun setFireTicks(ticks: Int) {
        TODO("Not yet implemented")
    }

    override fun setVisualFire(fire: Boolean) {
        TODO("Not yet implemented")
    }

    override fun isVisualFire(): Boolean {
        TODO("Not yet implemented")
    }

    override fun getFreezeTicks(): Int {
        TODO("Not yet implemented")
    }

    override fun getMaxFreezeTicks(): Int {
        TODO("Not yet implemented")
    }

    override fun setFreezeTicks(ticks: Int) {
        TODO("Not yet implemented")
    }

    override fun isFrozen(): Boolean {
        TODO("Not yet implemented")
    }

    override fun remove() {
        TODO("Not yet implemented")
    }

    override fun isDead(): Boolean {
        TODO("Not yet implemented")
    }

    override fun isValid(): Boolean {
        TODO("Not yet implemented")
    }

    override fun isPersistent(): Boolean {
        TODO("Not yet implemented")
    }

    override fun setPersistent(persistent: Boolean) {
        TODO("Not yet implemented")
    }

    override fun getPassenger(): Entity? {
        TODO("Not yet implemented")
    }

    override fun setPassenger(passenger: Entity): Boolean {
        TODO("Not yet implemented")
    }

    override fun getPassengers(): MutableList<Entity> {
        TODO("Not yet implemented")
    }

    override fun addPassenger(passenger: Entity): Boolean {
        TODO("Not yet implemented")
    }

    override fun removePassenger(passenger: Entity): Boolean {
        TODO("Not yet implemented")
    }

    override fun isEmpty(): Boolean {
        TODO("Not yet implemented")
    }

    override fun eject(): Boolean {
        TODO("Not yet implemented")
    }

    override fun getFallDistance(): Float {
        TODO("Not yet implemented")
    }

    override fun setFallDistance(distance: Float) {
        TODO("Not yet implemented")
    }

    override fun setLastDamageCause(event: EntityDamageEvent?) {
        TODO("Not yet implemented")
    }

    override fun getLastDamageCause(): EntityDamageEvent? {
        TODO("Not yet implemented")
    }

    override fun getUniqueId(): UUID {
        TODO("Not yet implemented")
    }

    override fun getTicksLived(): Int {
        TODO("Not yet implemented")
    }

    override fun setTicksLived(value: Int) {
        TODO("Not yet implemented")
    }

    override fun playEffect(type: EntityEffect) {
        TODO("Not yet implemented")
    }

    override fun getType(): EntityType {
        return EntityType.UNKNOWN
    }

    override fun isInsideVehicle(): Boolean {
        TODO("Not yet implemented")
    }

    override fun leaveVehicle(): Boolean {
        TODO("Not yet implemented")
    }

    override fun getVehicle(): Entity? {
        TODO("Not yet implemented")
    }

    override fun setCustomNameVisible(flag: Boolean) {
        TODO("Not yet implemented")
    }

    override fun isCustomNameVisible(): Boolean {
        TODO("Not yet implemented")
    }

    override fun setGlowing(flag: Boolean) {
        TODO("Not yet implemented")
    }

    override fun isGlowing(): Boolean {
        TODO("Not yet implemented")
    }

    override fun setInvulnerable(flag: Boolean) {
        TODO("Not yet implemented")
    }

    override fun isInvulnerable(): Boolean {
        TODO("Not yet implemented")
    }

    override fun isSilent(): Boolean {
        TODO("Not yet implemented")
    }

    override fun setSilent(flag: Boolean) {
        TODO("Not yet implemented")
    }

    override fun hasGravity(): Boolean {
        TODO("Not yet implemented")
    }

    override fun setGravity(gravity: Boolean) {
        TODO("Not yet implemented")
    }

    override fun getPortalCooldown(): Int {
        TODO("Not yet implemented")
    }

    override fun setPortalCooldown(cooldown: Int) {
        TODO("Not yet implemented")
    }

    override fun getScoreboardTags(): MutableSet<String> {
        TODO("Not yet implemented")
    }

    override fun addScoreboardTag(tag: String): Boolean {
        TODO("Not yet implemented")
    }

    override fun removeScoreboardTag(tag: String): Boolean {
        TODO("Not yet implemented")
    }

    override fun getPistonMoveReaction(): PistonMoveReaction {
        TODO("Not yet implemented")
    }

    override fun getFacing(): BlockFace {
        TODO("Not yet implemented")
    }

    override fun getPose(): Pose {
        TODO("Not yet implemented")
    }

    override fun teamDisplayName(): Component {
        TODO("Not yet implemented")
    }

    override fun getOrigin(): Location? {
        TODO("Not yet implemented")
    }

    override fun fromMobSpawner(): Boolean {
        TODO("Not yet implemented")
    }

    override fun getEntitySpawnReason(): CreatureSpawnEvent.SpawnReason {
        TODO("Not yet implemented")
    }

    override fun isInRain(): Boolean {
        TODO("Not yet implemented")
    }

    override fun isInBubbleColumn(): Boolean {
        TODO("Not yet implemented")
    }

    override fun isInWaterOrRain(): Boolean {
        TODO("Not yet implemented")
    }

    override fun isInWaterOrBubbleColumn(): Boolean {
        TODO("Not yet implemented")
    }

    override fun isInWaterOrRainOrBubbleColumn(): Boolean {
        TODO("Not yet implemented")
    }

    override fun isInLava(): Boolean {
        TODO("Not yet implemented")
    }

    override fun isTicking(): Boolean {
        TODO("Not yet implemented")
    }

    override fun getTrackedPlayers(): MutableSet<Player> {
        TODO("Not yet implemented")
    }

    override fun spawnAt(location: Location, reason: CreatureSpawnEvent.SpawnReason): Boolean {
        TODO("Not yet implemented")
    }

    override fun damage(amount: Double) {
        TODO("Not yet implemented")
    }

    override fun damage(amount: Double, source: Entity?) {
        TODO("Not yet implemented")
    }

    override fun getHealth(): Double {
        TODO("Not yet implemented")
    }

    override fun setHealth(health: Double) {
        TODO("Not yet implemented")
    }

    override fun getAbsorptionAmount(): Double {
        TODO("Not yet implemented")
    }

    override fun setAbsorptionAmount(amount: Double) {
        TODO("Not yet implemented")
    }

    override fun getMaxHealth(): Double {
        TODO("Not yet implemented")
    }

    override fun setMaxHealth(health: Double) {
        TODO("Not yet implemented")
    }

    override fun resetMaxHealth() {
        TODO("Not yet implemented")
    }

    override fun <T : Projectile?> launchProjectile(projectile: Class<out T>): T {
        TODO("Not yet implemented")
    }

    override fun <T : Projectile?> launchProjectile(projectile: Class<out T>, velocity: Vector?): T {
        TODO("Not yet implemented")
    }

    override fun getEyeHeight(): Double {
        TODO("Not yet implemented")
    }

    override fun getEyeHeight(ignorePose: Boolean): Double {
        TODO("Not yet implemented")
    }

    override fun getEyeLocation(): Location {
        TODO("Not yet implemented")
    }

    override fun getLineOfSight(transparent: MutableSet<Material>?, maxDistance: Int): MutableList<Block> {
        TODO("Not yet implemented")
    }

    override fun getTargetBlock(transparent: MutableSet<Material>?, maxDistance: Int): Block {
        TODO("Not yet implemented")
    }

    override fun getTargetBlock(maxDistance: Int, fluidMode: TargetBlockInfo.FluidMode): Block? {
        TODO("Not yet implemented")
    }

    override fun getTargetBlockFace(maxDistance: Int, fluidMode: TargetBlockInfo.FluidMode): BlockFace? {
        TODO("Not yet implemented")
    }

    override fun getTargetBlockInfo(maxDistance: Int, fluidMode: TargetBlockInfo.FluidMode): TargetBlockInfo? {
        TODO("Not yet implemented")
    }

    override fun getTargetEntity(maxDistance: Int, ignoreBlocks: Boolean): Entity? {
        TODO("Not yet implemented")
    }

    override fun getTargetEntityInfo(maxDistance: Int, ignoreBlocks: Boolean): TargetEntityInfo? {
        TODO("Not yet implemented")
    }

    override fun getLastTwoTargetBlocks(transparent: MutableSet<Material>?, maxDistance: Int): MutableList<Block> {
        TODO("Not yet implemented")
    }

    override fun getTargetBlockExact(maxDistance: Int): Block? {
        TODO("Not yet implemented")
    }

    override fun getTargetBlockExact(maxDistance: Int, fluidCollisionMode: FluidCollisionMode): Block? {
        TODO("Not yet implemented")
    }

    override fun rayTraceBlocks(maxDistance: Double): RayTraceResult? {
        TODO("Not yet implemented")
    }

    override fun rayTraceBlocks(maxDistance: Double, fluidCollisionMode: FluidCollisionMode): RayTraceResult? {
        TODO("Not yet implemented")
    }

    override fun getRemainingAir(): Int {
        TODO("Not yet implemented")
    }

    override fun setRemainingAir(ticks: Int) {
        TODO("Not yet implemented")
    }

    override fun getMaximumAir(): Int {
        TODO("Not yet implemented")
    }

    override fun setMaximumAir(ticks: Int) {
        TODO("Not yet implemented")
    }

    override fun getArrowCooldown(): Int {
        TODO("Not yet implemented")
    }

    override fun setArrowCooldown(ticks: Int) {
        TODO("Not yet implemented")
    }

    override fun getArrowsInBody(): Int {
        TODO("Not yet implemented")
    }

    override fun setArrowsInBody(count: Int) {
        TODO("Not yet implemented")
    }

    override fun getBeeStingerCooldown(): Int {
        TODO("Not yet implemented")
    }

    override fun setBeeStingerCooldown(ticks: Int) {
        TODO("Not yet implemented")
    }

    override fun getBeeStingersInBody(): Int {
        TODO("Not yet implemented")
    }

    override fun setBeeStingersInBody(count: Int) {
        TODO("Not yet implemented")
    }

    override fun getMaximumNoDamageTicks(): Int {
        TODO("Not yet implemented")
    }

    override fun setMaximumNoDamageTicks(ticks: Int) {
        TODO("Not yet implemented")
    }

    override fun getLastDamage(): Double {
        TODO("Not yet implemented")
    }

    override fun setLastDamage(damage: Double) {
        TODO("Not yet implemented")
    }

    override fun getNoDamageTicks(): Int {
        TODO("Not yet implemented")
    }

    override fun setNoDamageTicks(ticks: Int) {
        TODO("Not yet implemented")
    }

    override fun getKiller(): Player? {
        TODO("Not yet implemented")
    }

    override fun setKiller(killer: Player?) {
        TODO("Not yet implemented")
    }

    override fun addPotionEffect(effect: PotionEffect): Boolean {
        TODO("Not yet implemented")
    }

    override fun addPotionEffect(effect: PotionEffect, force: Boolean): Boolean {
        TODO("Not yet implemented")
    }

    override fun addPotionEffects(effects: MutableCollection<PotionEffect>): Boolean {
        TODO("Not yet implemented")
    }

    override fun hasPotionEffect(type: PotionEffectType): Boolean {
        TODO("Not yet implemented")
    }

    override fun getPotionEffect(type: PotionEffectType): PotionEffect? {
        TODO("Not yet implemented")
    }

    override fun removePotionEffect(type: PotionEffectType) {
        TODO("Not yet implemented")
    }

    override fun getActivePotionEffects(): MutableCollection<PotionEffect> {
        TODO("Not yet implemented")
    }

    override fun hasLineOfSight(other: Entity): Boolean {
        TODO("Not yet implemented")
    }

    override fun hasLineOfSight(location: Location): Boolean {
        TODO("Not yet implemented")
    }

    override fun getRemoveWhenFarAway(): Boolean {
        TODO("Not yet implemented")
    }

    override fun setRemoveWhenFarAway(remove: Boolean) {
        TODO("Not yet implemented")
    }

    override fun getEquipment(): EntityEquipment {
        TODO("Not yet implemented")
    }

    override fun setCanPickupItems(pickup: Boolean) {
        TODO("Not yet implemented")
    }

    override fun getCanPickupItems(): Boolean {
        TODO("Not yet implemented")
    }

    override fun isLeashed(): Boolean {
        TODO("Not yet implemented")
    }

    override fun getLeashHolder(): Entity {
        TODO("Not yet implemented")
    }

    override fun setLeashHolder(holder: Entity?): Boolean {
        TODO("Not yet implemented")
    }

    override fun isGliding(): Boolean {
        TODO("Not yet implemented")
    }

    override fun setGliding(gliding: Boolean) {
        TODO("Not yet implemented")
    }

    override fun isSwimming(): Boolean {
        TODO("Not yet implemented")
    }

    override fun setSwimming(swimming: Boolean) {
        TODO("Not yet implemented")
    }

    override fun isRiptiding(): Boolean {
        TODO("Not yet implemented")
    }

    override fun isSleeping(): Boolean {
        TODO("Not yet implemented")
    }

    override fun isClimbing(): Boolean {
        TODO("Not yet implemented")
    }

    override fun setAI(ai: Boolean) {
        TODO("Not yet implemented")
    }

    override fun hasAI(): Boolean {
        TODO("Not yet implemented")
    }

    override fun attack(target: Entity) {
        TODO("Not yet implemented")
    }

    override fun swingMainHand() {
        TODO("Not yet implemented")
    }

    override fun swingOffHand() {
        TODO("Not yet implemented")
    }

    override fun setCollidable(collidable: Boolean) {
        TODO("Not yet implemented")
    }

    override fun isCollidable(): Boolean {
        TODO("Not yet implemented")
    }

    override fun getCollidableExemptions(): MutableSet<UUID> {
        TODO("Not yet implemented")
    }

    override fun <T : Any?> getMemory(memoryKey: MemoryKey<T>): T? {
        TODO("Not yet implemented")
    }

    override fun <T : Any?> setMemory(memoryKey: MemoryKey<T>, memoryValue: T?) {
        TODO("Not yet implemented")
    }

    override fun getCategory(): EntityCategory {
        return EntityCategory.NONE
    }

    override fun setInvisible(invisible: Boolean) {
        TODO("Not yet implemented")
    }

    override fun isInvisible(): Boolean {
        TODO("Not yet implemented")
    }

    override fun getArrowsStuck(): Int {
        TODO("Not yet implemented")
    }

    override fun setArrowsStuck(arrows: Int) {
        TODO("Not yet implemented")
    }

    override fun getShieldBlockingDelay(): Int {
        TODO("Not yet implemented")
    }

    override fun setShieldBlockingDelay(delay: Int) {
        TODO("Not yet implemented")
    }

    override fun getActiveItem(): ItemStack? {
        TODO("Not yet implemented")
    }

    override fun clearActiveItem() {
        TODO("Not yet implemented")
    }

    override fun getItemUseRemainingTime(): Int {
        TODO("Not yet implemented")
    }

    override fun getHandRaisedTime(): Int {
        TODO("Not yet implemented")
    }

    override fun isHandRaised(): Boolean {
        TODO("Not yet implemented")
    }

    override fun getHandRaised(): EquipmentSlot {
        TODO("Not yet implemented")
    }

    override fun isJumping(): Boolean {
        TODO("Not yet implemented")
    }

    override fun setJumping(jumping: Boolean) {
        TODO("Not yet implemented")
    }

    override fun playPickupItemAnimation(item: Item, quantity: Int) {
        TODO("Not yet implemented")
    }

    override fun getHurtDirection(): Float {
        TODO("Not yet implemented")
    }

    override fun setHurtDirection(hurtDirection: Float) {
        TODO("Not yet implemented")
    }

    override fun getInventory(): PlayerInventory {
        return this.inventory
    }

    override fun getEnderChest(): Inventory {
        TODO("Not yet implemented")
    }

    override fun getMainHand(): MainHand {
        TODO("Not yet implemented")
    }

    override fun setWindowProperty(prop: InventoryView.Property, value: Int): Boolean {
        TODO("Not yet implemented")
    }

    override fun getOpenInventory(): InventoryView {
        TODO("Not yet implemented")
    }

    override fun openInventory(inventory: Inventory): InventoryView? {
        TODO("Not yet implemented")
    }

    override fun openInventory(inventory: InventoryView) {
        TODO("Not yet implemented")
    }

    override fun openWorkbench(location: Location?, force: Boolean): InventoryView? {
        TODO("Not yet implemented")
    }

    override fun openEnchanting(location: Location?, force: Boolean): InventoryView? {
        TODO("Not yet implemented")
    }

    override fun openMerchant(trader: Villager, force: Boolean): InventoryView? {
        TODO("Not yet implemented")
    }

    override fun openMerchant(merchant: Merchant, force: Boolean): InventoryView? {
        TODO("Not yet implemented")
    }

    override fun openAnvil(location: Location?, force: Boolean): InventoryView? {
        TODO("Not yet implemented")
    }

    override fun openCartographyTable(location: Location?, force: Boolean): InventoryView? {
        TODO("Not yet implemented")
    }

    override fun openGrindstone(location: Location?, force: Boolean): InventoryView? {
        TODO("Not yet implemented")
    }

    override fun openLoom(location: Location?, force: Boolean): InventoryView? {
        TODO("Not yet implemented")
    }

    override fun openSmithingTable(location: Location?, force: Boolean): InventoryView? {
        TODO("Not yet implemented")
    }

    override fun openStonecutter(location: Location?, force: Boolean): InventoryView? {
        TODO("Not yet implemented")
    }

    override fun closeInventory() {
        TODO("Not yet implemented")
    }

    override fun closeInventory(reason: InventoryCloseEvent.Reason) {
        TODO("Not yet implemented")
    }

    override fun getItemInHand(): ItemStack {
        TODO("Not yet implemented")
    }

    override fun setItemInHand(item: ItemStack?) {
        TODO("Not yet implemented")
    }

    override fun getItemOnCursor(): ItemStack {
        return this.cursor
    }

    override fun setItemOnCursor(item: ItemStack?) {
        if (item != null) {
            this.cursor = item
        } else {
            this.cursor = ItemStack(Material.AIR)
        }
    }

    override fun hasCooldown(material: Material): Boolean {
        TODO("Not yet implemented")
    }

    override fun getCooldown(material: Material): Int {
        TODO("Not yet implemented")
    }

    override fun setCooldown(material: Material, ticks: Int) {
        TODO("Not yet implemented")
    }

    override fun isDeeplySleeping(): Boolean {
        TODO("Not yet implemented")
    }

    override fun getSleepTicks(): Int {
        TODO("Not yet implemented")
    }

    override fun getPotentialBedLocation(): Location? {
        TODO("Not yet implemented")
    }

    override fun sleep(location: Location, force: Boolean): Boolean {
        TODO("Not yet implemented")
    }

    override fun wakeup(setSpawnLocation: Boolean) {
        TODO("Not yet implemented")
    }

    override fun getBedLocation(): Location {
        TODO("Not yet implemented")
    }

    override fun getGameMode(): GameMode {
        TODO("Not yet implemented")
    }

    override fun setGameMode(mode: GameMode) {
        TODO("Not yet implemented")
    }

    override fun isBlocking(): Boolean {
        TODO("Not yet implemented")
    }

    override fun getItemInUse(): ItemStack? {
        TODO("Not yet implemented")
    }

    override fun getExpToLevel(): Int {
        TODO("Not yet implemented")
    }

    override fun releaseLeftShoulderEntity(): Entity? {
        TODO("Not yet implemented")
    }

    override fun releaseRightShoulderEntity(): Entity? {
        TODO("Not yet implemented")
    }

    override fun getAttackCooldown(): Float {
        TODO("Not yet implemented")
    }

    override fun discoverRecipe(recipe: NamespacedKey): Boolean {
        TODO("Not yet implemented")
    }

    override fun discoverRecipes(recipes: MutableCollection<NamespacedKey>): Int {
        TODO("Not yet implemented")
    }

    override fun undiscoverRecipe(recipe: NamespacedKey): Boolean {
        TODO("Not yet implemented")
    }

    override fun undiscoverRecipes(recipes: MutableCollection<NamespacedKey>): Int {
        TODO("Not yet implemented")
    }

    override fun hasDiscoveredRecipe(recipe: NamespacedKey): Boolean {
        TODO("Not yet implemented")
    }

    override fun getDiscoveredRecipes(): MutableSet<NamespacedKey> {
        TODO("Not yet implemented")
    }

    override fun getShoulderEntityLeft(): Entity? {
        TODO("Not yet implemented")
    }

    override fun setShoulderEntityLeft(entity: Entity?) {
        TODO("Not yet implemented")
    }

    override fun getShoulderEntityRight(): Entity? {
        TODO("Not yet implemented")
    }

    override fun setShoulderEntityRight(entity: Entity?) {
        TODO("Not yet implemented")
    }

    override fun openSign(sign: Sign) {
        TODO("Not yet implemented")
    }

    override fun dropItem(dropAll: Boolean): Boolean {
        TODO("Not yet implemented")
    }

    override fun getExhaustion(): Float {
        TODO("Not yet implemented")
    }

    override fun setExhaustion(value: Float) {
        TODO("Not yet implemented")
    }

    override fun getSaturation(): Float {
        TODO("Not yet implemented")
    }

    override fun setSaturation(value: Float) {
        TODO("Not yet implemented")
    }

    override fun getFoodLevel(): Int {
        TODO("Not yet implemented")
    }

    override fun setFoodLevel(value: Int) {
        TODO("Not yet implemented")
    }

    override fun getSaturatedRegenRate(): Int {
        TODO("Not yet implemented")
    }

    override fun setSaturatedRegenRate(ticks: Int) {
        TODO("Not yet implemented")
    }

    override fun getUnsaturatedRegenRate(): Int {
        TODO("Not yet implemented")
    }

    override fun setUnsaturatedRegenRate(ticks: Int) {
        TODO("Not yet implemented")
    }

    override fun getStarvationRate(): Int {
        TODO("Not yet implemented")
    }

    override fun setStarvationRate(ticks: Int) {
        TODO("Not yet implemented")
    }

}
