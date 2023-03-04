package com.example.cidpro.bean

data class AccountData(
    val code: Int,
    val message: String,
    val result: Result
)
data class AccountDataz(
    val code: Int,
    val message: String,
    val result: Resultz
)
data class Bank(
    val code: Any,
    val description: Any,
    val id: Int,
    val isEnabled: Boolean,
    val name: String,
    val sort: Int,
    val typeCode: String,
    val value: String
)
data class Result(
    val code: Int,
    val `data`: List<Data?>?=null,
    val msg: String?=null
)
data class Resultz(
    val code: Int,
    val `data`: List<Bank?>?=null,
    val msg: Any
)

data class Data(
    val id: Int,
    val loginName: String,
    val password: String,
    val phone: String,
    val userName: String,
    val userType: Int
)