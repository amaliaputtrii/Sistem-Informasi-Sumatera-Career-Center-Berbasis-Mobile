package com.example.projectuasmobile

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.projectuasmobile.databinding.ActivityBerandaPerusahaanBinding

class MainActivityBerandaPerusahaan : AppCompatActivity() {

    private lateinit var binding: ActivityBerandaPerusahaanBinding

    //menampilkan layout beranda perusahaan
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBerandaPerusahaanBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.bottomNavigationViewPerBeranda.setOnNavigationItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.home ->
                {
//                    overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left)
                    // Ganti dengan Intent untuk Activity yang Anda inginkan
                    val intent = Intent(this, MainActivityBerandaPerusahaan::class.java)
                    startActivity(intent)
                }
//                    replaceFragment(HomeFragment())
                R.id.add ->
                    //replaceFragment(AddFragment())
                {
//                    replaceFragment(TentangPelamarFragment())
//                     Ganti dengan Intent untuk Activity yang Anda inginkan
                    val intent = Intent(this, ActivityAddLow::class.java)
                    startActivity(intent)
//                    overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left)
                }

                R.id.profile ->
                {
//                    replaceFragment(ProfilPelamarFragment())
                    val intent = Intent(this, ProfilPerusahaanActivity::class.java)
                    startActivity(intent)
//                    overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left)

                }
//                    replaceFragment(ProfileFragment())

            }
            true
        }


    }

    //ketika button 'masuk sebagai perusahaan' di homepage di klik, akan ngarah ke halaman beranda perusahaan
    fun btn_masuk_per(view: View) {
        val i = Intent(applicationContext, MainActivityBerandaPerusahaan::class.java)
        startActivity(i)
    }

    fun btnLihatDetailKlik(view: View){
        val i = Intent(applicationContext, MainActivityLihatCVPelamar::class.java)
        startActivity(i)
    }

}