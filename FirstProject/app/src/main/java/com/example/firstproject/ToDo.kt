package com.example.firstproject

import android.icu.text.CaseMap.Title

data class ToDo (
    val title: String,
    val isChecked: Boolean = false
        )