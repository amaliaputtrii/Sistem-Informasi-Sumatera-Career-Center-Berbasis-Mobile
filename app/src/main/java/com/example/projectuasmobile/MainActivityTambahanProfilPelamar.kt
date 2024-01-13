package com.example.projectuasmobile

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.projectuasmobile.databinding.ActivityTambahanProfilPelamarBinding
import com.example.projectuasmobile.table.DataDiriPelamar
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class MainActivityTambahanProfilPelamar : AppCompatActivity(){
    private lateinit var db : DatabaseReference
    private lateinit var binding: ActivityTambahanProfilPelamarBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTambahanProfilPelamarBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnSubmit.setOnClickListener(){
            val nama = intent.getStringExtra("nama")
            val tempat = intent.getStringExtra("tempat")
            val agama = intent.getStringExtra("agama")
            val alamat = intent.getStringExtra("alamat")
            val emailprofile = intent.getStringExtra("emailprofile")
            val tanggal = intent.getStringExtra("tanggal")
            val nohp = intent.getStringExtra("nohp")
            val kelamin = intent.getStringExtra("kelamin")
            val organisasi = binding.namaOrganisasi.text.toString()
            val ipk = binding.edtIpk.text.toString()
            val jabatan = binding.edtJabatan.text.toString()
            val kuliah = binding.edtKuliah.text.toString()
            val studi = binding.edtStudi.text.toString()
            val univ = binding.edtUniv.text.toString()
            val perusahaan = binding.edtNamaPerusahaan.text.toString()
            val pdk = binding.edtPdkAkhir.text.toString()
            val tipe = binding.edtTipeKerja.text.toString()

            val item = DataDiriPelamar(nama,emailprofile,kelamin,tempat,tanggal,agama,alamat,nohp,pdk,kuliah,univ,studi,ipk,tipe,perusahaan,organisasi,jabatan)
            db = FirebaseDatabase.getInstance().getReference("dataPelamar")
            val databaseReference = FirebaseDatabase.getInstance().reference
            val id = databaseReference.push().key
            db.child(id.toString()).setValue(item).addOnSuccessListener {
                Toast. makeText(applicationContext,"berhasil submit", Toast.LENGTH_SHORT).show()
            }.addOnFailureListener {
                Toast. makeText(applicationContext,"gagal submit", Toast.LENGTH_SHORT).show()
            }
        }

    }
    fun btn_kembali_tambahan_profil_pel(view: View) {
        val i = Intent(applicationContext, MainActivityUbahProfilPelamar::class.java)
        startActivity(i)
    }
}