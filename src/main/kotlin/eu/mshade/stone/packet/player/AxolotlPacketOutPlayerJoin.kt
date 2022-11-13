package eu.mshade.stone.packet.player

import eu.mshade.axolotl.protocol.AxolotlPacketOut
import eu.mshade.enderframe.entity.Player
import eu.mshade.enderframe.metadata.MetadataKeyValueBufferRegistry
import eu.mshade.mwork.binarytag.entity.CompoundBinaryTag
import eu.mshade.stone.marshal.entity.PlayerBinaryTagMarshal

class AxolotlPacketOutPlayerJoin(private val player: Player, private val metadataKeyValueBufferRegistry: MetadataKeyValueBufferRegistry): AxolotlPacketOut {

    override fun write(compoundBinaryTag: CompoundBinaryTag) {
        compoundBinaryTag.putBinaryTag("player", PlayerBinaryTagMarshal.serialize(player, metadataKeyValueBufferRegistry))
    }
}