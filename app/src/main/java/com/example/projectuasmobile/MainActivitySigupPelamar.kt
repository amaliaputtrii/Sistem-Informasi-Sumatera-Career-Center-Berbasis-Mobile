package com.example.projectuasmobile

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.projectuasmobile.databinding.ActivitySigupPelamarBinding
import com.example.projectuasmobile.table.userPelamar
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class MainActivitySigupPelamar : AppCompatActivity() {

    private lateinit var binding: ActivitySigupPelamarBinding
    private lateinit var ref: DatabaseReference


    //menampilkan layout sign up pelamar
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySigupPelamarBinding.inflate(layoutInflater)
        setContentView(binding.root)
        // val value = findViewById<ViewPager2>(R.id.signup_pel_id)
        val extras = intent.extras

        ref = FirebaseDatabase.getInstance().getReference("userPelamar")
        binding.btnregispel.setOnClickListener {
            val userIdPel = ref.push().key
            val emailPel = binding.inputEmailPelamar.text.toString()
            val usernamePel = binding.inputNamaPelamar.text.toString()
            val passwordPel = binding.inputPassPelamar.text.toString()
            val konfirmasiPassPel = binding.inputKonfPassPelamar.text.toString()
            val noHpPel = binding.inputHpPelamar.text.toString()
            val userpelamar = userPelamar(userIdPel!!, emailPel, usernamePel, passwordPel, noHpPel)

            if (emailPel.isNotEmpty() && usernamePel.isNotEmpty() && passwordPel.isNotEmpty() && konfirmasiPassPel.isNotEmpty() && noHpPel.isNotEmpty()) {
                if (passwordPel == konfirmasiPassPel) {
                    ref.child(userIdPel).setValue(userpelamar).addOnCompleteListener {
                        Toast.makeText(
                            applicationContext,
                            "Registrasi berhasil",
                            Toast.LENGTH_SHORT
                        )
                            .show()
                        val intent = Intent(this, MainActivitySiginPelamar::class.java)
                        startActivity(intent)
                    }
                } else {
                    Toast.makeText(this, "Password tidak cocok!", Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(this, "Field tidak boleh kosong", Toast.LENGTH_SHORT).show()
            }
        }
    }




        //ketika text 'Masuk' di halaman sign up pelamar di klik, akan ngarah ke halaman sign in pelamar
        fun btn_masuk_lagi_pel(view: View) {
            val i = Intent(applicationContext, MainActivitySiginPelamar::class.java)
            startActivity(i)
        }

        //ketika text 'Kembali' di halaman sign up pelamar di klik, akan ngarah ke halaman sign in pelamar
        fun btn_kembali_signin_pel(view: View) {
            val i = Intent(applicationContext, MainActivitySiginPelamar::class.java)
            startActivity(i)
        }

        //ketika button 'Masuk' di halaman sign up pelamar di klik, akan ngarah ke halaman sign in pelamar
        fun btn_masuk_login_pel(view: View) {
            val i = Intent(applicationContext, MainActivitySiginPelamar::class.java)
            startActivity(i)
        }

    }
