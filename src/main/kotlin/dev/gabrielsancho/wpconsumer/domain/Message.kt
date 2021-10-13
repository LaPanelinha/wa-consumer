package dev.gabrielsancho.wpconsumer.domain

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty

@JsonIgnoreProperties(ignoreUnknown = true)
class Message {
    lateinit var selectedButtonId: String
    lateinit var id: String
    lateinit var body: String
    lateinit var type: MessageType
    var filehash: String? = null
    var mimetype: String? = null
    var lat: String? = null
    var lng: String? = null
    var loc: String? = null
    var t: Int = 0
    lateinit var notifyName: String
    lateinit var from: String
    lateinit var to: String
    lateinit var self: SelfType
    var duration: String? = null
    lateinit var ack: MessageAck
    var invis: Boolean = false
    var isNewMsg: Boolean? = null
    var star: Boolean = false
    var recvFresh: Boolean = false
    var broadcast: Boolean = false
    var isForwarded: Boolean = false
    lateinit var labels: List<String>
    lateinit var mentionedJidList: List<String>
    var caption: String? = null
    lateinit var sender: Contact
    var timestamp: Int = 0
    var filePath: String? = null
    lateinit var content: String
    var isGroupMsg: Boolean = false
    var isMMS: Boolean = false
    var isMedia: Boolean = false
    var isNotification: Boolean = false
    var isPSA: Boolean = false
    var fromMe: Boolean = false
    lateinit var chat: Chat
    lateinit var chatId: String
    lateinit var author: String
    lateinit var deprecatedMms3Url: String
    var quotedMsg: Message? = null
    var quotedMsgObj: Message? = null
    lateinit var shareDuration: Number
    var isAnimated: Boolean = false
    var cloudUrl: String? = null
    lateinit var buttons: List<Button>
    var listResponse: Row? = null
    var list: MessageList? = null
    var contentDecrypted: String? = null
}

class MessageList {
    lateinit var sections: List<Section>
    lateinit var title: String
    lateinit var description: String
    lateinit var buttonText: String
}

enum class SelfType {
    @JsonProperty("in")
    IN,

    @JsonProperty("out")
    OUT
}

enum class MessageAck {
    @JsonProperty("-1")
    ACK_ERROR,

    @JsonProperty("0")
    ACK_PENDING,

    @JsonProperty("1")
    ACK_SERVER,

    @JsonProperty("2")
    ACK_DEVICE,

    @JsonProperty("3")
    ACK_READ,

    @JsonProperty("4")
    ACK_PLAYED,
}
