package eu.mshade.stone.packet.player

import eu.mshade.axolotl.protocol.AxolotlPacketIn
import eu.mshade.axolotl.protocol.AxolotlSession
import eu.mshade.mwork.binarytag.entity.CompoundBinaryTag

class AxolotlPacketInTitle: AxolotlPacketIn {

    lateinit var axolotlSession: AxolotlSession


    override fun read(axolotlSession: AxolotlSession, compoundBinaryTag: CompoundBinaryTag) {
        this.axolotlSession = axolotlSession

    }
}