package com.mvvm_starter.core.utils.measure_system


data class DistanceMetricSystemData(var ft: Int, var inches: Int) {

    override fun equals(other: Any?): Boolean {
        if (other is DistanceMetricSystemData) {
            return ft == other.ft && inches == other.inches
        }
        return super.equals(other)
    }

    override fun hashCode(): Int {
        var result = ft
        result = 31 * result + inches
        return result
    }
}