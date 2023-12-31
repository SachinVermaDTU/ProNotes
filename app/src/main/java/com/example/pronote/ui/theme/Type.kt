package com.example.pronote.ui.theme


import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.pronote.R
import com.example.pronote.R.font.roboto_light_italic



// Set of Material typography styles to start with

         val body= TextStyle(

        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp
    )
    /* Other default text styles to override
    button = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.W500,
        fontSize = 14.sp
    ),
    caption = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 12.sp
    )
    */




   val  body1 = TextStyle(
        fontFamily = FontFamily(
            fonts = listOf(
                Font(R.font.roboto_light, weight = FontWeight.W300),
            )
        ),
        fontWeight = FontWeight.SemiBold
    )
    val body2 = TextStyle(
        fontFamily = FontFamily(
            fonts = listOf(
                Font(R.font.roboto_thin, weight = FontWeight.W300),
            )
        )
    )
     val subtitle1 = TextStyle(
        fontFamily = FontFamily(
            fonts = listOf(
                Font(roboto_light_italic, weight = FontWeight.W300)
            )
        ),
        color = Color.Gray
    )
    val subtitle2 = TextStyle(
        fontFamily = FontFamily(
            fonts = listOf(
                Font(R.font.comfortaa_bold, weight = FontWeight.W300)
            )
        )
    )
