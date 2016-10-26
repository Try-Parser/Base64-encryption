/*
   Author: Franklin Embate
   Date-created: October-26-2016
   Description:
      Base64 for scala encoding encryption and decryption.
   Note:
      Those who want to use this code please feel free to use this is for all,
      and if you found out something wrong please feel free to contact me asap
      send the issue to franklin.embate@gmail.com or contact me in facebook
      link: https://www.facebook.com/MEET.YOUR.MAST3R
   Repository:
      https://github.com/Try-Parser/Base64-encryption
*/

object Base64 {

  def main(args:Array[String]):Unit = {
    println(encrypt("ManMan1"))
    println(decrypt("dSyUd30D4M"))
    println(code("ManMan13", "Ud30D4M"))
  }

  private[this] final val base: String = new String((('a' to 'z') ++ ('A' to 'Z') ++ ('0' to '9')).toArray) + "+/"

  private[this] final def convert(str: String): String = {
    val sb = new StringBuilder
    str.replaceAll(" ", "").toCharArray.map(r => String.format("%7s", r.toByte.toBinaryString).replace(' ', '0'))
       .addString(sb).toString
  }

  @scala.annotation.tailrec
  private[this] final def bicrypt(src: String, res:String , index: Int): String = {
     if((index+1) == src.length)
      if(((res + base.indexOf(src(index)).toBinaryString).length%7) == 0) res + base.indexOf(src(index)).toBinaryString
      else res + String.format("%5s", base.indexOf(src(index)).toBinaryString).replace(' ', '0')
     else bicrypt(src, res + String.format("%6s", base.indexOf(src(index)).toBinaryString).replace(' ', '0'), index + 1)
  }

  @scala.annotation.tailrec
  private[this] final def rec(d: Int, t: Int, result: String, source: String, rem: Int): String = {
    println(source)
    if(d == (source.length - rem) || (rem == 0 && d == source.length)) {
      if(rem == 0) result
      else base(BigInt(source.drop(d).take(rem), 2).toByte) + result
    } else rec(d+6, t, base(BigInt(source.drop(d).take(t), 2).toByte) + result , source, rem)

  }

  @scala.annotation.tailrec
  private[this] final def dec(d: Int, t: Int, result: String, source: String):String = {
    println(source)
    if(d == source.length || ((source.length % 7) != 0 && d >= source.length)) result
    else { dec(d+7, t, result + BigInt(source.drop(d).take(t), 2).toByte.toChar, source) }
  }

  final def code(plain_text: String, hash_text: String):Boolean = {
   if(encrypt(plain_text) == hash_text)  return true
   return false
  }

  final def decrypt(encoded_string: String): String = dec(0,7, "", bicrypt(encoded_string.reverse, "", 0))
  final def encrypt(binary_string: String): String = rec(0, 6, "", convert(binary_string), convert(binary_string).length % 6)
}
