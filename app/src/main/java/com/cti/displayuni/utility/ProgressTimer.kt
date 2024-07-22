package com.cti.displayuni.utility

import kotlinx.coroutines.*
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlin.collections.set

class ProgressTimer(private val duration: Long) {
    private val timers = mutableMapOf<String, Job>()
    private val progressStates = mutableMapOf<String, MutableStateFlow<Float>>()

    fun startTimer(paramId: String, onFinish: () -> Unit) {
        if (timers[paramId]?.isActive == true) return

        val progressState = MutableStateFlow(1f)
        progressStates[paramId] = progressState

        val timerJob = CoroutineScope(Dispatchers.Main).launch {
            val startTime = System.currentTimeMillis()
            while (isActive && System.currentTimeMillis() - startTime < duration) {
                val elapsedTime = System.currentTimeMillis() - startTime
                progressState.value = (duration - elapsedTime).toFloat() / duration
                delay(100)
            }
            progressState.value = 0f
            onFinish()
        }
        timers[paramId] = timerJob
    }

    fun getProgress(paramId: String): StateFlow<Float> {
        showLogs("PROGRESS TIMER: ", paramId + "PROGRESS" + progressStates[paramId])
        return progressStates[paramId] ?: MutableStateFlow(0f)
    }

    fun stopTimer(paramId: String) {
        timers[paramId]?.cancel()
        progressStates[paramId]?.value = 0f
        showLogs("STOPPING TIMER: ", paramId + "STOPPED")
    }

    fun hasTimerStarted(paramId: String): Boolean {
        return timers[paramId]?.isActive == true
    }
}

