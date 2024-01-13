    package com.example.projectuasmobile

    import android.view.View
    import android.widget.Button
    import android.widget.ImageView
    import android.widget.TextView
    import androidx.cardview.widget.CardView
    import androidx.recyclerview.widget.RecyclerView
    import com.example.projectuasmobile.table.Lowongan
    class LowViewHolder (itemView: View) : RecyclerView.ViewHolder(itemView){

        val cardView: CardView = itemView.findViewById(R.id.cardViewLowPelamar)
        val imageViewLow: ImageView = itemView.findViewById(R.id.imageViewLowPelamar)
        val textViewNamaPosisiBeranda: TextView = itemView.findViewById(R.id.textViewNamaPosisiLow)
        val textViewDurasiBeranda: TextView = itemView.findViewById(R.id.textViewDurasiLow)
        val textViewStatusBeranda: TextView = itemView.findViewById(R.id.textViewStatusLow)
        val textViewNamaIntansiBeranda: TextView = itemView.findViewById(R.id.textViewNamaInstansiLow)
        val textViewJumlahLowBeranda: TextView = itemView.findViewById(R.id.textViewJumlahLow)
        val buttonLihatDetail: Button = itemView.findViewById(R.id.buttonLihatDetailPelamar)

        fun bind(lowongan: Lowongan, onItemClickListener: LowAdapter.OnItemClickListener) {
            itemView.setOnClickListener {
                onItemClickListener.onItemClick(lowongan)
            }

            buttonLihatDetail.setOnClickListener {
                onItemClickListener.onItemClick(lowongan)
            }


        }
    }