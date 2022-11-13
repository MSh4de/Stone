package eu.mshade.stone.marshal.world

import eu.mshade.enderframe.world.Difficulty
import eu.mshade.enderframe.world.Dimension
import eu.mshade.enderframe.world.LevelType
import eu.mshade.mwork.binarytag.BinaryTag
import eu.mshade.mwork.binarytag.StringBinaryTag
import eu.mshade.mwork.binarytag.entity.CompoundBinaryTag

object WorldLevelBinaryTagMarshal {

    fun readDimension(binaryTag: BinaryTag<*>): Dimension {
        return Dimension.valueOf(binaryTag.value as String)
    }

    fun writeDimension(dimension: Dimension): BinaryTag<*> {
        return StringBinaryTag(dimension.name)
    }

    fun readDifficulty(binaryTag: BinaryTag<*>): Difficulty {
        return Difficulty.valueOf(binaryTag.value as String)
    }

    fun writeDifficulty(difficulty: Difficulty): BinaryTag<*> {
        return StringBinaryTag(difficulty.name)
    }


    fun readLevelType(binaryTag: BinaryTag<*>): LevelType {
        return LevelType.valueOf(binaryTag.value as String)
    }

    fun writeLevelType(levelType: LevelType): BinaryTag<*> {
        return StringBinaryTag(levelType.name)
    }

}