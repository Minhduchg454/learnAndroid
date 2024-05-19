package eu.tutorials.businesscard

import android.inputmethodservice.Keyboard
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
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Call
import androidx.compose.material.icons.rounded.Email
import androidx.compose.material.icons.rounded.Home
import androidx.compose.material.icons.rounded.Share
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.movableContentOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import eu.tutorials.businesscard.ui.theme.BusinessCardTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            BusinessCardTheme {
                Card(
                    fullName = stringResource(R.string.full_name_text),
                    title = stringResource(R.string.title_text),
                    numberPhone = stringResource(R.string.number_phone_text),
                    company = stringResource(R.string.company_text),
                    email = stringResource(R.string.email_text),
                    modifier= Modifier
                )
                }
            }
        }
    }


@Composable
fun Card(fullName: String,title: String,numberPhone: String,company: String, email: String, modifier: Modifier = Modifier) {
    val imageLogo= painterResource(R.drawable.android_logo)
    Column (
        Modifier
            .fillMaxSize()
            .background(Color(0xFF33CC33)),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ){
        Box (
            modifier= Modifier
                .size(105.dp)
                .background(Color.Black),
            contentAlignment = Alignment.Center
        ){
            Image(
                painter = imageLogo,
                contentDescription = null,
            )
        }
        Text(
            text = fullName,
            fontSize = 40.sp,
            modifier = modifier.padding(2.dp),
            textAlign = TextAlign.Center
        )
        Text(
            text=title,
            color= Color.DarkGray,
            fontSize = 20.sp,
            modifier = modifier.padding(2.dp),
            textAlign = TextAlign.Center
        )
    }


    Column (
        Modifier
            .fillMaxSize()
            .background(Color.Transparent),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Bottom
    ){
        Row{
                Column {

                    val iconNumber=Icons.Rounded.Call
                    Icon(
                        imageVector = iconNumber,
                        contentDescription = "Call Icon",
                        tint = Color.Black,
                        modifier = Modifier.size(24.dp)
                    )

                    val iconCompany=Icons.Rounded.Share
                    Icon(
                        imageVector = iconCompany,
                        contentDescription = "Comany Icon",
                        tint = Color.Black,
                        modifier = Modifier.size(24.dp)
                    )

                    val iconEmail=Icons.Rounded.Email
                    Icon(
                        imageVector = iconEmail,
                        contentDescription = "Email Icon",
                        tint = Color.Black,
                        modifier = Modifier.size(24.dp)
                    )
                }
            Column {
                Text(
                    text = numberPhone,
                    color = Color.Black,
                    fontSize = 15.sp,
                    modifier = modifier.padding(2.dp),
                    textAlign = TextAlign.Center
                )
                Text(
                    text = company,
                    color = Color.Black,
                    fontSize = 15.sp,
                    modifier = modifier.padding(2.dp),
                    textAlign = TextAlign.Center
                )

                Text(
                    text = email,
                    color = Color.Black,
                    fontSize = 15.sp,
                    modifier = modifier.padding(2.dp),
                    textAlign = TextAlign.Center
                )
            }
        }
    }
}

@Preview(
    showBackground = true,
    showSystemUi = true)
@Composable
fun GreetingPreview() {
    BusinessCardTheme {
        Card(
            fullName = stringResource(R.string.full_name_text),
            title = stringResource(R.string.title_text),
            numberPhone = stringResource(R.string.number_phone_text),
            company = stringResource(R.string.company_text),
            email = stringResource(R.string.email_text),
            modifier= Modifier
        )
    }
}