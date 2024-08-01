package com.cti.displayuni.utility

import org.json.JSONObject

fun extractChartData(jsonString: String): Map<String, Map<String, List<String>>> {
    val resultMap = mutableMapOf<String, Map<String, List<String>>>()

    val jsonObject = JSONObject(jsonString)
    val resultObject = jsonObject.getJSONObject("result")

    resultObject.keys().forEach { dateKey ->
        val dateObject = resultObject.getJSONObject(dateKey)
        val dateMap = mutableMapOf<String, List<String>>()

        dateObject.keys().forEach { groupKey ->
            val groupObject = dateObject.getJSONObject(groupKey)
            val cValues = groupObject.getJSONArray("C")

            val cList = mutableListOf<String>()
            for (i in 0 until cValues.length()) {
                cList.add(cValues.getString(i))
            }
            dateMap[groupKey] = cList
        }
        resultMap[dateKey] = dateMap
    }
    return resultMap
}