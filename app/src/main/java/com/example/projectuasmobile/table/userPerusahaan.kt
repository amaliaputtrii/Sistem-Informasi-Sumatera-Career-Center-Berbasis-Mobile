package com.example.projectuasmobile.table

class userPerusahaan (
    val userIdPer: String,
    val usernamePer: String,
    val emailPer: String,
    val passwordPer: String,
    val imageUrlPer: String?,

    ) {
        constructor() : this("", "", "", "", "")
}