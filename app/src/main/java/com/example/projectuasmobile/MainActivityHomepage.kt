package com.example.projectuasmobile

import android.content.Intent
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivityHomepage : AppCompatActivity() {
    //menampilkan layout homepage
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_homepage)
    }

    //ketika button 'masuk sebagai pelamar' di homepage di klik, akan ngarah ke halaman sign in pelamar
    fun btn_sigin_pel(view: View) {
        val i = Intent(applicationContext, MainActivitySiginPelamar::class.java)
        startActivity(i)
    }

    //ketika button 'masuk sebagai perusahaan' di homepage di klik, akan ngarah ke halaman sign in perusahaan
    fun btn_sigin_per(view: View) {
        val i = Intent(applicationContext, MainActivitySiginPerusahaan::class.java)
        startActivity(i)
    }


}