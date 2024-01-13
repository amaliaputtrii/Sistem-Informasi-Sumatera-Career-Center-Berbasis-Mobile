package com.example.projectuasmobile

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class MainActivityStatusSeleksiPelamar : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_status_seleksi_pelamar)
        // val value = findViewById<ViewPager2>(R.id.signup_pel_id)
        val extras = intent.extras

    }

//    fun btn_kembali_profil_pel(view: View) {
//        val i = Intent(applicationContext, MainActivityProfilPelamar::class.java)
//        startActivity(i)
//    }

}