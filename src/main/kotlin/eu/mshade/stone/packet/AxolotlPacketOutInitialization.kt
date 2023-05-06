package eu.mshade.stone.packet

import eu.mshade.axolotl.protocol.AxolotlPacketOut
import eu.mshade.enderframe.entity.Player
import eu.mshade.enderframe.metadata.MetadataKeyValueBufferRegistry
import eu.mshade.enderframe.world.World
import eu.mshade.mwork.binarytag.BinaryTagType
import eu.mshade.mwork.binarytag.entity.CompoundBinaryTag
import eu.mshade.mwork.binarytag.entity.ListBinaryTag
import eu.mshade.stone.marshal.entity.PlayerBinaryTagMarshal

class AxolotlPacketOutInitialization(private val worlds: Collection<World>, private val players: Collection<Player>, private val metadataKeyValueBufferRegistry: MetadataKeyValueBufferRegistry) : AxolotlPacketOut {

    override fun write(compoundBinaryTag: CompoundBinaryTag) {
        val worldsCompoundBinaryTag = ListBinaryTag(BinaryTagType.COMPOUND)
        for (world in worlds) {
            worldsCompoundBinaryTag.add(metadataKeyValueBufferRegistry.serialize(world.metadatas))
        }
        compoundBinaryTag.putBinaryTag("worlds", worldsCompoundBinaryTag)
        val playersCompoundBinaryTag = ListBinaryTag(BinaryTagType.COMPOUND)
        for (player in players) {
            playersCompoundBinaryTag.add(PlayerBinaryTagMarshal.serialize(player, metadataKeyValueBufferRegistry))
        }
        compoundBinaryTag.putBinaryTag("players", playersCompoundBinaryTag)
    }

}