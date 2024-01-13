package com.example.projectuasmobile

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.projectuasmobile.databinding.ActivitySiginPerusahaanBinding
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class MainActivitySiginPerusahaan : AppCompatActivity() {

    //menampilkan layout sign in perusahaan
    private lateinit var binding: ActivitySiginPerusahaanBinding
    private lateinit var ref: DatabaseReference
    private lateinit var sessionManagerPer: SessionManagerPer

    //menampilkan layout sign in pelamar
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySiginPerusahaanBinding.inflate(layoutInflater)
//        setContentView(R.layout.activity_sigin_pelamar)
        setContentView(binding.root)
//        val extras = intent.extras

        ref = FirebaseDatabase.getInstance().getReference("userPerusahaan")
        sessionManagerPer = SessionManagerPer(this)

        binding.btnSiginPerId.setOnClickListener {
            val emailPer = binding.emailPer.text.toString()
            val passwordPer = binding.passPer.text.toString()

            if (emailPer.isNotEmpty() && passwordPer.isNotEmpty()) {
                loginUserPer(emailPer, passwordPer)
            } else {
                Toast.makeText(this, "Field tidak boleh kosong", Toast.LENGTH_SHORT).show()
            }
        }

        binding.TextViewToRegisPer.setOnClickListener {
            navigateToRegisterPer()
        }
    }

    private fun loginUserPer(emailPer: String, passwordPer: String) {
        val query = ref.orderByChild("emailPer").equalTo(emailPer)

        query.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                if (dataSnapshot.exists()) {
                    for (userSnapshot in dataSnapshot.children) {
                        val storedPassword = userSnapshot.child("passwordPer").value.toString()
                        if (passwordPer == storedPassword) {
                            // Password sesuai, login berhasil
                            val userIdPer = userSnapshot.key
                            val usernamePer = userSnapshot.child("usernamePer").value.toString()
                            val imageUrlPer = userSnapshot.child("imageUrlPer").value.toString()
                            sessionManagerPer.saveUserSessionPer(userIdPer!!, emailPer, usernamePer, passwordPer, imageUrlPer )
                            navigateToMainPer()
                        } else {
                            showToast("Password salah")
                        }
                    }
                } else {
                    showToast("Pengguna dengan email $emailPer tidak ditemukan")
                }
            }
            //
            override fun onCancelled(databaseError: DatabaseError) {
                // Implementation for onCancelled method
            }
        })
    }


    //ketika text 'Kembali' di halaman sign in pelamar di klik, akan ngarah ke halaman homepage
    fun btn_kembali_homepage_pel(view: View) {
        val i = Intent(applicationContext, MainActivityHomepage::class.java)
        startActivity(i)
    }

    private fun navigateToRegisterPer() {
        val intent = Intent(this, MainActivitySigupPerusahaan::class.java)
        startActivity(intent)
    }

    private fun navigateToMainPer() {
        val intent = Intent(this, MainActivityBerandaPerusahaan::class.java)
        startActivity(intent)
        finish()
    }

    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }


//    fun btn_sigin_per(view: View) {
//        val i = Intent(applicationContext, MainActivityBerandaPelamar::class.java)
//        startActivity(i)
//    }

    //ketika button 'masuk' di halaman sign in perusahaan di klik, akan ngarah ke halaman beranda perusahaan
//    fun btn_masuk_per(view: View) {
//        val i = Intent(applicationContext, MainActivityBerandaPerusahaan::class.java)
//        startActivity(i)
//    }
//
//    //ketika text 'Daftar' di halaman sign in perusahaan di klik, akan ngarah ke halaman sign up perusahaan
//    fun btn_regis_per(view: View) {
//        val i = Intent(applicationContext, MainActivitySigupPerusahaan::class.java)
//        startActivity(i)
//    }

    //ketika text 'Kembali' di halaman sign in perusahaan di klik, akan ngarah ke halaman homepage
    fun btn_kembali_homepage_per(view: View) {
        val i = Intent(applicationContext, MainActivityHomepage::class.java)
        startActivity(i)
    }

}