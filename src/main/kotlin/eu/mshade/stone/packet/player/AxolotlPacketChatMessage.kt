package eu.mshade.stone.packet.player

import eu.mshade.axolotl.protocol.AxolotlPacketIn
import eu.mshade.axolotl.protocol.AxolotlPacketOut
import eu.mshade.axolotl.protocol.AxolotlSession
import eu.mshade.enderframe.entity.Player
import eu.mshade.enderframe.mojang.chat.TextComponent
import eu.mshade.enderframe.mojang.chat.TextPosition
import eu.mshade.mwork.binarytag.entity.CompoundBinaryTag
import eu.mshade.stone.marshal.mojang.TextComponentBinaryTagMarshal
import java.util.*

class AxolotlPacketOutChatMessage(var player: Player, var message: String): AxolotlPacketOut {
    override fun write(compoundBinaryTag: CompoundBinaryTag) {
        compoundBinaryTag.putString("uid", player.uniqueId.toString())
        compoundBinaryTag.putString("message", message)
    }
}

class AxolotlPacketInChatMessage: AxolotlPacketIn {

    lateinit var axolotlSession: AxolotlSession
    lateinit var uid: UUID
    lateinit var textComponent: TextComponent
    lateinit var textPosition: TextPosition

    override fun read(axolotlSession: AxolotlSession, compoundBinaryTag: CompoundBinaryTag) {
        this.axolotlSession = axolotlSession
        this.textPosition = TextPosition.from(compoundBinaryTag.getInt("textPosition"))
        this.uid = UUID.fromString(compoundBinaryTag.getString("uid"))
        this.textComponent = TextComponentBinaryTagMarshal.deserialize(compoundBinaryTag.getBinaryTag("message") as CompoundBinaryTag)
    }
}