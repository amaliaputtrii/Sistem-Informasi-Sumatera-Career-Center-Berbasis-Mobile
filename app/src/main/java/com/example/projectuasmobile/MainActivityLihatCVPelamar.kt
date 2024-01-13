package com.example.projectuasmobile

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.projectuasmobile.databinding.ActivityLihatCvPelamarBinding
import com.google.firebase.database.DatabaseReference

class MainActivityLihatCVPelamar : AppCompatActivity(){
    private lateinit var binding : ActivityLihatCvPelamarBinding
    private lateinit var db : DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lihat_cv_pelamar)
        // val value = findViewById<ViewPager2>(R.id.signup_pel_id)
        val extras = intent.extras

    }

    fun btnKembaliLihatCV(view: View) {
        val i = Intent(applicationContext, MainActivityBerandaPerusahaan::class.java)
        startActivity(i)
    }


}

