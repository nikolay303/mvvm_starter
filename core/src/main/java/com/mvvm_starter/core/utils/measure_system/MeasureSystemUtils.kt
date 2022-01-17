package com.mvvm_starter.core.utils.measure_system

import com.mvvm_starter.core.utils.toNormalText
import java.util.*
import kotlin.math.roundToInt


const val CM_IN_ONE_FT = 30.48
const val CM_IN_ONE_INCHES = 2.54
const val KM_IN_ONE_MILES = 0.621371
private const val FRACTION_INCHES = 12

const val KG_IN_ONE_LBS = 2.205


fun Locale.isMetric(): Boolean {
    return when (country.uppercase()) {
        //UK, Myanmar, Liberia, USA, Canada
        "GB", "MM", "LR", "US", "CA" -> false
        else -> true
    }
}

fun Locale.getMeasureSystem(): MeasureSystem {
    return if (isMetric()) {
        MeasureSystem.METRIC
    } else {
        MeasureSystem.IMPERIAL
    }
}

fun Number.formatHeightToUnit(): String {
    return if (Locale.getDefault().isMetric()) {
        this.toDouble().toNormalText()
    } else {
        this.cmToFtText()
    }
}

fun Number.formatDistanceToUnit(): String {
    return this.formatHeightToUnit()
}

fun Number.formatWeightToUnits(): String {
    return if (Locale.getDefault().isMetric()) {
        this.toDouble().toNormalText()
    } else {
        this.kgToLbsText()
    }
}

fun Number.cmToFtText(): String {
    val value = this.toDouble()
//    val feet = (value / CM_IN_ONE_FT).roundToInt()
//    val inches = (value / CM_IN_ONE_INCHES - (feet * FRACTION_INCHES)).roundToInt()
    val inches = (0.3937 * value).roundToInt()
    val feet = inches / FRACTION_INCHES
    val leftover = inches % FRACTION_INCHES
    return "$feet'$leftover\""
}

fun Number.convertCmToMetricSystem(): DistanceMetricSystemData {
    val value = this.toDouble()
//    val feet = (value / CM_IN_ONE_FT).roundToInt()
//    val inches = floor(value / CM_IN_ONE_INCHES - (feet * FRACTION_INCHES)).toInt()
    val inches = (0.3937 * value).roundToInt()
    val feet = inches / FRACTION_INCHES
    val leftover = inches % FRACTION_INCHES
    return DistanceMetricSystemData(feet, leftover)
}

fun Number.kmToMiles(): Double {
    return this.toDouble() * KM_IN_ONE_MILES
}

fun Number.milesToKm(): Double {
    return this.toDouble() / KM_IN_ONE_MILES
}

fun Number.kmToMilesText(): String {
    return this.kmToMiles().toNormalText(pattern = "###.#")
}

fun Number.kgToLbs(): Double {
    return this.toDouble() * KG_IN_ONE_LBS
}

fun Number.kgToLbsInt(): Int {
    return this.kgToLbs().toInt()
}

fun Number.kgToLbsText(): String {
    return this.kgToLbs().toNormalText(pattern = "###.#")
}

fun Number.lbsToKg(): Double {
    return this.toDouble() / KG_IN_ONE_LBS
}

fun Number.lbsToKgInt(): Int {
    return (this.toDouble() / KG_IN_ONE_LBS).roundToInt()
}

fun DistanceMetricSystemData.toCm(): Double {
    return (ft * CM_IN_ONE_FT) + (inches * CM_IN_ONE_INCHES)
}