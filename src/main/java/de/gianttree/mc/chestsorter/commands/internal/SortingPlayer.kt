@file:Suppress("removal", "OVERRIDE_DEPRECATION", "UnstableApiUsage", "DEPRECATION")

package de.gianttree.mc.chestsorter.commands.internal

import com.destroystokyo.paper.block.TargetBlockInfo
import com.destroystokyo.paper.entity.TargetEntityInfo
import io.papermc.paper.entity.LookAnchor
import io.papermc.paper.entity.PlayerGiveResult
import io.papermc.paper.entity.TeleportFlag
import io.papermc.paper.math.Position
import net.kyori.adventure.audience.Audience
import net.kyori.adventure.audience.MessageType
import net.kyori.adventure.bossbar.BossBar
import net.kyori.adventure.chat.ChatType
import net.kyori.adventure.chat.SignedMessage
import net.kyori.adventure.identity.Identified
import net.kyori.adventure.identity.Identity
import net.kyori.adventure.inventory.Book
import net.kyori.adventure.pointer.Pointer
import net.kyori.adventure.pointer.Pointers
import net.kyori.adventure.resource.ResourcePackInfoLike
import net.kyori.adventure.resource.ResourcePackRequest
import net.kyori.adventure.resource.ResourcePackRequestLike
import net.kyori.adventure.sound.Sound
import net.kyori.adventure.sound.SoundStop
import net.kyori.adventure.text.Component
import net.kyori.adventure.text.ComponentLike
import net.kyori.adventure.text.event.HoverEvent
import net.kyori.adventure.text.minimessage.tag.resolver.TagResolver
import net.kyori.adventure.title.Title
import net.kyori.adventure.title.TitlePart
import net.kyori.adventure.util.TriState
import net.md_5.bungee.api.ChatMessageType
import net.md_5.bungee.api.chat.BaseComponent
import org.bukkit.*
import org.bukkit.block.Block
import org.bukkit.block.BlockFace
import org.bukkit.block.BlockState
import org.bukkit.block.data.BlockData
import org.bukkit.command.CommandSender
import org.bukkit.entity.*
import org.bukkit.event.inventory.InventoryType
import org.bukkit.event.player.PlayerTeleportEvent
import org.bukkit.inventory.EquipmentSlot
import org.bukkit.inventory.ItemStack
import org.bukkit.inventory.PlayerInventory
import org.bukkit.permissions.Permission
import org.bukkit.util.RayTraceResult
import org.jetbrains.annotations.UnknownNullability
import java.util.*
import java.util.concurrent.CompletableFuture
import java.util.function.Consumer
import java.util.function.Predicate
import java.util.function.Supplier
import java.util.function.UnaryOperator

