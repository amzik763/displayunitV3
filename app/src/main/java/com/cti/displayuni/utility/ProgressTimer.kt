package com.cti.displayuni.utility

import kotlinx.coroutines.*
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlin.collections.set

/*class ProgressTimer(private val duration: Long) {
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
}*/

class ProgressTimer(private val duration: Long) {
    private val timers = mutableMapOf<String, Job>()
    private val progressStates = mutableMapOf<String, MutableStateFlow<Float>>()
    private val countdownStates = mutableMapOf<String, MutableStateFlow<Int>>()
    private val isTimeFinished = mutableMapOf<String, MutableStateFlow<Boolean>>()

    fun startTimer(paramId: String, onFinish: () -> Unit) {
        if (timers[paramId]?.isActive == true){
            return
        }

        val progressState = MutableStateFlow(1f)
        val countdownState = MutableStateFlow((duration / 1000).toInt())
        val isTimeFinishedUnique = MutableStateFlow(false)
        progressStates[paramId] = progressState
        countdownStates[paramId] = countdownState
        isTimeFinished[paramId] = isTimeFinishedUnique
        val timerJob = CoroutineScope(Dispatchers.Main).launch {
            val totalSeconds = (duration / 1000).toInt()
            for (second in totalSeconds downTo 0) {
                if (!isActive) break

                progressState.value = second.toFloat() / totalSeconds
                countdownState.value = second

                showLogs("PROGRESS VALUE:", progressState.value.toString())
                showLogs("COUNTDOWN VALUE:", countdownState.value.toString())

                delay(1000L)
            }
            progressState.value = 0f
            countdownState.value = 0
            onFinish()

        }
        timers[paramId] = timerJob
    }

    fun getProgress(paramId: String): StateFlow<Float> {
        return progressStates[paramId] ?: MutableStateFlow(0f)
    }

    fun stopTimer(paramId: String) {
        timers[paramId]?.cancel()
        progressStates[paramId]?.value = 0f
        countdownStates[paramId]?.value = 0
    }

    fun hasTimerStarted(paramId: String): Boolean {
        return timers[paramId]?.isActive == true
    }

    fun getCountdownProgress(paramId: String): StateFlow<Int> {
        return countdownStates[paramId] ?: MutableStateFlow(0)
    }

    fun getIsProgressFinished(paramId: String): StateFlow<Boolean> {
        return isTimeFinished[paramId] ?: MutableStateFlow(false)

    }

    fun setFalse(paramId: String) {
        isTimeFinished[paramId]?.value = true
        showLogs("TM FIN", isTimeFinished[paramId]?.value.toString())
        showLogs("TM FIN FFF", "isTimeFinished[paramId]?.value.toString()")
    }
}


