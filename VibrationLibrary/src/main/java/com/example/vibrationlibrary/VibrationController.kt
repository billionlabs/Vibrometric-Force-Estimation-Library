package com.example.vibrationlibrary

import android.content.Context
import android.os.VibrationEffect
import android.os.Vibrator
import androidx.core.content.ContextCompat

class VibrationController(
    private val context: Context
) {
    var vibrator: Vibrator? = null
    var isVibrating: Boolean = false

    init {
        vibrator = ContextCompat.getSystemService(context, Vibrator::class.java)
        if (vibrator == null) {
            throw NullPointerException("Vibrator service is not available on this device.")
        }
    }


    /**
     * TODO Can pass in device and return the specified vibrationeffect -> what if we want multiple vibration effect in a single device?
     */
    fun makeDeviceDefaultEffect(model: String) : VibrationEffect {
        return when (model) {
            "Pixel 4" -> VibrationEffect.createOneShot(1000 * 100, 255)
            "Pixel 8" -> VibrationEffect.createWaveform(longArrayOf(40 * 1000), intArrayOf(255), 0)
            else -> VibrationEffect.createWaveform(longArrayOf(1000 * 1), intArrayOf(255), 0)
        }
    }

    fun makeNewEffect() : VibrationEffect {
        return VibrationEffect.createOneShot(1000*10, 255)
    }

    fun startVibration(vibe: VibrationEffect) {
        checkVibrator()
        vibrator?.vibrate(vibe)
        isVibrating = true
    }

    fun cancelVibration() {
        checkVibrator()
        vibrator?.cancel()
        isVibrating = false
    }

    private fun checkVibrator(){
        if(vibrator == null){
            throw NullPointerException("Vibrator has not been initialized: Use setVibrator() to initialize your vibrator")
        }
    }
}