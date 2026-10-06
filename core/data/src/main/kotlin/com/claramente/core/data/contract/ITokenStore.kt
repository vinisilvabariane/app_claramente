package com.claramente.core.data.contract

import com.claramente.core.model.auth.AuthTokens

interface ITokenStore {
    fun load(): AuthTokens?

    fun save(tokens: AuthTokens)

    fun clear()
}
