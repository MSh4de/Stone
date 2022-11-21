package eu.mshade.stone

import eu.mshade.axolotl.protocol.AxolotlSession
import eu.mshade.enderframe.entity.Entity
import eu.mshade.enderframe.entity.Player
import eu.mshade.enderframe.metadata.MetadataKeyValueBufferRegistry
import eu.mshade.enderframe.world.World
import eu.mshade.enderframe.world.chunk.Chunk
import eu.mshade.enderframe.world.chunk.Section
import eu.mshade.stone.packet.entity.AxolotlPacketOutEntityLocation
import eu.mshade.stone.packet.player.AxolotlPacketOutInitializePlayer
import eu.mshade.stone.packet.player.AxolotlPacketOutPlayerJoin
import eu.mshade.stone.packet.player.AxolotlPacketOutPlayerLeave
import eu.mshade.stone.packet.world.AxolotlPacketOutChunk
import eu.mshade.stone.packet.world.AxolotlPacketOutChunkUnload
import eu.mshade.stone.packet.world.AxolotlPacketOutInitializeWorld
import eu.mshade.stone.packet.world.AxolotlPacketOutSection
import io.netty.channel.Channel

class StoneAxolotlSession(channel: Channel, private val metadataKeyValueBufferRegistry: MetadataKeyValueBufferRegistry) : AxolotlSession(channel) {


    override fun sendInitializePlayer(player: Player) {
        sendPacket(AxolotlPacketOutInitializePlayer(player, metadataKeyValueBufferRegistry))
    }

    override fun sendPlayerJoin(player: Player) {
        sendPacket(AxolotlPacketOutPlayerJoin(player, metadataKeyValueBufferRegistry))
    }

    override fun sendPlayerLeave(player: Player) {
        sendPacket(AxolotlPacketOutPlayerLeave(player))
    }

    override fun sendEntityLocation(entity: Entity) {
        sendPacket(AxolotlPacketOutEntityLocation(entity))
    }

    override fun sendChunk(chunk: Chunk) {
        sendPacket(AxolotlPacketOutChunk(chunk, metadataKeyValueBufferRegistry))
    }

    override fun sendChunkUnload(chunk: Chunk) {
        sendPacket(AxolotlPacketOutChunkUnload(chunk))
    }

    override fun sendSection(section: Section) {
        sendPacket(AxolotlPacketOutSection(section, metadataKeyValueBufferRegistry))
    }

    override fun sendInitializeWorld(world: World) {
        sendPacket(AxolotlPacketOutInitializeWorld(world, metadataKeyValueBufferRegistry))
    }

}