package eu.tutorials.courses

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import eu.tutorials.courses.DataSource.DataSource
import eu.tutorials.courses.Model.Topic
import eu.tutorials.courses.ui.theme.CoursesTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CoursesTheme {
                CourseGird(courseList = DataSource.topics)
            }
        }
    }
}

@Composable
fun CourseGird(
    modifier: Modifier=Modifier,
    courseList: List<Topic>
){
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        modifier = modifier.padding(8.dp).statusBarsPadding()
    ) {
        items (courseList)  {course ->
            CourseCard(topic = course)
        }
    }
}


@Composable
fun CourseCard(
    topic: Topic,
    modifier: Modifier = Modifier
){
    Card (
        modifier=Modifier
    ) {
        Row {
            Image(
                painter = painterResource(topic.image),
                contentDescription = stringResource(topic.name),
                modifier = Modifier
                    .size(width = 68.dp, height = 68.dp)
                    .fillMaxSize(),
                contentScale = ContentScale.Crop
            )
            Column {
                Text(
                    text = stringResource(topic.name),
                    modifier = Modifier.padding(
                        top = 16.dp,
                        start = 16.dp,
                        end = 16.dp,
                        bottom = 8.dp
                    ),
                    style = MaterialTheme.typography.bodyMedium,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
                Row (
                    verticalAlignment = Alignment.CenterVertically
                ){
                    Icon (
                        painter = painterResource(R.drawable.ic_grain) ,
                        contentDescription =null,
                        modifier = Modifier.padding(
                            start = 16.dp,
                            end = 8.dp)
                    )
                    Text(
                        text = topic.numberCourses.toString(),
                        style = MaterialTheme.typography.labelMedium
                    )
                }

            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true,
    showSystemUi = true)
@Composable
fun GreetingPreview() {
    CoursesTheme {
        CourseGird(courseList = DataSource.topics)
    }
}