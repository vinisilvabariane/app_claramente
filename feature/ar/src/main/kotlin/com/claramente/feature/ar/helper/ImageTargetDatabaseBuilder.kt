package com.claramente.feature.ar.helper

import com.claramente.feature.ar.state.ImageTarget
import android.content.Context
import android.graphics.BitmapFactory
import com.google.ar.core.AugmentedImageDatabase
import com.google.ar.core.Session

internal object ImageTargetDatabaseBuilder {
    fun build(context: Context, session: Session, targets: List<ImageTarget>): AugmentedImageDatabase {
        val database = AugmentedImageDatabase(session)
        targets.forEach { target ->
            val bitmap = context.assets.open(target.assetPath).use { BitmapFactory.decodeStream(it) }
            if (bitmap != null) {
                runCatching { database.addImage(target.name, bitmap, target.widthMeters) }
                bitmap.recycle()
            }
        }
        return database
    }
}
