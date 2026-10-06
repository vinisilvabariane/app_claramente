package com.claramente.feature.ar.view

import com.claramente.core.designsystem.component.LoadingScreen
import com.claramente.feature.ar.component.ArBottomBar
import com.claramente.feature.ar.component.ArMessageCard
import com.claramente.feature.ar.component.ArOverlay
import com.claramente.feature.ar.component.ArSessionView
import com.claramente.feature.ar.helper.ArCoreSupportProbe
import com.claramente.feature.ar.state.ArExperienceActions
import com.claramente.feature.ar.state.ArExperienceUiState
import com.claramente.feature.ar.state.ArStage
import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.provider.Settings
import androidx.activity.ComponentActivity
import androidx.activity.compose.LocalActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.core.content.ContextCompat
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.repeatOnLifecycle

@Composable
fun ArExperienceScreen(state: ArExperienceUiState, actions: ArExperienceActions) {
    val context = LocalContext.current
    val activity = LocalActivity.current as? ComponentActivity
    val launcher = rememberLauncherForActivityResult(ActivityResultContracts.RequestPermission()) { granted ->
        actions.onCameraState(granted)
    }

    LaunchedEffect(activity, state.attempt) {
        activity?.lifecycle?.repeatOnLifecycle(Lifecycle.State.RESUMED) {
            val granted = ContextCompat.checkSelfPermission(context, Manifest.permission.CAMERA) ==
                PackageManager.PERMISSION_GRANTED
            actions.onCameraState(granted)
            actions.onSupport(ArCoreSupportProbe.check(context))
        }
    }

    LaunchedEffect(state.stage) {
        if (state.stage == ArStage.NEEDS_PERMISSION) {
            actions.onCameraRequested()
            launcher.launch(Manifest.permission.CAMERA)
        }
    }

    Box(modifier = Modifier.fillMaxSize().background(Color.Black)) {
        when (state.stage) {
            ArStage.CHECKING -> LoadingScreen()
            ArStage.NEEDS_PERMISSION, ArStage.PERMISSION_DENIED -> ArMessageCard(
                title = "Precisamos da câmera",
                text = "A realidade aumentada usa a câmera do aparelho para reconhecer o alvo.",
                onBack = actions.onBack,
                primaryLabel = "Permitir câmera",
                onPrimary = { launcher.launch(Manifest.permission.CAMERA) },
                secondaryLabel = "Abrir configurações",
                onSecondary = {
                    context.startActivity(
                        Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS, Uri.fromParts("package", context.packageName, null)),
                    )
                },
            )
            ArStage.NEEDS_INSTALL -> ArMessageCard(
                title = "Falta o ARCore",
                text = "A realidade aumentada precisa do Google Play Services for AR. Instale para continuar.",
                onBack = actions.onBack,
                primaryLabel = "Instalar",
                onPrimary = { activity?.let(ArCoreSupportProbe::requestInstall) },
                secondaryLabel = if (state.webUrl != null) "Abrir versão web" else null,
                onSecondary = actions.onOpenWeb,
            )
            ArStage.UNSUPPORTED -> ArMessageCard(
                title = "Aparelho sem suporte a AR",
                text = "Este aparelho não é compatível com o ARCore. Use a versão web da realidade aumentada.",
                onBack = actions.onBack,
                primaryLabel = if (state.webUrl != null) "Abrir versão web" else null,
                onPrimary = actions.onOpenWeb,
            )
            ArStage.FAILED -> ArMessageCard(
                title = "Não foi possível iniciar a AR",
                text = state.failure ?: "Não foi possível verificar o suporte do aparelho a realidade aumentada.",
                onBack = actions.onBack,
                primaryLabel = "Tentar de novo",
                onPrimary = actions.onRetry,
                secondaryLabel = if (state.webUrl != null) "Abrir versão web" else null,
                onSecondary = actions.onOpenWeb,
            )
            ArStage.RUNNING -> {
                ArSessionView(state = state, actions = actions)
                ArOverlay(
                    title = state.title,
                    hint = state.hint,
                    onBack = actions.onBack,
                    modifier = Modifier.align(Alignment.TopCenter),
                )
                ArBottomBar(state = state, actions = actions, modifier = Modifier.align(Alignment.BottomCenter))
            }
        }
    }
}
