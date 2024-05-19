package eu.tutorials.composearticle

import android.graphics.Paint.Align
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import eu.tutorials.composearticle.ui.theme.ComposeArticleTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ComposeArticleTheme {
                /*Greeting(
                    fisrtTest = stringResource(R.string.first_text),
                    secondTest = stringResource(R.string.second_text),
                    thirdTest = stringResource(R.string.third_text),
                    modifier = Modifier)
                 */
            }
        }
    }
}

@Composable
fun Greeting(fisrtTest: String, secondTest: String, thirdTest: String, modifier: Modifier = Modifier) {
    val image= painterResource(R.drawable.bg_compose_background)
    Column {
        Image(
            painter = image,
            contentDescription = null
        )
        Text(
            text = fisrtTest,
            fontSize = 24.sp,
            modifier = Modifier.padding(16.dp)
        )
        Text(
            text = secondTest,
            fontSize = TextUnit.Unspecified,
            modifier = Modifier.padding(
                start = 16.dp,
                end = 16.dp),
            textAlign = TextAlign.Justify
        )
        Text(
            text = secondTest,
            fontSize = TextUnit.Unspecified,
            modifier = Modifier.padding(16.dp),
            textAlign = TextAlign.Justify
        )

    }
}
@Composable
fun TaskManager(fisrtText3: String, secondTest3: String, modifier: Modifier){
    val image= painterResource(R.drawable.ic_task_completed)
    Column (
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxSize()
    ) {
        Image(
            painter = image,
            contentDescription = null
        )
        Text(
            text = fisrtText3,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(
                top = 24.dp,
                bottom = 8.dp
            )
        )
        Text(
            text = secondTest3,
            fontSize = 16.sp
        )
    }
}
@Composable
fun Quadrant(modifier: Modifier){
    Column (
        Modifier.fillMaxSize()
    ) {
        Row(modifier=Modifier.weight(1f)){
            Element(
                fisrtText4 = stringResource(R.string.title1_text),
                secondTest4 =stringResource(R.string.conten1_text),
                backgroundColor =Color(0xFFEADDFF) ,
                modifier =Modifier.weight(1f))
            Element(
                fisrtText4 = stringResource(R.string.title2_text),
                secondTest4 =stringResource(R.string.conten3_text),
                backgroundColor =Color(0xFFD0BCFF),
                modifier =Modifier.weight(1f))
        }
        Row(modifier=Modifier.weight(1f)){
            Element(
                fisrtText4 = stringResource(R.string.title3_text),
                secondTest4 =stringResource(R.string.conten3_text),
                backgroundColor =Color(0xFFB69DF8) ,
                modifier =Modifier.weight(1f))
            Element(
                fisrtText4 = stringResource(R.string.title4_text),
                secondTest4 =stringResource(R.string.conten4_text),
                backgroundColor =Color(0xFFF6EDFF),
                modifier =Modifier.weight(1f))
        }
    }
}


@Composable
fun Element (
    fisrtText4: String,
    secondTest4: String,
    backgroundColor: Color,
    modifier: Modifier=Modifier){
    //val colorSelect=Color(android.graphics.Color.parseColor(colorString))
    Column(
        modifier= modifier
            .fillMaxSize()
            .background(backgroundColor)
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Text(
            text=fisrtText4,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(
                bottom = 16.dp)
        )
        Text(
            text=secondTest4,
            fontSize = TextUnit.Unspecified,
            textAlign=TextAlign.Justify
        )
    }
}



@Preview(
    showBackground = true,
    showSystemUi = true
    )
@Composable
fun GreetingPreview(
    ) {
    ComposeArticleTheme {
        /*
        Greeting(
            fisrtTest = stringResource(R.string.first_text),
            secondTest = stringResource(R.string.second_text),
            thirdTest = stringResource(R.string.third_text),
            modifier = Modifier)
        */

        /*
        TaskManager(
            fisrtText3 = stringResource(R.string.first_text3),
            secondTest3 = stringResource(R.string.second_text3),
            modifier= Modifier
        )
         */
        Quadrant(modifier=Modifier)
    }
}

