package com.mediafocusadmin.Pages

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.Send
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.mediafocusadmin.Navigation.Screen
import com.mediafocusadmin.R
import com.mediafocusadmin.data.MainViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainPage(
    navController: NavController,
    viewModel: MainViewModel
) {

    val payment by viewModel.payments.observeAsState(initial = emptyList())
    val expense by viewModel.expense.observeAsState(initial = emptyList())
    val totalBal by viewModel.totalBal
    val totalExp by viewModel.totalExp
    val totalPay by viewModel.totalPay

    Column(modifier =  Modifier.fillMaxSize()) {
        TopAppBar(
            title = { Text(
                text = stringResource(id = R.string.main_page_heading),
                style = MaterialTheme.typography.titleMedium
            ) },
            actions = {
                IconButton(onClick = { viewModel.sendNewPayment() }) {
                    Image(
                        imageVector = Icons.Default.Send,
                        contentDescription = stringResource(id = R.string.main_page_payment_action_desc)
                    )
                }
                IconButton(onClick = { navController.navigate(Screen.newUserPage.route) }) {
                    Image(
                        imageVector = Icons.Default.AddCircle,
                        contentDescription = stringResource(id = R.string.main_page_heading)
                    )
                }
            }
        )
        Column(modifier = Modifier
            .fillMaxSize()
            .padding(20.dp)) {
            Text(
                text = stringResource(id = R.string.collection_page_heading),
                style = MaterialTheme.typography.titleMedium
            )
            Spacer(modifier = Modifier.height(10.dp))
            Surface(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(160.dp),
                shape = RoundedCornerShape(15),
                shadowElevation = 3.dp
            ) {
                Column( modifier = Modifier
                    .fillMaxSize()
                    .padding(top = 20.dp)) {
//                    Spacer(modifier = Modifier.height(20.dp))
                    Row( modifier = Modifier.padding(horizontal = 20.dp) ) {
                        MediumText(text = if (payment.size > 0) payment.first().userId.toString() else "")
                        Spacer(modifier = Modifier.weight(0.1f))
                        MediumText(text = if (payment.size > 0) payment.first().amount.toString() else  "")
                    }
                    Spacer(modifier = Modifier.height(10.dp))
                    Row( modifier = Modifier.padding(horizontal = 20.dp) ) {
                        MediumText(
                            text = "+${payment.size - 1}",
                            color = Color.LightGray )
                    }
                    Spacer(modifier = Modifier.height(10.dp))
                    Row( modifier = Modifier.padding(horizontal = 20.dp) ) {
                        MediumText(text = stringResource(id = R.string.total_text))
                        Spacer(modifier = Modifier.weight(0.1f))
                        MediumText(text = totalPay.toString())
                    }
                    Spacer(modifier = Modifier.height(10.dp))
                    Row {
                        Spacer(modifier = Modifier.weight(0.1f))
                        TextButton(onClick = { navController.navigate(Screen.viewAllCollectionPage.route) }) {
                            MediumText(text = stringResource(id = R.string.view_all_text))
                            Image(
                                imageVector = Icons.Default.KeyboardArrowRight,
                                contentDescription = stringResource(id = R.string.view_all_text)
                            )
                        }
                    }
                }
            }
            Spacer(modifier = Modifier.height(10.dp))
            Text(
                text = stringResource(id = R.string.expense_page_heading),
                style = MaterialTheme.typography.titleMedium
            )
            Spacer(modifier = Modifier.height(10.dp))
            Surface(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(160.dp),
                shape = RoundedCornerShape(15),
                shadowElevation = 3.dp
            ) {
                Column( modifier = Modifier.fillMaxSize() ) {
                    Spacer(modifier = Modifier.height(20.dp))
                    if (!expense.isNullOrEmpty()){
                        Row( modifier = Modifier.padding(horizontal = 20.dp) ) {
                            MediumText(text = expense.first().desc.toString())
                            Spacer(modifier = Modifier.weight(0.1f))
                            MediumText(text = if (expense.size > 0) expense.first().amount.toString() else "")
                        }
                    }
                    Spacer(modifier = Modifier.height(10.dp))
                    Row( modifier = Modifier.padding(horizontal = 20.dp) ) {
                        MediumText(
                            text = "+ ${expense.size - 1}",
                            color = Color.LightGray
                        )
                    }
                    Spacer(modifier = Modifier.height(10.dp))
                    Row( modifier = Modifier.padding(horizontal = 20.dp) ) {
                        MediumText(text = stringResource(id = R.string.total_text),)
                        Spacer(modifier = Modifier.weight(0.1f))
                        MediumText(text = totalExp.toString())
                    }
                    Spacer(modifier = Modifier.height(10.dp))
                    Row {
                        Spacer(modifier = Modifier.weight(0.1f))
                        TextButton(onClick = { navController.navigate(Screen.viewAllExpPage.route) }) {
                            MediumText(text = stringResource(id = R.string.view_all_text),)
                            Image(
                                imageVector = Icons.Default.KeyboardArrowRight,
                                contentDescription = stringResource(id = R.string.view_all_text)
                            )
                        }
                    }
                }
            }
            Spacer(modifier = Modifier.height(50.dp))
            Row(modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp)
                .height(50.dp)
            ) {
                MediumText(text = stringResource(id = R.string.total_text),)
                Spacer(modifier = Modifier.weight(0.1f))
                MediumText(text = totalBal.toString())
            }
        }
    }
}

@Composable
fun MediumText(
    text: String,
    modifier: Modifier = Modifier,
    color: Color = Color.Black
) {
    Text(
        text = text,
        style = MaterialTheme.typography.labelMedium,
        modifier = modifier,
        color = color
    )
}