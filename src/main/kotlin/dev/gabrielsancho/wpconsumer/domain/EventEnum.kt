package dev.gabrielsancho.wpconsumer.domain

import com.fasterxml.jackson.annotation.JsonProperty

enum class EventEnum {
    @JsonProperty("onMessage")
    MESSAGE,

    @JsonProperty("onAnyMessage")
    ANY_MESSAGE,

    @JsonProperty("onMessageDeleted")
    MESSAGE_DELETED,

    @JsonProperty("onAck")
    ACK,

    @JsonProperty("onAddedToGroup")
    ADDED_TO_GROUP,

    @JsonProperty("onChatDeleted")
    CHAT_DELETED,

    @JsonProperty("onBattery")
    BATTERY,

    @JsonProperty("onChatOpened")
    CHAT_OPENED,

    @JsonProperty("onIncomingCall")
    INCOMING_CALL,

    @JsonProperty("onGlobalParticipantsChanged")
    GLOBAL_PARTICIPANTS_CHANGED,

    @JsonProperty("onChatState")
    CHAT_STATE,

    @JsonProperty("onLogout")
    LOGOUT,

    @JsonProperty("onPlugged")
    PLUGGED,

    @JsonProperty("onStateChanged")
    STATE_CHANGED,

    @JsonProperty("onButton")
    BUTTON,

    @JsonProperty("onStory")
    STORY,

    @JsonProperty("onRemovedFromGroup")
    REMOVED_FROM_GROUP,

    @JsonProperty("onContactAdded")
    CONTACT_ADDED,

    @JsonProperty("onOrder")
    ORDER,
}
