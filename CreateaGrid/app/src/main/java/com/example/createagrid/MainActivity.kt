package com.example.createagrid

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.createagrid.data.DataSource
import com.example.createagrid.model.Topic
import com.example.createagrid.ui.theme.CreateAGridTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CreateAGridTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    GridTopics(DataSource.topics, Modifier.fillMaxSize())
                }
            }
        }
    }
}

@Composable
fun GridTopics(topics: List<Topic>, modifier: Modifier = Modifier) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        contentPadding = PaddingValues(dimensionResource(id = R.dimen.dimension_small)), // Applicated to the LazyRow composable
        verticalArrangement = Arrangement.spacedBy(dimensionResource(id = R.dimen.dimension_small)), // Applicated to the elements of the LazyRow
        horizontalArrangement = Arrangement.spacedBy(dimensionResource(id = R.dimen.dimension_small)),
        modifier = modifier
    ) {
        items(topics) { topic ->
            TopicCard(topic = topic, Modifier.height(68.dp))
        }
    }

}

@Composable
fun TopicCard(topic: Topic, modifier: Modifier = Modifier) {
    Card(modifier = modifier) {
        Row() {
            Image(
                painter = painterResource(id = topic.image),
                contentDescription = stringResource(id = topic.name),
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .weight(1f)
                    .fillMaxSize()
            )
            Column(
                horizontalAlignment = Alignment.Start,
                modifier = Modifier
                    .weight(2f)
                    .padding(
                        dimensionResource(id = R.dimen.dimension_medium),
                        dimensionResource(id = R.dimen.dimension_medium),
                        dimensionResource(id = R.dimen.dimension_medium),
                        dimensionResource(id = R.dimen.dimension_small)
                    )
            ) {
                Text(
                    text = stringResource(id = topic.name),
                    style = MaterialTheme.typography.bodyMedium
                )
                Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.dimension_small)))
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_grain),
                        contentDescription = null
                    )
                    Spacer(modifier = Modifier.width(dimensionResource(id = R.dimen.dimension_small)))
                    Text(
                        text = topic.numberOfAssociatedCourses.toString(),
                        style = MaterialTheme.typography.labelMedium
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun GridTopicsPreview() {
    GridTopics(topics = DataSource.topics, Modifier.fillMaxSize())
}

@Preview(showBackground = true)
@Composable
fun TopicCardPreview() {
    CreateAGridTheme {
        val topic = Topic(R.drawable.photography, R.string.photography, 321)
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            TopicCard(topic, Modifier.height(68.dp))
        }
    }
}
