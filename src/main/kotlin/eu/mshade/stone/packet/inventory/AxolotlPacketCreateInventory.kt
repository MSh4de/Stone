package eu.mshade.stone.packet.inventory

import eu.mshade.axolotl.protocol.AxolotlPacketIn
import eu.mshade.axolotl.protocol.AxolotlPacketOut
import eu.mshade.axolotl.protocol.AxolotlSession
import eu.mshade.enderframe.inventory.Inventory
import eu.mshade.enderframe.metadata.MetadataKeyValueBufferRegistry
import eu.mshade.mwork.binarytag.entity.CompoundBinaryTag
import eu.mshade.stone.StoneAxolotlSession
import eu.mshade.stone.marshal.inventory.InventoryBinaryTagMarshal

class AxolotlPacketInCreateInventory: AxolotlPacketIn {

    lateinit var axolotlSession: AxolotlSession
    lateinit var inventory: Inventory

    override fun read(axolotlSession: AxolotlSession, compoundBinaryTag: CompoundBinaryTag) {
        this.axolotlSession = axolotlSession
        axolotlSession as StoneAxolotlSession
        this.inventory = InventoryBinaryTagMarshal.deserialize(compoundBinaryTag.getBinaryTag("inventory") as CompoundBinaryTag, axolotlSession.metadataKeyValueBufferRegistry)
    }
}


class AxolotlPacketOutCreateInventory(val inventory: Inventory, val metadataKeyValueBufferRegistry: MetadataKeyValueBufferRegistry): AxolotlPacketOut {

    override fun write(compoundBinaryTag: CompoundBinaryTag) {
        compoundBinaryTag.putBinaryTag("inventory", InventoryBinaryTagMarshal.serialize(inventory, metadataKeyValueBufferRegistry))
    }

}