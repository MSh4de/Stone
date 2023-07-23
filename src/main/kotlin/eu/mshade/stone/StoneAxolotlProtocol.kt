package eu.mshade.stone

import eu.mshade.axolotl.protocol.AxolotlPacketIn
import eu.mshade.axolotl.protocol.AxolotlProtocol
import eu.mshade.axolotl.protocol.AxolotlProtocolVersion
import eu.mshade.axolotl.protocol.AxolotlSession
import eu.mshade.enderframe.world.WorldMetadataType
import eu.mshade.enderframe.world.block.BlockMetadataType
import eu.mshade.stone.listener.AxolotlPacketInChatMessageListener
import eu.mshade.stone.marshal.metadata.*
import eu.mshade.stone.packet.AxolotlPacketOutInitialization
import eu.mshade.stone.packet.entity.AxolotlPacketOutEntityLocation
import eu.mshade.stone.packet.inventory.*
import eu.mshade.stone.packet.world.AxolotlPacketOutChunk
import eu.mshade.stone.packet.world.AxolotlPacketOutSection
import eu.mshade.stone.packet.player.*
import eu.mshade.stone.packet.world.AxolotlPacketOutInitializeWorld
import io.netty.channel.Channel

class StoneAxolotlProtocol: AxolotlProtocol() {

    init {
        //register world metadata
        metadataKeyValueBufferRegistry.register(WorldMetadataType.LEVEL_TYPE, LevelTypeWorldMetadataBuffer())
        metadataKeyValueBufferRegistry.register(WorldMetadataType.DIMENSION, DimensionWorldMetadataBuffer())
        metadataKeyValueBufferRegistry.register(WorldMetadataType.SEED, SeedWorldMetadataBuffer())
        metadataKeyValueBufferRegistry.register(WorldMetadataType.NAME, NameWorldMetadataBuffer())
        metadataKeyValueBufferRegistry.register(WorldMetadataType.PARENT, ParentWorldMetadataBuffer())

        //register block metadata
        metadataKeyValueBufferRegistry.register(BlockMetadataType.EXTRA, ExtraBlockMetadataBuffer())
        metadataKeyValueBufferRegistry.register(BlockMetadataType.FACE, FaceBlockMetadataBuffer())
        metadataKeyValueBufferRegistry.register(BlockMetadataType.HALF, HalfBlockMetadataBuffer())
        metadataKeyValueBufferRegistry.register(BlockMetadataType.SHAPE, ShapeBlockMetadataBuffer())
        metadataKeyValueBufferRegistry.register(BlockMetadataType.AXIS, AxisBlockMetadataBuffer())
        metadataKeyValueBufferRegistry.register(BlockMetadataType.POWER, PowerBlockMetadataBuffer())
        metadataKeyValueBufferRegistry.register(BlockMetadataType.DECAYABLE, DecayableBlockMetadataBuffer())
        metadataKeyValueBufferRegistry.register(BlockMetadataType.CHECK_DECAY, CheckDecayBlockMetadataBuffer())
        metadataKeyValueBufferRegistry.register(BlockMetadataType.SEAMLESS, SeamlessBlockMetadataBuffer())
        metadataKeyValueBufferRegistry.register(BlockMetadataType.MULTIPLE_FACE, MultipleFaceBlockMetadataBuffer())

        eventBus.subscribe(AxolotlPacketInChatMessage::class.java, AxolotlPacketInChatMessageListener())

        packetRegistry.registerPacketIn(0x01, AxolotlPacketInChatMessage::class)
        packetRegistry.registerPacketIn(0x02, AxolotlPacketInOpenInventory::class)
        packetRegistry.registerPacketIn(0x03, AxolotlPacketInSetItem::class)
        packetRegistry.registerPacketIn(0x04, AxolotlPacketInCreateInventory::class)


        //register all packets
        //packetRegistry.registerPacketOut(0x01, AxolotlPacketOutSpawnEntity::class)
        packetRegistry.registerPacketOut(0x02, AxolotlPacketOutChunk::class)
        packetRegistry.registerPacketOut(0x03, AxolotlPacketOutSection::class)
        packetRegistry.registerPacketOut(0x04, AxolotlPacketOutInitializeWorld::class)
        packetRegistry.registerPacketOut(0x05, AxolotlPacketOutInitializePlayer::class)
        packetRegistry.registerPacketOut(0x06, AxolotlPacketOutPlayerJoin::class)
        packetRegistry.registerPacketOut(0x07, AxolotlPacketOutPlayerLeave::class)
        packetRegistry.registerPacketOut(0x08, AxolotlPacketOutEntityLocation::class)
        packetRegistry.registerPacketOut(0x09, AxolotlPacketOutChatMessage::class)
        packetRegistry.registerPacketOut(0x0A, AxolotlPacketOutInitialization::class)
        packetRegistry.registerPacketOut(0x0B, AxolotlPacketOutOpenInventory::class)
        packetRegistry.registerPacketOut(0x0C, AxolotlPacketOutSetItem::class)
        packetRegistry.registerPacketOut(0x0D, AxolotlPacketOutCreateInventory::class)


    }

    override fun getAxolotlProtocolVersion(): AxolotlProtocolVersion {
        return AxolotlProtocolVersion.STONE
    }

    override fun getAxolotlSession(channel: Channel): AxolotlSession {
        return StoneAxolotlSession(channel, metadataKeyValueBufferRegistry)
    }
}