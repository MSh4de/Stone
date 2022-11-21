package eu.mshade.stone.packet.inventory

import eu.mshade.axolotl.protocol.AxolotlPacketIn
import eu.mshade.axolotl.protocol.AxolotlSession
import eu.mshade.enderframe.item.ItemStack
import eu.mshade.enderframe.metadata.MetadataKeyValueBufferRegistry
import eu.mshade.mwork.binarytag.entity.CompoundBinaryTag
import eu.mshade.stone.marshal.item.ItemStackBinaryTagMarshal

class AxolotlPacketInSetItem(val metadataKeyValueBufferRegistry: MetadataKeyValueBufferRegistry) : AxolotlPacketIn {

    lateinit var axolotlSession: AxolotlSession
    lateinit var itemStack: ItemStack
    var slot: Int = 0
    var inventoryTarget: Int = 0

    override fun read(axolotlSession: AxolotlSession, compoundBinaryTag: CompoundBinaryTag) {
        this.axolotlSession = axolotlSession
        this.slot = compoundBinaryTag.getInt("slot")
        this.inventoryTarget = compoundBinaryTag.getInt("inventoryTarget")
        this.itemStack = ItemStackBinaryTagMarshal.deserialize(
            compoundBinaryTag.getBinaryTag("itemStack") as CompoundBinaryTag,
            metadataKeyValueBufferRegistry
        )
    }
}