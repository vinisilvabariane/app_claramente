package com.claramente.core.data.store

import android.content.Context
import androidx.core.content.edit
import com.claramente.core.data.contract.ITokenStore
import com.claramente.core.model.auth.AuthTokens

class PrefsTokenStore(context: Context) : ITokenStore {
    private val prefs = context.getSharedPreferences("claramente.auth", Context.MODE_PRIVATE)

    override fun load(): AuthTokens? {
        val token = prefs.getString("token", null) ?: return null
        val refreshToken = prefs.getString("refreshToken", null) ?: return null
        val refreshExpires = prefs.getString("refreshExpires", null) ?: ""
        return AuthTokens(token, refreshToken, refreshExpires)
    }

    override fun save(tokens: AuthTokens) {
        prefs.edit {
            putString("token", tokens.token)
            putString("refreshToken", tokens.refreshToken)
            putString("refreshExpires", tokens.refreshExpires)
        }
    }

    override fun clear() {
        prefs.edit { clear() }
    }
}