@Suppress("DEPRECATION")
internal class SortingPlayer private constructor(
    private val delegate: Player,
    private val location: Location
) : Player by delegate {

    internal var cursor: ItemStack = ItemStack(Material.AIR)
    internal var inventory: PlayerInventory = SortingPlayerInventory(Bukkit.createInventory(null, InventoryType.PLAYER))

    override fun getLocation(): Location {
        return this.location
    }

    override fun getLocation(loc: Location?): Location? {
        loc?.set(this.location.x, this.location.y, this.location.z)
        loc?.direction = this.location.direction
        return loc
    }

    override fun getWorld(): World {
        return this.location.world
    }

    override fun teleport(
        location: Location,
        vararg teleportFlags: TeleportFlag
    ): Boolean {
        return this.delegate.teleport(location, *teleportFlags)
    }

    override fun lookAt(position: Position, entityAnchor: LookAnchor) {
        this.delegate.lookAt(position, entityAnchor)
    }

    override fun teleportAsync(loc: Location): CompletableFuture<Boolean?> {
        return this.delegate.teleportAsync(loc)
    }

    override fun teleportAsync(
        loc: Location,
        cause: PlayerTeleportEvent.TeleportCause
    ): CompletableFuture<Boolean?> {
        return this.delegate.teleportAsync(loc, cause)
    }

    override fun banPlayer(reason: String?): BanEntry<Any> {
        return this.delegate.banPlayer(reason)
    }

    override fun banPlayer(reason: String?, source: String?): BanEntry<Any> {
        return this.delegate.banPlayer(reason, source)
    }

    override fun banPlayer(reason: String?, expires: Date?): BanEntry<Any> {
        return this.delegate.banPlayer(reason, expires)
    }

    override fun banPlayer(
        reason: String?,
        expires: Date?,
        source: String?
    ): BanEntry<Any> {
        return this.delegate.banPlayer(reason, expires, source)
    }

    override fun banPlayer(
        reason: String?,
        expires: Date?,
        source: String?,
        kickIfOnline: Boolean
    ): BanEntry<Any> {
        return this.delegate.banPlayer(reason, expires, source, kickIfOnline)
    }

    override fun getBedSpawnLocation(): Location? {
        return this.delegate.bedSpawnLocation
    }

    override fun getType(): EntityType {
        return EntityType.UNKNOWN
    }

    override fun setPose(pose: Pose) {
        this.delegate.pose = pose
    }

    override fun getChunk(): Chunk {
        return this.delegate.chunk
    }

    override fun isInBubbleColumn(): Boolean {
        return this.delegate.isInBubbleColumn
    }

    override fun isInWaterOrRain(): Boolean {
        return this.delegate.isInWaterOrRain
    }

    override fun isInWaterOrBubbleColumn(): Boolean {
        return this.delegate.isInWaterOrBubbleColumn
    }

    override fun isInWaterOrRainOrBubbleColumn(): Boolean {
        return this.delegate.isInWaterOrRainOrBubbleColumn
    }

    override fun spawnAt(location: Location): Boolean {
        return this.delegate.spawnAt(location)
    }

    override fun getTargetBlock(maxDistance: Int): Block? {
        return this.delegate.getTargetBlock(maxDistance)
    }

    override fun getTargetBlockFace(maxDistance: Int): BlockFace? {
        return this.delegate.getTargetBlockFace(maxDistance)
    }

    override fun getTargetBlockInfo(maxDistance: Int): TargetBlockInfo? {
        return this.delegate.getTargetBlockInfo(maxDistance)
    }

    override fun getTargetEntity(maxDistance: Int): Entity? {
        return this.delegate.getTargetEntity(maxDistance)
    }

    override fun getTargetEntityInfo(maxDistance: Int): TargetEntityInfo? {
        return this.delegate.getTargetEntityInfo(maxDistance)
    }

    override fun rayTraceEntities(maxDistance: Int): RayTraceResult? {
        return this.delegate.rayTraceEntities(maxDistance)
    }

    override fun setArrowsInBody(count: Int) {
        this.delegate.arrowsInBody = count
    }

    @Deprecated("Deprecated in Java")
    override fun getCategory(): EntityCategory {
        return EntityCategory.NONE
    }

    override fun getItemUseRemainingTime(): Int {
        return this.delegate.itemUseRemainingTime
    }

    override fun getHandRaisedTime(): Int {
        return this.delegate.handRaisedTime
    }

    override fun getHandRaised(): EquipmentSlot {
        return this.delegate.handRaised
    }

    override fun playPickupItemAnimation(item: Item) {
        this.delegate.playPickupItemAnimation(item)
    }

    override fun swingHand(hand: EquipmentSlot) {
        this.delegate.swingHand(hand)
    }

    override fun getInventory(): PlayerInventory {
        return this.inventory
    }

    override fun closeInventory() {
        this.delegate.closeInventory()
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

    override fun getPotentialBedLocation(): Location? {
        return this.delegate.potentialBedLocation
    }

    override fun dropItem(slot: Int): Item? {
        return this.delegate.dropItem(slot)
    }

    override fun dropItem(slot: Int, amount: Int): Item? {
        return this.delegate.dropItem(slot, amount)
    }

    override fun dropItem(slot: EquipmentSlot): Item? {
        return this.delegate.dropItem(slot)
    }

    override fun dropItem(slot: EquipmentSlot, amount: Int): Item? {
        return this.delegate.dropItem(slot, amount)
    }

    override fun dropItem(itemStack: ItemStack): Item? {
        return this.delegate.dropItem(itemStack)
    }

    override fun identity(): Identity {
        return this.delegate.identity()
    }

    override fun kick(message: Component?) {
        this.delegate.kick(message)
    }

    override fun getRespawnLocation(): Location? {
        return this.delegate.respawnLocation
    }

    override fun sendBlockChanges(
        blocks: Collection<BlockState>,
        suppressLightUpdates: Boolean
    ) {
        this.delegate.sendBlockChanges(blocks, suppressLightUpdates)
    }

    override fun sendMultiBlockChange(
        blockChanges: Map<out Position, BlockData>,
        suppressLightUpdates: Boolean
    ) {
        this.delegate.sendMultiBlockChange(blockChanges, suppressLightUpdates)
    }

    override fun sendSignChange(
        loc: Location,
        lines: List<Component>?
    ) {
        this.delegate.sendSignChange(loc, lines)
    }

    override fun sendSignChange(
        loc: Location,
        lines: List<Component>?,
        dyeColor: DyeColor
    ) {
        this.delegate.sendSignChange(loc, lines, dyeColor)
    }

    override fun sendSignChange(
        loc: Location,
        lines: List<Component>?,
        hasGlowingText: Boolean
    ) {
        this.delegate.sendSignChange(loc, lines, hasGlowingText)
    }

    override fun banPlayerFull(reason: String?): BanEntry<Any>? {
        return this.delegate.banPlayerFull(reason)
    }

    override fun banPlayerFull(reason: String?, source: String?): BanEntry<Any>? {
        return this.delegate.banPlayerFull(reason, source)
    }

    override fun banPlayerFull(reason: String?, expires: Date?): BanEntry<Any>? {
        return this.delegate.banPlayerFull(reason, expires)
    }

    override fun banPlayerFull(
        reason: String?,
        expires: Date?,
        source: String?
    ): BanEntry<Any>? {
        return this.delegate.banPlayerFull(reason, expires, source)
    }

    override fun banPlayerIP(reason: String?, kickPlayer: Boolean): BanEntry<Any>? {
        return this.delegate.banPlayerIP(reason, kickPlayer)
    }

    override fun banPlayerIP(
        reason: String?,
        source: String?,
        kickPlayer: Boolean
    ): BanEntry<Any>? {
        return this.delegate.banPlayerIP(reason, source, kickPlayer)
    }

    override fun banPlayerIP(
        reason: String?,
        expires: Date?,
        kickPlayer: Boolean
    ): BanEntry<Any>? {
        return this.delegate.banPlayerIP(reason, expires, kickPlayer)
    }

    override fun banPlayerIP(reason: String?): BanEntry<Any>? {
        return this.delegate.banPlayerIP(reason)
    }

    override fun banPlayerIP(reason: String?, source: String?): BanEntry<Any>? {
        return this.delegate.banPlayerIP(reason, source)
    }

    override fun banPlayerIP(reason: String?, expires: Date?): BanEntry<Any>? {
        return this.delegate.banPlayerIP(reason, expires)
    }

    override fun banPlayerIP(
        reason: String?,
        expires: Date?,
        source: String?
    ): BanEntry<Any>? {
        return this.delegate.banPlayerIP(reason, expires, source)
    }

    override fun banPlayerIP(
        reason: String?,
        expires: Date?,
        source: String?,
        kickPlayer: Boolean
    ): BanEntry<Any>? {
        return this.delegate.banPlayerIP(reason, expires, source, kickPlayer)
    }

    override fun sendMessage(component: BaseComponent) {
        this.delegate.sendMessage(component)
    }

    override fun sendMessage(vararg components: BaseComponent) {
        this.delegate.sendMessage(*components)
    }

    override fun sendMessage(
        position: ChatMessageType,
        vararg components: BaseComponent
    ) {
        this.delegate.sendMessage(position, *components)
    }

    override fun giveExp(amount: Int) {
        this.delegate.giveExp(amount)
    }

    override fun setResourcePack(
        url: String,
        hash: ByteArray?,
        prompt: Component?
    ) {
        this.delegate.setResourcePack(url, hash, prompt)
    }

    override fun setResourcePack(
        url: String,
        hash: ByteArray?,
        prompt: Component?,
        force: Boolean
    ) {
        this.delegate.setResourcePack(url, hash, prompt, force)
    }

    override fun setResourcePack(url: String, hash: String) {
        this.delegate.setResourcePack(url, hash)
    }

    override fun setResourcePack(url: String, hash: String, required: Boolean) {
        this.delegate.setResourcePack(url, hash, required)
    }

    override fun setResourcePack(
        url: String,
        hash: String,
        required: Boolean,
        resourcePackPrompt: Component?
    ) {
        this.delegate.setResourcePack(url, hash, required, resourcePackPrompt)
    }

    override fun setResourcePack(
        uuid: UUID,
        url: String,
        hash: String,
        resourcePackPrompt: Component?,
        required: Boolean
    ) {
        this.delegate.setResourcePack(uuid, url, hash, resourcePackPrompt, required)
    }

    override fun getResourcePackHash(): String? {
        return this.delegate.resourcePackHash
    }

    override fun hasResourcePack(): Boolean {
        return this.delegate.hasResourcePack()
    }

    override fun getNoTickViewDistance(): Int {
        return this.delegate.noTickViewDistance
    }

    override fun setNoTickViewDistance(viewDistance: Int) {
        this.delegate.noTickViewDistance = viewDistance
    }

    override fun asHoverEvent(op: UnaryOperator<HoverEvent.ShowEntity>): HoverEvent<HoverEvent.ShowEntity> {
        return this.delegate.asHoverEvent(op)
    }

    override fun boostElytra(firework: ItemStack): Firework? {
        return this.delegate.boostElytra(firework)
    }

    override fun showElderGuardian() {
        this.delegate.showElderGuardian()
    }

    override fun isChunkSent(chunk: Chunk): Boolean {
        return this.delegate.isChunkSent(chunk)
    }

    override fun give(vararg items: ItemStack): PlayerGiveResult {
        return this.delegate.give(*items)
    }

    override fun give(items: Collection<ItemStack>): PlayerGiveResult {
        return this.delegate.give(items)
    }

    override fun filterAudience(filter: Predicate<in Audience>): Audience {
        return this.delegate.filterAudience(filter)
    }

    override fun forEachAudience(action: Consumer<in Audience>) {
        this.delegate.forEachAudience(action)
    }

    override fun sendMessage(message: ComponentLike) {
        this.delegate.sendMessage(message)
    }

    override fun sendMessage(message: Component) {
        this.delegate.sendMessage(message)
    }

    override fun sendMessage(
        message: ComponentLike,
        type: MessageType
    ) {
        this.delegate.sendMessage(message, type)
    }

    override fun sendMessage(
        message: Component,
        type: MessageType
    ) {
        this.delegate.sendMessage(message, type)
    }

    override fun sendMessage(
        source: Identified,
        message: ComponentLike
    ) {
        this.delegate.sendMessage(source, message)
    }

    override fun sendMessage(
        source: Identity,
        message: ComponentLike
    ) {
        this.delegate.sendMessage(source, message)
    }

    override fun sendMessage(
        source: Identified,
        message: Component
    ) {
        this.delegate.sendMessage(source, message)
    }

    override fun sendMessage(
        source: Identity,
        message: Component
    ) {
        this.delegate.sendMessage(source, message)
    }

    override fun sendMessage(
        source: Identified,
        message: ComponentLike,
        type: MessageType
    ) {
        this.delegate.sendMessage(source, message, type)
    }

    override fun sendMessage(
        source: Identity,
        message: ComponentLike,
        type: MessageType
    ) {
        this.delegate.sendMessage(source, message, type)
    }

    override fun sendMessage(
        source: Identified,
        message: Component,
        type: MessageType
    ) {
        this.delegate.sendMessage(source, message, type)
    }

    override fun sendMessage(
        message: Component,
        boundChatType: ChatType.Bound
    ) {
        this.delegate.sendMessage(message, boundChatType)
    }

    override fun sendMessage(
        message: ComponentLike,
        boundChatType: ChatType.Bound
    ) {
        this.delegate.sendMessage(message, boundChatType)
    }

    override fun sendMessage(
        signedMessage: SignedMessage,
        boundChatType: ChatType.Bound
    ) {
        this.delegate.sendMessage(signedMessage, boundChatType)
    }

    override fun deleteMessage(signedMessage: SignedMessage) {
        this.delegate.deleteMessage(signedMessage)
    }

    override fun deleteMessage(signature: SignedMessage.Signature) {
        this.delegate.deleteMessage(signature)
    }

    override fun sendActionBar(message: ComponentLike) {
        this.delegate.sendActionBar(message)
    }

    override fun sendActionBar(message: Component) {
        this.delegate.sendActionBar(message)
    }

    override fun sendPlayerListHeader(header: ComponentLike) {
        this.delegate.sendPlayerListHeader(header)
    }

    override fun sendPlayerListHeader(header: Component) {
        this.delegate.sendPlayerListHeader(header)
    }

    override fun sendPlayerListFooter(footer: ComponentLike) {
        this.delegate.sendPlayerListFooter(footer)
    }

    override fun sendPlayerListFooter(footer: Component) {
        this.delegate.sendPlayerListFooter(footer)
    }

    override fun sendPlayerListHeaderAndFooter(
        header: ComponentLike,
        footer: ComponentLike
    ) {
        this.delegate.sendPlayerListHeaderAndFooter(header, footer)
    }

    override fun sendPlayerListHeaderAndFooter(
        header: Component,
        footer: Component
    ) {
        this.delegate.sendPlayerListHeaderAndFooter(header, footer)
    }

    override fun showTitle(title: Title) {
        this.delegate.showTitle(title)
    }

    override fun <T : Any?> sendTitlePart(part: TitlePart<T?>, value: T & Any) {
        this.delegate.sendTitlePart(part, value)
    }

    override fun clearTitle() {
        this.delegate.clearTitle()
    }

    override fun showBossBar(bar: BossBar) {
        this.delegate.showBossBar(bar)
    }

    override fun hideBossBar(bar: BossBar) {
        this.delegate.hideBossBar(bar)
    }

    override fun playSound(sound: Sound) {
        this.delegate.playSound(sound)
    }

    override fun playSound(
        sound: Sound,
        x: Double,
        y: Double,
        z: Double
    ) {
        this.delegate.playSound(sound, x, y, z)
    }

    override fun playSound(sound: Sound, emitter: Sound.Emitter) {
        this.delegate.playSound(sound, emitter)
    }

    override fun stopSound(sound: Sound) {
        this.delegate.stopSound(sound)
    }

    override fun stopSound(stop: SoundStop) {
        this.delegate.stopSound(stop)
    }

    override fun openBook(book: Book.Builder) {
        this.delegate.openBook(book)
    }

    override fun openBook(book: Book) {
        this.delegate.openBook(book)
    }

    override fun sendResourcePacks(
        first: ResourcePackInfoLike,
        vararg others: ResourcePackInfoLike
    ) {
        this.delegate.sendResourcePacks(first, *others)
    }

    override fun sendResourcePacks(request: ResourcePackRequestLike) {
        this.delegate.sendResourcePacks(request)
    }

    override fun sendResourcePacks(request: ResourcePackRequest) {
        this.delegate.sendResourcePacks(request)
    }

    override fun removeResourcePacks(request: ResourcePackRequestLike) {
        this.delegate.removeResourcePacks(request)
    }

    override fun removeResourcePacks(request: ResourcePackRequest) {
        this.delegate.removeResourcePacks(request)
    }

    override fun removeResourcePacks(
        request: ResourcePackInfoLike,
        vararg others: ResourcePackInfoLike
    ) {
        this.delegate.removeResourcePacks(request, *others)
    }

    override fun removeResourcePacks(ids: Iterable<UUID?>) {
        this.delegate.removeResourcePacks(ids)
    }

    override fun removeResourcePacks(id: UUID, vararg others: UUID) {
        this.delegate.removeResourcePacks(id, *others)
    }

    override fun clearResourcePacks() {
        this.delegate.clearResourcePacks()
    }

    override fun sendMessage(
        identity: Identity,
        message: Component,
        type: MessageType
    ) {
        this.delegate.sendMessage(identity, message, type)
    }

    override fun sendRichMessage(message: String) {
        this.delegate.sendRichMessage(message)
    }

    override fun sendRichMessage(
        message: String,
        vararg resolvers: TagResolver
    ) {
        this.delegate.sendRichMessage(message, *resolvers)
    }

    override fun sendPlainMessage(message: String) {
        this.delegate.sendPlainMessage(message)
    }

    override fun asHoverEvent(): HoverEvent<HoverEvent.ShowEntity?> {
        return this.delegate.asHoverEvent()
    }

    override fun heal(amount: Double) {
        this.delegate.heal(amount)
    }

    override fun <T : Any?> get(pointer: Pointer<T?>): Optional<T?> {
        return this.delegate.get(pointer)
    }

    override fun <T : Any?> getOrDefault(
        pointer: Pointer<T?>,
        defaultValue: T?
    ): T? {
        return this.delegate.getOrDefault(pointer, defaultValue)
    }

    override fun <T : Any?> getOrDefaultFrom(
        pointer: Pointer<T?>,
        defaultValue: Supplier<out T?>
    ): @UnknownNullability T? {
        return this.delegate.getOrDefaultFrom(pointer, defaultValue)
    }

    override fun pointers(): Pointers {
        return this.delegate.pointers()
    }

    override fun permissionValue(permission: Permission): TriState {
        return this.delegate.permissionValue(permission)
    }

    override fun permissionValue(permission: String): TriState {
        return this.delegate.permissionValue(permission)
    }

    companion object {
        fun of(sender: CommandSender, location: Location): SortingPlayer {
            return if (sender is Player) {
                this.of(sender)
            } else {
                return SortingPlayer(ConsoleDelegate(sender), location)
            }
        }

        fun of(player: Player): SortingPlayer {
            return SortingPlayer(PlayerDelegate(player), player.location)
        }
    }
}
