package com.example.projectuasmobile

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.projectuasmobile.databinding.ActivityUbahProfilPelamarBinding

class MainActivityUbahProfilPelamar : AppCompatActivity() {

    private lateinit var binding: ActivityUbahProfilPelamarBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUbahProfilPelamarBinding.inflate(layoutInflater)
        setContentView(binding.root)
        // val value = findViewById<ViewPager2>(R.id.signup_pel_id)
        val extras = intent.extras

        binding.btnNext.setOnClickListener{
                val nama = binding.edtNama.text.toString()
                val tempat = binding.edtTempat.text.toString()
                val agama = binding.edtAgama.text.toString()
                val alamat = binding.edtAlamat.text.toString()
                val emailprofile = binding.edtEmail.text.toString()
                val tanggal = binding.edtTanggal.text.toString()
                val nohp = binding.edtNoHP.text.toString()
                val kelamin = binding.edtKelamin.text.toString()
                Intent(this,MainActivityTambahanProfilPelamar::class.java).also{
                    it.putExtra("nama",nama)
                    it.putExtra("tempat",tempat)
                    it.putExtra("agama",agama)
                    it.putExtra("alamat",alamat)
                    it.putExtra("emailprofile",emailprofile)
                    it.putExtra("tanggal",tanggal)
                    it.putExtra("nohp",nohp)
                    it.putExtra("kelamin",kelamin)
                    startActivity(it)
                }
            }
        }

    fun btn_kembali_ubah_profil_pel(view: View) {
        val i = Intent(applicationContext, ProfilPelamarActivity::class.java)
        startActivity(i)
    }

}
