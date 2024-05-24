package eu.tutorials.courses.Model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class Topic (
        @StringRes val name: Int,
        val numberCourses: Int,
        @DrawableRes val image: Int
)