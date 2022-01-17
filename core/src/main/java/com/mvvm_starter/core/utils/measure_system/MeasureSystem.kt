package com.mvvm_starter.core.utils.measure_system

import java.util.*


enum class MeasureSystem {
    METRIC, IMPERIAL;

    fun isMetric(): Boolean =
        this == METRIC

    companion object {
        fun get(name: String): MeasureSystem =
            values().find { it.name == name } ?: Locale.getDefault().getMeasureSystem()

    }
}