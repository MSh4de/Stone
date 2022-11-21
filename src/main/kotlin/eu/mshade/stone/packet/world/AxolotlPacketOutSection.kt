package eu.mshade.stone.packet.world

import eu.mshade.axolotl.protocol.AxolotlPacketOut
import eu.mshade.enderframe.metadata.MetadataKeyValueBufferRegistry
import eu.mshade.enderframe.world.chunk.Section
import eu.mshade.mwork.binarytag.entity.CompoundBinaryTag
import eu.mshade.stone.marshal.world.SectionBinaryTagMarshal

class AxolotlPacketOutSection(private val section: Section, private val metadataKeyValueBufferRegistry: MetadataKeyValueBufferRegistry): AxolotlPacketOut {

    override fun write(compoundBinaryTag: CompoundBinaryTag) {
        compoundBinaryTag.putBinaryTag("section", SectionBinaryTagMarshal.serialize(section, metadataKeyValueBufferRegistry))
    }

}