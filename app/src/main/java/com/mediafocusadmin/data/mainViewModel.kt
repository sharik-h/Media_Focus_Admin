package com.mediafocusadmin.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mediafocusadmin.model.CompactPayments
import com.mediafocusadmin.model.Expense
import com.mediafocusadmin.model.User
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class mainViewModel @Inject constructor(
    private val repo: RepoImpl
) : ViewModel() {


    private var _payments = MutableLiveData<List<CompactPayments>>()
    val payments: LiveData<List<CompactPayments>> = _payments
    private var _expense = MutableLiveData<List<Expense>>()
    val expense: LiveData<List<Expense>> = _expense
    private var _newExp = MutableLiveData<Expense>()
    val newExp = _newExp
    private var _newUser = MutableLiveData<User>()
    val newUser = _newUser


    fun getMyDetails() {
        viewModelScope.launch {
            _payments.value = repo.getAllPayments()
            _expense.value = repo.getAllExp()
        }
    }

    fun addNewExpense() {
        viewModelScope.launch {
            _newExp.value?.let {
                repo.addNewExp(it.desc, it.amount)
            }
        }
    }

    fun addNewUser() {
        viewModelScope.launch {
            _newUser.value?.let {
                repo.addNewUser(
                    name = it.name,
                    phone = it.phone,
                    email = it.email,
                    date = it.date
                )
            }
        }
    }

    fun updateExp() {
        viewModelScope.launch {
            _newExp.value?.let {
                repo.updateExp(it.id, it.desc, it.amount)
            }
        }
    }

    fun sendNewPayment() {
        viewModelScope.launch {
            repo.sendNewPayment()
        }
    }
}