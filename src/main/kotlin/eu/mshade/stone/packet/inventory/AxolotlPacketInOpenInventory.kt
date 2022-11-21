package eu.mshade.stone.packet.inventory

import eu.mshade.axolotl.protocol.AxolotlPacketIn
import eu.mshade.axolotl.protocol.AxolotlSession
import eu.mshade.enderframe.inventory.Inventory
import eu.mshade.enderframe.metadata.MetadataKeyValueBufferRegistry
import eu.mshade.mwork.binarytag.entity.CompoundBinaryTag
import eu.mshade.stone.marshal.item.InventoryBinaryTagMarshal

class AxolotlPacketInOpenInventory(val metadataKeyValueBufferRegistry: MetadataKeyValueBufferRegistry): AxolotlPacketIn {

    lateinit var axolotlSession: AxolotlSession
    lateinit var inventory: Inventory

    override fun read(axolotlSession: AxolotlSession, compoundBinaryTag: CompoundBinaryTag) {
        this.axolotlSession = axolotlSession
        this.inventory = InventoryBinaryTagMarshal.deserialize(compoundBinaryTag.getBinaryTag("inventory") as CompoundBinaryTag, metadataKeyValueBufferRegistry)
    }
}