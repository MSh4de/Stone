package eu.mshade.stone.marshal

import eu.mshade.enderframe.protocol.MinecraftProtocolVersion
import eu.mshade.mwork.binarytag.BinaryTag
import eu.mshade.mwork.binarytag.IntBinaryTag
import eu.mshade.mwork.binarytag.entity.CompoundBinaryTag

object MinecraftProtocolVersionBinaryTagMarshal {

    fun serialize(minecraftProtocolVersion: MinecraftProtocolVersion): BinaryTag<*> {
        return IntBinaryTag(minecraftProtocolVersion.protocolVersion)
    }

    fun deserialize(binaryTag: BinaryTag<*>): MinecraftProtocolVersion {
        return MinecraftProtocolVersion.getProtocolVersion((binaryTag as IntBinaryTag).value)
    }

}