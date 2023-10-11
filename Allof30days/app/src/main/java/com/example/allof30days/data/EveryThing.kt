package com.example.allof30days.data

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class EveryThing(
    @DrawableRes var image: Int,
    @StringRes var title: Int,
    @StringRes var description: Int
)
