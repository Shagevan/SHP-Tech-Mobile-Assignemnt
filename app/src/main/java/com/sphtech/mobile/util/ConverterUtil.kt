package com.sphtech.mobile.util

import com.sphtech.mobile.data.DataUsageYearly
import com.sphtech.mobile.data.MobileData


object ConverterUtil {

    fun convertMobileDataUsageToYearly(response: MobileData): List<DataUsageYearly> {
        var listDataUsageYearly: MutableList<DataUsageYearly> = mutableListOf()
        try {
            val byLength = response.result.records.filter { it.quarter.split("-")[0].toInt() in 2008..2018 }
                .groupBy { it.quarter.split("-")[0]}
            byLength.forEach {
                val dataUsageYearly = DataUsageYearly(it.key, it.key.toInt(), "", false,
                    ArrayList(),""
                )
                var yearVolumeOfMobileData = 0f
                var quarterVolumeOfMobileData = 0f
                var isDecreased = false
                it.value.forEach {
                    if (quarterVolumeOfMobileData > it.volume_of_mobile_data.toFloat()) {
                        isDecreased = true
                        dataUsageYearly.decreasedQuarter = it.quarter.split("-")[1]

                    }
                    quarterVolumeOfMobileData = it.volume_of_mobile_data.toFloat()

                    yearVolumeOfMobileData += it.volume_of_mobile_data.toFloat()

                    dataUsageYearly.quartiles.add(it.volume_of_mobile_data)
                }
                dataUsageYearly.yearVolumeOfMobileData = yearVolumeOfMobileData.toString()
                dataUsageYearly.isDecreased = isDecreased
                listDataUsageYearly.add(dataUsageYearly)
            }
        } catch (e: Exception) {
        }

        return listDataUsageYearly
    }

}
