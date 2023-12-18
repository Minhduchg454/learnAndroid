package eu.tutorials.unitconverter


import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import eu.tutorials.unitconverter.ui.theme.UnitConverterTheme
import kotlin.math.roundToInt
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.sp


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            UnitConverterTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    UnitConverter()
                }
            }
        }
    }
}


@Composable
fun UnitConverter(){

    //Bien trang thai
    var inputValue by remember {mutableStateOf("")}
    var outputValue by remember {mutableStateOf("")}
    var inputUnit by remember {mutableStateOf("Meters")}
    var outputUnit by remember {mutableStateOf("Meters")}
    var iExpanded by remember {mutableStateOf(false)}
    var oExpanded by remember {mutableStateOf(false)}
    val conversionFactor = remember {mutableStateOf(1.00)} //He so chuyen doi don vi dau vao, mac dinh la m
    val oConversionFactor = remember {mutableStateOf(1.00)} //He so chuyen doi don vi dau ra, mac dinh la m

    //Dinh dang kieu chu
    val customTextStyle = TextStyle (
        fontFamily = FontFamily.Default,
        fontSize = 32.sp,
        color = Color.Blue
    )


    //Funtion Corver Unit
    fun converUnit (){
        // ?: - elvis operator , toan tu if else
        // Neu gia tri hop le thi nhan no nguoc lai gan no bang 0.0
        val inputValueDouble = inputValue.toDoubleOrNull() ?: 0.0

        //Lam tron den hai so thap phan 0.00
        val result = (inputValueDouble * conversionFactor.value * 100.0 / oConversionFactor.value).roundToInt()/100.0

        outputValue = result.toString()
    }

    Column (
        //Lap day toan bo man hinh
        //Sap xep theo chieu doc
        //Sap xep theo chieu ngang
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Here all the UI elements be stack below each other
        // Noi cac thanh phan duoc xep chong len nhau


        //Title App
        Text(
            "Unit Converter",
            style = customTextStyle)

        //Tao khoang cach giua cac phan tu
        Spacer(modifier = Modifier.height(16.dp))

        //Input box
        OutlinedTextField(
            value = inputValue,
            onValueChange = {
                //Tu dong chay sau khi gia tri nhap thay doi
                //Here goes what should happen, when the value of of our OutlineTextField changes}
                //Don vi dau vao phai la chuoi
                inputValue = it
                converUnit()
            }
            ,label = { Text(text = "Enter Value")})


        Spacer(modifier = Modifier.height(16.dp))
        Row {
            // Here all the UI elements be stack next each other
            // Noi cac thanh phan duoc dat canh nhau

            //Input box
            Box {
                //Input button
                Button(onClick = { iExpanded = true }) {
                    Text (text = inputUnit)
                    Icon (Icons.Default.ArrowDropDown,
                        contentDescription = "Arrow Down")
                }
                // expanded thiet lap ban dau la sai, nham de cho menu dong lai
                DropdownMenu(expanded = iExpanded, onDismissRequest = {
                    //Khi nhan vao bat cu cho nao ngoai bang chon thi dong lai menu
                    iExpanded = false }) {
                    DropdownMenuItem(
                        text = { Text("Centimeters") },
                        onClick = {
                            inputUnit = "Centimeters"
                            iExpanded = false
                            conversionFactor.value = 0.01 //cm -> m
                            converUnit()
                        }
                    )

                    DropdownMenuItem(
                        text = { Text("Meters") },
                        onClick = {
                            inputUnit = "Meters"
                            iExpanded = false
                            conversionFactor.value = 1.0 //m -> m
                            converUnit()
                        }
                    )

                    DropdownMenuItem(
                        text = { Text("Feet") },
                        onClick = {
                            inputUnit = "Feet"
                            iExpanded = false
                            conversionFactor.value = 0.3048 // feet -> m
                            converUnit()
                        }
                    )

                    DropdownMenuItem(
                        text = { Text("Millimeters") },
                        onClick = {
                            inputUnit = "Millimeters"
                            iExpanded = false
                            conversionFactor.value = 0.001 // mli -> m
                            converUnit()
                        }
                    )
                }
            }

            Spacer(modifier = Modifier.width(16.dp))

            //Output box
            Box {
                //output button
                Button(onClick = { oExpanded = true }) {
                    Text (text = outputUnit)
                    Icon (Icons.Default.ArrowDropDown,
                        contentDescription = "Arrow Down")
                }
                DropdownMenu(expanded = oExpanded, onDismissRequest = { oExpanded = false  }) {
                    DropdownMenuItem(
                        text = { Text("Centimeters") },
                        onClick = {
                            outputUnit = "Centimeters"
                            oExpanded = false
                            oConversionFactor.value = 0.01 //cm -> m
                            converUnit()
                        }
                    )

                    DropdownMenuItem(
                        text = { Text("Meters") },
                        onClick = {
                            outputUnit = "Meters"
                            oExpanded = false
                            oConversionFactor.value = 1.00 //m -> m
                            converUnit()
                        }
                    )

                    DropdownMenuItem(
                        text = { Text("Feet") },
                        onClick = {
                            outputUnit = "Feet"
                            oExpanded = false
                            oConversionFactor.value = 0.3048 //feet -> m
                            converUnit()
                        }
                    )

                    DropdownMenuItem(
                        text = { Text("Millimeters") },
                        onClick = {
                            outputUnit = "Millimeters"
                            oExpanded = false
                            oConversionFactor.value = 0.001 //ml -> m
                            converUnit()
                        }
                    )
                }
            }


        }

        Spacer(modifier = Modifier.height(16.dp))
        //Result Text
        Text(
            "Result: $outputValue $outputUnit",
            style = MaterialTheme.typography.headlineMedium)


        Text(
            text = "",
            modifier = Modifier.padding(50.dp))
    }

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Bottom,
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Text(text = "Design by HuuDuc")
    }

}



@Preview (showBackground = true)
@Composable
fun UnitConverterPreview (){
    Column {
        UnitConverter()
    }

}