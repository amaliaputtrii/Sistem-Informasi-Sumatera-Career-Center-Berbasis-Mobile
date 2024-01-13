package com.example.projectuasmobile

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.view.View
import android.widget.EditText
import android.widget.SearchView
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.projectuasmobile.databinding.ActivityBerandaPelamarBinding
import com.example.projectuasmobile.table.Lowongan
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener


class MainActivityBerandaPelamar : AppCompatActivity() {

    private lateinit var databaseReference: DatabaseReference
    private lateinit var lowAdapter: LowAdapter
    private val lowList: MutableList<Lowongan> = ArrayList()
    private lateinit var searchEditText: EditText

    private lateinit var binding: ActivityBerandaPelamarBinding
    private lateinit var sessionManagerPel: SessionManagerPel
    private lateinit var sessionManagerPer: SessionManagerPer

    lateinit var toggle: ActionBarDrawerToggle

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBerandaPelamarBinding.inflate(layoutInflater)
        setContentView(binding.root)

        databaseReference = FirebaseDatabase.getInstance().getReference("lowongann")
        //  setContentView(R.layout.activity_add_low)

        sessionManagerPel = SessionManagerPel(this)
        sessionManagerPer = SessionManagerPer(this)

        val recyclerView: RecyclerView = binding.recyclerViewPel
        recyclerView.layoutManager = GridLayoutManager(applicationContext, 1)

        lowAdapter = LowAdapter(lowList, object : LowAdapter.OnItemClickListener {
            override fun onItemClick(lowongan: Lowongan) {
                // Handle item click, for example, navigate to detail activity
                val intent = Intent(applicationContext, DetailLowPelamarActivity::class.java)
                intent.putExtra("id_lowongan", lowongan.id_lowongan)
                intent.putExtra("posisiLow", lowongan.posisiLow)
                intent.putExtra("durasiLow", lowongan.durasiLow)
                intent.putExtra("statusLow", lowongan.statusLow)
                intent.putExtra("syaratLow", lowongan.syaratLow)
                intent.putExtra("penjelasanLow", lowongan.penjelasanLow)
                intent.putExtra("namaInstansi", lowongan.namaInstansi)
                intent.putExtra("jumlahLow", lowongan.jumlahLow)
                intent.putExtra("imageUrl", lowongan.imageUrl)
                startActivity(intent)
            }
        })
        recyclerView.adapter = lowAdapter

        fetchDataFromFirebase()

        binding.bottomNavigationViewPel.setOnNavigationItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.home ->
                {
                    val intent = Intent(this, MainActivityBerandaPelamar::class.java)
                    startActivity(intent)
                }

                R.id.about ->

                {

                    val intent = Intent(this, TentangPelamarActivity::class.java)
                    startActivity(intent)

                }
                R.id.profile ->
                {

                    val intent = Intent(this, ProfilPelamarActivity::class.java)
                    startActivity(intent)


                }


            }
            true
        }

    }

    fun fetchDataFromFirebase() {
        val databaseReference: DatabaseReference = FirebaseDatabase.getInstance().getReference("lowongann")

        databaseReference.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                lowList.clear()

                for (productSnapshot in snapshot.children) {
                    val lowongan = productSnapshot.getValue(Lowongan::class.java)
                    lowongan?.let { lowList.add(it) }
                }

                // Refresh tampilan RecyclerView dengan adapter
                lowAdapter.notifyDataSetChanged()

                Log.d("FetchDataDebug", "Data fetched successfully. Count: ${lowList.size}")
                Log.d("FetchDataDebug", "LowList: $lowList")
            }

            override fun onCancelled(error: DatabaseError) {
                Log.e("FetchDataError", "Failed to fetch data from Firebase: $error")
                // Handle error
            }
        })

        binding.search.setOnClickListener(object : SearchView.OnQueryTextListener,
            View.OnClickListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String): Boolean {
                searchList(newText)
                return true
            }

            override fun onClick(v: View?) {
                TODO("Not yet implemented")
            }

        })

    }

    fun searchList(text: String){
        val searchList = ArrayList<Lowongan>()
        for (lowongann in lowList){
            if (lowongann.posisiLow?.lowercase()?.contains(text.lowercase()) == true){
                searchList.add(lowongann)
            }
        }
        lowAdapter.searchLowonganList(searchList)

    }


    fun displayLowongann(lowongann: List<Lowongan>) {
//         Mengupdate data produk dalam adapter
//        lowAdapter.updateData(lowongann)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (toggle.onOptionsItemSelected(item)) {
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    //ketika button 'masuk sebagai pelamar' di homepage di klik, akan ngarah ke halaman beranda pelamar
    fun btn_masuk_pel(view: View) {
        val i = Intent(applicationContext, MainActivityBerandaPelamar::class.java)
        startActivity(i)
    }

    //ketika button 'Lihat Detail' di beranda pelamar di klik, akan ngarah ke halaman detail lowongan
    fun btn_lihat_detail_pel_1(view: View) {
        val i = Intent(applicationContext, DetailLowPelamarActivity::class.java)
        startActivity(i)
    }



}