package eu.mshade.stone.packet.world

import eu.mshade.axolotl.protocol.AxolotlPacketOut
import eu.mshade.enderframe.metadata.MetadataKeyValueBufferRegistry
import eu.mshade.enderframe.world.World
import eu.mshade.mwork.binarytag.entity.CompoundBinaryTag

class AxolotlPacketOutInitializeWorld(val world: World, private val metadataKeyValueBufferRegistry: MetadataKeyValueBufferRegistry): AxolotlPacketOut {

    override fun write(compoundBinaryTag: CompoundBinaryTag) {
        compoundBinaryTag.putBinaryTag("world",metadataKeyValueBufferRegistry.serialize(world.metadataKeyValueBucket))
    }

}