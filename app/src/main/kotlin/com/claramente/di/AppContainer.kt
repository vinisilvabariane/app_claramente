package com.claramente.di

import com.claramente.BuildConfig
import com.claramente.core.auth.controller.SessionController
import com.claramente.core.data.contract.ITokenStore
import com.claramente.core.data.store.PrefsTokenStore
import com.claramente.core.domain.contract.ILoginUseCase
import com.claramente.core.domain.contract.ILogoutUseCase
import com.claramente.core.domain.contract.IMockLoginUseCase
import com.claramente.core.domain.contract.IRestoreSessionUseCase
import com.claramente.core.domain.usecase.LoginUseCase
import com.claramente.core.domain.usecase.LogoutUseCase
import com.claramente.core.domain.usecase.MockLoginUseCase
import com.claramente.core.domain.usecase.RestoreSessionUseCase
import com.claramente.core.network.client.HttpAuthClient
import com.claramente.core.network.contract.IAuthClient
import com.claramente.core.network.http.ClaramenteHttp
import android.content.Context

class AppContainer(val context: Context) {
    val tokenStore: ITokenStore by lazy { PrefsTokenStore(context) }

    val sessions: SessionController by lazy { SessionController() }

    private val http: ClaramenteHttp by lazy {
        ClaramenteHttp(
            BuildConfig.API_BASE_URL,
            onRefreshed = { tokenStore.save(it.snapshot()) },
            onRejected = {
                tokenStore.clear()
                sessions.clear()
            },
        )
    }

    val authClient: IAuthClient by lazy { HttpAuthClient(http) }

    val restoreSession: IRestoreSessionUseCase by lazy { RestoreSessionUseCase(authClient, tokenStore, sessions) }

    val login: ILoginUseCase by lazy { LoginUseCase(authClient, tokenStore, sessions) }

    val mockLogin: IMockLoginUseCase by lazy { MockLoginUseCase(tokenStore, sessions) }

    val logout: ILogoutUseCase by lazy { LogoutUseCase(authClient, tokenStore, sessions) }

    val mockAuthEnabled: Boolean = BuildConfig.MOCK_AUTH

    val arWebUrl: String? = BuildConfig.AR_WEB_URL.takeIf { it.isNotBlank() }
}
