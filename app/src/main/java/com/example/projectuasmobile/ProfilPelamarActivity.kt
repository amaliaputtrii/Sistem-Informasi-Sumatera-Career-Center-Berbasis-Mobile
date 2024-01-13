package com.example.projectuasmobile

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.projectuasmobile.databinding.ActivityProfilPelamarBinding

class ProfilPelamarActivity : AppCompatActivity() {

    private lateinit var binding: ActivityProfilPelamarBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProfilPelamarBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.bottomNavigationViewPelProfil.setOnNavigationItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.home ->
                {
//                    overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left)
                    // Ganti dengan Intent untuk Activity yang Anda inginkan
                    val intent = Intent(this, MainActivityBerandaPelamar::class.java)
                    startActivity(intent)
                }
//                    replaceFragment(HomeFragment())
                R.id.about ->
                    //replaceFragment(AddFragment())
                {
//                    replaceFragment(TentangPelamarFragment())
//                     Ganti dengan Intent untuk Activity yang Anda inginkan
                    val intent = Intent(this, TentangPelamarActivity::class.java)
                    startActivity(intent)
//                    overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left)
                }
                R.id.profile ->
                {
//                    replaceFragment(ProfilPelamarFragment())
                    val intent = Intent(this, ProfilPelamarActivity::class.java)
                    startActivity(intent)
//                    overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left)

                }
//                    replaceFragment(ProfileFragment())

            }
            true
        }

    }
    //
        fun textViewKeluar(view: View) {
        val i = Intent(applicationContext, MainActivityHomepage::class.java)
        startActivity(i)
        }

        fun textViewUbahDataDiri(view: View) {
        val i = Intent(applicationContext, MainActivityUbahProfilPelamar::class.java)
        startActivity(i)
    }

        fun textViewStatusSeleksi(view: View) {
        val i = Intent(applicationContext, MainActivityStatusSeleksiPelamar::class.java)
        startActivity(i)
    }
    fun textViewUploadCV(view: View) {
        val i = Intent(applicationContext, MainActivityUploadCV::class.java)
        startActivity(i)
    }


}