package com.example.MovieApp.views

import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.DialogProperties
import androidx.compose.ui.window.Dialog
import com.example.MovieApp.ui.theme.Saffron
import com.example.MovieApp.ui.theme.Typography

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ChangePasswordDialog (
    onDismissRequest: () -> Unit,
    passwordChange: (oldPassword: String, newPassword: String) -> Unit
    ) {
    val oldPassword = remember { mutableStateOf("") }
    val newPassword = remember { mutableStateOf("") }
    val confirmPassword = remember { mutableStateOf("") }

    Dialog (
        onDismissRequest = { onDismissRequest() },
        properties = DialogProperties(
            usePlatformDefaultWidth = false
        )
    ) {
        Card(
            modifier = Modifier
                .fillMaxWidth(),
            shape = RoundedCornerShape(16.dp),
            border = androidx.compose.foundation.BorderStroke(2.dp, Saffron),
            colors = CardDefaults.cardColors(
                containerColor = Color.Black.copy(alpha = 0.7f)
            )
        ) {
            Column(
                modifier = Modifier.padding(horizontal = 32.dp, vertical = 28.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Text(
                    "Change Password",
                    style = Typography.titleLarge,
                    color = Color.White,
                    fontWeight = FontWeight.W700,
                    modifier = Modifier.padding(vertical = 4.dp)
                )
                Text(
                    "Enter your current and new password",
                    style = Typography.bodyMedium,
                    color = Color.LightGray,
                    modifier = Modifier.padding(vertical = 4.dp)
                )
                Spacer(modifier = Modifier.height(24.dp))
                Text(
                    "Current Password",
                    style = Typography.titleMedium,
                    textAlign = TextAlign.Start,
                    color = Color.White,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp)
                )
                NoPaddingTextField(
                    value = oldPassword.value,
                    onValueChange = { oldPassword.value = it },
                    labelText = "Enter current password"
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    "New Password",
                    style = Typography.titleMedium,
                    textAlign = TextAlign.Start,
                    color = Color.White,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp)
                )
                NoPaddingTextField(
                    value = newPassword.value,
                    onValueChange = { newPassword.value = it },
                    labelText = "Enter new password"
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    "Confirm Password",
                    style = Typography.titleMedium,
                    textAlign = TextAlign.Start,
                    color = Color.White,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp)
                )
                NoPaddingTextField(
                    value = confirmPassword.value,
                    onValueChange = { confirmPassword.value = it },
                    labelText = "Confirm new password"
                )
//                OutlinedTextField(
//                    value = confirmPassword.value,
//                    onValueChange = { confirmPassword.value = it },
//                    label = { Text("Confirm new password") },
//                    visualTransformation = PasswordVisualTransformation(),
//                    colors = OutlinedTextFieldDefaults.colors(
//                        cursorColor = Color.White,
//                        unfocusedContainerColor = Color.Black.copy(alpha = 0.5f),
//                        focusedContainerColor = Color.Black.copy(alpha = 0.5f),
//                        focusedBorderColor = Color.White,
//                        unfocusedBorderColor = Saffron.copy(alpha = 0.5f),
//                        unfocusedTextColor = Color.White,
//                        focusedTextColor = Color.White,
//                        unfocusedLabelColor = Color.LightGray,
//                        focusedLabelColor = Color.LightGray,
//                    ),
//                    modifier = Modifier.fillMaxWidth(),
//                    shape = RoundedCornerShape(100),
//                )
                Spacer(modifier = Modifier.height(32.dp))
                Button(
                    onClick = {
                        if (newPassword.value == confirmPassword.value) {
                            passwordChange(
                                oldPassword.value,
                                newPassword.value
                            )
                            onDismissRequest()
                        }
                    },
                    modifier = Modifier
                        .fillMaxWidth(),
                    shape = RoundedCornerShape(100),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Saffron,
                        contentColor = Color.Black,
                        disabledContentColor = Color.LightGray,
                        disabledContainerColor = Color.DarkGray
                    ),
                    contentPadding = PaddingValues(all = 0.dp)
                ) {
                    Text(
                        "Update Password",
                        style = Typography.titleSmall.copy(fontWeight = FontWeight.W600),
                        modifier = Modifier.padding(vertical = 4.dp)
                    )
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun NoPaddingTextField(
    value: String,
    onValueChange: (String) -> Unit,
    labelText: String,
) {
    val interactionSource = remember { MutableInteractionSource() }

    BasicTextField(
        value = value,
        onValueChange = onValueChange,
        modifier = Modifier.fillMaxWidth(),
        visualTransformation = PasswordVisualTransformation(),
        interactionSource = interactionSource,
        textStyle = Typography.bodySmall.copy(color = Color.White), // Set the text style
        decorationBox = { innerTextField ->
            OutlinedTextFieldDefaults.DecorationBox(
                value = value,
                innerTextField = innerTextField,
                enabled = true,
                singleLine = true,
                visualTransformation = PasswordVisualTransformation(),
                interactionSource = interactionSource,
                contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp),
                label = { Text(labelText) },
                colors = OutlinedTextFieldDefaults.colors(
                    cursorColor = Color.White,
                    unfocusedContainerColor = Color.Black.copy(alpha = 0.5f),
                    focusedContainerColor = Color.Black.copy(alpha = 0.5f),
                    focusedBorderColor = Color.White,
                    unfocusedBorderColor = Saffron.copy(alpha = 0.5f),
                    unfocusedTextColor = Color.White,
                    focusedTextColor = Color.White,
                    unfocusedLabelColor = Color.Gray,
                    focusedLabelColor = Color.LightGray,
                ),
                // We re-apply the container with the custom shape
                container = {
                    OutlinedTextFieldDefaults.Container(
                        enabled = true,
                        isError = false,
                        interactionSource = interactionSource,
                        colors = OutlinedTextFieldDefaults.colors(
                            unfocusedContainerColor = Color.Black.copy(alpha = 0.5f),
                            focusedContainerColor = Color.Black.copy(alpha = 0.5f),
                            focusedBorderColor = Color.White,
                            unfocusedBorderColor = Saffron.copy(alpha = 0.5f),
                        ),
                        shape = RoundedCornerShape(100)
                    )
                }
            )
        }
    )
}


@Preview(showBackground = true)
@Composable
fun ChangePasswordDialogPreview() { // Renamed for clarity to avoid conflicts
    ChangePasswordDialog(
        onDismissRequest = { },// The lambda now has the correct syntax
        passwordChange = { _, _ ->
            // This is a preview, so the function body can be empty.
        }
    )
}
