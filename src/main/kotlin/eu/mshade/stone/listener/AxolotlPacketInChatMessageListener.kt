package eu.mshade.stone.listener

import eu.mshade.axolotl.Axolotl
import eu.mshade.axolotl.event.ChatMessageAxolotlEvent
import eu.mshade.mwork.event.EventListener
import eu.mshade.stone.packet.player.AxolotlPacketInChatMessage

class AxolotlPacketInChatMessageListener : EventListener<AxolotlPacketInChatMessage>{

    override fun onEvent(event: AxolotlPacketInChatMessage) {
        Axolotl.eventBus.publish(ChatMessageAxolotlEvent(event.axolotlSession, event.uid, event.textComponent, event.textPosition))
    }

}