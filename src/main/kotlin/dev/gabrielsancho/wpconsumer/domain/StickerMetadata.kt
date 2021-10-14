package dev.gabrielsancho.wpconsumer.domain

data class StickerMetadata(
        val author: String? = null,
        val cropPosition: CropPosition? = null,
        val keepScale: Boolean? = null,
        val pack: String? = null,
        val circle: Boolean? = null,
)

enum class CropPosition(val value: String) {
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
    ATTENTION("attention");

    companion object {
        fun fromString(value: String?): CropPosition? {
            return when (value) {
                "top" -> TOP
                "right_top" -> RIGHT_TOP
                "right" -> RIGHT
                "right_bottom" -> RIGHT_BOTTOM
                "bottom" -> BOTTOM
                "left_bottom" -> LEFT_BOTTOM
                "left" -> LEFT
                "left_top" -> LEFT_TOP
                "north" -> NORTH
                "northeast" -> NORTHEAST
                "east" -> EAST
                "southeast" -> SOUTHEAST
                "south" -> SOUTH
                "southwest" -> SOUTHWEST
                "west" -> WEST
                "northwest" -> NORTHWEST
                "center" -> CENTER
                "centre" -> CENTRE
                "entropy" -> ENTROPY
                "attention" -> ATTENTION
                else -> null
            }
        }
    }
}
