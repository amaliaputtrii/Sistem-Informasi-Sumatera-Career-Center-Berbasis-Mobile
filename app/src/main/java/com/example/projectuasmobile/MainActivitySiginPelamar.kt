package com.example.projectuasmobile

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.projectuasmobile.databinding.ActivitySiginPelamarBinding
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class MainActivitySiginPelamar : AppCompatActivity() {

    private lateinit var binding: ActivitySiginPelamarBinding
    private lateinit var ref: DatabaseReference
    private lateinit var sessionManagerPel: SessionManagerPel

    //menampilkan layout sign in pelamar
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySiginPelamarBinding.inflate(layoutInflater)
//        setContentView(R.layout.activity_sigin_pelamar)
        setContentView(binding.root)
//        val extras = intent.extras

        ref = FirebaseDatabase.getInstance().getReference("userPelamar")
        sessionManagerPel = SessionManagerPel(this)

        binding.btnSiginPelId.setOnClickListener {
            val emailPel = binding.emailPel.text.toString()
            val passwordPel = binding.passPel.text.toString()

            if (emailPel.isNotEmpty() && passwordPel.isNotEmpty()) {
                loginUser(emailPel, passwordPel)
            } else {
                Toast.makeText(this, "Field tidak boleh kosong", Toast.LENGTH_SHORT).show()
            }
        }
        binding.TextViewToRegisPel.setOnClickListener {
            navigateToRegister()
        }
    }

    private fun loginUser(emailPel: String, passwordPel: String) {
        val query = ref.orderByChild("emailPel").equalTo(emailPel)

        query.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                if (dataSnapshot.exists()) {
                    for (userSnapshot in dataSnapshot.children) {
                        val storedPassword = userSnapshot.child("passwordPel").value.toString()
                        if (passwordPel == storedPassword) {
                            // Password sesuai, login berhasil
                            val userIdPel = userSnapshot.key
                            val usernamePel = userSnapshot.child("usernamePel").value.toString()
                            sessionManagerPel.saveUserSessionPel(userIdPel!!, emailPel, usernamePel)
                            navigateToMain()
                        } else {
                            showToast("Password salah")
                        }
                    }
                } else {
                    showToast("Pengguna dengan email $emailPel tidak ditemukan")
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

    private fun navigateToRegister() {
        val intent = Intent(this, MainActivitySigupPelamar::class.java)
        startActivity(intent)
    }

    private fun navigateToMain() {
        val intent = Intent(this, MainActivityBerandaPelamar::class.java)
        startActivity(intent)
        finish()
    }

    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

}