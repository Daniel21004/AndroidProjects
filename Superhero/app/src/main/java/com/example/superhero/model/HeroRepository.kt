package com.example.superhero.model

import com.example.superhero.R

object HeroRepository {
    val heroes = listOf(
        Hero(
            imageRes = R.drawable.android_superhero1,
            nameRes = R.string.hero1,
            descriptionRes = R.string.description1
        ),
        Hero(
            imageRes = R.drawable.android_superhero2,
            nameRes = R.string.hero2,
            descriptionRes = R.string.description2
        ),
        Hero(
            imageRes = R.drawable.android_superhero3,
            nameRes = R.string.hero3,
            descriptionRes = R.string.description3
        ),
        Hero(
            imageRes = R.drawable.android_superhero4,
            nameRes = R.string.hero4,
            descriptionRes = R.string.description4
        ),
        Hero(
            imageRes = R.drawable.android_superhero5,
            nameRes = R.string.hero5,
            descriptionRes = R.string.description5
        ),
        Hero(
            imageRes = R.drawable.android_superhero6,
            nameRes = R.string.hero6,
            descriptionRes = R.string.description6
        )
    )
}