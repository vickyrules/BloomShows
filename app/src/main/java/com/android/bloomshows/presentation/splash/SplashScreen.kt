package com.android.bloomshows.presentation.splash

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import com.android.bloomshows.R
import com.android.bloomshows.presentation.on_boarding.OnBoardingScreen
import com.android.bloomshows.ui.theme.BloomShowsTheme
import com.android.bloomshows.ui.theme.handlee

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun SplashScreen(
    navigate_to_home: () -> Unit,
    navigate_to_onboarding: () -> Unit,
) {
    Box(
        modifier = Modifier
            .fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        SplashLogoBloomShows(navigate_to_home,navigate_to_onboarding)
        CircularProgressIndicator(modifier = Modifier.align(Alignment.Center).offset(y = 100.dp), color = Color.Gray.copy(alpha = 0.5f))

        Row(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = 20.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Image(
                modifier = Modifier.size(28.dp),
                painter = painterResource(R.drawable.ic_bloomshows),
                contentDescription = "bloomshows logo"
            )
            Text(
                text = stringResource(R.string.app_name),
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold,
                fontFamily = handlee
            )
        }
    }
}


@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun SplashLogoBloomShows(
    navigate_to_home: () -> Unit,
    navigate_to_onboarding: () -> Unit,
) {

    val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.animated_logo_bloomshows))
    val logoAnimationState =
        animateLottieCompositionAsState(composition = composition, speed = 3f)
    LottieAnimation(
        modifier = Modifier.size(108.dp),
        composition = composition,
        progress = { logoAnimationState.progress }
    )
    if (logoAnimationState.isAtEnd && logoAnimationState.isPlaying) {
        navigate_to_onboarding()
        //TODO show
        //TODO call only when user laucnhed for the very first time
        // OnBoardingScreen()
    }
}


@RequiresApi(Build.VERSION_CODES.O)
@Preview
@Composable
fun SplashScreenPreview() {
    BloomShowsTheme(darkTheme = !true) {
        SplashScreen(navigate_to_home = {}, navigate_to_onboarding = {})
    }
}