package pe.geff.gohab.authentication.presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.focus.FocusManager
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import pe.geff.gohab.R
import pe.geff.gohab.core.components.CustomButton
import pe.geff.gohab.core.components.CustomOutlinedTextField
import pe.geff.gohab.core.components.CustomPasswordOutlinedTextField
import pe.geff.gohab.core.components.CustomTitle
import pe.geff.gohab.navigation.NavigationRoute
import pe.geff.gohab.ui.theme.GoHabTheme

@Composable
fun LoginScreen(
    navigateTo: (NavigationRoute) -> Unit,
    viewModel: LoginViewModel = hiltViewModel()
) {

    val loginState = viewModel.loginState

    LaunchedEffect(loginState.isLoggedIn) {
        if (loginState.isLoggedIn) {
            navigateTo(NavigationRoute.Home)
        }
    }

    LoginScreenContent(loginState, navigateTo) {
        viewModel.onEvent(it)
    }

}

@Composable
private fun LoginScreenContent(
    loginState: LoginState,
    navigateTo: (NavigationRoute) -> Unit,
    onEvent: (LoginEvent) -> Unit
) {
    val focusManager = LocalFocusManager.current

    Box(modifier = Modifier.fillMaxSize()) {
        BackgroundImage()

        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier)
            Spacer(modifier = Modifier)
            CustomTitle(title = "Welcome to GoHab \n Good Habit")

            Column(
                modifier = Modifier
                    .background(
                        MaterialTheme.colorScheme.secondaryContainer,
                        shape = RoundedCornerShape(20.dp)
                    ),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Text(
                    text = "Log in with email",
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(top = 12.dp),
                    fontSize = 16.sp,
                    style = MaterialTheme.typography.bodyLarge,
                    color = MaterialTheme.colorScheme.tertiary
                )

                HorizontalDivider(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 12.dp, bottom = 12.dp),
                    color = MaterialTheme.colorScheme.background
                )

                EmailComponent(
                    email = loginState.email,
                    focusManager = focusManager,
                    onValueChange = {
                        onEvent(LoginEvent.EmailChange(it))
                    }
                )

                if (!loginState.emailError.isNullOrEmpty()) {
                    Spacer(modifier = Modifier.padding(4.dp))
                    Text(text = loginState.emailError, color = Color.Red)
                }

                PasswordComponent(
                    password = loginState.password,
                    focusManager = focusManager,
                    onValueChange = {
                        onEvent(LoginEvent.PasswordChange(it))
                    }
                )

                if (!loginState.passwordError.isNullOrEmpty()) {
                    Spacer(modifier = Modifier.padding(4.dp))
                    Text(text = loginState.passwordError, color = Color.Red)
                }

                Spacer(modifier = Modifier.padding(8.dp))
                CustomButton(
                    onclick = {
                        onEvent(LoginEvent.Login)
                    },
                    text = "Login",
                    modifier = Modifier.padding(start = 16.dp, end = 16.dp)
                )

                Spacer(modifier = Modifier.padding(4.dp))
                TextButton(onClick = {
                    navigateTo(NavigationRoute.SignUp)
                }) {
                    Text(text = "Don't have an account? Sign up")
                }
                Spacer(modifier = Modifier.padding(16.dp))
            }
        }

        if (loginState.isLoading) {
            CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
        }
    }
}

@Composable
fun BackgroundImage() {
    Image(
        painterResource(id = R.drawable.loginbackground),
        contentDescription = "Login",
        contentScale = ContentScale.Crop,
        modifier = Modifier
            .aspectRatio(1f)
            .graphicsLayer(
                scaleX = 1.27f,
                scaleY = 1.27f
            )
    )
    Spacer(
        modifier = Modifier
            .fillMaxSize()
            .background(
                Brush.verticalGradient(
                    colors = listOf(
                        Color.Transparent,
                        MaterialTheme.colorScheme.background,
                        MaterialTheme.colorScheme.background
                    )
                )
            )
    )
}

@Composable
fun PasswordComponent(password: String, focusManager: FocusManager, onValueChange: (String) -> Unit) {
    var showPassword by remember { mutableStateOf(false) }

    CustomPasswordOutlinedTextField(
        value = password,
        visualTransformation = if (showPassword) {
            VisualTransformation.None
        } else {
            PasswordVisualTransformation()
        },
        keyboardActions = KeyboardActions(
            onAny = { focusManager.clearFocus() }
        ),
        onValueChange = onValueChange,
        trailingIcon = {
            IconButton(onClick = {
                showPassword = !showPassword
            }) {
                val (icon, iconColor, description) = if (showPassword) {
                    Triple(
                        R.drawable.ic_eye_visibility_off,
                        Color.Gray,
                        "Hide password"
                    )
                } else {
                    Triple(
                        R.drawable.ic_eye_visibility,
                        MaterialTheme.colorScheme.primary,
                        "Show password"
                    )
                }
                Icon(
                    painter = painterResource(id = icon),
                    tint = iconColor,
                    contentDescription = description
                )
            }
        }
    )
}

@Composable
fun EmailComponent(
    email: String,
    onValueChange: (String) -> Unit,
    focusManager: FocusManager
) {
    CustomOutlinedTextField(
        value = email,
        onValueChange = onValueChange,
        keyboardActions = KeyboardActions(
            onAny = { focusManager.moveFocus(FocusDirection.Next) }
        )
    )
}

@Preview(showBackground = true)
@Composable
fun LoginScreenPreview() {
    GoHabTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
        ) {
            LoginScreenContent(navigateTo = {}, loginState = LoginState(), onEvent = {})
        }
    }
}
