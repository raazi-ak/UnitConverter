package com.raazi.unitconverter

import android.os.Bundle
import android.widget.Space
import android.widget.Toast
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
import androidx.compose.foundation.layout.size
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
import androidx.compose.runtime.MutableIntState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.raazi.unitconverter.ui.theme.UnitConverterTheme
import kotlin.math.roundToInt
import kotlin.random.Random

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


    val set = remember {
        mutableIntStateOf(0)
    }
    var inputValue by remember {
        mutableStateOf("")
    }
    var outputValue by remember {
        mutableStateOf("")
    }
    var inputUnit by remember {
        mutableStateOf("Metres")
    }
    var outputUnit by remember {
        mutableStateOf("Metres")
    }
    var iExpanded by remember {
        mutableStateOf(false)
    }
    var oExpanded by remember {
        mutableStateOf(false)
    }
    val conversionFactor = remember {
        mutableStateOf(1.0)
    }
    val oConversionFactor = remember {
        mutableStateOf(1.0)
    }
    fun ConvertUnits(){
        var inputValueDouble= inputValue.toDoubleOrNull() ?: 0.0
        var result = (inputValueDouble * conversionFactor.value * 100.0/oConversionFactor.value).roundToInt()/100
        outputValue=result.toString()

    }
    when(set.intValue) {
       0-> Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Box(modifier = Modifier.padding(5.dp)){
                Button(onClick = { set.intValue=1 }) {
                    Text("Go to Captain Game")

                }
            }
            Text(text = "Unit Converter", modifier = Modifier.padding(30.dp), style = MaterialTheme.typography.headlineLarge)

            OutlinedTextField(value = inputValue,
                onValueChange = {inputValue=it
                                ConvertUnits()},
                label = { Text(text = "Enter value")})
            Spacer(modifier = Modifier.height(16.dp))
            Row {
                Box {//input box
                    Button(onClick = { iExpanded=true }) {
                        Text(text = inputUnit)
                        Icon(Icons.Default.ArrowDropDown, contentDescription = "Dropdown Arrow")
                    }
                    DropdownMenu(expanded = iExpanded, onDismissRequest = { iExpanded=false }) {
                        DropdownMenuItem(text = { Text("Centimetres") },
                            onClick = { iExpanded=false
                                inputUnit="Centimetres"
                                conversionFactor.value=0.01
                                ConvertUnits()
                        })
                        DropdownMenuItem(text = { Text("Metres") }, onClick = {  iExpanded=false
                            inputUnit="Metres"
                            conversionFactor.value=1.0
                            ConvertUnits()

                        })
                        DropdownMenuItem(text = { Text("Feet") }, onClick = { iExpanded=false
                            inputUnit="Feet"
                            conversionFactor.value=3.048
                            ConvertUnits() })
                        DropdownMenuItem(text = { Text("Millimetres") }, onClick = { iExpanded=false
                            inputUnit="Millimetres"
                            conversionFactor.value=0.001
                            ConvertUnits() })

                    }
                }
                Spacer(modifier = Modifier.width(15.dp))
                Box {//output box
                    Button(onClick = { oExpanded=true }) {
                        Text(text = outputUnit)
                        Icon(Icons.Default.ArrowDropDown, contentDescription = "Dropdown Arrow")
                    }
                    DropdownMenu(expanded = oExpanded, onDismissRequest = { oExpanded=false }) {
                        DropdownMenuItem(text = { Text("Centimetres") }, onClick = { oExpanded=false
                            outputUnit="Centimetres"
                            oConversionFactor.value=0.01
                            ConvertUnits() })
                        DropdownMenuItem(text = { Text("Metres") }, onClick = { oExpanded=false
                            outputUnit="Metres"
                            oConversionFactor.value=1.0
                            ConvertUnits() })
                        DropdownMenuItem(text = { Text("Feet") }, onClick = { oExpanded=false
                            outputUnit="Feet"
                            conversionFactor.value=3.048
                            ConvertUnits() })
                        DropdownMenuItem(text = { Text("Millimetres") }, onClick = { oExpanded=false
                            outputUnit="Millimetres"
                            conversionFactor.value=0.001
                            ConvertUnits() })


                    }
                }

            }
            Spacer(modifier = Modifier.height(7.dp))
            Text(text = "Result: $outputValue $outputUnit", style = MaterialTheme.typography.headlineSmall)

        }
       1-> CaptainGame(set)
    }
}
@Composable
fun CaptainGame(set:MutableIntState){
    val treasureCount= remember{ mutableIntStateOf(0) }
    val direction = remember {
        mutableStateOf("North")
    }
    val lucky= remember {
        mutableStateOf(" ")
    }
    val context = LocalContext.current
    Column(modifier=Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally)
    {

        Text("Treasure count is ${treasureCount.intValue}")
        Text(text = "Current Direction: ${direction.value}")
        Text(text = " ${lucky.value}")

        Button(onClick = {
            direction.value = "North"
            if (Random.nextBoolean()){

                treasureCount.intValue+=1
                lucky.value= "lucky"
                Toast.makeText(context, "You are lucky", Toast.LENGTH_LONG).show()
            }
            else{
                lucky.value= "not lucky"
            }

        }) {
            Text(text = "Sail North")
        }
        Button(onClick = {
            direction.value = "East"
            if (Random.nextBoolean()){
                treasureCount.intValue+=1
                lucky.value= "lucky"
                Toast.makeText(context, "You are lucky", Toast.LENGTH_LONG).show()
            }
            else{
                lucky.value= "not lucky"
            }
        }) {
            Text(text = "Sail East")
        }
        Button(onClick = {
            direction.value = "South"
            if (Random.nextBoolean()){
                treasureCount.intValue+=1
                lucky.value= "lucky"
                Toast.makeText(context, "You are lucky", Toast.LENGTH_LONG).show()
            }
        }) {
            Text(text = "Sail South")
        }
        Button(onClick = {
            direction.value = "West"
            if (Random.nextBoolean()){
                treasureCount.intValue+=1
                lucky.value= "lucky"
                Toast.makeText(context, "You are lucky", Toast.LENGTH_LONG).show()

            }else{
                lucky.value= "not lucky"
            }
        }) {
            Text(text = "Sail West")
        }

        Box(modifier = Modifier.padding(10.dp)){
            Button(onClick = { set.intValue=0 }) {
                Text("Go to Unit Converter")

            }
        }

    }
}

@Preview(showBackground = true)
@Composable
fun UnitConverterPreview(){
    UnitConverter()
}