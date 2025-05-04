@file:Suppress("OVERRIDE_DEPRECATION", "UnstableApiUsage", "removal", "DEPRECATION")

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
import org.bukkit.BanEntry
import org.bukkit.Chunk
import org.bukkit.DyeColor
import org.bukkit.Location
import org.bukkit.block.Block
import org.bukkit.block.BlockFace
import org.bukkit.block.BlockState
import org.bukkit.block.data.BlockData
import org.bukkit.entity.Entity
import org.bukkit.entity.Firework
import org.bukkit.entity.Item
import org.bukkit.entity.Player
import org.bukkit.entity.Pose
import org.bukkit.event.player.PlayerTeleportEvent
import org.bukkit.inventory.EquipmentSlot
import org.bukkit.inventory.ItemStack
import org.bukkit.permissions.Permission
import org.bukkit.util.RayTraceResult
import org.jetbrains.annotations.UnknownNullability
import java.util.Date
import java.util.Optional
import java.util.UUID
import java.util.concurrent.CompletableFuture
import java.util.function.Consumer
import java.util.function.Predicate
import java.util.function.Supplier
import java.util.function.UnaryOperator

@Suppress("removal")
internal class PlayerDelegate(
    val delegatePlayer: Player
) : Player by delegatePlayer {
    override fun identity(): Identity {
        return this.delegatePlayer.identity()
    }

    override fun kick(message: Component?) {
        this.delegatePlayer.kick(message)
    }

    override fun getRespawnLocation(): Location? {
        return this.delegatePlayer.respawnLocation
    }

    override fun sendBlockChanges(
        blocks: Collection<BlockState>,
        suppressLightUpdates: Boolean
    ) {
        this.delegatePlayer.sendBlockChanges(blocks, suppressLightUpdates)
    }

    override fun sendMultiBlockChange(
        blockChanges: Map<out Position, BlockData>,
        suppressLightUpdates: Boolean
    ) {
        this.delegatePlayer.sendMultiBlockChange(blockChanges, suppressLightUpdates)
    }

    override fun sendSignChange(
        loc: Location,
        lines: List<Component>?
    ) {
        this.delegatePlayer.sendSignChange(loc, lines)
    }

    override fun sendSignChange(
        loc: Location,
        lines: List<Component>?,
        dyeColor: DyeColor
    ) {
        this.delegatePlayer.sendSignChange(loc, lines, dyeColor)
    }

    override fun sendSignChange(
        loc: Location,
        lines: List<Component>?,
        hasGlowingText: Boolean
    ) {
        this.delegatePlayer.sendSignChange(loc, lines, hasGlowingText)
    }

    override fun banPlayerFull(reason: String?): BanEntry<Any>? {
        return this.delegatePlayer.banPlayerFull(reason)
    }

    override fun banPlayerFull(reason: String?, source: String?): BanEntry<Any>? {
        return this.delegatePlayer.banPlayerFull(reason, source)
    }

    override fun banPlayerFull(reason: String?, expires: Date?): BanEntry<Any>? {
        return this.delegatePlayer.banPlayerFull(reason, expires)
    }

    override fun banPlayerFull(
        reason: String?,
        expires: Date?,
        source: String?
    ): BanEntry<Any>? {
        return this.delegatePlayer.banPlayerFull(reason, expires, source)
    }

    override fun banPlayerIP(reason: String?, kickPlayer: Boolean): BanEntry<Any>? {
        return this.delegatePlayer.banPlayerIP(reason, kickPlayer)
    }

    override fun banPlayerIP(
        reason: String?,
        source: String?,
        kickPlayer: Boolean
    ): BanEntry<Any>? {
        return this.delegatePlayer.banPlayerIP(reason, source, kickPlayer)
    }

    override fun banPlayerIP(
        reason: String?,
        expires: Date?,
        kickPlayer: Boolean
    ): BanEntry<Any>? {
        return this.delegatePlayer.banPlayerIP(reason, expires, kickPlayer)
    }

    override fun banPlayerIP(reason: String?): BanEntry<Any>? {
        return this.delegatePlayer.banPlayerIP(reason)
    }

    override fun banPlayerIP(reason: String?, source: String?): BanEntry<Any>? {
        return this.delegatePlayer.banPlayerIP(reason, source)
    }

    override fun banPlayerIP(reason: String?, expires: Date?): BanEntry<Any>? {
        return this.delegatePlayer.banPlayerIP(reason, expires)
    }

    override fun banPlayerIP(
        reason: String?,
        expires: Date?,
        source: String?
    ): BanEntry<Any>? {
        return this.delegatePlayer.banPlayerIP(reason, expires, source)
    }

    override fun banPlayerIP(
        reason: String?,
        expires: Date?,
        source: String?,
        kickPlayer: Boolean
    ): BanEntry<Any>? {
        return this.delegatePlayer.banPlayerIP(reason, expires, source, kickPlayer)
    }

    override fun sendMessage(component: BaseComponent) {
        this.delegatePlayer.sendMessage(component)
    }

    override fun sendMessage(vararg components: BaseComponent) {
        this.delegatePlayer.sendMessage(*components)
    }

    override fun sendMessage(
        position: ChatMessageType,
        vararg components: BaseComponent
    ) {
        this.delegatePlayer.sendMessage(position, *components)
    }

    override fun giveExp(amount: Int) {
        this.delegatePlayer.giveExp(amount)
    }

    override fun setResourcePack(
        url: String,
        hash: ByteArray?,
        prompt: Component?
    ) {
        this.delegatePlayer.setResourcePack(url, hash, prompt)
    }

    override fun setResourcePack(
        url: String,
        hash: ByteArray?,
        prompt: Component?,
        force: Boolean
    ) {
        this.delegatePlayer.setResourcePack(url, hash, prompt, force)
    }

    override fun setResourcePack(url: String, hash: String) {
        this.delegatePlayer.setResourcePack(url, hash)
    }

    override fun setResourcePack(url: String, hash: String, required: Boolean) {
        this.delegatePlayer.setResourcePack(url, hash, required)
    }

    override fun setResourcePack(
        url: String,
        hash: String,
        required: Boolean,
        resourcePackPrompt: Component?
    ) {
        this.delegatePlayer.setResourcePack(url, hash, required, resourcePackPrompt)
    }

    override fun setResourcePack(
        uuid: UUID,
        url: String,
        hash: String,
        resourcePackPrompt: Component?,
        required: Boolean
    ) {
        this.delegatePlayer.setResourcePack(uuid, url, hash, resourcePackPrompt, required)
    }

    override fun getResourcePackHash(): String? {
        return this.delegatePlayer.resourcePackHash
    }

    override fun hasResourcePack(): Boolean {
        return this.delegatePlayer.hasResourcePack()
    }

    override fun getNoTickViewDistance(): Int {
        return this.delegatePlayer.noTickViewDistance
    }

    override fun setNoTickViewDistance(viewDistance: Int) {
        this.delegatePlayer.noTickViewDistance = viewDistance
    }

    override fun asHoverEvent(op: UnaryOperator<HoverEvent.ShowEntity>): HoverEvent<HoverEvent.ShowEntity> {
        return this.delegatePlayer.asHoverEvent(op)
    }

    override fun boostElytra(firework: ItemStack): Firework? {
        return this.delegatePlayer.boostElytra(firework)
    }

    override fun showElderGuardian() {
        this.delegatePlayer.showElderGuardian()
    }

    override fun isChunkSent(chunk: Chunk): Boolean {
        return this.delegatePlayer.isChunkSent(chunk)
    }

    override fun give(vararg items: ItemStack): PlayerGiveResult {
        return this.delegatePlayer.give(*items)
    }

    override fun give(items: Collection<ItemStack>): PlayerGiveResult {
        return this.delegatePlayer.give(items)
    }

    override fun filterAudience(filter: Predicate<in Audience>): Audience {
        return this.delegatePlayer.filterAudience(filter)
    }

    override fun forEachAudience(action: Consumer<in Audience>) {
        this.delegatePlayer.forEachAudience(action)
    }

    override fun sendMessage(message: ComponentLike) {
        this.delegatePlayer.sendMessage(message)
    }

    override fun sendMessage(message: Component) {
        this.delegatePlayer.sendMessage(message)
    }

    override fun sendMessage(
        message: ComponentLike,
        type: MessageType
    ) {
        this.delegatePlayer.sendMessage(message, type)
    }

    override fun sendMessage(
        message: Component,
        type: MessageType
    ) {
        this.delegatePlayer.sendMessage(message, type)
    }

    override fun sendMessage(
        source: Identified,
        message: ComponentLike
    ) {
        this.delegatePlayer.sendMessage(source, message)
    }

    override fun sendMessage(
        source: Identity,
        message: ComponentLike
    ) {
        this.delegatePlayer.sendMessage(source, message)
    }

    override fun sendMessage(
        source: Identified,
        message: Component
    ) {
        this.delegatePlayer.sendMessage(source, message)
    }

    override fun sendMessage(
        source: Identity,
        message: Component
    ) {
        this.delegatePlayer.sendMessage(source, message)
    }

    override fun sendMessage(
        source: Identified,
        message: ComponentLike,
        type: MessageType
    ) {
        this.delegatePlayer.sendMessage(source, message, type)
    }

    override fun sendMessage(
        source: Identity,
        message: ComponentLike,
        type: MessageType
    ) {
        this.delegatePlayer.sendMessage(source, message, type)
    }

    override fun sendMessage(
        source: Identified,
        message: Component,
        type: MessageType
    ) {
        this.delegatePlayer.sendMessage(source, message, type)
    }

    override fun sendMessage(
        message: Component,
        boundChatType: ChatType.Bound
    ) {
        this.delegatePlayer.sendMessage(message, boundChatType)
    }

    override fun sendMessage(
        message: ComponentLike,
        boundChatType: ChatType.Bound
    ) {
        this.delegatePlayer.sendMessage(message, boundChatType)
    }

    override fun sendMessage(
        signedMessage: SignedMessage,
        boundChatType: ChatType.Bound
    ) {
        this.delegatePlayer.sendMessage(signedMessage, boundChatType)
    }

    override fun deleteMessage(signedMessage: SignedMessage) {
        this.delegatePlayer.deleteMessage(signedMessage)
    }

    override fun deleteMessage(signature: SignedMessage.Signature) {
        this.delegatePlayer.deleteMessage(signature)
    }

    override fun sendActionBar(message: ComponentLike) {
        this.delegatePlayer.sendActionBar(message)
    }

    override fun sendActionBar(message: Component) {
        this.delegatePlayer.sendActionBar(message)
    }

    override fun sendPlayerListHeader(header: ComponentLike) {
        this.delegatePlayer.sendPlayerListHeader(header)
    }

    override fun sendPlayerListHeader(header: Component) {
        this.delegatePlayer.sendPlayerListHeader(header)
    }

    override fun sendPlayerListFooter(footer: ComponentLike) {
        this.delegatePlayer.sendPlayerListFooter(footer)
    }

    override fun sendPlayerListFooter(footer: Component) {
        this.delegatePlayer.sendPlayerListFooter(footer)
    }

    override fun sendPlayerListHeaderAndFooter(
        header: ComponentLike,
        footer: ComponentLike
    ) {
        this.delegatePlayer.sendPlayerListHeaderAndFooter(header, footer)
    }

    override fun sendPlayerListHeaderAndFooter(
        header: Component,
        footer: Component
    ) {
        this.delegatePlayer.sendPlayerListHeaderAndFooter(header, footer)
    }

    override fun showTitle(title: Title) {
        this.delegatePlayer.showTitle(title)
    }

    override fun <T : Any?> sendTitlePart(part: TitlePart<T?>, value: T & Any) {
        this.delegatePlayer.sendTitlePart(part, value)
    }

    override fun clearTitle() {
        this.delegatePlayer.clearTitle()
    }

    override fun showBossBar(bar: BossBar) {
        this.delegatePlayer.showBossBar(bar)
    }

    override fun hideBossBar(bar: BossBar) {
        this.delegatePlayer.hideBossBar(bar)
    }

    override fun playSound(sound: Sound) {
        this.delegatePlayer.playSound(sound)
    }

    override fun playSound(
        sound: Sound,
        x: Double,
        y: Double,
        z: Double
    ) {
        this.delegatePlayer.playSound(sound, x, y, z)
    }

    override fun playSound(sound: Sound, emitter: Sound.Emitter) {
        this.delegatePlayer.playSound(sound, emitter)
    }

    override fun stopSound(sound: Sound) {
        this.delegatePlayer.stopSound(sound)
    }

    override fun stopSound(stop: SoundStop) {
        this.delegatePlayer.stopSound(stop)
    }

    override fun openBook(book: Book.Builder) {
        this.delegatePlayer.openBook(book)
    }

    override fun openBook(book: Book) {
        this.delegatePlayer.openBook(book)
    }

    override fun sendResourcePacks(
        first: ResourcePackInfoLike,
        vararg others: ResourcePackInfoLike
    ) {
        this.delegatePlayer.sendResourcePacks(first, *others)
    }

    override fun sendResourcePacks(request: ResourcePackRequestLike) {
        this.delegatePlayer.sendResourcePacks(request)
    }

    override fun sendResourcePacks(request: ResourcePackRequest) {
        this.delegatePlayer.sendResourcePacks(request)
    }

    override fun removeResourcePacks(request: ResourcePackRequestLike) {
        this.delegatePlayer.removeResourcePacks(request)
    }

    override fun removeResourcePacks(request: ResourcePackRequest) {
        this.delegatePlayer.removeResourcePacks(request)
    }

    override fun removeResourcePacks(
        request: ResourcePackInfoLike,
        vararg others: ResourcePackInfoLike
    ) {
        this.delegatePlayer.removeResourcePacks(request, *others)
    }

    override fun removeResourcePacks(ids: Iterable<UUID?>) {
        this.delegatePlayer.removeResourcePacks(ids)
    }

    override fun removeResourcePacks(id: UUID, vararg others: UUID) {
        this.delegatePlayer.removeResourcePacks(id, *others)
    }

    override fun clearResourcePacks() {
        this.delegatePlayer.clearResourcePacks()
    }

    override fun sendMessage(
        identity: Identity,
        message: Component,
        type: MessageType
    ) {
        this.delegatePlayer.sendMessage(identity, message, type)
    }

    override fun sendRichMessage(message: String) {
        this.delegatePlayer.sendRichMessage(message)
    }

    override fun sendRichMessage(
        message: String,
        vararg resolvers: TagResolver
    ) {
        this.delegatePlayer.sendRichMessage(message, *resolvers)
    }

    override fun sendPlainMessage(message: String) {
        this.delegatePlayer.sendPlainMessage(message)
    }

    override fun asHoverEvent(): HoverEvent<HoverEvent.ShowEntity?> {
        return this.delegatePlayer.asHoverEvent()
    }

    override fun teleport(
        location: Location,
        vararg teleportFlags: TeleportFlag
    ): Boolean {
        return this.delegatePlayer.teleport(location, *teleportFlags)
    }

    override fun lookAt(position: Position, entityAnchor: LookAnchor) {
        this.delegatePlayer.lookAt(position, entityAnchor)
    }

    override fun teleportAsync(loc: Location): CompletableFuture<Boolean?> {
        return this.delegatePlayer.teleportAsync(loc)
    }

    override fun teleportAsync(
        loc: Location,
        cause: PlayerTeleportEvent.TeleportCause
    ): CompletableFuture<Boolean?> {
        return this.delegatePlayer.teleportAsync(loc, cause)
    }

    override fun setPose(pose: Pose) {
        this.delegatePlayer.pose = pose
    }

    override fun getChunk(): Chunk {
        return this.delegatePlayer.chunk
    }

    override fun isInBubbleColumn(): Boolean {
        return this.delegatePlayer.isInBubbleColumn
    }

    override fun isInWaterOrRain(): Boolean {
        return this.delegatePlayer.isInWaterOrRain
    }

    override fun isInWaterOrBubbleColumn(): Boolean {
        return this.delegatePlayer.isInWaterOrBubbleColumn
    }

    override fun isInWaterOrRainOrBubbleColumn(): Boolean {
        return this.delegatePlayer.isInWaterOrRainOrBubbleColumn
    }

    override fun spawnAt(location: Location): Boolean {
        return this.delegatePlayer.spawnAt(location)
    }

    override fun closeInventory() {
        this.delegatePlayer.closeInventory()
    }

    override fun getPotentialBedLocation(): Location? {
        return this.delegatePlayer.potentialBedLocation
    }

    override fun dropItem(slot: Int): Item? {
        return this.delegatePlayer.dropItem(slot)
    }

    override fun dropItem(slot: Int, amount: Int): Item? {
        return this.delegatePlayer.dropItem(slot, amount)
    }

    override fun dropItem(slot: EquipmentSlot): Item? {
        return this.delegatePlayer.dropItem(slot)
    }

    override fun dropItem(slot: EquipmentSlot, amount: Int): Item? {
        return this.delegatePlayer.dropItem(slot, amount)
    }

    override fun dropItem(itemStack: ItemStack): Item? {
        return this.delegatePlayer.dropItem(itemStack)
    }

    override fun getTargetBlock(maxDistance: Int): Block? {
        return this.delegatePlayer.getTargetBlock(maxDistance)
    }

    override fun getTargetBlockFace(maxDistance: Int): BlockFace? {
        return this.delegatePlayer.getTargetBlockFace(maxDistance)
    }

    override fun getTargetBlockInfo(maxDistance: Int): TargetBlockInfo? {
        return this.delegatePlayer.getTargetBlockInfo(maxDistance)
    }

    override fun getTargetEntity(maxDistance: Int): Entity? {
        return this.delegatePlayer.getTargetEntity(maxDistance)
    }

    override fun getTargetEntityInfo(maxDistance: Int): TargetEntityInfo? {
        return this.delegatePlayer.getTargetEntityInfo(maxDistance)
    }

    override fun rayTraceEntities(maxDistance: Int): RayTraceResult? {
        return this.delegatePlayer.rayTraceEntities(maxDistance)
    }

    override fun setArrowsInBody(count: Int) {
        this.delegatePlayer.arrowsInBody = count
    }

    override fun getItemUseRemainingTime(): Int {
        return this.delegatePlayer.itemUseRemainingTime
    }

    override fun getHandRaisedTime(): Int {
        return this.delegatePlayer.handRaisedTime
    }

    override fun getHandRaised(): EquipmentSlot {
        return this.delegatePlayer.handRaised
    }

    override fun playPickupItemAnimation(item: Item) {
        this.delegatePlayer.playPickupItemAnimation(item)
    }

    override fun swingHand(hand: EquipmentSlot) {
        this.delegatePlayer.swingHand(hand)
    }

    override fun heal(amount: Double) {
        this.delegatePlayer.heal(amount)
    }

    override fun <T : Any?> get(pointer: Pointer<T?>): Optional<T?> {
        return this.delegatePlayer.get(pointer)
    }

    override fun <T : Any?> getOrDefault(
        pointer: Pointer<T?>,
        defaultValue: T?
    ): T? {
        return this.delegatePlayer.getOrDefault(pointer, defaultValue)
    }

    override fun <T : Any?> getOrDefaultFrom(
        pointer: Pointer<T?>,
        defaultValue: Supplier<out T?>
    ): @UnknownNullability T? {
        return this.delegatePlayer.getOrDefaultFrom(pointer, defaultValue)
    }

    override fun pointers(): Pointers {
        return this.delegatePlayer.pointers()
    }

    override fun permissionValue(permission: Permission): TriState {
        return this.delegatePlayer.permissionValue(permission)
    }

    override fun permissionValue(permission: String): TriState {
        return this.delegatePlayer.permissionValue(permission)
    }

    override fun banPlayer(reason: String?): BanEntry<Any> {
        return this.delegatePlayer.banPlayer(reason)
    }

    override fun banPlayer(reason: String?, source: String?): BanEntry<Any> {
        return this.delegatePlayer.banPlayer(reason, source)
    }

    override fun banPlayer(reason: String?, expires: Date?): BanEntry<Any> {
        return this.delegatePlayer.banPlayer(reason, expires)
    }

    override fun banPlayer(
        reason: String?,
        expires: Date?,
        source: String?
    ): BanEntry<Any> {
        return this.delegatePlayer.banPlayer(reason, expires, source)
    }

    override fun banPlayer(
        reason: String?,
        expires: Date?,
        source: String?,
        kickIfOnline: Boolean
    ): BanEntry<Any> {
        return this.delegatePlayer.banPlayer(reason, expires, source, kickIfOnline)
    }

    override fun getBedSpawnLocation(): Location? {
        return this.delegatePlayer.bedSpawnLocation
    }
}