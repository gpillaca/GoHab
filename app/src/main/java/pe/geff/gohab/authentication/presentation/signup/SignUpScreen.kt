package pe.geff.gohab.authentication.presentation.signup

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material3.CircularProgressIndicator
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
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import pe.geff.gohab.R
import pe.geff.gohab.core.components.CustomButton
import pe.geff.gohab.core.components.CustomOutlinedTextField
import pe.geff.gohab.core.components.CustomPasswordOutlinedTextField
import pe.geff.gohab.core.components.CustomTitle
import pe.geff.gohab.navigation.NavigationRoute
import pe.geff.gohab.ui.theme.GoHabTheme

@Composable
fun SignUpScreen(
    navigateTo: (NavigationRoute) -> Unit,
    viewModel: SignUpViewModel = hiltViewModel()
) {

    val loginState = viewModel.state

    LaunchedEffect(loginState.isLoggedIn) {
        if (loginState.isLoggedIn) {
            navigateTo(NavigationRoute.Home)
        }
    }

    SignUpScreenContent(loginState, navigateTo) {
        viewModel.onEvent(it)
    }
}

@Composable
private fun SignUpScreenContent(
    signUpState: SignUpState,
    navigateTo: (NavigationRoute) -> Unit,
    onEvent: (SignUpEvent) -> Unit
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
            CustomTitle(title = "Create your account")

            Column(
                modifier = Modifier
                    .background(
                        MaterialTheme.colorScheme.secondaryContainer,
                        shape = RoundedCornerShape(20.dp)
                    ),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                EmailComponent(
                    email = signUpState.email,
                    focusManager = focusManager,
                    onValueChange = {
                        onEvent(SignUpEvent.EmailChange(it))
                    }
                )

                if (!signUpState.emailError.isNullOrEmpty()) {
                    Spacer(modifier = Modifier.padding(4.dp))
                    Text(text = signUpState.emailError, color = Color.Red)
                }

                PasswordComponent(
                    password = signUpState.password,
                    focusManager = focusManager,
                    onValueChange = {
                        onEvent(SignUpEvent.PasswordChange(it))
                    }
                )

                if (!signUpState.passwordError.isNullOrEmpty()) {
                    Spacer(modifier = Modifier.padding(4.dp))
                    Text(text = signUpState.passwordError, color = Color.Red)
                }

                Spacer(modifier = Modifier.padding(8.dp))
                CustomButton(
                    onclick = {
                        onEvent(SignUpEvent.SignUp)
                    },
                    text = "Create Account",
                    modifier = Modifier.padding(start = 16.dp, end = 16.dp)
                )

                Spacer(modifier = Modifier.padding(4.dp))
                TextButton(onClick = {
                    navigateTo(NavigationRoute.Login)
                }) {
                    Text(text = "Already have an account? Sign In")
                }
                Spacer(modifier = Modifier.padding(16.dp))
            }
        }

        if (signUpState.isLoading) {
            CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
        }
    }
}

@Composable
fun BackgroundImage() {
    Image(
        painterResource(id = R.drawable.create_your_account),
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
fun SignUpScreenPreview() {
    GoHabTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
        ) {
            SignUpScreenContent(navigateTo = {}, signUpState = SignUpState(), onEvent = {})
        }
    }
}
