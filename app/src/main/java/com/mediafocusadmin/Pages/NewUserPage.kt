package com.mediafocusadmin.Pages

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardOptions
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
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.mediafocusadmin.R
import com.mediafocusadmin.data.MainViewModel
import com.mediafocusadmin.model.User

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NewUserPage(navController: NavController, viewModel: MainViewModel) {

    val user by viewModel.newUser
    val allUsers by viewModel.unRegUsers.observeAsState(initial = emptyList())


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

        LazyColumn{
            items(items = allUsers){
                println(it.email)
                var open by remember { mutableStateOf(false) }
                if (open) {
                    newUser(user, viewModel, onclick = { open = false })
                }else {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(70.dp)
                            .padding(horizontal = 20.dp)
                    ) {
                        MediumText(text = it.email.take(25)+ "...")
                        Spacer(modifier = Modifier.weight(0.1f))
                        Button(onClick = {
                            viewModel.setNewUser(it.id)
                            open = true
                        }) {
                            Text(text = "Register")
                        }
                    }
                }
            }
        }


    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun newUser(user: User, viewModel: MainViewModel, onclick: () -> Unit) {
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
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            textStyle = MaterialTheme.typography.labelMedium,
            placeholder = { MediumText(text = "Phone")},
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(10.dp))
        OutlinedTextField(
            value = user?.plan ?: "",
            onValueChange = { viewModel.updateUser("plan", it)},
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            textStyle = MaterialTheme.typography.labelMedium,
            placeholder = { MediumText(text = "Plan")},
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(10.dp))
        Button(
            onClick = {
                if (viewModel.isNewUserOk()){
                    viewModel.addNewUser()
                }
                viewModel.clearNewUser()
                onclick()
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