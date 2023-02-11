package com.kolanse.tiktaktoe.ui.view

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Blue
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.core.util.UiText
import com.kolanse.tiktaktoe.ui.theme.lightPurple
import com.kolanse.tiktaktoe.ui.theme.titleTextColor
import com.kolanse.tiktaktoe.ui.theme.Typography
import com.kolanse.tiktaktoe.ui.theme.borderColor


@Composable
fun OutlinedButton(modifier: Modifier, buttonText: String, onClick: () -> Unit) {
    if (buttonText.isNotEmpty()) {
        TextButton(
            modifier = modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp),
            shape = RoundedCornerShape(90.dp),
            onClick = onClick,
            border = BorderStroke(1.dp, lightPurple),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.White,
                contentColor = lightPurple
            )
        ) {
            Text(
                text = buttonText,
                modifier = Modifier.padding(
                    top = 10.dp,
                    bottom = 10.dp
                ),
                style = Typography.titleSmall
            )
        }
    }
}

@Composable
fun FilledButton(modifier: Modifier, buttonText: String, onClick: () -> Unit) {
    if (buttonText.isNotEmpty()) {
        TextButton(
            modifier = modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp),
            shape = RoundedCornerShape(90.dp),
            onClick = onClick,
            colors = ButtonDefaults.buttonColors(
                containerColor = lightPurple,
                contentColor = Color.White
            )
        ) {
            Text(
                text = buttonText,
                modifier = Modifier.padding(
                    top = 10.dp,
                    bottom = 10.dp
                ),
                style = Typography.titleSmall
            )
        }
    }
}

@Composable
fun ColumnScope.TitleText(text: String) {
    Text(
        color = titleTextColor,
        style = Typography.titleLarge,
        text = text,
        textAlign = TextAlign.Center,
        modifier = Modifier
            .align(Alignment.CenterHorizontally)
    )
}

@Composable
fun RowScope.ColoredText(text: String, modifier: Modifier =  Modifier) {
    Text(
        color = lightPurple,
        style = Typography.titleSmall,
        text = text,
        modifier = modifier
    )
}

@Composable
fun BoldColoredText(text: String, modifier: Modifier =  Modifier) {
    Text(
        color = lightPurple,
        style = Typography.bodyMedium,
        text = text,
        modifier = modifier
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditTextInput() {
    var textInput by remember { mutableStateOf("") }
    OutlinedTextField(
        value = textInput,
        onValueChange = { textInput = it },
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp, horizontal = 24.dp),
        shape = RoundedCornerShape(29.dp),
        colors = TextFieldDefaults.outlinedTextFieldColors(
            containerColor = Color.White,
            textColor = Color.Black,
            focusedBorderColor = borderColor,
            unfocusedBorderColor = borderColor,
        ),
        placeholder = {
            Text(
                text = "Enter your name",
                style = Typography.titleSmall,
                color = Color.Black
            )
        },
    )
}

@Preview
@Composable
fun OutlinedButtonPreview() {
    OutlinedButton(
        modifier = Modifier,
        buttonText = "Join Game"
    ) {}
}

@Preview
@Composable
fun FilledButtonPreview() {
    FilledButton(
        modifier = Modifier,
        buttonText = "Continue"
    ) {}
}

@Preview
@Composable
fun EditTextInputPreview() {
    EditTextInput()
}