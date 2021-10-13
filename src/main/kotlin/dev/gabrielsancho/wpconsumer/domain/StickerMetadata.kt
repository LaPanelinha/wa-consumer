package dev.gabrielsancho.wpconsumer.domain

data class StickerMetadata(
        val author: String? = null,
        val cropPosition: CropPosition? = null,
        val keepScale: Boolean? = null,
        val pack: String? = null,
)

enum class CropPosition(value: String) {
    TOP("top"),
    RIGHT_TOP("right top"),
    RIGHT("right"),
    RIGHT_BOTTOM("right bottom"),
    BOTTOM("bottom"),
    LEFT_BOTTOM("left bottom"),
    LEFT("left"),
    LEFT_TOP("left top"),
    NORTH("north"),
    NORTHEAST("northeast"),
    EAST("east"),
    SOUTHEAST("southeast"),
    SOUTH("south"),
    SOUTHWEST("southwest"),
    WEST("west"),
    NORTHWEST("northwest"),
    CENTER("center"),
    CENTRE("centre"),
    ENTROPY("entropy"),
    ATTENTION("attention"),
}
