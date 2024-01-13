package com.example.projectuasmobile

import android.app.ProgressDialog
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.projectuasmobile.databinding.ActivitySigupPerusahaanBinding
import com.example.projectuasmobile.table.userPerusahaan
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import java.util.UUID
class MainActivitySigupPerusahaan : AppCompatActivity() {

    private lateinit var binding: ActivitySigupPerusahaanBinding
    private lateinit var ref: DatabaseReference
    lateinit var choose_img: Button
    lateinit var register: Button
    lateinit var image_view: ImageView
    var fileUri: Uri? = null
    private var imageUrlPer: String? = null

    //menampilkan layout sign up perusahaan
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySigupPerusahaanBinding.inflate(layoutInflater)
        setContentView(binding.root)


        choose_img = findViewById(R.id.uploadLogoPer)
        register = findViewById(R.id.btnregisper)
        image_view = findViewById(R.id.uploadLogo)

        ref = FirebaseDatabase.getInstance().getReference("userPerusahaan")

        choose_img.setOnClickListener {
            val intent = Intent()
            intent.type = "image/*"
            intent.action = Intent.ACTION_GET_CONTENT
            startActivityForResult(
                Intent.createChooser(intent, "Pilih Logo"), 0
            )
        }

        register.setOnClickListener {
            if (fileUri != null) {
                val userIdPer = ref.push().key!!
                addData()
            } else {
                Toast.makeText(
                    applicationContext, "Please select image",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode == 0 && resultCode == RESULT_OK && data != null && data.data != null) {
            fileUri = data.data
            try {
                val bitmap : Bitmap = MediaStore.Images.Media.getBitmap(contentResolver,fileUri)
                image_view.setImageBitmap(bitmap)
            } catch (e: Exception) {
                Log.e("Exception", "Error: " + e)
            }
        }
    }

    private fun addData() {
        if (fileUri != null) {
            val progressDialog = ProgressDialog(this)
            progressDialog.setMessage("Memproses")
            progressDialog.show()

            val ref: StorageReference =
                FirebaseStorage.getInstance().getReference("userPerusahaan")
                    .child(UUID.randomUUID().toString())

            ref.putFile(fileUri!!).addOnSuccessListener { taskSnapshot ->
                // Gambar berhasil diunggah, dapatkan URL gambar
                ref.downloadUrl.addOnSuccessListener { uri ->
                    imageUrlPer= uri.toString() // Simpan URL gambar ke variabel global
                    progressDialog.dismiss()

                    // Setelah upload gambar selesai, baru lakukan registrasi
                    performRegistration()

                    Toast.makeText(
                        applicationContext, "Data Berhasil Ditambahkan",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }.addOnFailureListener {
                progressDialog.dismiss()
                Toast.makeText(applicationContext, "Gagal!", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun performRegistration() {
        val userIdPer = ref.push().key
        val usernamePer = binding.inputNamaPerusahaan.text.toString()
        val emailPer = binding.inputEmailPerusahaan.text.toString()
        val passwordPer = binding.inputPassPerusahaan.text.toString()
        val konfirmasiPassPer = binding.inputKonfPassPerusahaan.text.toString()

        val userperusahaan = userPerusahaan(userIdPer!!, usernamePer, emailPer, passwordPer, imageUrlPer)

            if (usernamePer.isNotEmpty() && emailPer.isNotEmpty() && passwordPer.isNotEmpty() && konfirmasiPassPer.isNotEmpty()) {
                if (passwordPer == konfirmasiPassPer) {
                    ref.child(userIdPer).setValue(userperusahaan).addOnCompleteListener {
                        Toast.makeText(
                            applicationContext,
                            "Registrasi berhasil",
                            Toast.LENGTH_SHORT
                        )
                            .show()
                        val intent = Intent(this, MainActivitySiginPerusahaan::class.java)
                        startActivity(intent)
                    }
                } else {
                    Toast.makeText(this, "Password tidak cocok!", Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(this, "Field tidak boleh kosong", Toast.LENGTH_SHORT).show()
            }
        }

    //ketika text 'Masuk' di halaman sign up perusahaan di klik, akan ngarah ke halaman sign in perusahaan
    fun btn_masuk_lagi_per(view: View) {
        val i = Intent(applicationContext, MainActivitySiginPerusahaan::class.java)
        startActivity(i)
    }

    //ketika text 'Kembali' di halaman sign up perusahaan di klik, akan ngarah ke halaman sign in perusahaan
    fun btn_kembali_signin_per(view: View) {
        val i = Intent(applicationContext, MainActivitySiginPerusahaan::class.java)
        startActivity(i)
    }

    //ketika button 'Masuk' di halaman sign up perusahaan di klik, akan ngarah ke halaman sign in perusahaan
    fun btn_masuk_login_per(view: View) {
        val i = Intent(applicationContext, MainActivitySiginPerusahaan::class.java)
        startActivity(i)
    }
}