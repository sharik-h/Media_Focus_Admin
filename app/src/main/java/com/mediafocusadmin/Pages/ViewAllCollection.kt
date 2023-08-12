package com.mediafocusadmin.Pages

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.mediafocusadmin.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ViewAllCollections(navController: NavController) {

    val listOfPaidCus = listOf(
        Pair("cus_1", "399"),
        Pair("cus_2", "299"),
        Pair("cus_3", "199"),
        Pair("cus_4", "99")
    )

    Column(modifier = Modifier.fillMaxSize()) {
        TopAppBar(
            title = {
                Text(
                    text = stringResource(id = R.string.collection_page_heading),
                    style = MaterialTheme.typography.titleMedium
                ) },
            navigationIcon = {
                IconButton(onClick = { navController.navigateUp() }) {
                    Image(
                        imageVector = Icons.Default.ArrowBack,
                        contentDescription = stringResource(id = R.string.navigate_up_text)
                    )
                }
            }
        )

        Column(modifier = Modifier
            .fillMaxSize()
            .padding(20.dp)) {
            LazyColumn{
                item {
                    Text(
                        text = stringResource(id = R.string.paid_group_heading),
                        style = MaterialTheme.typography.titleMedium
                    )
                }
                items(items = listOfPaidCus) {
                    Row( modifier = Modifier.padding(horizontal = 20.dp) ) {
                        MediumText(text = it.first)
                        Spacer(modifier = Modifier.weight(0.1f))
                        MediumText(text = it.second)
                    }
                    Spacer(modifier = Modifier.height(10.dp))
                }
                item {
                    Text(
                        text = stringResource(id = R.string.un_paid_group_heading),
                        style = MaterialTheme.typography.titleMedium
                    )
                }
                items(items = listOfPaidCus) {
                    Row( modifier = Modifier.padding(horizontal = 20.dp) ) {
                        MediumText(text = it.first)
                        Spacer(modifier = Modifier.weight(0.1f))
                        MediumText(text = it.second)
                    }
                    Spacer(modifier = Modifier.height(10.dp))
                }
            }
        }
    }
}