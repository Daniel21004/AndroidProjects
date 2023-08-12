package com.example.lemonade

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.lemonade.ui.theme.LemonadeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LemonadeTheme {
                LemonMain()
            }
        }
    }
}

@Composable
fun LemonMain() {
    var currentStep: Int by remember { mutableStateOf(1) }
    var numberOfSqueeze by remember { mutableStateOf(0) }

    val onClick: () -> Unit = {
        if (currentStep == 1) {
            numberOfSqueeze = (2..4).random()
        }

        when {
            (currentStep == 2 && numberOfSqueeze != 0) -> numberOfSqueeze--
            currentStep < 4 -> currentStep++
            else -> currentStep = 1
        }
    }

    val image: Painter = when (currentStep) {
        1 -> painterResource(id = R.drawable.lemon_tree)
        2 -> painterResource(id = R.drawable.lemon_squeeze)
        3 -> painterResource(id = R.drawable.lemon_drink)
        else -> painterResource(id = R.drawable.lemon_restart)
    }

    val imageContentDescription: String = when (currentStep) {
        1 -> stringResource(R.string.lemon_tree_content_description)
        2 -> stringResource(R.string.glass_of_lemonade_content_description)
        3 -> stringResource(R.string.lemon_content_description)
        else -> stringResource(R.string.empty_glass_content_description)
    }

    val description: String = when (currentStep) {
        1 -> stringResource(R.string.text_lemon_tree)
        2 -> stringResource(R.string.text_lemon)
        3 -> stringResource(R.string.text_glass_of_lemonade)
        else -> stringResource(R.string.text_empty_glass)
    }

    InterfaceLemonWithOutRandomClicks(
        modifier = Modifier.fillMaxSize(),
        onClick = onClick,
        image = image,
        imageContentDescription = imageContentDescription,
        description = description
    )
}

@Composable
fun InterfaceLemonWithOutRandomClicks(
    modifier: Modifier,
    onClick: () -> Unit,
    image: Painter,
    imageContentDescription: String,
    description: String
) {

    val backgroundColorImage = colorResource(id = R.color.green_pale)

    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = image,
            contentDescription = imageContentDescription,
            contentScale = ContentScale.Fit, // For the uniform scale
            modifier = Modifier
                .size(300.dp)
                .clickable { onClick() }
                .clip(RoundedCornerShape(48.dp)) // Redounded the image
                .background(color = backgroundColorImage) // Has the background in bottom always.
        )
        Spacer(modifier = Modifier.height(22.dp))
        Text(text = description, fontSize = 18.sp)
    }
}

@Preview(showBackground = true)
@Composable
fun LemonMainPreview() {
    LemonMain()
}