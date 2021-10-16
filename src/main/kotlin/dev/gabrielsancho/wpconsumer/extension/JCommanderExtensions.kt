package dev.gabrielsancho.wpconsumer.extension

import com.beust.jcommander.JCommander
import java.io.ByteArrayOutputStream
import java.io.PrintStream

fun JCommander.usageString(): String {
    val baos = ByteArrayOutputStream()
    val ps = PrintStream(baos)
    val old = System.out
    System.setOut(ps)

    usage()

    System.out.flush()
    System.setOut(old)

    return baos.toString()
}
