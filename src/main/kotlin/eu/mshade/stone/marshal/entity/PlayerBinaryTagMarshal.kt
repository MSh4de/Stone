package eu.mshade.stone.marshal.entity

import eu.mshade.enderframe.entity.Player
import eu.mshade.enderframe.metadata.MetadataKeyValueBufferRegistry
import eu.mshade.mwork.binarytag.BinaryTag
import eu.mshade.mwork.binarytag.entity.CompoundBinaryTag
import eu.mshade.stone.marshal.InetSocketAdressBinaryTagMarshal
import eu.mshade.stone.marshal.LocationBinaryTagMarshal
import eu.mshade.stone.marshal.MinecraftProtocolVersionBinaryTagMarshal
import eu.mshade.stone.marshal.mojang.GameProfileBinaryTagMarshal

object PlayerBinaryTagMarshal {

    fun serialize(player: Player, metadataKeyValueBufferRegistry: MetadataKeyValueBufferRegistry): BinaryTag<*>{
        val compoundBinaryTag = CompoundBinaryTag()
        compoundBinaryTag.putBinaryTag("gameProfile", GameProfileBinaryTagMarshal.serialize(player.gameProfile))
        compoundBinaryTag.putBinaryTag("minecraftProtocolVersion", MinecraftProtocolVersionBinaryTagMarshal.serialize(player.minecraftProtocolVersion))
        compoundBinaryTag.putBinaryTag("inetSocketAddress", InetSocketAdressBinaryTagMarshal.serialize(player.inetSocketAddress))
        compoundBinaryTag.putBinaryTag("metadataKeyValueBucket", metadataKeyValueBufferRegistry.serialize(player.metadataKeyValueBucket))
        compoundBinaryTag.putBinaryTag("location", LocationBinaryTagMarshal.serialize(player.location))
        return compoundBinaryTag
    }

}