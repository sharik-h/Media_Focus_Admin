package com.mediafocusadmin.Pages

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.mediafocusadmin.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ViewAllExp(navController: NavController) {

    var isNewOpen by remember { mutableStateOf(false) }
    var desc by remember { mutableStateOf("") }
    var amount by remember { mutableStateOf("") }
    val expenses = listOf(
        Pair("cus_1", "399"),
        Pair("cus_2", "299"),
        Pair("cus_3", "199"),
        Pair("cus_4", "99")
    )

    Column(modifier = Modifier.fillMaxSize()) {
        TopAppBar(
            title = {
                Text(
                    text = stringResource(id = R.string.expense_page_heading),
                    style = MaterialTheme.typography.titleMedium
                ) },
            navigationIcon = {
                IconButton(onClick = { navController.navigateUp()}) {
                    Image(
                        imageVector = Icons.Default.ArrowBack,
                        contentDescription = stringResource(id = R.string.navigate_up_text)
                    )
                } },
            actions = {
                IconButton(onClick = { isNewOpen = !isNewOpen }) {
                    Image(
                        imageVector = Icons.Default.AddCircle,
                        contentDescription = stringResource(id = R.string.expense_page_action_desc)
                    )
                }
            }
        )
        Row {
            if (isNewOpen){
                OutlinedTextField(
                    value = desc,
                    onValueChange = { desc = it },
                    modifier = Modifier.weight(0.6f)
                )
                Spacer(modifier = Modifier.weight(0.1f))
                OutlinedTextField(
                    value = amount,
                    onValueChange = { amount = it },
                    modifier = Modifier.weight(0.3f),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
                )
            }
        }
        Column(modifier = Modifier
            .fillMaxSize()
            .padding(20.dp)) {
            LazyColumn{
                items(items = expenses) {
                    Row( modifier = Modifier.padding(horizontal = 20.dp) ) {
                        Text(text = it.first, fontFamily = FontFamily(Font(R.font.quicksand)), fontSize = 18.sp)
                        Spacer(modifier = Modifier.weight(0.1f))
                        Text(text = it.second, fontFamily = FontFamily(Font(R.font.quicksand)), fontSize = 18.sp)
                    }
                    Spacer(modifier = Modifier.height(10.dp))
                }
            }
        }
    }
    Column(modifier = Modifier.fillMaxSize(), verticalArrangement = Arrangement.Bottom) {
        BottomAppBar {
            Spacer(modifier = Modifier.width(20.dp))
            MediumText(text = stringResource(id = R.string.total_text))
            Spacer(modifier = Modifier.weight(0.1f))
            MediumText(text = "3000")
            Spacer(modifier = Modifier.width(20.dp))
        }
    }
}