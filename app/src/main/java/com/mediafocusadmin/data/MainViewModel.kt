package com.mediafocusadmin.data

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mediafocusadmin.NetwordkObserver.ConnectivityObserver
import com.mediafocusadmin.NetwordkObserver.NetworkConnectivityObserver
import com.mediafocusadmin.Room.ExpenseRepo
import com.mediafocusadmin.Room.PaymentRepo
import com.mediafocusadmin.model.Expense
import com.mediafocusadmin.model.Payment
import com.mediafocusadmin.model.User
import com.mediafocusadmin.model.UserResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.time.LocalDate
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val repo: RepoImpl,
    private val expRoomRepo: ExpenseRepo,
    private val payRoomRepo: PaymentRepo,
    private val networkObserver: NetworkConnectivityObserver
) : ViewModel() {


    private var _payments :MutableLiveData<List<Payment>> = MutableLiveData()
    val payments: MutableLiveData<List<Payment>> = _payments
    private var _expense :MutableLiveData<List<Expense>> = MutableLiveData()
    val expense: MutableLiveData<List<Expense>> = _expense
    private var _newExp = mutableStateOf(Expense(0, "", "", ""))
    var newExp = _newExp
    private var _newUser = mutableStateOf(User("","","","","", ""))
    val newUser = _newUser
    private val _totalBal = mutableStateOf(0)
    val totalBal = _totalBal
    private val _totalExp= mutableStateOf(0)
    val totalExp = _totalExp
    private val _totalPay= mutableStateOf(0)
    val totalPay = _totalPay
    val netObserve = networkObserver.observe()
    private val _unRegUsers: MutableLiveData<List<User>> = MutableLiveData()
    val unRegUsers = _unRegUsers
    private val _allRegUsers: MutableLiveData<List<User>> = MutableLiveData()
    val allRegUsers = _allRegUsers

    init {
        viewModelScope.launch {
            netObserve.collect { status ->
                if (status == ConnectivityObserver.Status.Available) {
                        updateRoom()
                }
            }
        }
        getMyDetailsFromRoom()
    }

    fun updateRoom() {
        viewModelScope.launch {
            repo.getAllPayments().forEach {
                payRoomRepo.addNewPayment(it)
            }
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
                    userId = it.id,
                    name = it.name,
                    phone = it.phone,
                    date = LocalDate.now().toString(),
                    plan = it.plan
                )
            }
            _unRegUsers.value = _unRegUsers.value?.filter { it.id == _newUser.value.id }
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
                "plan" -> it.value = it.value.copy(plan = value)
            }
        }
    }

    fun updateExp(feildName: String, value: String){
        _newExp.let {
            when(feildName){
                "desc" -> it.value = it.value.copy(desc = value)
                "amount" -> it.value = it.value.copy(amount = value)
                "date" -> it.value = it.value.copy(date = value)
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
        _totalBal.value = _totalPay.value!! - _totalExp.value!!
    }

    fun isNewExpOk(): Boolean {
      return  _newExp.value.desc != "" && newExp.value.amount != ""
    }

    fun deleteExp(expense: Expense) {
        viewModelScope.launch {
            expRoomRepo.deleteExp(expense)
            calTotal()
            getMyDetailsFromRoom()
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
        _expense.value = emptyList()
        _payments.value = emptyList()
        viewModelScope.launch {
            _expense.value = expRoomRepo.getAllExp()
            _payments.value = payRoomRepo.getAllPayments()
        }
    }

    fun addNewExpIntoRoom() {
        viewModelScope.launch {
            updateExp("date", LocalDate.now().toString())
            expRoomRepo.addNewExp(_newExp.value)
            clearExp()
            getMyDetailsFromRoom()
            calTotal()
        }
    }

    fun getAllUnRegUsers() {
        viewModelScope.launch {
            val result = repo.getUnRegUsers()
            when(result){
                is UserResult.Success -> {
                    _unRegUsers.value = result.data
                }
                is UserResult.Error -> {
                    println(result.error)
                }
            }
        }
    }

    private fun getAllRegUsers() {
        viewModelScope.launch {
            val result = repo.getAllRegUsers()
            when(result){
                is UserResult.Success -> {
                    _allRegUsers.value = result.data
                }
                is UserResult.Error -> {
                    println(result.error)
                }
            }
        }
    }
    fun setNewUser(userId: String) {
        _unRegUsers.value?.forEach {
            if (it.id == userId){
                _newUser.value = it
            }
        }
    }

    fun clearNewUser1() {
        _newUser.value = User("","", "", "","","")
    }
}