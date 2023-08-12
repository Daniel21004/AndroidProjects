package com.example.happybirthday

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Call
import androidx.compose.material.icons.rounded.Email
import androidx.compose.material.icons.rounded.Share
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.happybirthday.ui.theme.HappyBirthdayTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            HappyBirthdayTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background
                ) {
//                    BirthdayGratingWithImage(stringResource(R.string.happy_birthday), stringResource(R.string.name_birther))
//                    ArticleCompose()
//                    Task()
//                    CardsComposable()
                    CardPresentation()
                }
            }
        }
    }
}

// The compose function name should not be a verb or verb phrase
// The compose name use Pascal Format

@Composable
fun BirthdayGratingWithText(message: String, from: String) {
    Column {
        Text(
            text = message, fontSize = 36.sp, modifier = Modifier
                .fillMaxWidth()
                .wrapContentWidth(align = Alignment.CenterHorizontally)
        )
        Text(
            text = "\t- from: $from", fontSize = 24.sp, modifier = Modifier
                .fillMaxWidth()
                .wrapContentWidth(align = Alignment.Start)
        )
    }
}

@Composable
fun BirthdayGratingWithImage(message: String, from: String) {
    // JetCompose accessed to de Image for its Id.
    val image = painterResource(id = R.drawable._259698)

    // The first elements in component 'Box' moves to background and the last elements moves to first layer
    Box {
        // 'contentDescription' content the text for TalkBack - Used for user with disables
        Image(
            painter = image,
            contentDescription = null,
            modifier = Modifier
                .fillMaxHeight()
                .fillMaxWidth(),
            // Scale the image uniformly in all screen
            contentScale = ContentScale.Crop
        )

        BirthdayGratingWithText(message = message, from = from)
    }
}


//@Preview(showBackground = true)
@Composable
fun BirthdayGratingWithTextPreview() {
    HappyBirthdayTheme {
        BirthdayGratingWithText(
            stringResource(R.string.happy_birthday),
            stringResource(R.string.name_birther)
        )
    }
}

//@Preview(showBackground = false)
@Composable
fun BirthdayGratingWithImagePreview() {
    HappyBirthdayTheme {
        BirthdayGratingWithImage(
            stringResource(R.string.happy_birthday),
            stringResource(R.string.name_birther)
        )
    }
}


// ------------------------------
//          PRACTICAS
// --------------------------------------------------------------------------------------------------

@Composable
fun ArticleCompose() {

    val bgImage = painterResource(id = R.drawable.bg_compose_background)
    Column(modifier = Modifier.fillMaxHeight()) {
        Image(painter = bgImage, contentDescription = null)
        Text(
            text = stringResource(R.string.tutorial_compose_title),
            fontSize = 24.sp,
            modifier = Modifier.padding(16.dp)
        )
        Text(
            text = stringResource(R.string.principle_article_compose),
            textAlign = TextAlign.Justify,
            modifier = Modifier.padding(16.dp)
        )
        Text(
            text = stringResource(R.string.tutorial_compose_body),
            textAlign = TextAlign.Justify,
            modifier = Modifier.padding(16.dp)
        )
    }
}

//@Preview(showBackground = true)
@Composable
fun ArticleComposePreview() {
    HappyBirthdayTheme {
        ArticleCompose()
    }
}


// --------------------------------------------------------------------------------------------------


@Composable
fun Task() {

    val taskImage = painterResource(id = R.drawable.ic_task_completed)

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier.fillMaxSize()
    ) {
        Image(painter = taskImage, contentDescription = null)
        Text(
            text = stringResource(R.string.task_complete_value),
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(top = 24.dp, bottom = 8.dp)
        )
        Text(text = stringResource(R.string.nice_work_value), fontSize = 16.sp)
    }
}


//@Preview(showBackground = true)
@Composable
fun TaskPreview() {
    HappyBirthdayTheme {
        Task()
    }
}


// --------------------------------------------------------------------------------------------------

@Composable
fun CardsComposable() {
    Column(modifier = Modifier.fillMaxWidth()) {
        Row(Modifier.weight(1f)) {
            Card(
                title = "Text composable",
                description = "Displays text and follows Material Design guidelines.",
                color = Color.Green,
                modifier = Modifier.weight(1f)
            )
            Card(
                title = "Image composable",
                description = "Creates a composable that lays out and draws a given Painter class object.",
                color = Color.Yellow,
                modifier = Modifier.weight(1f)
            )
        }
        Row(Modifier.weight(1f)) {
            Card(
                title = "Row composable",
                description = "A layout composable that places its children in a horizontal sequence.",
                color = Color.Cyan,
                modifier = Modifier.weight(1f)
            )
            Card(
                title = "Column composable",
                description = "A layout composable that places its children in a vertical sequence.",
                color = Color.LightGray,
                modifier = Modifier.weight(1f)
            )
        }
    }
}


@Composable
fun Card(title: String, description: String, color: Color, modifier: Modifier) {

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(color = color),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        Text(
            text = title,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 16.dp),
        )
        Text(
            text = description,
            textAlign = TextAlign.Justify,
            modifier = Modifier.padding(horizontal = 16.dp)
        )
    }

}

//@Preview(showBackground = true)
@Composable
fun CardsComposablePreview() {
    HappyBirthdayTheme {
//        Card(
//            title = "Column composable",
//            description = "A layout composable that places its children in a vertical sequence.",
//            color = Color.Green
//        )
        CardsComposable()
    }
}


// ------------------------------------------------------------------------------------------------

@Composable
fun CardPresentation() {
    Presentation()
}

@Composable
fun Presentation() {

    val image = painterResource(id = R.drawable.img20230620121108)
    val green = colorResource(id = R.color.Green_Principal)
    val backColor = colorResource(id = R.color.Background)
    Column(
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .background(color = backColor)
    ) {

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxWidth()
                .background(color = backColor)
                .padding(top = 50.dp)
        ) {
            Image(
                painter = image,
                contentDescription = "Avatar",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(200.dp)
                    .clip(
                        CircleShape
                    )
            )
            Text(
                text = "Daniel Ortega",
                fontSize = 30.sp,
                fontWeight = FontWeight.Light,
                modifier = Modifier.padding(vertical = 5.dp),
                color = Color.White
            )
            Text(
                text = "Android developer",
                fontSize = 15.sp,
                fontWeight = FontWeight.Bold,
                color = green
            )
        }
        Contact(green)
    }


}

@Composable
fun Contact(color: Color) {
    Column(
        verticalArrangement = Arrangement.Bottom,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .padding(bottom = 10.dp)
    ) {
        Divider(
            color = Color.White,
        )
        ItemContact(Icons.Rounded.Call, "+593 985 645 452", color)
        Divider(
            color = Color.White
        )
        ItemContact(Icons.Rounded.Share, "@AndroidDev", color)
        Divider(
            color = Color.White
        )
        ItemContact(Icons.Rounded.Email, "example@hotmail.com", color)
    }
}

@Composable
fun ItemContact(icon: ImageVector, text: String, color: Color) {
    Row(
        horizontalArrangement = Arrangement.Start, modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
    ) {
        Icon(
            icon,
            contentDescription = "Call icon",
            modifier = Modifier
                .padding(start = 40.dp, end = 25.dp),
            tint = color
        )
        Text(text = text, color = Color.White)
    }
}

@Preview(showBackground = true)
@Composable
fun CardPresentationPreview() {
    HappyBirthdayTheme {
        CardPresentation()
    }
}
