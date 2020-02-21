package com.sphtech.mobile.data

data class DataUsageYearly(
        var year: String,
        val id: Int,
        var yearVolumeOfMobileData: String,
        var isDecreased: Boolean,
        val quartiles: ArrayList<String>,
        var decreasedQuarter: String
)