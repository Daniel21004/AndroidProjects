package com.example.artspacerapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.artspacerapp.domain.Card
import com.example.artspacerapp.domain.CircularList
import com.example.artspacerapp.domain.Node
import com.example.artspacerapp.ui.theme.ArtSpacerAppTheme


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ArtSpacerAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    ArtSpacerLayout()
                }
            }
        }
    }
}

@Composable
fun ArtSpacerLayout() {
    val cardList = CircularList()
    cardList.addNode(Card(R.drawable.efiel_tower, "La Torre Efiel", "Leonardo Da Vinci", 2004))
    cardList.addNode(Card(R.drawable.calcifer, "Calcifer", "Mampa", 2000))
    cardList.addNode(Card(R.drawable.archer_boy_minimalista, "Minimalist Boy", "Unkown", 2012))

    var cardNode by remember { mutableStateOf(cardList.getByIndex(0)) }

    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        Surface(
            modifier = Modifier.weight(3f)
        ) {
            ImageVisor(
                card = cardNode,
                modifier = Modifier
                    .fillMaxSize()
            )
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxSize()
            ) {
                Button(
                    R.drawable.arrow_left,
                    "Arrow left",
                    { cardNode = cardNode.previous!! },
                    "Previous card"
                )
                Button(
                    R.drawable.arrow_right,
                    "Arrow right",
                    { cardNode = cardNode.next!! },
                    "Next card"
                )
            }
        }
        DescriptionArea(
            card = cardNode, modifier = Modifier
                .weight(1f)
        )
    }
}

@Composable
fun ImageVisor(card: Node, modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
    ) {
        Surface {
            Image(
                painter = painterResource(id = card.value.image),
                contentDescription = card.value.title,
                contentScale = ContentScale.Fit,
                modifier = Modifier
                    .fillMaxSize()
//                    .border(1.dp, Color.Red)
            )
        }
    }
}

@Composable
fun Button(
    imageID: Int,
    contentDescription: String,
    lambdaFunction: () -> Unit,
    labelClick: String,
) {
    val image = painterResource(id = imageID)

    Box(
        modifier = Modifier
            .height(60.dp)
            .width(50.dp)
            .background(colorResource(id = R.color.gray_description))
            .wrapContentSize(align = Alignment.Center)
            .clickable(
                onClick = lambdaFunction,
                onClickLabel = labelClick
            )
    ) {
        Image(painter = image, contentDescription = contentDescription)
    }
}

@Composable
fun DescriptionArea(card: Node, modifier: Modifier = Modifier) {
    val fontFamily = FontFamily(Font(R.font.vercetti, FontWeight.Normal))
    val fontSizeTitle = 25.88.sp
    val fontSizeDescription = 16.sp
    val shapeColor = colorResource(
        id = R.color.gray_description
    )

    Column(
        modifier = modifier
            .padding(horizontal = 20.dp, vertical = 25.dp)
            .fillMaxSize()
//            .border(1.dp, Color.Blue)
    ) {
        Text(
            text = card.value.title,
            fontSize = fontSizeTitle,
            fontFamily = fontFamily
        )
        Row(
            horizontalArrangement = Arrangement.spacedBy(8.dp, Alignment.CenterHorizontally),
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(top = 10.dp)
        ) {
            Text(
                text = card.value.author,
                fontSize = fontSizeDescription,
                fontFamily = fontFamily,
                color = shapeColor
            )
            BoxShape(shape = CircleShape, color = shapeColor)
            Text(
                text = "(${card.value.year})",
                fontSize = fontSizeDescription,
                fontFamily = fontFamily,
                color = shapeColor
            )
        }
    }
}

@Composable
fun BoxShape(shape: Shape, color: Color) {
    Column {
        Box(
            modifier = Modifier
                .size(5.dp)
                .clip(shape)
                .background(color)
                .semantics { contentDescription = "Circle shape" }
        )
    }
}


@Preview(showBackground = true, showSystemUi = true)
@Composable
fun ArtSpacerLayoutPreview() {
    ArtSpacerAppTheme {
        ArtSpacerLayout()
    }
}