package com.tasktrader.presentation.extensions

import android.view.View
import com.google.android.material.textfield.TextInputLayout

internal fun View.setVisible(visible: Boolean) {
    visibility = if (visible) View.VISIBLE else View.GONE
}

internal fun TextInputLayout.getText(): String =
    editText?.text?.toString() ?: ""

internal fun TextInputLayout.setText(text: String) {
    editText?.setText(text)
}