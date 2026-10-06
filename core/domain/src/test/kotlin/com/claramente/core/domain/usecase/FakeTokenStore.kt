package com.claramente.core.domain.usecase

import com.claramente.core.data.contract.ITokenStore
import com.claramente.core.model.auth.AuthTokens

internal class FakeTokenStore(private var tokens: AuthTokens? = null) : ITokenStore {
    val calls = mutableListOf<String>()

    override fun load(): AuthTokens? {
        calls += "load"
        return tokens
    }

    override fun save(tokens: AuthTokens) {
        calls += "save"
        this.tokens = tokens
    }

    override fun clear() {
        calls += "clear"
        tokens = null
    }

    fun current(): AuthTokens? = tokens
}
