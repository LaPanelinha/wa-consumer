package dev.gabrielsancho.wpconsumer.domain

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty

@JsonIgnoreProperties(ignoreUnknown = true)
class GroupMetadata {
    lateinit var id: String
    var creation: Long = 0
    lateinit var owner: String
    lateinit var participants: List<Participant>
    lateinit var pendingParticipants: List<Participant>
    var descTime: Long = 0
    var announce: Boolean = false
    var noFrequentlyForwarded: Boolean = false
    var ephemeralDuration: Long = 0
    var support: Boolean = false
    var uniqueShortNameMap: Any? = null
    var restrict: Boolean = false
}

class Participant {
    lateinit var id: String
    @JsonProperty("isAdmin")
    var isAdmin: Boolean = false
    @JsonProperty("isSuperAdmin")
    var isSuperAdmin: Boolean = false
}
