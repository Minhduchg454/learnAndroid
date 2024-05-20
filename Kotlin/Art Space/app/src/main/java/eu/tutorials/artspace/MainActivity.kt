package eu.tutorials.artspace

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import eu.tutorials.artspace.ui.theme.ArtSpaceTheme
import java.nio.file.WatchEvent

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ArtSpaceTheme {
                    ArtSpaceApp()
            }
        }
    }
}

@Composable
fun ArtSpaceApp() {
    var value by remember { mutableStateOf(1)}
    var nextValue: ()->Unit={
        if(value==3){
            value=1
        }else{
            value++
        }
    }
    var previousValue: ()->Unit={
        if(value==1){
            value=3
        }else{
            value--
        }
    }

    var imageResource = when(value){
        1 -> R.drawable.damian_markutt_ea7qmegxqsm_unsplash
        2 -> R.drawable.jeremy_bishop_b_iz9tkrw6a_unsplash
        else -> R.drawable.jorge_fernandez_wh3yxjwmc5o_unsplash
    }

    var titleContent = when(value){
        1 -> R.string.title_art_one
        2 -> R.string.title_art_two
        else -> R.string.title_art_three
    }

    var artistConten = when(value){
        1 -> R.string.artist_one
        2 -> R.string.artist_two
        else -> R.string.artist_three
    }

    Column (
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Spacer(modifier = Modifier.height(40.dp))

        Box(
            modifier = Modifier
                .size(500.dp)
                .shadow(16.dp, RoundedCornerShape(10.dp))
                .background(
                    color = Color(0xffECEBF4),
                    shape = RoundedCornerShape(10.dp)
                )
        ) {
            Image(
                painter = painterResource(imageResource),
                contentDescription = stringResource(titleContent),
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp)
            )
        }

        ArtworkDesciptor(
            titleContent = titleContent,
            artistContent = artistConten,
            modifier = Modifier
                .background(
                    color = Color(0xffECEBF4),
                    shape = RoundedCornerShape(10.dp)
                )
                .padding(16.dp)
        )

        Row (
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    top = 20.dp,
                    bottom = 20.dp
                ),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ){
            ButtonAction(
                label = R.string.previous_button,
                onValueChange = previousValue,
                modifier = Modifier.weight(1f)
            )

            Spacer(modifier=Modifier.width(24.dp))

            ButtonAction(
                label = R.string.next_button,
                onValueChange = nextValue,
                modifier = Modifier.weight(1f)
                )
        }


    }


}

@Composable
fun ButtonAction (
    @StringRes label: Int,
    onValueChange:()->Unit,
    modifier: Modifier = Modifier
){
    Button(
        onClick = onValueChange,
        modifier = modifier
            .fillMaxWidth()
            .padding(8.dp)) {
        Text(
            text = stringResource(label),
            textAlign = TextAlign.Center
        )
    }
}

@Composable
fun ArtworkDesciptor(
    modifier: Modifier = Modifier,
    @StringRes titleContent: Int,
    @StringRes artistContent: Int,
    ){
    Column (
        modifier=modifier
    ){
        Text(
            text= stringResource(titleContent),
            textAlign = TextAlign.Left,
            fontWeight = FontWeight.Bold,
            fontSize = 20.sp,
            modifier = Modifier.padding(
                bottom = 4.dp
            )
        )
        Text(
            text= stringResource(artistContent),
            textAlign = TextAlign.Left,
            fontWeight = FontWeight.Bold,
            fontSize = 10.sp,
            modifier = Modifier
        )
    }
}






@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ArtSpaceTheme {
        ArtSpaceApp()
    }
}