package com.mediafocusadmin.Pages

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.SearchBar
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.mediafocusadmin.R
import com.mediafocusadmin.data.MainViewModel
import com.mediafocusadmin.model.User
import kotlinx.coroutines.launch

@Composable
fun allUsersPage(viewModel: MainViewModel, paddingValues: PaddingValues) {

    val all by viewModel.allRegUsers.observeAsState(initial = emptyList())
    var isSearchOpen by remember { mutableStateOf(false) }
    val listState = rememberLazyListState()
    val scope = rememberCoroutineScope()

    Column {

            if (isSearchOpen){
                searchBar(close = {
                    isSearchOpen = false
                    scope.launch { listState.animateScrollToItem(it) }
                                  },
                    onSearch = { },
                    allUsers = all)
            }else{
                topAppBar( onclick = { isSearchOpen = true} )
            }
        LazyColumn(state = listState, modifier = Modifier.padding(paddingValues)){
            items(items = all){
                var open by remember{ mutableStateOf(false) }
                if (open){
                    editUser(user = it, viewModel = viewModel, onCancel = { open = false })
                }else {
                    viewUser(user = it, onclick = { open = true })
                }
            }
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun searchBar(close: (Int) -> Unit, onSearch: (String ) -> Unit, allUsers: List<User>) {

    var srch by remember { mutableStateOf("") }
    var all = allUsers

    SearchBar(
        query = srch,
        onQueryChange = {
            srch = it
            onSearch(srch)
                        },
        onSearch =  { onSearch(srch) },
        placeholder = { MediumText(text = "Name or Number") },
        active = true ,
        trailingIcon = {
                       IconButton(onClick = { close(0) }) {
                           Image(imageVector = Icons.Default.Close, contentDescription = null)
                       }
        },
        onActiveChange = {}
    )  {
        LazyColumn{
            items(items = allUsers.filter { it.name.contains(srch) || it.phone.contains(srch) }) {
                searchView(user = it, onclick = { close(allUsers.indexOf(it)) })
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun topAppBar(onclick: () -> Unit) {
    TopAppBar(
        title = {
            Text(text = "All Users")
                },
        actions = {
            IconButton(onClick = { onclick() }) {
                Image(imageVector = Icons.Default.Search, contentDescription = null)
            }
        }
    )
}

@Composable
fun viewUser(user: User, onclick: () -> Unit) {
    Row(modifier = Modifier
        .fillMaxWidth()
        .padding(10.dp)) {
        Column {
            Text(text = "Name: ${user.name}", style = MaterialTheme.typography.labelLarge)
            Text(text = "Phone : ${user.phone}", style = MaterialTheme.typography.labelLarge)
            Text(text = "Plan :${user.plan}", style = MaterialTheme.typography.labelLarge)
        }
        Spacer(modifier = Modifier.weight(0.1f))
        IconButton(onClick = { onclick() }) {
            Image(imageVector = Icons.Default.Edit, contentDescription = null)
        }
    }
}

@Composable
fun editUser(user: User, viewModel: MainViewModel, onCancel: () -> Unit) {

    var editedUser by remember { mutableStateOf(user) }

    Column(modifier = Modifier
        .fillMaxSize()
        .padding(20.dp)) {

        OutlinedTextField(
            value = editedUser?.name ?: "",
            onValueChange = { editedUser = editedUser.copy(name = it) },
            textStyle = MaterialTheme.typography.labelMedium,
            placeholder = { MediumText(text = "Name")},
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(10.dp))
        OutlinedTextField(
            value = editedUser?.email ?: "",
            onValueChange = { editedUser = editedUser.copy(email = it) },
            textStyle = MaterialTheme.typography.labelMedium,
            placeholder = { MediumText(text = "E-mail")},
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(10.dp))
        OutlinedTextField(
            value = editedUser?.phone ?: "",
            onValueChange = { editedUser = editedUser.copy(phone = it) },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            textStyle = MaterialTheme.typography.labelMedium,
            placeholder = { MediumText(text = "Phone")},
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(10.dp))
        OutlinedTextField(
            value = editedUser?.plan ?: "",
            onValueChange = { editedUser = editedUser.copy(plan = it) },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            textStyle = MaterialTheme.typography.labelMedium,
            placeholder = { MediumText(text = "Plan")},
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(10.dp))
        Row(Modifier.fillMaxWidth()) {
            Button(
                onClick = { onCancel() },
                modifier = Modifier.fillMaxWidth(0.5f)
            ) {
                MediumText(text = stringResource(id = R.string.cancel_text))
            }
            Spacer(modifier = Modifier.width(5.dp))
            Button(
                onClick = {
                   viewModel.updateUserData(editedUser)
                    onCancel()
                },
                modifier = Modifier.fillMaxWidth(),
                enabled = editedUser.isEverythingOk() && user != editedUser
            ) {
                MediumText(text = stringResource(id = R.string.save_button_text))
            }
        }
    }
}

@Composable
fun searchView(user: User, onclick: (User) -> Unit) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 10.dp, vertical = 5.dp)
            .clickable { onclick(user) }
    ) {
        Text(text = "${user.name}, ${user.phone}", style = MaterialTheme.typography.labelLarge)
        Spacer(modifier = Modifier.weight(0.1f))
        Image(imageVector = Icons.Default.KeyboardArrowRight, contentDescription = null)
    }
}