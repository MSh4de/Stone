package eu.mshade.stone.marshal.world

import eu.mshade.axolotl.provider.ChunkProvider
import eu.mshade.axolotl.provider.SectionProvider
import eu.mshade.enderframe.metadata.MetadataKeyValueBufferRegistry
import eu.mshade.enderframe.world.World
import eu.mshade.enderframe.world.chunk.Chunk
import eu.mshade.mwork.binarytag.BinaryTag
import eu.mshade.mwork.binarytag.BinaryTagType
import eu.mshade.mwork.binarytag.ByteArrayBinaryTag
import eu.mshade.mwork.binarytag.ZstdCompoundBinaryTag
import eu.mshade.mwork.binarytag.entity.CompoundBinaryTag
import eu.mshade.mwork.binarytag.entity.ListBinaryTag

object ChunkBinaryTagMarshal {


    fun serialize(chunk: Chunk, metadataKeyValueBufferRegistry: MetadataKeyValueBufferRegistry): CompoundBinaryTag {
        val compoundBinaryTag = CompoundBinaryTag()
        compoundBinaryTag.putInt("x", chunk.x)
        compoundBinaryTag.putInt("z", chunk.z)
        compoundBinaryTag.putBinaryTag("biomes", ByteArrayBinaryTag(chunk.biomes))
        val listBinaryTagSections = ListBinaryTag(BinaryTagType.COMPOUND)
        val listBinaryTagEntities = ListBinaryTag(BinaryTagType.COMPOUND)

        for (i in chunk.sections.indices) {
            val section = chunk.sections[i]
            if (section != null) {
                listBinaryTagSections.add(SectionBinaryTagMarshal.serialize(section, metadataKeyValueBufferRegistry))
            }
        }
        compoundBinaryTag.putBinaryTag("sections", listBinaryTagSections)
        compoundBinaryTag.putBinaryTag("entities", listBinaryTagEntities)
        return compoundBinaryTag
    }


    fun deserialize(
        binaryTag: BinaryTag<*>,
        world: World,
        metadataKeyValueBufferRegistry: MetadataKeyValueBufferRegistry,
        chunkProvider: ChunkProvider,
        sectionProvider: SectionProvider
    ): Chunk {
        val compoundBinaryTag = binaryTag as CompoundBinaryTag?

        val x = compoundBinaryTag!!.getInt("x")
        val z = compoundBinaryTag.getInt("z")
        val biome = compoundBinaryTag.getByteArray("biomes")
        val sectionBinaryTags = compoundBinaryTag.getBinaryTag("sections") as ListBinaryTag
        val entityBinaryTags = compoundBinaryTag.getBinaryTag("entities") as ListBinaryTag
        val chunk: Chunk = chunkProvider.provide(world, x, z)
        chunk.biomes = biome
        val sections = chunk.sections
        sectionBinaryTags.value.forEach { sectionBinaryTag ->
            val section =
                SectionBinaryTagMarshal.deserialize(
                    sectionBinaryTag,
                    chunk,
                    sectionProvider,
                    metadataKeyValueBufferRegistry
                )
            sections[section.y] = section
        }

        /*
    entityBinaryTags.forEach(entityBinaryTag ->{
        CompoundBinaryTag compoundBinaryTagEntity = (CompoundBinaryTag)entityBinaryTag;
        EntityType entityType = EntityType.getEntityTypeByName(compoundBinaryTagEntity.getString("entityType"));
        chunk.addEntity(binaryTagDriver.unMarshal(entityBinaryTag, entityType.getClazz()));
    });

     */return chunk
    }

}