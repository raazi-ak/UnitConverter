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
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.raazi.unitconverter.ui.theme.UnitConverterTheme
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
    val treasureCount= remember{ mutableIntStateOf(0) }
    val direction = remember {
        mutableStateOf("North")
    }

    val set = remember {
        mutableIntStateOf(0)
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
            Text(text = "Unit Converter", modifier = Modifier.padding(30.dp))

            OutlinedTextField(value = "", onValueChange = {})
            Spacer(modifier = Modifier.height(16.dp))
            Row {
                Box {
                    Button(onClick = { /*TODO*/ }) {
                        Text(text = "Select")
                        Icon(Icons.Default.ArrowDropDown, contentDescription = "Dropdown Arrow")
                    }
                    DropdownMenu(expanded = false, onDismissRequest = { /*TODO*/ }) {
                        DropdownMenuItem(text = { Text("Centimetres") }, onClick = { /*TODO*/ })
                        DropdownMenuItem(text = { Text("Metres") }, onClick = { /*TODO*/ })
                        DropdownMenuItem(text = { Text("Feet") }, onClick = { /*TODO*/ })
                        DropdownMenuItem(text = { Text("Inches") }, onClick = { /*TODO*/ })
                        DropdownMenuItem(text = { Text("Litres") }, onClick = { /*TODO*/ })
                        DropdownMenuItem(text = { Text("Ounces") }, onClick = { /*TODO*/ })
                        DropdownMenuItem(text = { Text("Kilograms") }, onClick = { /*TODO*/ })
                        DropdownMenuItem(text = { Text("Pounds") }, onClick = { /*TODO*/ })

                    }
                }
                Spacer(modifier = Modifier.width(15.dp))
                Box {
                    Button(onClick = { /*TODO*/ }) {
                        Text(text = "Select")
                        Icon(Icons.Default.ArrowDropDown, contentDescription = "Dropdown Arrow")
                    }
                    DropdownMenu(expanded = false, onDismissRequest = { /*TODO*/ }) {
                        DropdownMenuItem(text = { Text("Centimetres") }, onClick = { /*TODO*/ })
                        DropdownMenuItem(text = { Text("Metres") }, onClick = { /*TODO*/ })
                        DropdownMenuItem(text = { Text("Feet") }, onClick = { /*TODO*/ })
                        DropdownMenuItem(text = { Text("Inches") }, onClick = { /*TODO*/ })
                        DropdownMenuItem(text = { Text("Litres") }, onClick = { /*TODO*/ })
                        DropdownMenuItem(text = { Text("Ounces") }, onClick = { /*TODO*/ })
                        DropdownMenuItem(text = { Text("Kilograms") }, onClick = { /*TODO*/ })
                        DropdownMenuItem(text = { Text("Pounds") }, onClick = { /*TODO*/ })

                    }
                }

            }
            Spacer(modifier = Modifier.height(7.dp))
            Text(text = "Result: ")

        }
       1-> Column(modifier=Modifier.fillMaxSize(), verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally){
                val lucky= remember {
                    mutableStateOf(" ")
                }
                val context = LocalContext.current
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
}

@Preview(showBackground = true)
@Composable
fun UnitConverterPreview(){
    UnitConverter()
}