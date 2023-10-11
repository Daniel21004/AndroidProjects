package com.example.createagrid.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class Topic(
    @DrawableRes val image: Int,
    @StringRes val name: Int,
    val numberOfAssociatedCourses: Int
)
