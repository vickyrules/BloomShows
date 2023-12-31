package com.android.bloomshows.presentation.splash

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.android.bloomshows.R
import com.android.bloomshows.ui.common_components.BloomshowsBranding
import com.android.bloomshows.ui.theme.BloomShowsTheme
import com.android.bloomshows.ui.theme.MediumPadding
import com.android.bloomshows.utils.animations.ShowLootieAnimation

@Composable
fun SplashScreen(
    navigate_to_home: () -> Unit,
    navigate_to_onboarding: () -> Unit,
    navigate_to_login: () -> Unit,
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .windowInsetsPadding(androidx.compose.foundation.layout.WindowInsets.systemBars),
        contentAlignment = Alignment.Center
    ) {
        SplashLogoBloomShows(
            navigate_to_home,
            navigate_to_onboarding,
            navigate_to_login = navigate_to_login
        )
        CircularProgressIndicator(
            modifier = Modifier.align(Alignment.Center).offset(y = 100.dp),
            color = Color.Gray.copy(alpha = 0.5f)
        )
        BloomshowsBranding(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = MediumPadding),
        )
    }
}


@Composable
private fun SplashLogoBloomShows(
    navigate_to_home: () -> Unit,
    navigate_to_onboarding: () -> Unit,
    navigate_to_login: () -> Unit,
    splashViewModel: SplashViewModel = hiltViewModel()
) {
    val isFirstTime = splashViewModel.isFirstTime.collectAsStateWithLifecycle()

    ShowLootieAnimation(
        modifier = Modifier.size(120.dp).zIndex(100f).background(Color.Transparent),
        animationJsonResId = R.raw.animated_logo_bloomshows,
        speed = 2.5f,
        onComplete = {
            // call only when user laucnhed for the very first time
            if (isFirstTime.value) {
                navigate_to_onboarding()
            } else {
                splashViewModel.onAppStart(
                    openHome = navigate_to_home,
                    openLogIn = navigate_to_login
                )
            }
        }
    )
}


@Preview
@Composable
fun SplashScreenPreview() {

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        BloomShowsTheme(darkTheme = false) {
            SplashScreen(navigate_to_home = {}, navigate_to_onboarding = {}, {})
        }
    }
}

@Preview
@Composable
fun SplashScreenPreviewDark() {
    BloomShowsTheme(darkTheme = true) {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            SplashScreen(navigate_to_home = {}, navigate_to_onboarding = {}, {})
        }
    }
}