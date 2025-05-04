@file:Suppress("removal")

package de.gianttree.mc.chestsorter.commands.internal

import com.destroystokyo.paper.ClientOption
import com.destroystokyo.paper.Title
import com.destroystokyo.paper.block.TargetBlockInfo
import com.destroystokyo.paper.entity.TargetEntityInfo
import com.destroystokyo.paper.profile.PlayerProfile
import io.papermc.paper.datacomponent.DataComponentType
import io.papermc.paper.entity.LookAnchor
import io.papermc.paper.entity.PlayerGiveResult
import io.papermc.paper.entity.TeleportFlag
import io.papermc.paper.math.Position
import io.papermc.paper.threadedregions.scheduler.EntityScheduler
import io.papermc.paper.world.damagesource.CombatTracker
import net.kyori.adventure.bossbar.BossBar
import net.kyori.adventure.key.Key
import net.kyori.adventure.text.Component
import net.kyori.adventure.util.TriState
import net.md_5.bungee.api.chat.BaseComponent
import org.bukkit.*
import org.bukkit.advancement.Advancement
import org.bukkit.advancement.AdvancementProgress
import org.bukkit.attribute.Attribute
import org.bukkit.attribute.AttributeInstance
import org.bukkit.block.*
import org.bukkit.block.data.BlockData
import org.bukkit.block.sign.Side
import org.bukkit.command.CommandSender
import org.bukkit.conversations.Conversation
import org.bukkit.conversations.ConversationAbandonedEvent
import org.bukkit.damage.DamageSource
import org.bukkit.entity.*
import org.bukkit.entity.memory.MemoryKey
import org.bukkit.event.entity.CreatureSpawnEvent
import org.bukkit.event.entity.EntityDamageEvent
import org.bukkit.event.entity.EntityRegainHealthEvent
import org.bukkit.event.inventory.InventoryCloseEvent
import org.bukkit.event.inventory.InventoryType
import org.bukkit.event.player.PlayerKickEvent
import org.bukkit.event.player.PlayerResourcePackStatusEvent
import org.bukkit.event.player.PlayerTeleportEvent
import org.bukkit.inventory.*
import org.bukkit.map.MapView
import org.bukkit.metadata.MetadataValue
import org.bukkit.permissions.Permission
import org.bukkit.permissions.PermissionAttachment
import org.bukkit.permissions.PermissionAttachmentInfo
import org.bukkit.persistence.PersistentDataContainer
import org.bukkit.plugin.Plugin
import org.bukkit.potion.PotionEffect
import org.bukkit.potion.PotionEffectType
import org.bukkit.scoreboard.Scoreboard
import org.bukkit.util.BoundingBox
import org.bukkit.util.RayTraceResult
import org.bukkit.util.Vector
import java.net.InetAddress
import java.net.InetSocketAddress
import java.time.Duration
import java.time.Instant
import java.util.*
import java.util.concurrent.CompletableFuture
import java.util.function.Consumer

