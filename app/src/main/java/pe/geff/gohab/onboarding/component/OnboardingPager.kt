package pe.geff.gohab.onboarding.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.HorizontalPagerIndicator
import com.google.accompanist.pager.rememberPagerState
import pe.geff.gohab.R
import pe.geff.gohab.core.components.CustomButton
import pe.geff.gohab.core.components.CustomTitle
import pe.geff.gohab.onboarding.OnboardingPagerInformation
import kotlinx.coroutines.launch

@OptIn(ExperimentalPagerApi::class)
@Composable
fun OnboardingPager(
    pages: List<OnboardingPagerInformation>,
    onComplete: () -> Unit,
    modifier: Modifier = Modifier
) {
    val pagerState = rememberPagerState()
    val coroutineScope = rememberCoroutineScope()

    Box(modifier = modifier.background(MaterialTheme.colorScheme.background)) {
        HorizontalPager(count = pages.size, state = pagerState) { index ->
            val information = pages[index]

            Column(
                modifier = modifier
                    .fillMaxSize()
                    .padding(start = 16.dp, end = 16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Spacer(modifier = modifier.height(32.dp))
                CustomTitle(title = information.title)
                Spacer(modifier = modifier.height(16.dp))
                Image(painterResource(id = information.image), modifier = modifier.aspectRatio(1f),  contentScale = ContentScale.FillHeight, contentDescription = "Onboarding")
                Spacer(modifier = modifier.height(16.dp))
                Text(
                    text = information.message.uppercase(),
                    style = MaterialTheme.typography.bodyLarge.copy(
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.tertiary
                    ),
                    textAlign = TextAlign.Center
                )
                Spacer(modifier = modifier.height(16.dp))
            }
        }

        Row(
            modifier = modifier
                .align(Alignment.BottomCenter)
                .fillMaxWidth()
                .padding(start = 16.dp, end = 16.dp, bottom = 32.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            
            if (pagerState.currentPage == pages.lastIndex) {
                CustomButton(
                    onclick = onComplete,
                    text = stringResource(id = R.string.button_get_started),
                    modifier = modifier
                )
            } else {
                TextButton(onClick = {
                    onComplete()
                }) {
                    Text(text = stringResource(id = R.string.skip))
                }
                HorizontalPagerIndicator(pagerState = pagerState)
                TextButton(onClick = {
                    coroutineScope.launch {
                        pagerState.animateScrollToPage(pagerState.currentPage + 1)
                    }
                }) {
                    Text(text = stringResource(id = R.string.next))
                }
            }
        }
    }
}
