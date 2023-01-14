package eu.mshade.stone.marshal.inventory

import eu.mshade.enderframe.item.ItemStack
import eu.mshade.enderframe.item.Material
import eu.mshade.enderframe.metadata.MetadataKeyValueBufferRegistry
import eu.mshade.mwork.binarytag.entity.CompoundBinaryTag

object ItemStackBinaryTagMarshal {

    fun serialize(itemStack: ItemStack?, metadataKeyValueBufferRegistry: MetadataKeyValueBufferRegistry): CompoundBinaryTag {
        val compoundBinaryTag = CompoundBinaryTag()
        itemStack ?: return compoundBinaryTag

        compoundBinaryTag.putInt("type", itemStack.material.id)
        compoundBinaryTag.putInt("amount", itemStack.amount)
        compoundBinaryTag.putInt("durability", itemStack.durability)
        compoundBinaryTag.putBinaryTag("metadata", metadataKeyValueBufferRegistry.serialize(itemStack.metadataKeyValueBucket))

        return compoundBinaryTag
    }

    fun deserialize(compoundBinaryTag: CompoundBinaryTag?, metadataKeyValueBufferRegistry: MetadataKeyValueBufferRegistry): ItemStack? {
        if (compoundBinaryTag == null || compoundBinaryTag.isEmpty()) return null
        val type = compoundBinaryTag.getInt("type")
        val amount = compoundBinaryTag.getInt("amount")
        val durability = compoundBinaryTag.getInt("durability")
        val metadata = metadataKeyValueBufferRegistry.deserialize(compoundBinaryTag.getBinaryTag("metadata") as CompoundBinaryTag)

        return ItemStack(Material.fromId(type), amount, durability, metadata)
    }

}