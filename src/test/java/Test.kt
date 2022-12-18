import eu.mshade.enderframe.mojang.chat.ChatColor
import eu.mshade.enderframe.mojang.chat.TextComponent
import eu.mshade.mwork.binarytag.BinaryTagDriver
import eu.mshade.mwork.binarytag.DeflateCompoundBinaryTag
import eu.mshade.mwork.binarytag.entity.CompoundBinaryTag
import eu.mshade.stone.marshal.mojang.TextComponentBinaryTagMarshal
import java.awt.Color
import java.io.ByteArrayInputStream
import java.io.ByteArrayOutputStream
import java.io.DataOutputStream
import java.util.Arrays

fun main() {

    println(ChatColor.of(Color(1, 2, 98)).name)
    val binaryTagDriver = BinaryTagDriver()

    val textComponent = TextComponent.of("Hello World!")
    textComponent.addExtras(TextComponent.of("Hello World!2"))

    val byteArrayOutputStream = ByteArrayOutputStream()
    val dataOutputStream = DataOutputStream(byteArrayOutputStream)

    val compoundBinaryTag = DeflateCompoundBinaryTag()
    compoundBinaryTag.putInt("packetId", 1)
    compoundBinaryTag.putInt("textPosition", 0)
    compoundBinaryTag.putString("uid", "00000000-0000-0000-0000-000000000000")
    compoundBinaryTag.putBinaryTag("message", TextComponentBinaryTagMarshal.serialize(textComponent))

    binaryTagDriver.writeCompoundBinaryTag(compoundBinaryTag, dataOutputStream)

    val finallyByteArrayOutputStream = ByteArrayOutputStream()
    val finallyDataOutputStream = DataOutputStream(finallyByteArrayOutputStream)
    finallyDataOutputStream.writeInt(byteArrayOutputStream.size())
    finallyDataOutputStream.write(byteArrayOutputStream.toByteArray())

    println("byteArrayOutputStream.size() = ${finallyByteArrayOutputStream.size()}, payload = ${Arrays.toString(byteArrayOutputStream.toByteArray())}")



    val byteArrayInputStream = ByteArrayInputStream(byteArrayOf(21, 0, 0, 0, 0, 0, -75, 120, -38, -91, -51, 75, 10, -62, 48, 24, 4, -32, -111, -6, -88, 98, 87, 94, -64, -123, -37, -126, 72, 91, -45, -75, 32, -72, -13, 10, 127, -46, -33, 26, -84, -115, -28, 1, -30, -123, -68, -121, 39, 51, 93, 120, 2, 119, -61, 12, 124, -77, -64, -20, -50, -50, 81, -53, 41, -58, -98, -97, 30, -85, -49, 91, 30, 76, 80, 38, -84, -113, -106, -7, -59, 69, 89, 102, 24, 75, -45, 53, -56, 48, -43, -98, 58, -83, 98, 90, -124, -66, 97, -37, -23, -98, -121, 62, 115, -34, -22, 27, -5, -85, 53, -95, -67, 14, -77, -111, -105, -32, 20, -7, 56, -49, 49, -119, -78, -91, 57, -128, -47, -17, 7, 127, -94, 64, -126, -27, 32, -99, -115, -45, 94, -101, 62, 54, 72, -111, 4, -35, 96, 67, -86, 41, 74, 41, 118, 57, 85, -11, 46, 47, -74, -91, -56, -91, -88, 57, 39, 65, 123, 33, 100, 93, 21, 21, 39, 72, 31, -92, 34, 127, 26, -80, 17, -66, 83, 96, 72, 40))
//    val byteArrayInputStream = ByteArrayInputStream(byteArrayOutputStream.toByteArray())
    val textComponentCompoundBinaryTag = binaryTagDriver.readCompoundBinaryTag(byteArrayInputStream)
    println("textComponent = ${TextComponentBinaryTagMarshal.deserialize(textComponentCompoundBinaryTag.getBinaryTag("message") as CompoundBinaryTag)}")


}