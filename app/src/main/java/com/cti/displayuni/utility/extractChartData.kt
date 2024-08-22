package com.cti.displayuni.utility

import com.google.gson.Gson
import com.google.gson.JsonObject
import com.google.gson.reflect.TypeToken
import org.json.JSONObject


fun extractChartData(jsonString: String): Map<String, Map<String, List<String>>> {
    var resultMap = mutableMapOf<String, Map<String, List<String>>>()

    try {
        val gson = Gson()

        // Parse the JSON string into a JsonObject
        val jsonObject = gson.fromJson(jsonString, JsonObject::class.java)

        // Get the "result" JsonObject
        val resultObject = jsonObject.getAsJsonObject("result")

        // Create a mutable map to store the final result
//        resultMap = mutableMapOf<String, Map<String, List<String>>>()

        // Iterate over each date key in the "result" object
        for (dateKey in resultObject.keySet()) {
            val dateObject = resultObject.getAsJsonObject(dateKey)
            val dateMap = mutableMapOf<String, List<String>>()

            // Iterate over each group key in the date object
            for (groupKey in dateObject.keySet()) {
                val groupObject = dateObject.getAsJsonObject(groupKey)
                val cValues = groupObject.getAsJsonArray("A")

                // Convert the JsonArray to a List<String>
                val cList =
                    gson.fromJson<List<String>>(cValues, object : TypeToken<List<String>>() {}.type)

                dateMap[groupKey] = cList
            }

            resultMap[dateKey] = dateMap
        }
    }catch (e:Exception){

    }
    return resultMap
}




/*
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
*/

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