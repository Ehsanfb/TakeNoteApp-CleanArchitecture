package com.example.takenoteapp_cleanarchitecture.feature_take_note.presentation.edit_notes.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusState
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun TransparentHintTextField(
    text: String,
    hint: String,
    modifier: Modifier = Modifier,
    isHintVisible: Boolean = true,
    onValueChange: (String) -> Unit,
    textStyle: TextStyle = TextStyle(),
    singleLine: Boolean = false,
    onFocusChange: (FocusState) -> Unit
) {
    Box(
        modifier = modifier
    ) {
        BasicTextField(
            value = text,
            onValueChange = onValueChange,
            singleLine = singleLine,
            textStyle = textStyle,
            modifier = Modifier
                .fillMaxWidth()
                .onFocusChanged {
                    onFocusChange(it)
                }
        )
        if(isHintVisible) {
            Text(text = hint, style = textStyle, color = Color.DarkGray)
        }
    }
}


@Preview(showBackground = true)
@Composable
fun TransparentHintTextFieldPreview() {
    var text by remember { mutableStateOf("") }
    var isFocused by remember { mutableStateOf(false) }

    TransparentHintTextField(
        text = text,
        hint = "Enter something...",
        isHintVisible = text.isEmpty() && !isFocused,
        onValueChange = { text = it },
        textStyle = TextStyle(fontSize = 16.sp),
        singleLine = true,
        onFocusChange = { focusState -> isFocused = focusState.isFocused },
        modifier = Modifier
            .padding(16.dp)
            .background(Color.LightGray.copy(alpha = 0.2f), shape = RoundedCornerShape(4.dp))
            .padding(12.dp)
    )
}
