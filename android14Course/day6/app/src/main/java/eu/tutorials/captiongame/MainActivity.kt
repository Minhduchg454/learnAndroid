package eu.tutorials.captiongame

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import eu.tutorials.captiongame.ui.theme.CaptionGameTheme
import kotlin.random.Random

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CaptionGameTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    captionGame()
                }
            }
        }
    }
    @Composable
    fun captionGame(){

        //Ghi nho lich su va cap nhat lich su
        val treasuresFound = remember { mutableStateOf(0) }
        val direction = remember { mutableStateOf("North") }
        val stromOrTreasur = remember { mutableStateOf("") }
        val healthPoint = remember { mutableStateOf(100) }

        Column (
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ){
            Text(text = "Treasure Found: ${treasuresFound.value}")
            Text(text = "Current Direction: ${direction.value}")
            if (healthPoint.value == 0){
                Text("You are die")
            }else{
                Text(text = "Health Point: ${healthPoint.value} HP")
            }

            Text(text = stromOrTreasur.value)

            Spacer(modifier = Modifier.height(16.dp))
            //Chon huong va ra ket qua ngau nhien
            Button(onClick = {
                direction.value = "East"
                if (Random.nextBoolean()){
                    treasuresFound.value +=1
                    stromOrTreasur.value = "Found a Treasure!"
                }else{
                    stromOrTreasur.value = "Storm Ahead!"
                    healthPoint.value -=1
                }
            }) {
                Text("Sail East")
            }

            Button(onClick = {
                direction.value = "West"
                if (Random.nextBoolean()){
                    treasuresFound.value +=1
                    stromOrTreasur.value = "Found a Treasure!"
                }else{
                    stromOrTreasur.value = "Storm Ahead!"
                    healthPoint.value -=1
                }
            }) {
                Text("Sail West")
            }

            Button(onClick = {
                direction.value = "North"
                if (Random.nextBoolean()){
                    treasuresFound.value +=1
                    stromOrTreasur.value = "Found a Treasure!"
                }else{
                    stromOrTreasur.value = "Storm Ahead!"
                    healthPoint.value -=1
                }
            }) {
                Text("Sail North")
            }

            Button(onClick = {
                direction.value = "South"
                if (Random.nextBoolean()){
                    treasuresFound.value +=1
                    stromOrTreasur.value = "Found a Treasure!"
                }else{
                    stromOrTreasur.value = "Storm Ahead!"
                    healthPoint.value -=1
                }
            }) {
                Text("Sail South")
            }


        }
    }

}

