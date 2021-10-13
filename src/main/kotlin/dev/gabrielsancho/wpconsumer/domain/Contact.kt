package dev.gabrielsancho.wpconsumer.domain

import com.fasterxml.jackson.annotation.JsonIgnoreProperties

@JsonIgnoreProperties(ignoreUnknown = true)
class Contact {
    lateinit var formattedName: String
    lateinit var id: String
    var isBusiness: Boolean? = null
    var isEnterprise: Boolean = false
    var isMe: Boolean = false
    var isMyContact: Boolean = false
    var isWAContact: Boolean = false
    lateinit var labels: List<String>
    var msgs: List<Message>? = null
    lateinit var name: String
    var plaintextDisabled: Boolean = false
    lateinit var profilePicThumbObj: ProfilePicture
    lateinit var pushname: String
    lateinit var shortName: String
    var statusMute: Boolean = false
    lateinit var type: String
    lateinit var verifiedLevel: String
    lateinit var verifiedName: String
    var isOnline: Boolean = false
    var lastSeen: Int? = 0
}

@JsonIgnoreProperties(ignoreUnknown = true)
class ProfilePicture {
    lateinit var eurl: String
    lateinit var id: String
    lateinit var img: String
    lateinit var imgFull: String
    lateinit var tag: String
}
