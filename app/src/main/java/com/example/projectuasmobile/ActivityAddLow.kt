package com.example.projectuasmobile

//import com.example.projectmobile.session.SessionManager
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
import com.example.projectuasmobile.databinding.ActivityAddLowBinding
import com.example.projectuasmobile.table.Lowongan
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import java.util.UUID

class ActivityAddLow : AppCompatActivity() {

    lateinit var choose_img: Button
    lateinit var add_low: Button
    lateinit var image_view: ImageView
    lateinit var databaseReference: DatabaseReference
    private lateinit var binding: ActivityAddLowBinding
    lateinit var ref: DatabaseReference
    lateinit var imageView: ImageView
    var selectedImageUri: Uri? = null
    var fileUri: Uri? = null
    private var imageUrl: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddLowBinding.inflate(layoutInflater)
        val view = setContentView(binding.root)

        choose_img = findViewById(R.id.pilihFotoID)
        add_low = findViewById(R.id.tambahButton)
        image_view = findViewById(R.id.uploadImage)
        //lihatdetail: findViewById(R.id.lihatDetailLow)

        // Initialize Firebase Database
        databaseReference = FirebaseDatabase.getInstance().reference
        ref = FirebaseDatabase.getInstance().getReference("lowongann")

        choose_img.setOnClickListener{
            val intent = Intent()
            intent.type = "image/*"
            intent.action = Intent.ACTION_GET_CONTENT
            startActivityForResult(
                Intent.createChooser(intent, "Pilih foto poster lowongan"),0
            )
        }

        add_low.setOnClickListener{
            if (fileUri != null) {
                val id_lowongan = ref.push().key!!
                uploadImage()
            } else {
                Toast.makeText(applicationContext, "Mohon lengkapi data", Toast.LENGTH_SHORT).show()
            }
        }

        // Inisialisasi referensi produk di Firebase Realtime Database
        ref = FirebaseDatabase.getInstance().getReference("lowongann")

        return view

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

    private fun uploadImage() {
        if (fileUri != null) {
            val progressDialog = ProgressDialog(this)
            progressDialog.setTitle("Menambahkan data...")
            progressDialog.setMessage("Memproses")
            progressDialog.show()

            val ref: StorageReference =
                FirebaseStorage.getInstance().getReference("lowongann")
                    .child(UUID.randomUUID().toString())

            ref.putFile(fileUri!!).addOnSuccessListener { taskSnapshot ->
                // Gambar berhasil diunggah, dapatkan URL gambar
                ref.downloadUrl.addOnSuccessListener { uri ->
                    imageUrl = uri.toString() // Simpan URL gambar ke variabel global
                    addLow()

                    progressDialog.dismiss()
                    Toast.makeText(
                        applicationContext, "File berhasil diupload",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }.addOnFailureListener {
                progressDialog.dismiss()
                Toast.makeText(applicationContext, "Gagal", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun addLow() {
        val sessionManagerPer = SessionManagerPer(applicationContext)
        val id_lowongan = ref.push().key
        val lowImage = "lowongann/$id_lowongan"
        val posisiLow = binding.uploadPosisi.text.toString()
        val durasiLow = binding.uploadDurasi.text.toString()
//        val statusLow = binding.uploadStatusLow.text.toString()
        val syaratLow = binding.uploadSyarat.text.toString()
        val penjelasanLow = binding.uploadPenjelasanLowongan.text.toString()
        val namaInstansi = binding.uploadNamaInstansi.text.toString()
        val jumlahLow = binding.uploadJumlahLow.text.toString()

        val lowongan = Lowongan(
            id_lowongan!!,
            lowImage,
            posisiLow,
            durasiLow,
            "Buka",
            syaratLow,
            penjelasanLow,
            namaInstansi,
            jumlahLow,
            imageUrl
        )

        if (posisiLow.isNotEmpty() && durasiLow.isNotEmpty() && syaratLow.isNotEmpty() && penjelasanLow.isNotEmpty() && namaInstansi.isNotEmpty() && jumlahLow.isNotEmpty()) {
            ref.child(id_lowongan).setValue(lowongan).addOnCompleteListener {
                Toast.makeText(
                    applicationContext, "Data lowongan berhasil ditambahkan", Toast.LENGTH_SHORT
                ).show()
                val intent = Intent(this, ActivityAddLow::class.java)
                startActivity(intent)
            }
        } else {
            Toast.makeText(this, "Harap isi semua data", Toast.LENGTH_SHORT).show()
        }

    }



    fun btnKembaliAddLow(view: View) {
        val i = Intent(applicationContext, MainActivityBerandaPerusahaan::class.java)
        startActivity(i)
    }


}