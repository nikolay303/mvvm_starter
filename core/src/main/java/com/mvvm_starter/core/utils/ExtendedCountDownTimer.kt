package com.mvvm_starter.core.utils

import android.os.CountDownTimer


abstract class ExtendedCountDownTimer(
    private var millisInFuture: Long,
    private val countDownInterval: Long,
) {

    private var timer: CountDownTimer? = null

    /**
     * Callback fired on regular interval.
     * @param millisUntilFinished The amount of time until finished.
     */
    abstract fun onTick(millisUntilFinished: Long)

    /**
     * Callback fired when the time is up.
     */
    abstract fun onFinish()

    fun start() {
        timer = object : CountDownTimer(millisInFuture, countDownInterval) {
            override fun onTick(millisUntilFinished: Long) {
                millisInFuture -= countDownInterval
                this@ExtendedCountDownTimer.onTick(millisUntilFinished)
            }

            override fun onFinish() {
                this@ExtendedCountDownTimer.onFinish()
            }
        }
        timer?.start()
    }

    fun pause() {
        cancel()
    }

    fun resume() {
        start()
    }

    fun cancel() {
        timer?.cancel()
        timer = null
    }
}

fun scheduleTimer(
    millisInFuture: Long,
    countDownInterval: Long = millisInFuture,
    onTick: (Long) -> Unit = {},
    onFinish: () -> Unit,
): ExtendedCountDownTimer {
    return object : ExtendedCountDownTimer(millisInFuture, countDownInterval) {
        override fun onFinish() {
            onFinish.invoke()
        }

        override fun onTick(millisUntilFinished: Long) {
            onTick.invoke(millisUntilFinished)
        }
    }
}