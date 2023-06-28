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

class SearchAdapter(var kostList:ArrayList<Kost>):RecyclerView.Adapter<SearchAdapter.KostViewHolder>() {
    class KostViewHolder(var view: View): RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): KostViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.search_list_item, parent, false)

        return KostViewHolder(view)
    }

    override fun getItemCount(): Int {
        return kostList.size
    }

    override fun onBindViewHolder(holder: KostViewHolder, position: Int) {
        var imageView = holder.view.findViewById<ImageView>(R.id.imageViewSearch)
        var progressBar = holder.view.findViewById<ProgressBar>(R.id.progressBarSearch)
        imageView.loadImage(kostList[position].picture, progressBar)

        holder.view.findViewById<TextView>(R.id.txtNamaSearch).text = kostList[position].nama
        holder.view.findViewById<TextView>(R.id.txtTypeSearch).text = "Kost " + kostList[position].typeKost
        var harga = NumberFormat.getNumberInstance(Locale.US).format(kostList[position].harga_per_bulan)
        holder.view.findViewById<TextView>(R.id.txtHargaSearch).text = "Rp. $harga"

        imageView.setOnClickListener{
            val action = SearchFragmentDirections.actionDetailFromSearch(kostList[position].idKost)
            Navigation.findNavController(it).navigate(action)
        }

    }

    fun updateKostList(newKostList: List<Kost>){
        kostList.clear()
        kostList.addAll(newKostList)
        notifyDataSetChanged()
    }

    fun setFilteredList(kostFilter: ArrayList<Kost>){
        this.kostList = kostFilter
        notifyDataSetChanged()
    }
}