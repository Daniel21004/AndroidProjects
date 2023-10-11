package com.example.allof30days

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.allof30days.data.EveryThing
import com.example.allof30days.data.LoadData
import com.example.allof30days.ui.theme.AllOf30DaysTheme


@Composable
fun ThingsList(
    things: List<EveryThing>,
    contentPaddingValues: PaddingValues,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        contentPadding = contentPaddingValues,
        modifier = modifier
    ) {
        itemsIndexed(things) { index, thing ->
            CardItem(
                numberDay = index + 1,
                everyThing = thing,
                modifier = Modifier.padding(dimensionResource(id = R.dimen.small_dimen))
            )
        }
    }
}


@Composable
fun CardItem(numberDay: Int, everyThing: EveryThing, modifier: Modifier = Modifier) {
    var isCollapse by remember { mutableStateOf(true) }

    Card(
        modifier = modifier, // min 162 dp
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(
            modifier = Modifier
        ) {
            Surface(
                modifier = Modifier
                    .fillMaxWidth()
                    .heightIn(
                        min = dimensionResource(id = R.dimen.min_height_surface_dimen),
                        max = dimensionResource(id = R.dimen.max_height_surface_dimen)
                    )
            ) {
                Image(
                    painter = painterResource(id = everyThing.image),
                    contentDescription = stringResource(id = everyThing.title),
                    contentScale = ContentScale.FillHeight,
                    modifier = Modifier
                        .fillMaxSize()
                )
                Column(
                    verticalArrangement = Arrangement.Bottom,
                    horizontalAlignment = Alignment.End,
                    modifier = Modifier.fillMaxSize()
                ) {
                    Box(
                        contentAlignment = Alignment.Center,
                        modifier = Modifier
                            .size(
                                width = dimensionResource(id = R.dimen.width_badge_dimen),
                                height = dimensionResource(id = R.dimen.height_badge_dimen)
                            )
                            .background(Color.Gray) // TODO corregir el color
                    ) {
                        Text(
                            text = stringResource(
                                id = R.string.day_number,
                                numberDay
                            ), // TODO corregir el color
                            style = MaterialTheme.typography.headlineSmall
                        )
                    }
                }

            }
            Column(
                modifier = Modifier
                    .wrapContentWidth(align = Alignment.Start)
                    .padding(
                        start = dimensionResource(id = R.dimen.medium_dimen),
                        top = dimensionResource(id = R.dimen.small_dimen),
                        end = dimensionResource(id = R.dimen.medium_dimen),
                        bottom = dimensionResource(id = R.dimen.medium_dimen)
                    )
            ) {
                Text(
                    text = stringResource(id = everyThing.title),
                    style = MaterialTheme.typography.titleMedium
                )
                ClickableText(
                    onClick = { isCollapse = !isCollapse },
                    modifier = Modifier.animateContentSize(),
                    text = AnnotatedString(stringResource(id = everyThing.description)),
                    style = MaterialTheme.typography.bodySmall,
                    maxLines = if (isCollapse) 3 else 10,
                    overflow = TextOverflow.Ellipsis // Supress the end lines of the text with ...
                )
            }
        }
    }
}

@Preview
@Composable
fun CardItemPreview() {
    val everyThingExample =
        EveryThing(R.drawable.archer_boy_minimalista, R.string.calcifer, R.string.lorem_ipsum)
    val index = 1
    AllOf30DaysTheme {
        CardItem(
            numberDay = index,
            everyThing = everyThingExample,
        )
    }
}

@Preview
@Composable
fun ThingsListPreview() {
    AllOf30DaysTheme {
        ThingsList(things = LoadData.data, contentPaddingValues = PaddingValues(16.dp))
    }
}
