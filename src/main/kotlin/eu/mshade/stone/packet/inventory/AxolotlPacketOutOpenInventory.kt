package eu.mshade.stone.packet.inventory

import eu.mshade.axolotl.protocol.AxolotlPacketIn
import eu.mshade.axolotl.protocol.AxolotlPacketOut
import eu.mshade.axolotl.protocol.AxolotlSession
import eu.mshade.enderframe.entity.Player
import eu.mshade.enderframe.inventory.Inventory
import eu.mshade.mwork.binarytag.entity.CompoundBinaryTag
import java.util.*

class AxolotlPacketInOpenInventory: AxolotlPacketIn {

    lateinit var axolotlSession: AxolotlSession
    var inventory: Inventory? = null
    var playerUid: UUID? = null

    override fun read(axolotlSession: AxolotlSession, compoundBinaryTag: CompoundBinaryTag) {
        this.axolotlSession = axolotlSession
        val uid = compoundBinaryTag.getString("inventoryUid")
        this.playerUid = UUID.fromString(compoundBinaryTag.getString("playerUid"))
    }
}

class AxolotlPacketOutOpenInventory(val player: Player, val inventory: Inventory): AxolotlPacketOut {

    override fun write(compoundBinaryTag: CompoundBinaryTag) {
        compoundBinaryTag.putString("playerUid", player.uniqueId.toString())
    }
}
