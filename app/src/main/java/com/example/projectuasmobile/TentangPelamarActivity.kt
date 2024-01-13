package com.example.projectuasmobile

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.projectuasmobile.databinding.ActivityTentangPelamarBinding

class TentangPelamarActivity : AppCompatActivity() {

    private lateinit var binding: ActivityTentangPelamarBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTentangPelamarBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.bottomNavigationViewPelTentang.setOnNavigationItemSelectedListener { menuItem ->
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
}