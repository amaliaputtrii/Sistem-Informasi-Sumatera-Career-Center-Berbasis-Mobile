package com.example.projectuasmobile

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.projectuasmobile.table.Lowongan

//class untuk menampilkan yang disimpan di firebase

class LowAdapter(
    private var lowList: MutableList<Lowongan> = mutableListOf(),
    private val onItemClickListener: OnItemClickListener
)  : RecyclerView.Adapter<LowViewHolder>() {


    interface OnItemClickListener {
        fun onItemClick(lowongan: Lowongan)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LowViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.low_card_layout_pelamar, parent, false)
        return LowViewHolder(view)
    }

    override fun onBindViewHolder(holder: LowViewHolder, position: Int) {
        val lowongan = lowList[position]
        lowongan.imageUrl?.let {
            Glide.with(holder.itemView.context)
                .load(it)
                .placeholder(R.drawable.low2)
                .into(holder.imageViewLow)
        }
        holder.textViewNamaPosisiBeranda.text = lowongan.posisiLow
        holder.textViewDurasiBeranda.text = lowongan.durasiLow
        holder.textViewStatusBeranda.text = lowongan.statusLow
        holder.textViewNamaIntansiBeranda.text = lowongan.namaInstansi
        holder.textViewJumlahLowBeranda.text = lowongan.jumlahLow

        holder.bind(lowongan, onItemClickListener)
    }
    override fun getItemCount(): Int {
        return lowList.size
    }

    fun searchLowonganList(searchList: List<Lowongan>){
        lowList = searchList.toMutableList()
        notifyDataSetChanged()
    }
}


