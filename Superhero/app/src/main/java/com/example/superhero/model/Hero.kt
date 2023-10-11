package com.example.superhero.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class Hero(
    @DrawableRes var imageRes: Int,
    @StringRes var nameRes: Int,
    @StringRes var descriptionRes: Int
)