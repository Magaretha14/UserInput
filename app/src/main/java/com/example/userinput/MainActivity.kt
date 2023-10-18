@file:OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterial3Api::class,
    ExperimentalMaterial3Api::class, ExperimentalMaterial3Api::class,
    ExperimentalMaterial3Api::class, ExperimentalMaterial3Api::class,
    ExperimentalMaterial3Api::class, ExperimentalMaterial3Api::class
)

package com.example.userinput

import android.os.Bundle
import android.print.PrintAttributes.Margins
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.userinput.ui.theme.UserInputTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            UserInputTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    FormLogin()
                }
            }
        }
    }
}

@Composable
fun FormLogin(modifier: Modifier = Modifier){
    var nama by remember { mutableStateOf(TextFieldValue("")) }
    var text by remember { mutableStateOf(TextFieldValue("")) }
    var text2 by remember { mutableStateOf(TextFieldValue("")) }
    var password by remember { mutableStateOf(TextFieldValue("")) }
    var resultText by remember { mutableStateOf("") }

    var gender by remember {
        mutableStateOf("")
    }

    val dropdownOptions = listOf("Option 1", "Option 2", "Option 3")
    var selectedOption by remember { mutableStateOf(dropdownOptions[0]) }
    var isExpanded by remember { mutableStateOf(false) }

    Column (
        modifier = Modifier,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally

    ){
        Text(text = "REGISTER")

        Spacer(modifier = Modifier.height(20.dp))

        OutlinedTextField(
            value = nama,
            leadingIcon = { Icon(imageVector = Icons.Default.Person, contentDescription = "personIcon")},
            onValueChange = {
                nama = it
            }, label = { Text(text = "Name")},
            placeholder = { Text(text = "Enter your name")}
        )
        OutlinedTextField(
            value = text,
            leadingIcon = { Icon(imageVector = Icons.Default.Email, contentDescription = "emailIcon")},
            onValueChange = {
                text = it
            }, label = { Text(text = "Email")},
            placeholder = { Text(text = "Enter your email")}
        )

        OutlinedTextField(
            value = text2,
            leadingIcon = { Icon(imageVector = Icons.Default.Call, contentDescription = "callicon")},
            onValueChange = {
                text2 = it
            }, label = { Text(text = "No. Hp")},
            placeholder = { Text(text = "Enter your No. Hp")}
        )

        OutlinedTextField(
            value = password,
            leadingIcon = { Icon(imageVector = Icons.Default.Lock, contentDescription = "pwicon")},
            onValueChange = {
                password = it
            }, label = { Text(text = "Password")},
            placeholder = { Text(text = "Enter your Password")}
        )

        //RadioButton
        Box(
            modifier = Modifier.fillMaxWidth().padding(start = 55.dp, top = 5.dp),
            contentAlignment = Alignment.CenterStart
        ){
            Row {
                Text(text = "Gender:")
                Column (verticalArrangement = Arrangement.Center){
                    Row (verticalAlignment = Alignment.CenterVertically){
                        RadioButton(selected = gender == "Male", onClick = { gender = "Male" })
                        Text(text = "Male")
                    }

                    Row (verticalAlignment = Alignment.CenterVertically){
                        RadioButton(selected = gender == "Female", onClick = { gender = "Female" })
                        Text(text = "Female")
                    }
                }

            }
        }

        Spacer(modifier = Modifier.height(12.dp))

        //Dropdown
        Box(
            modifier = Modifier.fillMaxWidth(),
            contentAlignment = Alignment.Center
        ){
            ExposedDropdownMenuBox(
                expanded = isExpanded,
                onExpandedChange = { isExpanded = it }
            ) {
                TextField(
                    value = selectedOption,
                    onValueChange = {},
                    readOnly = true,
                    trailingIcon = {
                        ExposedDropdownMenuDefaults.TrailingIcon(expanded = isExpanded)
                    },
                    colors = ExposedDropdownMenuDefaults.textFieldColors(),
                    modifier = Modifier.menuAnchor()
                )
                
                ExposedDropdownMenu(
                    expanded = isExpanded,
                    onDismissRequest = { isExpanded = false })
                {
                    for (option in dropdownOptions){
                        DropdownMenuItem(text = { Text(text = option)},
                            onClick = {
                                selectedOption = option
                                isExpanded = false})
                    }
                    
                }
                
            }
        }

        Spacer(modifier = Modifier.height(30.dp))

        Button(onClick = { resultText = " Name: ${nama.text} \nEmail: ${text.text} \n No. Hp: ${text2.text} \n Password: ${password.text}" +
                " \n Gender: ${gender}" +
                " \n Pilihan: ${selectedOption}" }) {
            Text(text = "Save")
        }

        Spacer(modifier = Modifier.height(30.dp))

        Text(text = resultText)

    }

}


@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    UserInputTheme {
        FormLogin()
    }
}