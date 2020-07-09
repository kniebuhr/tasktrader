package com.tasktrader.presentation.extensions

import android.widget.Toast
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar

fun AppCompatActivity.enableToolbar(toolbar: Toolbar, title: String, showHomeAsUp: Boolean = false) {
    setSupportActionBar(toolbar)
    supportActionBar?.setDisplayHomeAsUpEnabled(showHomeAsUp)
    this.title = title
}

fun AppCompatActivity.enableToolbar(toolbar: Toolbar, @StringRes titleRes: Int, showHomeAsUp: Boolean = false) {
    enableToolbar(toolbar, getString(titleRes), showHomeAsUp)
}