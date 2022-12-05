package eu.mshade.stone.packet.entity

import eu.mshade.axolotl.protocol.AxolotlPacketIn
import eu.mshade.axolotl.protocol.AxolotlSession
import eu.mshade.enderframe.world.Location
import eu.mshade.mwork.binarytag.entity.CompoundBinaryTag
import java.util.*

class AxolotlPacketInEntityTeleport: AxolotlPacketIn {

    lateinit var axolotlSession: AxolotlSession
    lateinit var uid: UUID
    lateinit var location: Location

    override fun read(axolotlSession: AxolotlSession, compoundBinaryTag: CompoundBinaryTag) {
        this.axolotlSession = axolotlSession
        this.uid = UUID.fromString(compoundBinaryTag.getString("uid"))


    }


}