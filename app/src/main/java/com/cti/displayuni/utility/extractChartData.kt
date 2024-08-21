package com.cti.displayuni.utility

import org.json.JSONObject

fun extractChartData(jsonString: String): Map<String, Map<String, List<String>>> {
    val resultMap = mutableMapOf<String, Map<String, List<String>>>()

    val jsonObject = JSONObject(jsonString)
    val resultObject = jsonObject.getJSONObject("result")

    resultObject.keys().forEach { dateKey ->
        val dateKeyStr = dateKey as String
        val dateObject = resultObject.getJSONObject(dateKeyStr)
        val dateMap = mutableMapOf<String, List<String>>()

        dateObject.keys().forEach { groupKey ->
            val groupKeyStr = groupKey as String
            val groupObject = dateObject.getJSONObject(groupKeyStr)
            val cValues = groupObject.getJSONArray("A")

            val cList = mutableListOf<String>()
            for (i in 0 until cValues.length()) {
                cList.add(cValues.getString(i))
            }

            dateMap[groupKeyStr] = cList
        }

        resultMap[dateKeyStr] = dateMap
    }

    return resultMap
}

fun calculateAverageData(resultMap: Map<String, Map<String, List<String>>>): Map<String, Float> {
    val dateData = mutableMapOf<String, Float>()

    for ((date, groupMap) in resultMap) {
        // Flatten all the values from the groupMap into a single list of Float
        val allValues = groupMap.values.flatten().map { it.toFloat() }

        // Calculate the average
        val average = allValues.average().toFloat()

        // Store the result in dateData map
        dateData[date] = average
    }

    return dateData
}