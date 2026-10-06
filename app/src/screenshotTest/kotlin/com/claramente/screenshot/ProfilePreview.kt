package com.claramente.screenshot

import com.android.tools.screenshot.PreviewTest
import com.claramente.core.designsystem.theme.ClaramenteTheme
import com.claramente.feature.profile.view.ProfileScreen
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview

@PreviewTest
@Preview(showBackground = true, device = PreviewDevices.PHONE)
@Composable
fun ProfilePreview() {
    ClaramenteTheme {
        ProfileScreen(user = null, loggingOut = false, onLogout = {})
    }
}
