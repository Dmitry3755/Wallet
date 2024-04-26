package com.example.data.utils

import com.example.domain.entities.CardDetails
import com.example.domain.entities.User

fun parserData(cardData: String, user : User): CardDetails {
    val lines = cardData.split("\n")
    val number = parserNumber(lines)

    return CardDetails(
        uid = user.uid,
        number = number,
        null
    )
}

private fun parserNumber(lines: List<String>): String? {
    return lines.firstOrNull { line ->
        val subNumbers = line.split(" ")
        subNumbers.isNotEmpty() && subNumbers.flatMap { it.asIterable() }.all { it.isDigit() }
    }
}

/*private fun extractOwner(lines: List<String>): String? {
    for (line in lines) {
        line.asIterable()
        var a = line
        var c = 90
    }
    return ""
}
/ return lines
      .filter { it.contains(" ") }
      .filter { line -> line.asIterable().none { char -> char.isDigit() } }
      .maxBy { it.length }


      private fun extractExpiration(lines: List<String>): Pair<String?, String?> {
    val expirationLine = extractExpirationLine(lines)
    val month = expirationLine?.substring(startIndex = 0, endIndex = 2)
    val year = expirationLine?.substring(startIndex = 3)
    return Pair(month, year)
}

     private fun extractExpirationLine(lines: List<String>) =
    lines.flatMap { it.split(" ") }
        .firstOrNull { (it.length == 5 || it.length == 7) && it[2] == '/' }
      */





