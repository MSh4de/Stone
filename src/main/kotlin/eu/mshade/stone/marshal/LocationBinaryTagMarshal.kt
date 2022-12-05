package eu.mshade.stone.marshal

import eu.mshade.axolotl.provider.WorldProvider
import eu.mshade.enderframe.world.Location
import eu.mshade.enderframe.world.WorldRepository
import eu.mshade.mwork.binarytag.entity.CompoundBinaryTag

object LocationBinaryTagMarshal {

    fun serialize(location: Location): CompoundBinaryTag {
        val compoundBinaryTag = CompoundBinaryTag()
        compoundBinaryTag.putString("world", location.world.name)
        compoundBinaryTag.putDouble("x", location.x)
        compoundBinaryTag.putDouble("y", location.y)
        compoundBinaryTag.putDouble("z", location.z)
        compoundBinaryTag.putFloat("yaw", location.yaw)
        compoundBinaryTag.putFloat("pitch", location.pitch)
        return compoundBinaryTag
    }

    fun deserialize(compoundBinaryTag: CompoundBinaryTag, worldProvider: WorldProvider): Location {
        val world = compoundBinaryTag.getString("world")!!
        val x = compoundBinaryTag.getDouble("x")
        val y = compoundBinaryTag.getDouble("y")
        val z = compoundBinaryTag.getDouble("z")
        val yaw = compoundBinaryTag.getFloat("yaw")
        val pitch = compoundBinaryTag.getFloat("pitch")
        return Location(worldProvider.getWorld(world), x, y, z, yaw, pitch)
    }

}