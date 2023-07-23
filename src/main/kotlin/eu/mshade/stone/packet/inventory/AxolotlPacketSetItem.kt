package eu.mshade.stone.packet.inventory

import eu.mshade.axolotl.protocol.AxolotlPacketIn
import eu.mshade.axolotl.protocol.AxolotlPacketOut
import eu.mshade.axolotl.protocol.AxolotlSession
import eu.mshade.enderframe.inventory.Inventory
import eu.mshade.enderframe.item.ItemStack
import eu.mshade.enderframe.metadata.MetadataKeyValueBufferRegistry
import eu.mshade.mwork.binarytag.entity.CompoundBinaryTag
import eu.mshade.stone.StoneAxolotlSession
import eu.mshade.stone.marshal.inventory.ItemStackBinaryTagMarshal

class AxolotlPacketInSetItem : AxolotlPacketIn {

    lateinit var axolotlSession: AxolotlSession
    var itemStack: ItemStack? = null
    var slot: Int = 0
    var inventory: Inventory? = null

    override fun read(axolotlSession: AxolotlSession, compoundBinaryTag: CompoundBinaryTag) {
        this.axolotlSession = axolotlSession
        axolotlSession as StoneAxolotlSession
        this.slot = compoundBinaryTag.getInt("slot")
        val uid = compoundBinaryTag.getString("uid")

        this.itemStack = ItemStackBinaryTagMarshal.deserialize(compoundBinaryTag.getBinaryTag("itemStack") as? CompoundBinaryTag, axolotlSession.metadataKeyValueBufferRegistry)
    }
}

class AxolotlPacketOutSetItem(private val itemStack: ItemStack, private val slot: Int, val inventory: Inventory, val metadataKeyValueBufferRegistry: MetadataKeyValueBufferRegistry) : AxolotlPacketOut {

    override fun write(compoundBinaryTag: CompoundBinaryTag) {
        compoundBinaryTag.putInt("slot", slot)
        compoundBinaryTag.putBinaryTag("itemStack", ItemStackBinaryTagMarshal.serialize(itemStack, metadataKeyValueBufferRegistry))
    }
}