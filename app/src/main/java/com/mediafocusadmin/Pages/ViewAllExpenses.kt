package com.mediafocusadmin.Pages

import androidx.compose.foundation.Image
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.mediafocusadmin.R
import com.mediafocusadmin.data.MainViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ViewAllExp(
    navController: NavController,
    viewModel: MainViewModel
) {

    var isNewOpen by remember { mutableStateOf(false) }
    val newExp by viewModel.newExp
    val allExp by viewModel.expense.observeAsState(initial = emptyList())
    val totalExp by viewModel.totalExp

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
                IconButton(onClick = {
                    if (viewModel.isNewExpOk()) { viewModel.addNewExpIntoRoom() }
                    isNewOpen = !isNewOpen
                }
                ) {
                    Image(
                        imageVector = if(isNewOpen && viewModel.isNewExpOk())Icons.Default.CheckCircle
                                      else if(!isNewOpen) Icons.Default.AddCircle
                                      else Icons.Default.Close,
                        contentDescription = stringResource(id = R.string.expense_page_action_desc)
                    )
                }
            }
        )
        if (isNewOpen){
        Surface(
            shadowElevation = 3.dp,
            modifier = Modifier.padding(10.dp),
            shape = RoundedCornerShape(10)
        ) {
            Row(modifier = Modifier
            .fillMaxWidth()
            .padding(20.dp)) {

                    OutlinedTextField(
                        value = newExp?.desc ?: "",
                        onValueChange = { viewModel.updateExp("desc", it) },
                        modifier = Modifier.weight(0.6f),
                        placeholder = { MediumText(text = "Description")},
                        keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next)
                    )
                    Spacer(modifier = Modifier.weight(0.1f))
                    OutlinedTextField(
                        value = newExp?.amount.toString() ?: " ",
                        onValueChange = { viewModel.updateExp("amount", it)},
                        modifier = Modifier.weight(0.3f),
                        placeholder = { MediumText(text = "Amount")},
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number, imeAction = ImeAction.Done),
                        keyboardActions = KeyboardActions {
                            isNewOpen = false
                        }
                    )
                }
            }
        }
        Column(modifier = Modifier
            .fillMaxSize()
            .padding(top = 20.dp, bottom = 80.dp)) {
            LazyColumn{
                items(items = allExp!!) {item ->
                    var open by remember { mutableStateOf(false) }
                    if (open){
                        Surface(
                            shadowElevation = 3.dp,
                            modifier = Modifier
                                .pointerInput(Unit) {
                                    detectTapGestures(onTap = { open = false })
                                }
                                .padding(10.dp),
                            shape = RoundedCornerShape(10)
                        ) {
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                modifier = Modifier
                                .fillMaxWidth()
                                .height(50.dp)
                                .padding(10.dp)) {
                                MediumText(text = item.desc.toString())
                                Spacer(modifier = Modifier.weight(0.3f))
                                MediumText(text = item.amount.toString())
                                Spacer(modifier = Modifier.weight(0.1f))
                                IconButton(onClick = {
                                    viewModel.deleteExp(item)
                                    open = false
                                }) {
                                    Image(
                                        imageVector = Icons.Default.Delete,
                                        contentDescription = stringResource(id = R.string.delete_text)
                                    )
                                }
                            }
                        }
                    }else {
                        Row(
                            modifier = Modifier
                                .pointerInput(Unit) {
                                    detectTapGestures(onLongPress = { open = true })
                                }
                                .padding(horizontal = 20.dp)
                        ) {
                            MediumText(text = item.desc.toString())
                            Spacer(modifier = Modifier.weight(0.1f))
                            MediumText(text = item.date.toString(), color = Color.LightGray)
                            Spacer(modifier = Modifier.weight(0.1f))
                            MediumText(text = item.amount.toString())
                        }
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
            MediumText(text = totalExp.toString())
            Spacer(modifier = Modifier.width(20.dp))
        }
    }
}