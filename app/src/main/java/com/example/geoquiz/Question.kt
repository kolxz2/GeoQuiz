package com.example.geoquiz

import androidx.annotation.StringRes

data class Question (
    @StringRes // checked string resource on correct
    val textResId: Int,
    val answer : Boolean
        )