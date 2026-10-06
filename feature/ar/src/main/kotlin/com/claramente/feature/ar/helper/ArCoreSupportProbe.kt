package com.claramente.feature.ar.helper

import com.claramente.feature.ar.state.ArSupport
import android.app.Activity
import android.content.Context
import com.google.ar.core.ArCoreApk
import kotlinx.coroutines.delay

internal object ArCoreSupportProbe {
    private const val POLL_MS = 200L
    private const val MAX_POLLS = 25

    suspend fun check(context: Context): ArSupport {
        val apk = ArCoreApk.getInstance()
        var availability = apk.checkAvailability(context)
        var polls = 0
        while (availability.isTransient && polls < MAX_POLLS) {
            delay(POLL_MS)
            availability = apk.checkAvailability(context)
            polls++
        }
        return when (availability) {
            ArCoreApk.Availability.SUPPORTED_INSTALLED -> ArSupport.READY
            ArCoreApk.Availability.SUPPORTED_APK_TOO_OLD,
            ArCoreApk.Availability.SUPPORTED_NOT_INSTALLED,
            -> ArSupport.NEEDS_INSTALL
            ArCoreApk.Availability.UNSUPPORTED_DEVICE_NOT_CAPABLE -> ArSupport.UNSUPPORTED
            else -> ArSupport.FAILED
        }
    }

    fun requestInstall(activity: Activity) {
        runCatching { ArCoreApk.getInstance().requestInstall(activity, true) }
    }
}
