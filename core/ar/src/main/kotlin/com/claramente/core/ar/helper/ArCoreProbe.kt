package com.claramente.core.ar.helper

import android.content.Context
import com.claramente.core.ar.model.ArMode
import com.google.ar.core.ArCoreApk

object ArCoreProbe {
    fun mode(context: Context): ArMode {
        val availability = ArCoreApk.getInstance().checkAvailability(context)
        return when {
            availability.isTransient -> ArMode.CHECKING
            availability.isSupported -> ArMode.AR
            else -> ArMode.VIEWER
        }
    }
}
