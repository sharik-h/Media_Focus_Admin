package com.mediafocusadmin.Pages

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.mediafocusadmin.R
import com.mediafocusadmin.data.MainViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NewUserPage(navController: NavController, viewModel: MainViewModel) {

    val user by viewModel.newUser


    Column(modifier = Modifier.fillMaxSize()) {
        TopAppBar(
            title = {
                Text(
                    text = stringResource(id = R.string.new_user_page_heading),
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

            OutlinedTextField(
                value = user?.name ?: "",
                onValueChange = { viewModel.updateUser("name", it) },
                textStyle = MaterialTheme.typography.labelMedium,
                placeholder = { MediumText(text = "Name")},
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(10.dp))
            OutlinedTextField(
                value = user?.email ?: "",
                onValueChange = { viewModel.updateUser("email", it) },
                textStyle = MaterialTheme.typography.labelMedium,
                placeholder = { MediumText(text = "E-mail")},
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(10.dp))
            OutlinedTextField(
                value = user?.phone ?: "",
                onValueChange = { viewModel.updateUser("phone", it) },
                textStyle = MaterialTheme.typography.labelMedium,
                placeholder = { MediumText(text = "Phone")},
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(10.dp))
            OutlinedTextField(
                value = user?.date ?: "",
                onValueChange = { viewModel.updateUser("date", it)},
                textStyle = MaterialTheme.typography.labelMedium,
                placeholder = { MediumText(text = "Date")},
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(10.dp))
            Button(
                onClick = {
                    if (viewModel.isNewUserOk()){
                        viewModel.addNewUser()
                    }
                    viewModel.clearNewUser()
                    navController.navigateUp()
                          },
                modifier = Modifier.fillMaxWidth()
            ) {
                if(viewModel.isNewUserOk()){
                    MediumText(text = stringResource(id = R.string.save_button_text))
                }else {
                    MediumText(text = stringResource(id = R.string.cancel_text))
                }
            }
        }
    }
}