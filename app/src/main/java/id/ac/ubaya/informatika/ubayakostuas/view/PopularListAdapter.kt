package id.ac.ubaya.informatika.ubayakostuas.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import id.ac.ubaya.informatika.ubayakostuas.R
import id.ac.ubaya.informatika.ubayakostuas.model.Kost
import id.ac.ubaya.informatika.ubayakostuas.util.loadImage
import java.text.NumberFormat
import java.util.*
import kotlin.collections.ArrayList

class PopularListAdapter(val popularList:ArrayList<Kost>) : RecyclerView.Adapter<PopularListAdapter.PopularViewHolder>() {
    class PopularViewHolder(var view: View) :RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PopularListAdapter.PopularViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.popular_list_item, parent, false)

        return PopularViewHolder(view)
    }

    override fun onBindViewHolder(holder: PopularViewHolder, position: Int) {
        holder.view.findViewById<TextView>(R.id.txtNamaPopuler).text = popularList[position].nama
        var harga = NumberFormat.getNumberInstance(Locale.US).format(popularList[position].harga_per_bulan)
        holder.view.findViewById<TextView>(R.id.txtHargaPopuler).text = "Rp. $harga"
        val sisaKamar = popularList[position].jumlahKamar!! - popularList[position].kamarTerisi!!
        holder.view.findViewById<TextView>(R.id.txtSisaKamar).text = "TERSISA $sisaKamar KAMAR !!"

        var imageView = holder.view.findViewById<ImageView>(R.id.imgViewPopular)
        var progressBar = holder.view.findViewById<ProgressBar>(R.id.progressBarPopular)
        imageView.loadImage(popularList[position].picture, progressBar)

        imageView.setOnClickListener{
            val action = PopularKostFragmentDirections.actionDetailFromPopular(popularList[position].idKost)
            Navigation.findNavController(it).navigate(action)
        }
    }

    override fun getItemCount(): Int {
       return popularList.size
    }

    fun updateKostList(newPopularList: List<Kost>){
        popularList.clear()
        popularList.addAll(newPopularList)
        notifyDataSetChanged()
    }
}