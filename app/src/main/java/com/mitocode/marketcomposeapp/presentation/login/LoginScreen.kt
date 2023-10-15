package com.mitocode.marketcomposeapp.presentation.login

import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.*
import com.mitocode.marketcomposeapp.R
import com.mitocode.marketcomposeapp.domain.models.User
import com.mitocode.marketcomposeapp.domain.states.GenericState
import com.mitocode.marketcomposeapp.presentation.component.BasicImageComponent
import com.mitocode.marketcomposeapp.presentation.component.RoundedButtonComponent
import com.mitocode.marketcomposeapp.util.DefaultPreview

@Composable
fun LoginScreen(
    viewModel: LoginViewModel = viewModel(),
    onLogin: () -> Unit
) {

    val state = viewModel.state
    val stateElement = viewModel.stateElements
    val context = LocalContext.current

    Column(modifier = Modifier.fillMaxSize()) {
        LoginHeader()
        LoginBody(viewModel, stateElement, state)

        LaunchedEffect(key1 = state.successful, key2 = state.error) {
            if (state.successful != null) {
                onLogin()
            }

            if (state.error != null) {
                Toast.makeText(context, state.error, Toast.LENGTH_LONG).show()
            }

            viewModel.resetStateError()
        }
    }
}

@Composable
fun ColumnScope.LoginHeader() {

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .weight(7f),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        BasicImageComponent(
            modifier = Modifier.size(100.dp),
            image = R.drawable.image_login,
            description = "Imagen Login"
        )

        BasicImageComponent(
            modifier = Modifier.size(200.dp),
            image = R.drawable.header_login,
            description = "Header Login"
        )

        Text(
            text = "Bienvenidos",
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 10.dp),
            fontSize = 25.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center
        )

        Text(
            text = "Iniciar Sesión",
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 10.dp),
            fontSize = 17.sp,
            fontWeight = FontWeight.SemiBold,
            textAlign = TextAlign.Center
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ColumnScope.LoginBody(
    viewModel: LoginViewModel,
    stateElements: LoginElements,
    state: GenericState<User>
) {

    val focusManager = LocalFocusManager.current

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .weight(6f)
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = stateElements.email,
            onValueChange = {
                viewModel.onEvent(LoginFormEvent.EmailChange(it))
            },
            placeholder = {
                Text(text = "Enter your Email")
            },
            trailingIcon = {
                IconButton(onClick = { /*TODO*/ }) {
                    Icon(
                        imageVector = Icons.Filled.Clear,
                        contentDescription = "Clear"
                    )
                }
            },
            singleLine = true,
            keyboardOptions = KeyboardOptions(
                capitalization = KeyboardCapitalization.None,
                imeAction = ImeAction.Next
            ),
            keyboardActions = KeyboardActions(
                onNext = {
                    focusManager.moveFocus(FocusDirection.Down)
                }
            )
        )

        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = stateElements.password,
            onValueChange = {
                viewModel.onEvent(LoginFormEvent.PasswordChange(it))
            },
            placeholder = {
                Text(text = "Enter your password")
            },
            trailingIcon = {
                IconButton(onClick = {
                    viewModel.onEvent(LoginFormEvent.VisualTransformationChange(!stateElements.visualTransformation))
                }) {
                    Icon(
                        imageVector = if (stateElements.visualTransformation)
                            Icons.Filled.Visibility
                        else
                            Icons.Filled.VisibilityOff,
                        contentDescription = "Clear"
                    )
                }
            },
            singleLine = true,
            keyboardOptions = KeyboardOptions(
                capitalization = KeyboardCapitalization.None,
                imeAction = ImeAction.Done
            ),
            keyboardActions = KeyboardActions(
                onDone = {
                    focusManager.clearFocus()
                }
            ),
            visualTransformation = if (stateElements.visualTransformation) {
                VisualTransformation.None
            } else {
                PasswordVisualTransformation()
            }
        )

        Spacer(modifier = Modifier.height(12.dp))

        RoundedButtonComponent(
            modifier = Modifier
                .fillMaxWidth()
                .height(40.dp),
            title = "Sign In",
            onClick = {
                viewModel.onEvent(LoginFormEvent.Submit)
            },
            displayProgressBar = state.isLoading
        )

        Text(
            modifier = Modifier.fillMaxWidth(),
            text = buildAnnotatedString {
                append("¿Aún no te has registrado?")
                withStyle(
                    style = SpanStyle(
                        textDecoration = TextDecoration.Underline,
                        fontWeight = FontWeight.Bold
                    )
                ) {
                    append(" Registrate")
                }
            },
            textAlign = TextAlign.Center
        )

        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp),
            text = "Cambiar contraseña",
            textAlign = TextAlign.Center
        )
    }
}

@DefaultPreview
@Composable
fun LoginScreenPreview() {
    LoginScreen() {}
}