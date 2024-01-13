package com.example.projectuasmobile

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide

class DetailLowPelamarActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_low_pelamar)

        val id_lowongan = intent.getStringExtra("id_lowongan")
        val posisiLow = intent.getStringExtra("posisiLow")
        val durasiLow = intent.getStringExtra("durasiLow")
        val syaratLow = intent.getStringExtra("syaratLow")
        val penjelasanLow = intent.getStringExtra("penjelasanLow")
        val namaInstansi = intent.getStringExtra("namaInstansi")
        val jumlahLow = intent.getStringExtra("jumlahLow")
        val statusLow = intent.getStringExtra("statusLow")
        val imageUrl = intent.getStringExtra("imageUrl")

        val konfirmasi = Intent(applicationContext, MainActivityUploadCV::class.java)


        findViewById<TextView>(R.id.textViewPosisiDetail).text = posisiLow
        findViewById<TextView>(R.id.textViewDurasiDetail).text = durasiLow
        findViewById<TextView>(R.id.textViewSyaratLowDetail).text = syaratLow
        findViewById<TextView>(R.id.textViewPenjelasanLowDetail).text = penjelasanLow
        findViewById<TextView>(R.id.textViewNamaInstansiDetail).text = namaInstansi
        findViewById<TextView>(R.id.textViewJumlahLowDetail).text = jumlahLow
        findViewById<TextView>(R.id.textViewStatusLowDetail).text = statusLow

        val imageView = findViewById<ImageView>(R.id.imageDetailLow)
        Glide.with(this)
            .load(imageUrl)
            .placeholder(R.drawable.low1) // You can set a placeholder image
            .into(imageView)

//        fetchDataFromFirebase(id_lowongan)

    }

    fun btn_kembali_beranda_pel(view: View) {
        val i = Intent(applicationContext, MainActivityBerandaPelamar::class.java)
        startActivity(i)
    }

    fun btn_lamar_kerja(view: View) {

        val id_lowongan = intent.getStringExtra("id_lowongan")
        val posisiLow = intent.getStringExtra("posisiLow")


        val daftarIntent = Intent(applicationContext, MainActivityUploadCV::class.java)

        daftarIntent.putExtra("id_lowongan",id_lowongan)
        daftarIntent.putExtra("posisiLow",posisiLow)

        startActivity(daftarIntent)
        }

}