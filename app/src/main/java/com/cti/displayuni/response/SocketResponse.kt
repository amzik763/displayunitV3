package com.cti.displayuni.response

import org.json.JSONObject

data class StationCspDataStatus(
    val statusList: List<Map<String, Boolean>>
)

fun parseStationCspDataStatus(jsonObject: JSONObject): StationCspDataStatus {
    val jsonArray = jsonObject.getJSONArray("station_csp_data_status")
    val statusList = mutableListOf<Map<String, Boolean>>()

    for (i in 0 until jsonArray.length()) {
        val item = jsonArray.getJSONObject(i)
        val map = mutableMapOf<String, Boolean>()

        // Extract keys and values from JSONObject
        val keys = item.keys()
        while (keys.hasNext()) {
            val key = keys.next() as String
            map[key] = item.getBoolean(key)
        }

        statusList.add(map)
    }

    return StationCspDataStatus(statusList)
}

fun getValueForKey(dataStatus: StationCspDataStatus, key: String): Boolean? {
    for (status in dataStatus.statusList) {
        if (status.containsKey(key)) {
            return status[key]
        }
    }
    return null
}