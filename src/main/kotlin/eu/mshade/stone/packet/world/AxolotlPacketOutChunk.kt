package eu.mshade.stone.packet.world

import eu.mshade.axolotl.protocol.AxolotlPacketOut
import eu.mshade.enderframe.metadata.MetadataKeyValueBufferRegistry
import eu.mshade.enderframe.world.chunk.Chunk
import eu.mshade.mwork.binarytag.entity.CompoundBinaryTag
import eu.mshade.stone.marshal.world.ChunkBinaryTagMarshal

class AxolotlPacketOutChunk(private val chunk: Chunk, private val metadataKeyValueBufferRegistry: MetadataKeyValueBufferRegistry): AxolotlPacketOut {

    override fun write(compoundBinaryTag: CompoundBinaryTag) {
        compoundBinaryTag.putBinaryTag("chunk", ChunkBinaryTagMarshal.serialize(chunk, metadataKeyValueBufferRegistry))
    }
}

class AxolotlPacketOutChunkUnload(private val chunk: Chunk): AxolotlPacketOut {

    override fun write(compoundBinaryTag: CompoundBinaryTag) {
        compoundBinaryTag.putString("world", chunk.world.name)
        compoundBinaryTag.putInt("x", chunk.x)
        compoundBinaryTag.putInt("z", chunk.z)
    }
}