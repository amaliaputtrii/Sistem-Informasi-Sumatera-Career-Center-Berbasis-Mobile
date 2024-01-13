package com.example.projectuasmobile

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.projectuasmobile.databinding.ActivityDataProfilePerusahaanBinding

class DataProfilePerusahaanActivity : AppCompatActivity() {

    private lateinit var binding : ActivityDataProfilePerusahaanBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDataProfilePerusahaanBinding.inflate(layoutInflater)
        setContentView(binding.root)
//        setContentView(R.layout.activity_data_profile_perusahaan)

        val sessionManagerPer = SessionManagerPer(applicationContext)

        val userIdPer = sessionManagerPer.getUserIdPer()
        val usernamePer = sessionManagerPer.getUserNamePer()
        val emailPer = sessionManagerPer.getUserEmailPer()
        val passwordPer = sessionManagerPer.getUserPasswordPer()
        val image = sessionManagerPer.getUserImageUrlPer()

        binding.textViewNamaPerusahaanData.text = usernamePer
        binding.textViewEmailPerusahaanData.text = emailPer
        binding.textViewPassEmailPerusahaanData.text = passwordPer

        image?.let {
            Glide.with(this)
                .load(it)
                .placeholder(R.drawable.ic_upload) // placeholder image while loading
                .error(R.drawable.ic_upload) // error image if loading fails
                .into(binding.LogoPerData)
        }


    }

    fun btnKembaliLihatDataDiriPerusahaan(view: View) {
        val i = Intent(applicationContext, ProfilPerusahaanActivity::class.java)
        startActivity(i)
    }


}