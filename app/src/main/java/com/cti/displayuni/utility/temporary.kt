package com.cti.displayuni.utility

fun main() {
    println(extractPattern("G01 F02 L31 S12"))
}

fun extractPattern(input: String): String {
    try {
        // Define the regular expression pattern
        val pattern = """(\w+\d+ F\d+ L\d+)""".toRegex()

        // Find the match in the input string
        val matchResult = pattern.find(input)

        // Return the matched group or an empty string if no match found
        return matchResult?.value ?: ""
    } catch (e: Exception) {
        return ""
    }
}