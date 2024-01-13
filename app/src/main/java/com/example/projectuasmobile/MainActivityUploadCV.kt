package com.example.projectuasmobile

import android.app.ProgressDialog
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.projectuasmobile.databinding.ActivityUploadCvBinding
import com.example.projectuasmobile.table.Daftar
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import java.util.UUID

class MainActivityUploadCV : AppCompatActivity() {
    private lateinit var binding: ActivityUploadCvBinding
    private lateinit var ref: DatabaseReference
    lateinit var choose_img: Button
    lateinit var upload_image: Button
    lateinit var image_view: ImageView
    var fileUri: Uri? = null
    private var imageUrl: String? = null
    private lateinit var id_lowongan: String
    private lateinit var userIdPel: String


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUploadCvBinding.inflate(layoutInflater)
        setContentView(binding.root)

        id_lowongan = intent.getStringExtra("id_lowongan") ?: ""

        choose_img = findViewById(R.id.pilihFileCV)
        upload_image = findViewById(R.id.kirimFileCV)
        image_view = findViewById(R.id.uploadCVpel)

        ref = FirebaseDatabase.getInstance().getReference("userPendaftaran")

        choose_img.setOnClickListener{
            val intent = Intent()
            intent.type = "image/*"
            intent.action = Intent.ACTION_GET_CONTENT
            startActivityForResult(
                Intent.createChooser(intent, "Choose image to upload"),0
            )
        }

        val intent = intent
        val id_lowongan = intent.getStringExtra("id_lowongan")
        val posisiLow = intent.getStringExtra("posisiLow")
        val durasiLow = intent.getStringExtra("durasiLow")
        val statusLow = intent.getStringExtra("statusLow")
        val penjelasanLow = intent.getStringExtra("penjelasanLow")
        val namaInstansi = intent.getStringExtra("namaInstansi")
        val jumlahLow = intent.getStringExtra("jumlahLow")

        upload_image.setOnClickListener{
            if (fileUri != null) {
                val productId = ref.push().key!!
                uploadImage()
            } else {
                Toast.makeText(applicationContext, "Please select image",
                    Toast.LENGTH_SHORT).show()
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

    private fun performPurchase(){
        val idPendaftaran = ref.push().key
        val userPendaftaran = Daftar(
            idPendaftaran!!,
            imageUrl,
            id_lowongan,)

        ref.child(idPendaftaran ?: "").setValue(userPendaftaran).addOnCompleteListener{
            Toast.makeText(
                applicationContext,"Pendaftaran Berhasil, Tunggu Konfirmasi Selanjutnya",
                Toast.LENGTH_SHORT
            ).show()
        }
    }

    private fun uploadImage() {
        if (fileUri != null) {
            val progressDialog = ProgressDialog(this)
            progressDialog.setTitle("Uploading Image...")
            progressDialog.setMessage("Processing")
            progressDialog.show()

            val ref: StorageReference =
                FirebaseStorage.getInstance().getReference("user")
                    .child(UUID.randomUUID().toString())

            ref.putFile(fileUri!!).addOnSuccessListener { taskSnapshot ->
                // Gambar berhasil diunggah, dapatkan URL gambar
                ref.downloadUrl.addOnSuccessListener { uri ->
                    imageUrl = uri.toString() // Simpan URL gambar ke variabel global
                    progressDialog.dismiss()

                    // Setelah upload gambar selesai, baru lakukan registrasi
                    performPurchase()

                    Toast.makeText(
                        applicationContext, "File Uploaded Successfully",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }.addOnFailureListener {
                progressDialog.dismiss()
                Toast.makeText(applicationContext, "Failed", Toast.LENGTH_SHORT).show()
            }
        }
    }

}
