package eu.mshade.stone.packet.entity

import eu.mshade.axolotl.protocol.AxolotlPacketOut
import eu.mshade.enderframe.entity.Entity
import eu.mshade.mwork.binarytag.entity.CompoundBinaryTag
import eu.mshade.stone.marshal.LocationBinaryTagMarshal

class AxolotlPacketOutEntityLocation(val entity: Entity): AxolotlPacketOut {

    override fun write(compoundBinaryTag: CompoundBinaryTag) {
        compoundBinaryTag.putString("entity", entity.uniqueId.toString())
        compoundBinaryTag.putBinaryTag("location", LocationBinaryTagMarshal.serialize(entity.location))
    }
}