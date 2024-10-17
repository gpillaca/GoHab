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
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
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
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import pe.geff.gohab.R
import pe.geff.gohab.core.components.CustomButton
import pe.geff.gohab.core.components.CustomTitle
import pe.geff.gohab.navigation.NavigationRoute
import pe.geff.gohab.ui.theme.GoHabTheme

@Composable
fun LoginScreen(
    navigateTo: (NavigationRoute) -> Unit,
    viewModel: LoginViewModel = hiltViewModel()
) {

    val loginState = viewModel.loginState

    var showPassword by remember { mutableStateOf(false) }
    val focusManager = LocalFocusManager.current

    LaunchedEffect(loginState.isLoggedIn) {
        if (loginState.isLoggedIn) {
            navigateTo(NavigationRoute.Home)
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
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

                OutlinedTextField(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 16.dp, end = 16.dp),
                    keyboardOptions = KeyboardOptions(
                        autoCorrectEnabled = false,
                        keyboardType = KeyboardType.Email,
                        imeAction = ImeAction.Next
                    ),
                    keyboardActions = KeyboardActions(
                        onAny = { focusManager.moveFocus(FocusDirection.Next) }
                    ),
                    value = loginState.email, onValueChange = {
                        viewModel.onEvent(LoginEvent.EmailChange(it))
                    },
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Default.Email,
                            contentDescription = "Email"
                        )
                    },
                    label = {
                        Text(text = "Email")
                    }, singleLine = true
                )

                if (!loginState.emailError.isNullOrEmpty()) {
                    Spacer(modifier = Modifier.padding(4.dp))
                    Text(text = loginState.emailError, color = Color.Red)
                }

                OutlinedTextField(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 16.dp, end = 16.dp),
                    keyboardOptions = KeyboardOptions(
                        autoCorrectEnabled = false,
                        keyboardType = KeyboardType.Password,
                        imeAction = ImeAction.Done
                    ),
                    keyboardActions = KeyboardActions(
                        onAny = { focusManager.clearFocus() }
                    ),
                    visualTransformation = if (showPassword) {
                        VisualTransformation.None
                    } else {
                        PasswordVisualTransformation()
                    },
                    value = loginState.password,
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Default.Lock,
                            contentDescription = "Password"
                        )
                    },
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
                    },
                    onValueChange = {
                        viewModel.onEvent(LoginEvent.PasswordChange(it))
                    }, label = {
                        Text(text = "Password")
                    }, singleLine = true
                )

                if (!loginState.passwordError.isNullOrEmpty()) {
                    Spacer(modifier = Modifier.padding(4.dp))
                    Text(text = loginState.passwordError, color = Color.Red)
                }

                Spacer(modifier = Modifier.padding(8.dp))
                CustomButton(
                    onclick = {
                        viewModel.onEvent(LoginEvent.Login)
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
private fun LoginScreenContent(navigateTo: (NavigationRoute) -> Unit) {

}

@Preview(showBackground = true)
@Composable
fun LoginScreenPreview() {
    GoHabTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
        ) {
            LoginScreenContent(navigateTo = {})
        }
    }
}

