package dev.gabrielsancho.wpconsumer.domain

import com.fasterxml.jackson.annotation.JsonIgnoreProperties

@JsonIgnoreProperties(ignoreUnknown = true)
class Chat {
    var archive: Boolean = false
    lateinit var changeNumberNewJid: Any
    lateinit var changeNumberOldJid: Any
    lateinit var contact: Contact
    var groupMetadata: GroupMetadata? = null
    lateinit var id: String
    lateinit var isAnnounceGrpRestrict: Any
    var formattedTitle: String? = null
    var canSend: Boolean? = null
    var isGroup: Boolean = false
    var isReadOnly: Boolean = false
    lateinit var kind: String
    lateinit var labels: List<String>
    lateinit var lastReceivedKey: Any
    lateinit var modifyTag: Number
    var msgs: List<Message>? = null
    lateinit var muteExpiration: Number
    var ephemeralDuration: Int = 0
    var unreadMentionCount: Int = 0
    var hasUnreadMention: Boolean = false
    var archiveAtMentionViewedInDrawer: Boolean = false
    var hasChatBeenOpened: Boolean = false
    lateinit var disappearingModeInitiator: String
    lateinit var name: String
    var notSpam: Boolean = false
    var pendingMsgs: Boolean = false
    lateinit var pin: Number
    lateinit var presence: Any
    lateinit var t: Number
    lateinit var unreadCount: Number
    var ack: MessageAck? = null
}
