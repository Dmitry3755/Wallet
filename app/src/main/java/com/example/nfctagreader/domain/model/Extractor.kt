package com.example.nfctagreader.domain.model

import androidx.core.text.isDigitsOnly
import com.example.nfctagreader.domain.model.data_class.CardDetails

object Extractor {

    fun extractData(cardData: String): CardDetails {
        val lines = cardData.split("\n")
        val owner = extractOwner(lines)
        val number = extractNumber(lines)
        val (month, year) = extractExpiration(lines)
        return CardDetails(
            owner = owner,
            number = number,
            expirationMonth = month,
            expirationYear = year
        )
    }

    private fun extractOwner(lines: List<String>): String? {
        for (line in lines) {
            line.asIterable()
            var a = line
            var c = 90
        }
        return ""
    }
    /*  return lines
          .filter { it.contains(" ") }
          .filter { line -> line.asIterable().none { char -> char.isDigit() } }
          .maxBy { it.length }*/

    private fun extractNumber(lines: List<String>): String? {
        return lines.firstOrNull { line ->
            val subNumbers = line.split(" ")
            subNumbers.isNotEmpty() && subNumbers.flatMap { it.asIterable() }.all { it.isDigit() }
        }
    }

    private fun extractExpirationLine(lines: List<String>) =
        lines.flatMap { it.split(" ") }
            .firstOrNull { (it.length == 5 || it.length == 7) && it[2] == '/' }

    private fun extractExpiration(lines: List<String>): Pair<String?, String?> {
        val expirationLine = extractExpirationLine(lines)
        val month = expirationLine?.substring(startIndex = 0, endIndex = 2)
        val year = expirationLine?.substring(startIndex = 3)
        return Pair(month, year)
    }
}