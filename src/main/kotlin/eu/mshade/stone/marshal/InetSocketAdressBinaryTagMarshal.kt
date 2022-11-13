package eu.mshade.stone.marshal

import eu.mshade.mwork.binarytag.BinaryTag
import eu.mshade.mwork.binarytag.entity.CompoundBinaryTag
import java.net.InetSocketAddress
import java.net.SocketAddress

object InetSocketAdressBinaryTagMarshal {

    fun serialize(socketAddress: InetSocketAddress): BinaryTag<*> {
        val compoundBinaryTag = CompoundBinaryTag()
        compoundBinaryTag.putString("ip", socketAddress.hostName)
        compoundBinaryTag.putInt("port", socketAddress.port)
        return compoundBinaryTag
    }

    fun deserialize(binaryTag: BinaryTag<*>): SocketAddress {
        val compoundBinaryTag = binaryTag as CompoundBinaryTag
        return InetSocketAddress.createUnresolved(compoundBinaryTag.getString("ip"), compoundBinaryTag.getInt("port"))
    }

}