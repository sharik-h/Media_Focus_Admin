package com.mediafocusadmin.model

data class User(
    val id: String,
    var name: String,
    var phone: String,
    var email: String,
    var date: String,
    var plan: String,
) {
    fun isEverythingOk(): Boolean {
        return name.isNotEmpty() && phone.isNotEmpty() && email.isNotEmpty() && date.isNotEmpty() && plan.isNotEmpty()
    }
}

sealed class UserResult {
    data class Success(val data: List<User>): UserResult()
    data class Error(val error: String): UserResult()
}