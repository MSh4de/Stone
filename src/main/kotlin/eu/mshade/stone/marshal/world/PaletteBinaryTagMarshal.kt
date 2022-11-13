package eu.mshade.stone.marshal.world

import eu.mshade.enderframe.metadata.MetadataKeyValueBufferRegistry
import eu.mshade.enderframe.item.Material
import eu.mshade.enderframe.world.block.Block
import eu.mshade.enderframe.world.chunk.Palette
import eu.mshade.mwork.binarytag.BinaryTag
import eu.mshade.mwork.binarytag.BinaryTagType
import eu.mshade.mwork.binarytag.entity.CompoundBinaryTag
import eu.mshade.mwork.binarytag.entity.ListBinaryTag

object PaletteBinaryTagMarshal {

    fun serialize(palette: Palette, metadataKeyValueBufferRegistry: MetadataKeyValueBufferRegistry): BinaryTag<*> {
        val paletteCompound = CompoundBinaryTag()
        val blockList = ListBinaryTag(BinaryTagType.COMPOUND)
        palette.blockById.forEach { (id, block) ->
            val blockCompound = CompoundBinaryTag()
            blockCompound.putInt("blockId", id);
            blockCompound.putInt("materialId", block.getMaterialKey().id);
            blockCompound.putInt("count", palette.getCount(id));
            blockCompound.putBinaryTag(
                "metadataKeyValueBucket",
                metadataKeyValueBufferRegistry.serialize(block.getMetadataKeyValueBucket())
            )
            blockList.add(blockCompound)
        }
        paletteCompound.putBinaryTag("blocks", blockList)
        return paletteCompound
    }

    fun deserialize(binaryTag: BinaryTag<*>, metadataKeyValueBufferRegistry: MetadataKeyValueBufferRegistry): Palette {
        val paletteCompound = binaryTag as CompoundBinaryTag
        val blockList = paletteCompound.getBinaryTag("blocks") as ListBinaryTag
        val palette = Palette()
        blockList.value.forEach {
            val blockCompound = it as CompoundBinaryTag
            val blockId = blockCompound.getInt("blockId")
            val materialId = blockCompound.getInt("materialId")
            val count = blockCompound.getInt("count")
            val metadata = blockCompound.getBinaryTag("metadataKeyValueBucket") as CompoundBinaryTag
            val block = Block(Material.fromId(materialId), metadataKeyValueBufferRegistry.deserialize(metadata))
            palette.setBlock(blockId, count, block)
        }
        return palette
    }

}