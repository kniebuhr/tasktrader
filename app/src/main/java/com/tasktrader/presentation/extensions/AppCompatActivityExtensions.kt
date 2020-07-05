package com.tasktrader.presentation.extensions

import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar

fun AppCompatActivity.enableToolbar(toolbar: Toolbar, title: String) {
    setSupportActionBar(toolbar)
    this.title = title
}