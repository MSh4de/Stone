package eu.mshade.stone.packet.player

import eu.mshade.axolotl.protocol.AxolotlPacketOut
import eu.mshade.enderframe.entity.Player
import eu.mshade.mwork.binarytag.entity.CompoundBinaryTag

class AxolotlPacketOutPlayerLeave(private val player: Player): AxolotlPacketOut {

    override fun write(compoundBinaryTag: CompoundBinaryTag) {
        compoundBinaryTag.putString("player", player.uniqueId.toString())
    }

}