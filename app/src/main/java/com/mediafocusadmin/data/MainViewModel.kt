package com.mediafocusadmin.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mediafocusadmin.Room.ExpenseRepo
import com.mediafocusadmin.Room.PaymentRepo
import com.mediafocusadmin.model.CompactPayments
import com.mediafocusadmin.model.Expense
import com.mediafocusadmin.model.User
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.time.LocalDate
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val repo: RepoImpl,
    private val roomRepo: ExpenseRepo,
    private val payRoomRepo: PaymentRepo
) : ViewModel() {


    private var _payments = MutableLiveData<List<CompactPayments>>()
    val payments: LiveData<List<CompactPayments>> = _payments
    private var _expense = MutableLiveData<List<Expense>>()
    val expense: LiveData<List<Expense>> = _expense
    private var _newExp = MutableLiveData<Expense>()
    val newExp = _newExp
    private var _newUser = MutableLiveData<User>()
    val newUser = _newUser
    private val _totalBal = mutableStateOf(0)
    val totalBal = _totalBal
    private val _totalExp= mutableStateOf(0)
    val totalExp = _totalExp
    private val _totalPay= mutableStateOf(0)
    val totalPay = _totalPay


    init {
        getMyDetailsFromRoom()
    }

    private fun getMyDetails() {
        viewModelScope.launch {
            _payments.value = repo.getAllPayments()
            _expense.value = repo.getAllExp()
            calTotal()
        }
    }

    fun addNewExpense() {
        viewModelScope.launch {
            _newExp.value?.let {
                repo.addNewExp(it.desc!!, it.amount!!, LocalDate.now().toString())
            }
            clearExp()
            _expense.value = repo.getAllExp()
            calTotal()
        }
    }

    private fun clearExp() {
        _newExp.let {
            it.value = it.value.copy(desc = "")
            it.value = it.value.copy(amount = "")
        }
    }

    fun addNewUser() {
        viewModelScope.launch {
            _newUser.value?.let {
                repo.addNewUser(
                    name = it.name,
                    phone = it.phone,
                    email = it.email,
                    date = LocalDate.now().toString()
                )
            }
        }
    }

    fun updateExp() {
        viewModelScope.launch {
            _newExp.value?.let {
                repo.updateExp(it.id!!, it.desc!!, it.amount!!, it.date!!)
            }
        }
    }

    fun updateUser(feildName: String, value: String){
        _newUser.let {
            when(feildName){
                "name" -> it.value = it.value.copy(name = value)
                "phone" -> it.value = it.value.copy(phone = value)
                "email" -> it.value = it.value.copy(email = value)
                "date" -> it.value = it.value.copy(date = value)
            }
        }
    }

    fun updateExp(feildName: String, value: String){
        _newExp.let {
            when(feildName){
                "desc" -> it.value = it.value.copy(desc = value)
                "amount" -> it.value = it.value.copy(amount = value)
            }
        }
    }

    fun sendNewPayment() {
        viewModelScope.launch {
            repo.sendNewPayment()
        }
    }


    fun calTotal(){
        _totalPay.value = 0
        _totalBal.value = 0
        _totalExp.value = 0
        _payments.value?.forEach { _totalPay.value += it.amount ?: 0 }
        _expense.value?.forEach { _totalExp.value += it.amount?.toInt() ?: 0   }
        println(_totalPay.value)
        println(_totalExp.value)
        _totalBal.value = _totalPay.value!! - _totalExp.value!!
    }

    fun isNewExpOk(): Boolean {
      return  _newExp.value.desc != "" && newExp.value.amount != ""
    }

    fun deleteExp(id: Int) {
        viewModelScope.launch {
            if (repo.deleteExp(id)){
                _expense.value = repo.getAllExp()
                calTotal()
            }
        }
    }

    fun isNewUserOk(): Boolean {
        return     _newUser.value.name != ""
                && _newUser.value.phone != ""
                && _newUser.value.email != ""
    }

    fun clearNewUser() {
        _newUser.let {
            it.value = it.value.copy(name = "")
            it.value = it.value.copy(phone = "")
            it.value = it.value.copy(email = "")
            it.value = it.value.copy(date = "")
        }
    }

    fun getMyDetailsFromRoom() {
        viewModelScope.launch {
            val list = mutableListOf<Expense>()
            roomRepo.getAllExp().forEach {
                list.add(Expense(id = it.id, desc = it.desc, amount = it.amount, date = it.date))
            }
            val list2 = mutableListOf<CompactPayments>()
            payRoomRepo.getAllPayments().forEach {
                list.add(Expense(id = it.id, desc = it.userId!!, amount = it.amount.toString(), date = it.date!!))
            }
            _expense.value = list
            _payments.value = list2
        }
    }

    fun addNewExpIntoRoom() {
        viewModelScope.launch {
            _newExp.value?.let {
                updateExp("date", LocalDate.now().toString())
                val exproom = Expense(
                    desc =  it.desc!!,
                    amount = it.amount!!,
                    date = it.date!!
                )
                roomRepo.addNewExp(exproom)
            }
            clearExp()
            getMyDetailsFromRoom()
            calTotal()
        }
    }
}