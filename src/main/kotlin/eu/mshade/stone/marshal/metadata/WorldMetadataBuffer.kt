package eu.mshade.stone.marshal.metadata

import eu.mshade.enderframe.metadata.MetadataKeyValue
import eu.mshade.enderframe.metadata.MetadataKeyValueBuffer
import eu.mshade.enderframe.world.*
import eu.mshade.mwork.binarytag.BinaryTag
import eu.mshade.mwork.binarytag.BinaryTagDriver
import eu.mshade.mwork.binarytag.LongBinaryTag
import eu.mshade.mwork.binarytag.StringBinaryTag
import eu.mshade.stone.marshal.world.WorldLevelBinaryTagMarshal

class NameWorldMetadataBuffer : MetadataKeyValueBuffer {

    override fun read(binaryTag: BinaryTag<*>): MetadataKeyValue<*> {
        val stringBinaryTag = binaryTag as? StringBinaryTag
        return NameWorldMetadata(stringBinaryTag!!.value)
    }

    override fun write(metadataKeyValue: MetadataKeyValue<*>): BinaryTag<*> {
        val nameWorldMetadata = metadataKeyValue as? NameWorldMetadata
        return StringBinaryTag(nameWorldMetadata!!.metadataValue)
    }

}

class SeedWorldMetadataBuffer : MetadataKeyValueBuffer {

    override fun read(binaryTag: BinaryTag<*>): MetadataKeyValue<*> {
        val longBinaryTag = binaryTag as? LongBinaryTag
        return SeedWorldMetadata(longBinaryTag!!.value)
    }

    override fun write(metadataKeyValue: MetadataKeyValue<*>): BinaryTag<*> {
        val seedWorldMetadata = metadataKeyValue as? SeedWorldMetadata
        return LongBinaryTag(seedWorldMetadata?.metadataValue ?: 0)
    }

}

class DimensionWorldMetadataBuffer() : MetadataKeyValueBuffer {

    override fun read(binaryTag: BinaryTag<*>): MetadataKeyValue<*> {
        return DimensionWorldMetadata(WorldLevelBinaryTagMarshal.readDimension(binaryTag))
    }

    override fun write(metadataKeyValue: MetadataKeyValue<*>): BinaryTag<*> {
        return WorldLevelBinaryTagMarshal.writeDimension((metadataKeyValue as DimensionWorldMetadata).metadataValue)
    }

}

class LevelTypeWorldMetadataBuffer(): MetadataKeyValueBuffer {

    override fun read(binaryTag: BinaryTag<*>): MetadataKeyValue<*> {
        return LevelTypeWorldMetadata(WorldLevelBinaryTagMarshal.readLevelType(binaryTag))
    }

    override fun write(metadataKeyValue: MetadataKeyValue<*>): BinaryTag<*> {
        return WorldLevelBinaryTagMarshal.writeLevelType((metadataKeyValue as LevelTypeWorldMetadata).metadataValue)
    }

}

class DifficultyWorldMetadataBuffer(): MetadataKeyValueBuffer {

    override fun read(binaryTag: BinaryTag<*>): MetadataKeyValue<*> {
        return DifficultyWorldMetadata(WorldLevelBinaryTagMarshal.readDifficulty(binaryTag))
    }

    override fun write(metadataKeyValue: MetadataKeyValue<*>): BinaryTag<*> {
        return WorldLevelBinaryTagMarshal.writeDifficulty((metadataKeyValue as DifficultyWorldMetadata).metadataValue)
    }

}

class ParentWorldMetadataBuffer: MetadataKeyValueBuffer {
    override fun read(binaryTag: BinaryTag<*>): MetadataKeyValue<*> {
        return ParentWorldMetadata(WorldRepository.getWorld(binaryTag.value as String))
    }

    override fun write(metadataKeyValue: MetadataKeyValue<*>): BinaryTag<*> {
        return StringBinaryTag((metadataKeyValue.metadataValue as World).name)
    }
}