@Suppress("UnstableApiUsage", "DEPRECATION")
class SortingPlayer(
    private val sender: CommandSender,
    private val location: Location
) : Player {

    internal var cursor: ItemStack = ItemStack(Material.AIR)
    internal var inventory: PlayerInventory = SortingPlayerInventory(Bukkit.createInventory(null, InventoryType.PLAYER))

    @JvmField
    val uniqueId: UUID = if (sender is Entity) {
        sender.uniqueId
    } else {
        UUID.randomUUID()
    }

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

    @Deprecated("Deprecated in Java")
    override fun sendMessage(sender: UUID?, message: String) {
        this.sender.sendMessage(sender, message)
    }

    @Deprecated("Deprecated in Java")
    override fun sendMessage(sender: UUID?, vararg messages: String) {
        this.sender.sendMessage(sender, *messages)
    }

    @Deprecated("Deprecated in Java")
    override fun sendActionBar(message: String) {
        TODO("Not yet implemented")
    }

    @Deprecated("Deprecated in Java")
    override fun sendActionBar(alternateChar: Char, message: String) {
        TODO("Not yet implemented")
    }

    @Deprecated("Deprecated in Java")
    override fun sendActionBar(vararg message: BaseComponent) {
        TODO("Not yet implemented")
    }

    @Deprecated("Deprecated in Java")
    override fun showTitle(title: Array<out BaseComponent?>) {
        TODO("Not yet implemented")
    }

    @Deprecated("Deprecated in Java")
    override fun showTitle(title: BaseComponent?) {
        TODO("Not yet implemented")
    }

    @Deprecated("Deprecated in Java")
    override fun showTitle(
        title: Array<out BaseComponent?>,
        subtitle: Array<out BaseComponent?>,
        fadeInTicks: Int,
        stayTicks: Int,
        fadeOutTicks: Int
    ) {
        TODO("Not yet implemented")
    }

    @Deprecated("Deprecated in Java")
    override fun showTitle(
        title: BaseComponent?,
        subtitle: BaseComponent?,
        fadeInTicks: Int,
        stayTicks: Int,
        fadeOutTicks: Int
    ) {
        TODO("Not yet implemented")
    }

    override fun resetTitle() {
        TODO("Not yet implemented")
    }

    override fun playSound(location: Location, sound: Sound, volume: Float, pitch: Float) {
        TODO("Not yet implemented")
    }

    override fun playSound(location: Location, sound: String, volume: Float, pitch: Float) {
        TODO("Not yet implemented")
    }

    override fun playSound(location: Location, sound: Sound, category: SoundCategory, volume: Float, pitch: Float) {
        TODO("Not yet implemented")
    }

    override fun playSound(location: Location, sound: String, category: SoundCategory, volume: Float, pitch: Float) {
        TODO("Not yet implemented")
    }

    override fun playSound(
        location: Location,
        sound: Sound,
        category: SoundCategory,
        volume: Float,
        pitch: Float,
        seed: Long
    ) {
        TODO("Not yet implemented")
    }

    override fun playSound(
        location: Location,
        sound: String,
        category: SoundCategory,
        volume: Float,
        pitch: Float,
        seed: Long
    ) {
        TODO("Not yet implemented")
    }

    override fun playSound(entity: Entity, sound: Sound, volume: Float, pitch: Float) {
        TODO("Not yet implemented")
    }

    override fun playSound(entity: Entity, sound: String, volume: Float, pitch: Float) {
        TODO("Not yet implemented")
    }

    override fun playSound(entity: Entity, sound: Sound, category: SoundCategory, volume: Float, pitch: Float) {
        TODO("Not yet implemented")
    }

    override fun playSound(entity: Entity, sound: String, category: SoundCategory, volume: Float, pitch: Float) {
        TODO("Not yet implemented")
    }

    override fun playSound(
        entity: Entity,
        sound: Sound,
        category: SoundCategory,
        volume: Float,
        pitch: Float,
        seed: Long
    ) {
        TODO("Not yet implemented")
    }

    override fun playSound(
        entity: Entity,
        sound: String,
        category: SoundCategory,
        volume: Float,
        pitch: Float,
        seed: Long
    ) {
        TODO("Not yet implemented")
    }

    override fun stopSound(sound: Sound) {
        TODO("Not yet implemented")
    }

    override fun stopSound(sound: String) {
        TODO("Not yet implemented")
    }

    override fun stopSound(sound: Sound, category: SoundCategory?) {
        TODO("Not yet implemented")
    }

    override fun stopSound(sound: String, category: SoundCategory?) {
        TODO("Not yet implemented")
    }

    override fun stopSound(category: SoundCategory) {
        TODO("Not yet implemented")
    }

    override fun openBook(book: ItemStack) {
        TODO("Not yet implemented")
    }

    override fun removeResourcePacks() {
        TODO("Not yet implemented")
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

    override fun spigot(): Player.Spigot {
        TODO("Not yet implemented")
    }

    override fun sendEntityEffect(effect: EntityEffect, target: Entity) {
        TODO("Not yet implemented")
    }

    override fun give(
        items: Collection<ItemStack>,
        dropIfFull: Boolean,
    ): PlayerGiveResult {
        TODO("Not yet implemented")
    }

    override fun getDeathScreenScore(): Int {
        TODO("Not yet implemented")
    }

    override fun setDeathScreenScore(score: Int) {
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

    @Deprecated("Deprecated in Java")
    override fun getCustomName(): String? {
        TODO("Not yet implemented")
    }

    @Deprecated("Deprecated in Java")
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

    @Deprecated("Deprecated in Java")
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

    override fun teleport(
        location: Location,
        cause: PlayerTeleportEvent.TeleportCause,
        vararg teleportFlags: TeleportFlag
    ): Boolean {
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

    override fun teleportAsync(
        loc: Location,
        cause: PlayerTeleportEvent.TeleportCause,
        vararg teleportFlags: TeleportFlag
    ): CompletableFuture<Boolean> {
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

    override fun isFreezeTickingLocked(): Boolean {
        TODO("Not yet implemented")
    }

    override fun lockFreezeTicks(locked: Boolean) {
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

    @Deprecated("Deprecated in Java")
    override fun getPassenger(): Entity? {
        TODO("Not yet implemented")
    }

    @Deprecated("Deprecated in Java")
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

    @Deprecated("Deprecated in Java")
    override fun setLastDamageCause(event: EntityDamageEvent?) {
        TODO("Not yet implemented")
    }

    override fun getLastDamageCause(): EntityDamageEvent? {
        TODO("Not yet implemented")
    }

    override fun getUniqueId(): UUID {
        return this.uniqueId
    }

    override fun getTicksLived(): Int {
        TODO("Not yet implemented")
    }

    override fun setTicksLived(value: Int) {
        TODO("Not yet implemented")
    }

    @Deprecated("Deprecated in Java")
    override fun playEffect(loc: Location, effect: Effect, data: Int) {
        TODO("Not yet implemented")
    }

    override fun <T : Any?> playEffect(loc: Location, effect: Effect, data: T?) {
        TODO("Not yet implemented")
    }

    override fun playEffect(type: EntityEffect) {
        TODO("Not yet implemented")
    }

    override fun getType(): EntityType {
        return EntityType.UNKNOWN
    }

    override fun getSwimSound(): Sound {
        TODO("Not yet implemented")
    }

    override fun getSwimSplashSound(): Sound {
        TODO("Not yet implemented")
    }

    override fun getSwimHighSpeedSplashSound(): Sound {
        TODO("Not yet implemented")
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

    override fun setVisibleByDefault(visible: Boolean) {
        TODO("Not yet implemented")
    }

    override fun isVisibleByDefault(): Boolean {
        TODO("Not yet implemented")
    }

    override fun getTrackedBy(): MutableSet<Player> {
        TODO("Not yet implemented")
    }

    override fun isTrackedBy(player: Player): Boolean {
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

    override fun getSpawnCategory(): SpawnCategory {
        TODO("Not yet implemented")
    }

    override fun isInWorld(): Boolean {
        TODO("Not yet implemented")
    }

    override fun getAsString(): String? {
        TODO("Not yet implemented")
    }

    override fun createSnapshot(): EntitySnapshot? {
        TODO("Not yet implemented")
    }

    override fun copy(): Entity {
        TODO("Not yet implemented")
    }

    override fun copy(to: Location): Entity {
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

    override fun isUnderWater(): Boolean {
        TODO("Not yet implemented")
    }

    override fun isInRain(): Boolean {
        TODO("Not yet implemented")
    }

    @Deprecated("Deprecated in Java")
    override fun isInBubbleColumn(): Boolean {
        TODO("Not yet implemented")
    }

    @Deprecated("Deprecated in Java")
    override fun isInWaterOrRain(): Boolean {
        TODO("Not yet implemented")
    }

    @Deprecated("Deprecated in Java")
    override fun isInWaterOrBubbleColumn(): Boolean {
        TODO("Not yet implemented")
    }

    @Deprecated("Deprecated in Java")
    override fun isInWaterOrRainOrBubbleColumn(): Boolean {
        TODO("Not yet implemented")
    }

    override fun isInLava(): Boolean {
        TODO("Not yet implemented")
    }

    override fun isTicking(): Boolean {
        TODO("Not yet implemented")
    }

    @Deprecated("Deprecated in Java")
    override fun getTrackedPlayers(): MutableSet<Player> {
        TODO("Not yet implemented")
    }

    override fun spawnAt(location: Location, reason: CreatureSpawnEvent.SpawnReason): Boolean {
        TODO("Not yet implemented")
    }

    override fun isInPowderedSnow(): Boolean {
        TODO("Not yet implemented")
    }

    override fun getX(): Double {
        TODO("Not yet implemented")
    }

    override fun getY(): Double {
        TODO("Not yet implemented")
    }

    override fun getZ(): Double {
        TODO("Not yet implemented")
    }

    override fun getPitch(): Float {
        TODO("Not yet implemented")
    }

    override fun getYaw(): Float {
        TODO("Not yet implemented")
    }

    override fun collidesAt(location: Location): Boolean {
        TODO("Not yet implemented")
    }

    override fun wouldCollideUsing(boundingBox: BoundingBox): Boolean {
        TODO("Not yet implemented")
    }

    override fun getScheduler(): EntityScheduler {
        TODO("Not yet implemented")
    }

    override fun getScoreboardEntryName(): String {
        TODO("Not yet implemented")
    }

    override fun broadcastHurtAnimation(players: Collection<Player?>) {
        TODO("Not yet implemented")
    }

    override fun damage(amount: Double) {
        TODO("Not yet implemented")
    }

    override fun damage(amount: Double, source: Entity?) {
        TODO("Not yet implemented")
    }

    override fun damage(amount: Double, damageSource: DamageSource) {
        TODO("Not yet implemented")
    }

    override fun getHealth(): Double {
        TODO("Not yet implemented")
    }

    override fun setHealth(health: Double) {
        TODO("Not yet implemented")
    }

    override fun heal(amount: Double, reason: EntityRegainHealthEvent.RegainReason) {
        TODO("Not yet implemented")
    }

    override fun getAbsorptionAmount(): Double {
        TODO("Not yet implemented")
    }

    override fun setAbsorptionAmount(amount: Double) {
        TODO("Not yet implemented")
    }

    @Deprecated("Deprecated in Java")
    override fun getMaxHealth(): Double {
        TODO("Not yet implemented")
    }

    @Deprecated("Deprecated in Java")
    override fun setMaxHealth(health: Double) {
        TODO("Not yet implemented")
    }

    @Deprecated("Deprecated in Java")
    override fun resetMaxHealth() {
        TODO("Not yet implemented")
    }

    override fun <T : Projectile> launchProjectile(projectile: Class<out T>): T {
        TODO("Not yet implemented")
    }

    override fun <T : Projectile> launchProjectile(projectile: Class<out T>, velocity: Vector?): T {
        TODO("Not yet implemented")
    }

    override fun <T : Projectile> launchProjectile(
        projectile: Class<out T>,
        velocity: Vector?,
        function: Consumer<in T>?
    ): T {
        TODO("Not yet implemented")
    }

    override fun getFrictionState(): TriState {
        TODO("Not yet implemented")
    }

    override fun setFrictionState(state: TriState) {
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

    @Deprecated("Deprecated in Java")
    override fun getTargetBlock(maxDistance: Int, fluidMode: TargetBlockInfo.FluidMode): Block? {
        TODO("Not yet implemented")
    }

    @Deprecated("Deprecated in Java")
    override fun getTargetBlockFace(maxDistance: Int, fluidMode: TargetBlockInfo.FluidMode): BlockFace? {
        TODO("Not yet implemented")
    }

    override fun getTargetBlockFace(maxDistance: Int, fluidMode: FluidCollisionMode): BlockFace? {
        TODO("Not yet implemented")
    }

    @Deprecated("Deprecated in Java")
    override fun getTargetBlockInfo(maxDistance: Int, fluidMode: TargetBlockInfo.FluidMode): TargetBlockInfo? {
        TODO("Not yet implemented")
    }

    override fun getTargetEntity(maxDistance: Int, ignoreBlocks: Boolean): Entity? {
        TODO("Not yet implemented")
    }

    @Deprecated("Deprecated in Java")
    override fun getTargetEntityInfo(maxDistance: Int, ignoreBlocks: Boolean): TargetEntityInfo? {
        TODO("Not yet implemented")
    }

    override fun rayTraceEntities(maxDistance: Int, ignoreBlocks: Boolean): RayTraceResult? {
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

    override fun setArrowsInBody(count: Int, fireEvent: Boolean) {
        TODO("Not yet implemented")
    }

    override fun setNextArrowRemoval(ticks: Int) {
        TODO("Not yet implemented")
    }

    override fun getNextArrowRemoval(): Int {
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

    override fun setNextBeeStingerRemoval(ticks: Int) {
        TODO("Not yet implemented")
    }

    override fun getNextBeeStingerRemoval(): Int {
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

    override fun getNoActionTicks(): Int {
        TODO("Not yet implemented")
    }

    override fun setNoActionTicks(ticks: Int) {
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

    @Deprecated("Deprecated in Java")
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

    override fun clearActivePotionEffects(): Boolean {
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

    @Deprecated("Deprecated in Java")
    override fun setSwimming(swimming: Boolean) {
        TODO("Not yet implemented")
    }

    override fun isRiptiding(): Boolean {
        TODO("Not yet implemented")
    }

    override fun setRiptiding(riptiding: Boolean) {
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

    override fun playHurtAnimation(yaw: Float) {
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

    override fun getHurtSound(): Sound? {
        TODO("Not yet implemented")
    }

    override fun getDeathSound(): Sound? {
        TODO("Not yet implemented")
    }

    override fun getFallDamageSound(fallHeight: Int): Sound {
        TODO("Not yet implemented")
    }

    override fun getFallDamageSoundSmall(): Sound {
        TODO("Not yet implemented")
    }

    override fun getFallDamageSoundBig(): Sound {
        TODO("Not yet implemented")
    }

    override fun getDrinkingSound(itemStack: ItemStack): Sound {
        TODO("Not yet implemented")
    }

    override fun getEatingSound(itemStack: ItemStack): Sound {
        TODO("Not yet implemented")
    }

    override fun canBreatheUnderwater(): Boolean {
        TODO("Not yet implemented")
    }

    @Deprecated("Deprecated in Java")
    override fun getCategory(): EntityCategory {
        return EntityCategory.NONE
    }

    override fun setInvisible(invisible: Boolean) {
        TODO("Not yet implemented")
    }

    override fun isInvisible(): Boolean {
        TODO("Not yet implemented")
    }

    override fun setNoPhysics(noPhysics: Boolean) {
        TODO("Not yet implemented")
    }

    override fun hasNoPhysics(): Boolean {
        TODO("Not yet implemented")
    }

    @Deprecated("Deprecated in Java")
    override fun getArrowsStuck(): Int {
        TODO("Not yet implemented")
    }

    @Deprecated("Deprecated in Java")
    override fun setArrowsStuck(arrows: Int) {
        TODO("Not yet implemented")
    }

    override fun getShieldBlockingDelay(): Int {
        TODO("Not yet implemented")
    }

    override fun setShieldBlockingDelay(delay: Int) {
        TODO("Not yet implemented")
    }

    override fun getSidewaysMovement(): Float {
        TODO("Not yet implemented")
    }

    override fun getUpwardsMovement(): Float {
        TODO("Not yet implemented")
    }

    override fun getForwardsMovement(): Float {
        TODO("Not yet implemented")
    }

    override fun startUsingItem(hand: EquipmentSlot) {
        TODO("Not yet implemented")
    }

    override fun completeUsingActiveItem() {
        TODO("Not yet implemented")
    }

    override fun getActiveItem(): ItemStack {
        TODO("Not yet implemented")
    }

    override fun clearActiveItem() {
        TODO("Not yet implemented")
    }

    override fun getActiveItemRemainingTime(): Int {
        TODO("Not yet implemented")
    }

    override fun setActiveItemRemainingTime(ticks: Int) {
        TODO("Not yet implemented")
    }

    override fun hasActiveItem(): Boolean {
        TODO("Not yet implemented")
    }

    override fun getActiveItemUsedTime(): Int {
        TODO("Not yet implemented")
    }

    override fun getActiveItemHand(): EquipmentSlot {
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

    override fun knockback(strength: Double, directionX: Double, directionZ: Double) {
        TODO("Not yet implemented")
    }

    override fun broadcastSlotBreak(slot: EquipmentSlot) {
        TODO("Not yet implemented")
    }

    override fun broadcastSlotBreak(slot: EquipmentSlot, players: MutableCollection<Player>) {
        TODO("Not yet implemented")
    }

    override fun damageItemStack(stack: ItemStack, amount: Int): ItemStack {
        TODO("Not yet implemented")
    }

    override fun damageItemStack(slot: EquipmentSlot, amount: Int) {
        TODO("Not yet implemented")
    }

    override fun getBodyYaw(): Float {
        TODO("Not yet implemented")
    }

    override fun setBodyYaw(bodyYaw: Float) {
        TODO("Not yet implemented")
    }

    override fun canUseEquipmentSlot(slot: EquipmentSlot): Boolean {
        TODO("Not yet implemented")
    }

    override fun getCombatTracker(): CombatTracker {
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

    @Deprecated("Deprecated in Java")
    override fun setWindowProperty(prop: InventoryView.Property, value: Int): Boolean {
        TODO("Not yet implemented")
    }

    override fun getEnchantmentSeed(): Int {
        TODO("Not yet implemented")
    }

    override fun setEnchantmentSeed(seed: Int) {
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

    @Deprecated("Deprecated in Java")
    override fun openWorkbench(location: Location?, force: Boolean): InventoryView? {
        TODO("Not yet implemented")
    }

    @Deprecated("Deprecated in Java")
    override fun openEnchanting(location: Location?, force: Boolean): InventoryView? {
        TODO("Not yet implemented")
    }

    @Deprecated("Deprecated in Java")
    override fun openMerchant(trader: Villager, force: Boolean): InventoryView? {
        TODO("Not yet implemented")
    }

    @Deprecated("Deprecated in Java")
    override fun openMerchant(merchant: Merchant, force: Boolean): InventoryView? {
        TODO("Not yet implemented")
    }

    @Deprecated("Deprecated in Java")
    override fun openAnvil(location: Location?, force: Boolean): InventoryView? {
        TODO("Not yet implemented")
    }

    @Deprecated("Deprecated in Java")
    override fun openCartographyTable(location: Location?, force: Boolean): InventoryView? {
        TODO("Not yet implemented")
    }

    @Deprecated("Deprecated in Java")
    override fun openGrindstone(location: Location?, force: Boolean): InventoryView? {
        TODO("Not yet implemented")
    }

    @Deprecated("Deprecated in Java")
    override fun openLoom(location: Location?, force: Boolean): InventoryView? {
        TODO("Not yet implemented")
    }

    @Deprecated("Deprecated in Java")
    override fun openSmithingTable(location: Location?, force: Boolean): InventoryView? {
        TODO("Not yet implemented")
    }

    @Deprecated("Deprecated in Java")
    override fun openStonecutter(location: Location?, force: Boolean): InventoryView? {
        TODO("Not yet implemented")
    }

    override fun closeInventory() {
        TODO("Not yet implemented")
    }

    override fun closeInventory(reason: InventoryCloseEvent.Reason) {
        TODO("Not yet implemented")
    }

    @Deprecated("Deprecated in Java")
    override fun getItemInHand(): ItemStack {
        TODO("Not yet implemented")
    }

    @Deprecated("Deprecated in Java")
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

    override fun hasCooldown(item: ItemStack): Boolean {
        TODO("Not yet implemented")
    }

    override fun getCooldown(item: ItemStack): Int {
        TODO("Not yet implemented")
    }

    override fun setCooldown(item: ItemStack, ticks: Int) {
        TODO("Not yet implemented")
    }

    override fun getCooldown(key: Key): Int {
        TODO("Not yet implemented")
    }

    override fun setCooldown(key: Key, ticks: Int) {
        TODO("Not yet implemented")
    }

    override fun getSleepTicks(): Int {
        TODO("Not yet implemented")
    }

    @Deprecated("Deprecated in Java")
    override fun getPotentialBedLocation(): Location? {
        TODO("Not yet implemented")
    }

    @Deprecated("Deprecated in Java")
    override fun getPotentialRespawnLocation(): Location? {
        TODO("Not yet implemented")
    }

    override fun getFishHook(): FishHook? {
        TODO("Not yet implemented")
    }

    override fun sleep(location: Location, force: Boolean): Boolean {
        TODO("Not yet implemented")
    }

    override fun wakeup(setSpawnLocation: Boolean) {
        TODO("Not yet implemented")
    }

    override fun startRiptideAttack(
        duration: Int,
        attackStrength: Float,
        attackItem: ItemStack?,
    ) {
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

    @Deprecated("Deprecated in Java")
    override fun getItemInUse(): ItemStack? {
        TODO("Not yet implemented")
    }

    @Deprecated("Deprecated in Java")
    override fun getItemInUseTicks(): Int {
        TODO("Not yet implemented")
    }

    @Deprecated("Deprecated in Java")
    override fun setItemInUseTicks(ticks: Int) {
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

    @Deprecated("Deprecated in Java")
    override fun getShoulderEntityLeft(): Entity? {
        TODO("Not yet implemented")
    }

    @Deprecated("Deprecated in Java")
    override fun setShoulderEntityLeft(entity: Entity?) {
        TODO("Not yet implemented")
    }

    @Deprecated("Deprecated in Java")
    override fun getShoulderEntityRight(): Entity? {
        TODO("Not yet implemented")
    }

    @Deprecated("Deprecated in Java")
    override fun setShoulderEntityRight(entity: Entity?) {
        TODO("Not yet implemented")
    }

    @Deprecated("Deprecated in Java")
    override fun openSign(sign: Sign) {
        TODO("Not yet implemented")
    }

    override fun openSign(sign: Sign, side: Side) {
        TODO("Not yet implemented")
    }

    override fun dropItem(dropAll: Boolean): Boolean {
        TODO("Not yet implemented")
    }

    override fun dropItem(
        slot: Int,
        amount: Int,
        throwRandomly: Boolean,
        entityOperation: Consumer<Item>?,
    ): Item? {
        TODO("Not yet implemented")
    }

    override fun dropItem(
        slot: EquipmentSlot,
        amount: Int,
        throwRandomly: Boolean,
        entityOperation: Consumer<Item>?,
    ): Item? {
        TODO("Not yet implemented")
    }

    override fun dropItem(
        itemStack: ItemStack,
        throwRandomly: Boolean,
        entityOperation: Consumer<Item>?,
    ): Item? {
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

    override fun getLastDeathLocation(): Location? {
        TODO("Not yet implemented")
    }

    override fun setLastDeathLocation(location: Location?) {
        TODO("Not yet implemented")
    }

    override fun fireworkBoost(fireworkItemStack: ItemStack): Firework? {
        TODO("Not yet implemented")
    }

    override fun isConversing(): Boolean {
        TODO("Not yet implemented")
    }

    override fun acceptConversationInput(input: String) {
        TODO("Not yet implemented")
    }

    override fun beginConversation(conversation: Conversation): Boolean {
        TODO("Not yet implemented")
    }

    override fun abandonConversation(conversation: Conversation) {
        TODO("Not yet implemented")
    }

    override fun abandonConversation(conversation: Conversation, details: ConversationAbandonedEvent) {
        TODO("Not yet implemented")
    }

    override fun sendRawMessage(message: String) {
        TODO("Not yet implemented")
    }

    @Deprecated("Deprecated in Java")
    override fun sendRawMessage(sender: UUID?, message: String) {
        TODO("Not yet implemented")
    }

    override fun serialize(): MutableMap<String, Any> {
        TODO("Not yet implemented")
    }

    override fun isOnline(): Boolean {
        TODO("Not yet implemented")
    }

    override fun isConnected(): Boolean {
        TODO("Not yet implemented")
    }

    override fun isBanned(): Boolean {
        TODO("Not yet implemented")
    }

    override fun <E : BanEntry<in PlayerProfile>?> ban(
        reason: String?,
        expires: Date?,
        source: String?,
        kickPlayer: Boolean
    ): E? {
        TODO("Not yet implemented")
    }

    override fun <E : BanEntry<in PlayerProfile>?> ban(
        reason: String?,
        expires: Instant?,
        source: String?,
        kickPlayer: Boolean
    ): E? {
        TODO("Not yet implemented")
    }

    override fun <E : BanEntry<in PlayerProfile>?> ban(
        reason: String?,
        duration: Duration?,
        source: String?,
        kickPlayer: Boolean
    ): E? {
        TODO("Not yet implemented")
    }

    override fun <E : BanEntry<in PlayerProfile>?> ban(reason: String?, expires: Date?, source: String?): E? {
        TODO("Not yet implemented")
    }

    override fun <E : BanEntry<in PlayerProfile>?> ban(reason: String?, expires: Instant?, source: String?): E? {
        TODO("Not yet implemented")
    }

    override fun <E : BanEntry<in PlayerProfile>?> ban(reason: String?, duration: Duration?, source: String?): E? {
        TODO("Not yet implemented")
    }

    override fun isWhitelisted(): Boolean {
        TODO("Not yet implemented")
    }

    override fun setWhitelisted(value: Boolean) {
        TODO("Not yet implemented")
    }

    override fun getPlayer(): Player? {
        TODO("Not yet implemented")
    }

    override fun getFirstPlayed(): Long {
        TODO("Not yet implemented")
    }

    @Deprecated("Deprecated in Java")
    override fun getLastPlayed(): Long {
        TODO("Not yet implemented")
    }

    override fun hasPlayedBefore(): Boolean {
        TODO("Not yet implemented")
    }

    @Deprecated("Deprecated in Java")
    override fun getBedSpawnLocation(): Location? {
        TODO("Not yet implemented")
    }

    override fun getLastLogin(): Long {
        TODO("Not yet implemented")
    }

    override fun getLastSeen(): Long {
        TODO("Not yet implemented")
    }

    override fun getRespawnLocation(): Location? {
        TODO("Not yet implemented")
    }

    override fun getRespawnLocation(loadLocationAndValidate: Boolean): Location? {
        TODO("Not yet implemented")
    }

    override fun incrementStatistic(statistic: Statistic) {
        TODO("Not yet implemented")
    }

    override fun incrementStatistic(statistic: Statistic, amount: Int) {
        TODO("Not yet implemented")
    }

    override fun incrementStatistic(statistic: Statistic, material: Material) {
        TODO("Not yet implemented")
    }

    override fun incrementStatistic(statistic: Statistic, material: Material, amount: Int) {
        TODO("Not yet implemented")
    }

    override fun incrementStatistic(statistic: Statistic, entityType: EntityType) {
        TODO("Not yet implemented")
    }

    override fun incrementStatistic(statistic: Statistic, entityType: EntityType, amount: Int) {
        TODO("Not yet implemented")
    }

    override fun decrementStatistic(statistic: Statistic) {
        TODO("Not yet implemented")
    }

    override fun decrementStatistic(statistic: Statistic, amount: Int) {
        TODO("Not yet implemented")
    }

    override fun decrementStatistic(statistic: Statistic, material: Material) {
        TODO("Not yet implemented")
    }

    override fun decrementStatistic(statistic: Statistic, material: Material, amount: Int) {
        TODO("Not yet implemented")
    }

    override fun decrementStatistic(statistic: Statistic, entityType: EntityType) {
        TODO("Not yet implemented")
    }

    override fun decrementStatistic(statistic: Statistic, entityType: EntityType, amount: Int) {
        TODO("Not yet implemented")
    }

    override fun setStatistic(statistic: Statistic, newValue: Int) {
        TODO("Not yet implemented")
    }

    override fun setStatistic(statistic: Statistic, material: Material, newValue: Int) {
        TODO("Not yet implemented")
    }

    override fun setStatistic(statistic: Statistic, entityType: EntityType, newValue: Int) {
        TODO("Not yet implemented")
    }

    override fun getStatistic(statistic: Statistic): Int {
        TODO("Not yet implemented")
    }

    override fun getStatistic(statistic: Statistic, material: Material): Int {
        TODO("Not yet implemented")
    }

    override fun getStatistic(statistic: Statistic, entityType: EntityType): Int {
        TODO("Not yet implemented")
    }

    override fun sendPluginMessage(source: Plugin, channel: String, message: ByteArray) {
        TODO("Not yet implemented")
    }

    override fun getListeningPluginChannels(): MutableSet<String> {
        TODO("Not yet implemented")
    }

    override fun activeBossBars(): MutableIterable<BossBar> {
        TODO("Not yet implemented")
    }

    override fun getAddress(): InetSocketAddress {
        TODO("Not yet implemented")
    }

    override fun getProtocolVersion(): Int {
        TODO("Not yet implemented")
    }

    override fun getVirtualHost(): InetSocketAddress? {
        TODO("Not yet implemented")
    }

    override fun displayName(): Component {
        TODO("Not yet implemented")
    }

    override fun displayName(displayName: Component?) {
        TODO("Not yet implemented")
    }

    @Deprecated("Deprecated in Java")
    override fun getDisplayName(): String {
        TODO("Not yet implemented")
    }

    @Deprecated("Deprecated in Java")
    override fun setDisplayName(name: String?) {
        TODO("Not yet implemented")
    }

    override fun playerListName(name: Component?) {
        TODO("Not yet implemented")
    }

    override fun playerListName(): Component {
        TODO("Not yet implemented")
    }

    override fun playerListHeader(): Component? {
        TODO("Not yet implemented")
    }

    override fun playerListFooter(): Component? {
        TODO("Not yet implemented")
    }

    @Deprecated("Deprecated in Java")
    override fun getPlayerListName(): String {
        TODO("Not yet implemented")
    }

    @Deprecated("Deprecated in Java")
    override fun setPlayerListName(name: String?) {
        TODO("Not yet implemented")
    }

    override fun getPlayerListOrder(): Int {
        TODO("Not yet implemented")
    }

    override fun setPlayerListOrder(order: Int) {
        TODO("Not yet implemented")
    }

    @Deprecated("Deprecated in Java")
    override fun getPlayerListHeader(): String? {
        TODO("Not yet implemented")
    }

    @Deprecated("Deprecated in Java")
    override fun getPlayerListFooter(): String? {
        TODO("Not yet implemented")
    }

    @Deprecated("Deprecated in Java")
    override fun setPlayerListHeader(header: String?) {
        TODO("Not yet implemented")
    }

    @Deprecated("Deprecated in Java")
    override fun setPlayerListFooter(footer: String?) {
        TODO("Not yet implemented")
    }

    @Deprecated("Deprecated in Java")
    override fun setPlayerListHeaderFooter(header: String?, footer: String?) {
        TODO("Not yet implemented")
    }

    @Deprecated("Deprecated in Java")
    override fun setPlayerListHeaderFooter(header: Array<out BaseComponent>?, footer: Array<out BaseComponent>?) {
        TODO("Not yet implemented")
    }

    @Deprecated("Deprecated in Java")
    override fun setPlayerListHeaderFooter(header: BaseComponent?, footer: BaseComponent?) {
        TODO("Not yet implemented")
    }

    override fun setCompassTarget(loc: Location) {
        TODO("Not yet implemented")
    }

    override fun getCompassTarget(): Location {
        TODO("Not yet implemented")
    }

    override fun getHAProxyAddress(): InetSocketAddress? {
        TODO("Not yet implemented")
    }

    override fun isTransferred(): Boolean {
        TODO("Not yet implemented")
    }

    override fun retrieveCookie(key: NamespacedKey): CompletableFuture<ByteArray> {
        TODO("Not yet implemented")
    }

    override fun storeCookie(key: NamespacedKey, value: ByteArray) {
        TODO("Not yet implemented")
    }

    override fun transfer(host: String, port: Int) {
        TODO("Not yet implemented")
    }

    @Deprecated("Deprecated in Java")
    override fun kickPlayer(message: String?) {
        TODO("Not yet implemented")
    }

    override fun kick() {
        TODO("Not yet implemented")
    }

    override fun kick(message: Component?) {
        TODO("Not yet implemented")
    }

    override fun kick(message: Component?, cause: PlayerKickEvent.Cause) {
        TODO("Not yet implemented")
    }

    override fun banIp(reason: String?, expires: Date?, source: String?, kickPlayer: Boolean): BanEntry<InetAddress>? {
        TODO("Not yet implemented")
    }

    override fun banIp(
        reason: String?,
        expires: Instant?,
        source: String?,
        kickPlayer: Boolean
    ): BanEntry<InetAddress>? {
        TODO("Not yet implemented")
    }

    override fun banIp(
        reason: String?,
        duration: Duration?,
        source: String?,
        kickPlayer: Boolean
    ): BanEntry<InetAddress>? {
        TODO("Not yet implemented")
    }

    override fun chat(msg: String) {
        TODO("Not yet implemented")
    }

    override fun performCommand(command: String): Boolean {
        TODO("Not yet implemented")
    }

    override fun isSneaking(): Boolean {
        TODO("Not yet implemented")
    }

    override fun setSneaking(sneak: Boolean) {
        TODO("Not yet implemented")
    }

    override fun setPose(pose: Pose, fixed: Boolean) {
        TODO("Not yet implemented")
    }

    override fun hasFixedPose(): Boolean {
        TODO("Not yet implemented")
    }

    override fun isSprinting(): Boolean {
        TODO("Not yet implemented")
    }

    override fun setSprinting(sprinting: Boolean) {
        TODO("Not yet implemented")
    }

    override fun saveData() {
        TODO("Not yet implemented")
    }

    override fun loadData() {
        TODO("Not yet implemented")
    }

    override fun setSleepingIgnored(isSleeping: Boolean) {
        TODO("Not yet implemented")
    }

    override fun isSleepingIgnored(): Boolean {
        TODO("Not yet implemented")
    }

    @Deprecated("Deprecated in Java")
    override fun setBedSpawnLocation(location: Location?) {
        TODO("Not yet implemented")
    }

    @Deprecated("Deprecated in Java")
    override fun setBedSpawnLocation(location: Location?, force: Boolean) {
        TODO("Not yet implemented")
    }

    override fun setRespawnLocation(location: Location?) {
        TODO("Not yet implemented")
    }

    override fun setRespawnLocation(location: Location?, force: Boolean) {
        TODO("Not yet implemented")
    }

    override fun getEnderPearls(): Collection<EnderPearl> {
        TODO("Not yet implemented")
    }

    override fun getCurrentInput(): Input {
        TODO("Not yet implemented")
    }

    @Deprecated("Deprecated in Java")
    override fun playNote(loc: Location, instrument: Byte, note: Byte) {
        TODO("Not yet implemented")
    }

    override fun playNote(loc: Location, instrument: Instrument, note: Note) {
        TODO("Not yet implemented")
    }

    override fun stopAllSounds() {
        TODO("Not yet implemented")
    }

    override fun breakBlock(block: Block): Boolean {
        TODO("Not yet implemented")
    }

    @Deprecated("Deprecated in Java")
    override fun sendBlockChange(loc: Location, material: Material, data: Byte) {
        TODO("Not yet implemented")
    }

    override fun sendBlockChange(loc: Location, block: BlockData) {
        TODO("Not yet implemented")
    }

    override fun sendBlockChanges(blocks: MutableCollection<BlockState>) {
        TODO("Not yet implemented")
    }

    @Deprecated("Deprecated in Java")
    override fun sendBlockChanges(blocks: MutableCollection<BlockState>, suppressLightUpdates: Boolean) {
        TODO("Not yet implemented")
    }

    override fun sendBlockDamage(loc: Location, progress: Float) {
        TODO("Not yet implemented")
    }

    override fun sendBlockDamage(loc: Location, progress: Float, source: Entity) {
        TODO("Not yet implemented")
    }

    override fun sendBlockDamage(loc: Location, progress: Float, sourceId: Int) {
        TODO("Not yet implemented")
    }

    override fun sendMultiBlockChange(blockChanges: MutableMap<out Position, BlockData>) {
        TODO("Not yet implemented")
    }

    override fun sendEquipmentChange(entity: LivingEntity, slot: EquipmentSlot, item: ItemStack?) {
        TODO("Not yet implemented")
    }

    override fun sendEquipmentChange(entity: LivingEntity, items: MutableMap<EquipmentSlot, ItemStack?>) {
        TODO("Not yet implemented")
    }

    @Deprecated("Deprecated in Java")
    override fun sendSignChange(
        loc: Location,
        lines: MutableList<out Component>?,
        dyeColor: DyeColor,
        hasGlowingText: Boolean
    ) {
        TODO("Not yet implemented")
    }

    @Deprecated("Deprecated in Java")
    override fun sendSignChange(loc: Location, lines: Array<out String?>) {
        TODO("Not yet implemented")
    }

    @Deprecated("Deprecated in Java")
    override fun sendSignChange(loc: Location, lines: Array<out String?>, dyeColor: DyeColor) {
        TODO("Not yet implemented")
    }

    @Deprecated("Deprecated in Java")
    override fun sendSignChange(
        loc: Location,
        lines: Array<out String?>,
        dyeColor: DyeColor,
        hasGlowingText: Boolean
    ) {
        TODO("Not yet implemented")
    }

    override fun sendBlockUpdate(loc: Location, tileState: TileState) {
        TODO("Not yet implemented")
    }

    override fun sendPotionEffectChange(entity: LivingEntity, effect: PotionEffect) {
        TODO("Not yet implemented")
    }

    override fun sendPotionEffectChangeRemove(entity: LivingEntity, type: PotionEffectType) {
        TODO("Not yet implemented")
    }

    override fun sendMap(map: MapView) {
        TODO("Not yet implemented")
    }

    override fun showWinScreen() {
        TODO("Not yet implemented")
    }

    override fun hasSeenWinScreen(): Boolean {
        TODO("Not yet implemented")
    }

    override fun setHasSeenWinScreen(hasSeenWinScreen: Boolean) {
        TODO("Not yet implemented")
    }

    @Deprecated("Deprecated in Java")
    override fun setTitleTimes(fadeInTicks: Int, stayTicks: Int, fadeOutTicks: Int) {
        TODO("Not yet implemented")
    }

    @Deprecated("Deprecated in Java")
    override fun setSubtitle(subtitle: Array<out BaseComponent>) {
        TODO("Not yet implemented")
    }

    @Deprecated("Deprecated in Java")
    override fun setSubtitle(subtitle: BaseComponent) {
        TODO("Not yet implemented")
    }

    @Deprecated("Deprecated in Java")
    override fun sendTitle(@Suppress("DEPRECATION") title: Title) {
        TODO("Not yet implemented")
    }

    @Deprecated("Deprecated in Java")
    override fun sendTitle(title: String?, subtitle: String?) {
        TODO("Not yet implemented")
    }

    @Deprecated("Deprecated in Java")
    override fun sendTitle(title: String?, subtitle: String?, fadeIn: Int, stay: Int, fadeOut: Int) {
        TODO("Not yet implemented")
    }

    @Deprecated("Deprecated in Java")
    override fun updateTitle(@Suppress("DEPRECATION") title: Title) {
        TODO("Not yet implemented")
    }

    @Deprecated("Deprecated in Java")
    override fun hideTitle() {
        TODO("Not yet implemented")
    }

    override fun sendHurtAnimation(yaw: Float) {
        TODO("Not yet implemented")
    }

    override fun sendLinks(links: ServerLinks) {
        TODO("Not yet implemented")
    }

    override fun addCustomChatCompletions(completions: MutableCollection<String>) {
        TODO("Not yet implemented")
    }

    override fun removeCustomChatCompletions(completions: MutableCollection<String>) {
        TODO("Not yet implemented")
    }

    override fun setCustomChatCompletions(completions: MutableCollection<String>) {
        TODO("Not yet implemented")
    }

    override fun updateInventory() {
        TODO("Not yet implemented")
    }

    override fun getPreviousGameMode(): GameMode? {
        TODO("Not yet implemented")
    }

    override fun setPlayerTime(time: Long, relative: Boolean) {
        TODO("Not yet implemented")
    }

    override fun getPlayerTime(): Long {
        TODO("Not yet implemented")
    }

    override fun getPlayerTimeOffset(): Long {
        TODO("Not yet implemented")
    }

    override fun isPlayerTimeRelative(): Boolean {
        TODO("Not yet implemented")
    }

    override fun resetPlayerTime() {
        TODO("Not yet implemented")
    }

    override fun setPlayerWeather(type: WeatherType) {
        TODO("Not yet implemented")
    }

    override fun getPlayerWeather(): WeatherType? {
        TODO("Not yet implemented")
    }

    override fun resetPlayerWeather() {
        TODO("Not yet implemented")
    }

    override fun giveExp(amount: Int, applyMending: Boolean) {
        TODO("Not yet implemented")
    }

    override fun getExpCooldown(): Int {
        TODO("Not yet implemented")
    }

    override fun setExpCooldown(ticks: Int) {
        TODO("Not yet implemented")
    }

    override fun applyMending(amount: Int): Int {
        TODO("Not yet implemented")
    }

    override fun giveExpLevels(amount: Int) {
        TODO("Not yet implemented")
    }

    override fun getExp(): Float {
        TODO("Not yet implemented")
    }

    override fun setExp(exp: Float) {
        TODO("Not yet implemented")
    }

    override fun getLevel(): Int {
        TODO("Not yet implemented")
    }

    override fun setLevel(level: Int) {
        TODO("Not yet implemented")
    }

    override fun getTotalExperience(): Int {
        TODO("Not yet implemented")
    }

    override fun setTotalExperience(exp: Int) {
        TODO("Not yet implemented")
    }

    override fun calculateTotalExperiencePoints(): Int {
        TODO("Not yet implemented")
    }

    override fun setExperienceLevelAndProgress(totalExperience: Int) {
        TODO("Not yet implemented")
    }

    override fun getExperiencePointsNeededForNextLevel(): Int {
        TODO("Not yet implemented")
    }

    override fun sendExperienceChange(progress: Float) {
        TODO("Not yet implemented")
    }

    override fun sendExperienceChange(progress: Float, level: Int) {
        TODO("Not yet implemented")
    }

    override fun getAllowFlight(): Boolean {
        TODO("Not yet implemented")
    }

    override fun setAllowFlight(flight: Boolean) {
        TODO("Not yet implemented")
    }

    override fun setFlyingFallDamage(flyingFallDamage: TriState) {
        TODO("Not yet implemented")
    }

    override fun hasFlyingFallDamage(): TriState {
        TODO("Not yet implemented")
    }

    @Deprecated("Deprecated in Java")
    override fun hidePlayer(player: Player) {
        TODO("Not yet implemented")
    }

    override fun hidePlayer(plugin: Plugin, player: Player) {
        TODO("Not yet implemented")
    }

    @Deprecated("Deprecated in Java")
    override fun showPlayer(player: Player) {
        TODO("Not yet implemented")
    }

    override fun showPlayer(plugin: Plugin, player: Player) {
        TODO("Not yet implemented")
    }

    override fun canSee(player: Player): Boolean {
        TODO("Not yet implemented")
    }

    override fun canSee(entity: Entity): Boolean {
        TODO("Not yet implemented")
    }

    override fun hideEntity(plugin: Plugin, entity: Entity) {
        TODO("Not yet implemented")
    }

    override fun showEntity(plugin: Plugin, entity: Entity) {
        TODO("Not yet implemented")
    }

    override fun isListed(other: Player): Boolean {
        TODO("Not yet implemented")
    }

    override fun unlistPlayer(other: Player): Boolean {
        TODO("Not yet implemented")
    }

    override fun listPlayer(other: Player): Boolean {
        TODO("Not yet implemented")
    }

    override fun isFlying(): Boolean {
        TODO("Not yet implemented")
    }

    override fun setFlying(value: Boolean) {
        TODO("Not yet implemented")
    }

    override fun setFlySpeed(value: Float) {
        TODO("Not yet implemented")
    }

    override fun setWalkSpeed(value: Float) {
        TODO("Not yet implemented")
    }

    override fun getFlySpeed(): Float {
        TODO("Not yet implemented")
    }

    override fun getWalkSpeed(): Float {
        TODO("Not yet implemented")
    }

    @Deprecated("Deprecated in Java")
    override fun setTexturePack(url: String) {
        TODO("Not yet implemented")
    }

    @Deprecated("Deprecated in Java")
    override fun setResourcePack(url: String) {
        TODO("Not yet implemented")
    }

    @Deprecated("Deprecated in Java")
    override fun setResourcePack(url: String, hash: ByteArray?) {
        TODO("Not yet implemented")
    }

    @Deprecated("Deprecated in Java")
    override fun setResourcePack(url: String, hash: ByteArray?, prompt: String?) {
        TODO("Not yet implemented")
    }

    @Deprecated("Deprecated in Java")
    override fun setResourcePack(url: String, hash: ByteArray?, force: Boolean) {
        TODO("Not yet implemented")
    }

    @Deprecated("Deprecated in Java")
    override fun setResourcePack(url: String, hash: ByteArray?, prompt: String?, force: Boolean) {
        TODO("Not yet implemented")
    }

    override fun setResourcePack(url: String, hash: ByteArray?, prompt: Component?, force: Boolean) {
        TODO("Not yet implemented")
    }

    @Deprecated("Deprecated in Java")
    override fun setResourcePack(id: UUID, url: String, hash: ByteArray?, prompt: String?, force: Boolean) {
        TODO("Not yet implemented")
    }

    override fun setResourcePack(uuid: UUID, url: String, hash: ByteArray?, prompt: Component?, force: Boolean) {
        TODO("Not yet implemented")
    }

    override fun setResourcePack(url: String, hash: String) {
        TODO("Not yet implemented")
    }

    override fun setResourcePack(url: String, hash: String, required: Boolean) {
        TODO("Not yet implemented")
    }

    override fun setResourcePack(url: String, hash: String, required: Boolean, resourcePackPrompt: Component?) {
        TODO("Not yet implemented")
    }

    override fun getScoreboard(): Scoreboard {
        TODO("Not yet implemented")
    }

    override fun setScoreboard(scoreboard: Scoreboard) {
        TODO("Not yet implemented")
    }

    override fun getWorldBorder(): WorldBorder? {
        TODO("Not yet implemented")
    }

    override fun setWorldBorder(border: WorldBorder?) {
        TODO("Not yet implemented")
    }

    override fun isHealthScaled(): Boolean {
        TODO("Not yet implemented")
    }

    override fun setHealthScaled(scale: Boolean) {
        TODO("Not yet implemented")
    }

    override fun setHealthScale(scale: Double) {
        TODO("Not yet implemented")
    }

    override fun getHealthScale(): Double {
        TODO("Not yet implemented")
    }

    override fun sendHealthUpdate(health: Double, foodLevel: Int, saturationLevel: Float) {
        TODO("Not yet implemented")
    }

    override fun sendHealthUpdate() {
        TODO("Not yet implemented")
    }

    override fun getSpectatorTarget(): Entity? {
        TODO("Not yet implemented")
    }

    override fun setSpectatorTarget(entity: Entity?) {
        TODO("Not yet implemented")
    }

    override fun spawnParticle(particle: Particle, location: Location, count: Int) {
        TODO("Not yet implemented")
    }

    override fun spawnParticle(particle: Particle, x: Double, y: Double, z: Double, count: Int) {
        TODO("Not yet implemented")
    }

    override fun <T : Any?> spawnParticle(particle: Particle, location: Location, count: Int, data: T?) {
        TODO("Not yet implemented")
    }

    override fun <T : Any?> spawnParticle(particle: Particle, x: Double, y: Double, z: Double, count: Int, data: T?) {
        TODO("Not yet implemented")
    }

    override fun spawnParticle(
        particle: Particle,
        location: Location,
        count: Int,
        offsetX: Double,
        offsetY: Double,
        offsetZ: Double
    ) {
        TODO("Not yet implemented")
    }

    override fun spawnParticle(
        particle: Particle,
        x: Double,
        y: Double,
        z: Double,
        count: Int,
        offsetX: Double,
        offsetY: Double,
        offsetZ: Double
    ) {
        TODO("Not yet implemented")
    }

    override fun <T : Any?> spawnParticle(
        particle: Particle,
        location: Location,
        count: Int,
        offsetX: Double,
        offsetY: Double,
        offsetZ: Double,
        data: T?
    ) {
        TODO("Not yet implemented")
    }

    override fun <T : Any?> spawnParticle(
        particle: Particle,
        x: Double,
        y: Double,
        z: Double,
        count: Int,
        offsetX: Double,
        offsetY: Double,
        offsetZ: Double,
        data: T?
    ) {
        TODO("Not yet implemented")
    }

    override fun spawnParticle(
        particle: Particle,
        location: Location,
        count: Int,
        offsetX: Double,
        offsetY: Double,
        offsetZ: Double,
        extra: Double
    ) {
        TODO("Not yet implemented")
    }

    override fun spawnParticle(
        particle: Particle,
        x: Double,
        y: Double,
        z: Double,
        count: Int,
        offsetX: Double,
        offsetY: Double,
        offsetZ: Double,
        extra: Double
    ) {
        TODO("Not yet implemented")
    }

    override fun <T : Any?> spawnParticle(
        particle: Particle,
        location: Location,
        count: Int,
        offsetX: Double,
        offsetY: Double,
        offsetZ: Double,
        extra: Double,
        data: T?
    ) {
        TODO("Not yet implemented")
    }

    override fun <T : Any?> spawnParticle(
        particle: Particle,
        x: Double,
        y: Double,
        z: Double,
        count: Int,
        offsetX: Double,
        offsetY: Double,
        offsetZ: Double,
        extra: Double,
        data: T?
    ) {
        TODO("Not yet implemented")
    }

    override fun <T : Any?> spawnParticle(
        particle: Particle,
        location: Location,
        count: Int,
        offsetX: Double,
        offsetY: Double,
        offsetZ: Double,
        extra: Double,
        data: T?,
        force: Boolean
    ) {
        TODO("Not yet implemented")
    }

    override fun <T : Any?> spawnParticle(
        particle: Particle,
        x: Double,
        y: Double,
        z: Double,
        count: Int,
        offsetX: Double,
        offsetY: Double,
        offsetZ: Double,
        extra: Double,
        data: T?,
        force: Boolean
    ) {
        TODO("Not yet implemented")
    }

    override fun getAdvancementProgress(advancement: Advancement): AdvancementProgress {
        TODO("Not yet implemented")
    }

    override fun getClientViewDistance(): Int {
        TODO("Not yet implemented")
    }

    override fun locale(): Locale {
        TODO("Not yet implemented")
    }

    override fun getPing(): Int {
        TODO("Not yet implemented")
    }

    @Deprecated("Deprecated in Java")
    override fun getLocale(): String {
        TODO("Not yet implemented")
    }

    override fun getAffectsSpawning(): Boolean {
        TODO("Not yet implemented")
    }

    override fun setAffectsSpawning(affects: Boolean) {
        TODO("Not yet implemented")
    }

    override fun getViewDistance(): Int {
        TODO("Not yet implemented")
    }

    override fun setViewDistance(viewDistance: Int) {
        TODO("Not yet implemented")
    }

    override fun getSimulationDistance(): Int {
        TODO("Not yet implemented")
    }

    override fun setSimulationDistance(simulationDistance: Int) {
        TODO("Not yet implemented")
    }

    @Deprecated("Deprecated in Java")
    override fun getNoTickViewDistance(): Int {
        TODO("Not yet implemented")
    }

    @Deprecated("Deprecated in Java")
    override fun setNoTickViewDistance(viewDistance: Int) {
        TODO("Not yet implemented")
    }

    override fun getSendViewDistance(): Int {
        TODO("Not yet implemented")
    }

    override fun setSendViewDistance(viewDistance: Int) {
        TODO("Not yet implemented")
    }

    override fun updateCommands() {
        TODO("Not yet implemented")
    }

    override fun showDemoScreen() {
        TODO("Not yet implemented")
    }

    override fun isAllowingServerListings(): Boolean {
        TODO("Not yet implemented")
    }

    override fun getResourcePackStatus(): PlayerResourcePackStatusEvent.Status? {
        TODO("Not yet implemented")
    }

    @Deprecated("Deprecated in Java")
    override fun getResourcePackHash(): String? {
        TODO("Not yet implemented")
    }

    override fun hasResourcePack(): Boolean {
        TODO("Not yet implemented")
    }

    override fun addResourcePack(id: UUID, url: String, hash: ByteArray, prompt: String?, force: Boolean) {
        TODO("Not yet implemented")
    }

    override fun removeResourcePack(id: UUID) {
        TODO("Not yet implemented")
    }

    override fun getPlayerProfile(): PlayerProfile {
        TODO("Not yet implemented")
    }

    override fun setPlayerProfile(profile: PlayerProfile) {
        TODO("Not yet implemented")
    }

    override fun getCooldownPeriod(): Float {
        TODO("Not yet implemented")
    }

    override fun getCooledAttackStrength(adjustTicks: Float): Float {
        TODO("Not yet implemented")
    }

    override fun resetCooldown() {
        TODO("Not yet implemented")
    }

    override fun <T : Any> getClientOption(option: ClientOption<T>): T {
        TODO("Not yet implemented")
    }

    @Deprecated("Deprecated in Java")
    override fun boostElytra(firework: ItemStack): Firework? {
        TODO("Not yet implemented")
    }

    override fun sendOpLevel(level: Byte) {
        TODO("Not yet implemented")
    }

    @Deprecated("Deprecated in Java")
    override fun addAdditionalChatCompletions(completions: MutableCollection<String>) {
        TODO("Not yet implemented")
    }

    @Deprecated("Deprecated in Java")
    override fun removeAdditionalChatCompletions(completions: MutableCollection<String>) {
        TODO("Not yet implemented")
    }

    override fun getClientBrandName(): String? {
        TODO("Not yet implemented")
    }

    override fun lookAt(x: Double, y: Double, z: Double, playerAnchor: LookAnchor) {
        TODO("Not yet implemented")
    }

    override fun lookAt(entity: Entity, playerAnchor: LookAnchor, entityAnchor: LookAnchor) {
        TODO("Not yet implemented")
    }

    override fun showElderGuardian(silent: Boolean) {
        TODO("Not yet implemented")
    }

    override fun getWardenWarningCooldown(): Int {
        TODO("Not yet implemented")
    }

    override fun setWardenWarningCooldown(cooldown: Int) {
        TODO("Not yet implemented")
    }

    override fun getWardenTimeSinceLastWarning(): Int {
        TODO("Not yet implemented")
    }

    override fun setWardenTimeSinceLastWarning(time: Int) {
        TODO("Not yet implemented")
    }

    override fun getWardenWarningLevel(): Int {
        TODO("Not yet implemented")
    }

    override fun setWardenWarningLevel(warningLevel: Int) {
        TODO("Not yet implemented")
    }

    override fun increaseWardenWarningLevel() {
        TODO("Not yet implemented")
    }

    override fun getIdleDuration(): Duration {
        TODO("Not yet implemented")
    }

    override fun resetIdleDuration() {
        TODO("Not yet implemented")
    }

    override fun getSentChunkKeys(): MutableSet<Long> {
        TODO("Not yet implemented")
    }

    override fun getSentChunks(): MutableSet<Chunk> {
        TODO("Not yet implemented")
    }

    override fun isChunkSent(chunkKey: Long): Boolean {
        TODO("Not yet implemented")
    }

    override fun <T : Any> getData(type: DataComponentType.Valued<T>): T? {
        TODO("Not yet implemented")
    }

    override fun <T : Any> getDataOrDefault(
        type: DataComponentType.Valued<out T>,
        fallback: T?,
    ): T? {
        TODO("Not yet implemented")
    }

    override fun hasData(type: DataComponentType): Boolean {
        TODO("Not yet implemented")
    }

}
