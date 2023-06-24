package id.ac.ubaya.informatika.ubayakostuas.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import id.ac.ubaya.informatika.ubayakostuas.R
import id.ac.ubaya.informatika.ubayakostuas.model.Kost
import id.ac.ubaya.informatika.ubayakostuas.util.loadImage
import java.text.NumberFormat
import java.util.Locale

class KostListAdapter(val kostList:ArrayList<Kost>) : RecyclerView.Adapter<KostListAdapter.KostViewHolder>() {
    class KostViewHolder(var view: View) :RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): KostViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.kost_list_item, parent, false)

        return KostViewHolder(view)
    }

    override fun getItemCount(): Int {
        return kostList.size
    }

    override fun onBindViewHolder(holder: KostViewHolder, position: Int) {
        holder.view.findViewById<TextView>(R.id.txtNamaKost).text = kostList[position].nama
        holder.view.findViewById<TextView>(R.id.txtAlamatKost).text = kostList[position].alamat
        var harga = NumberFormat.getNumberInstance(Locale.US).format(kostList[position].harga_per_bulan)
        holder.view.findViewById<TextView>(R.id.txtHargaKost).text = "Rp. $harga"

        holder.view.findViewById<Button>(R.id.btnDetailKost).setOnClickListener {
            val action = KostListFragmentDirections.actionDetailFragment(kostList[position].id.toString())
            Navigation.findNavController(it).navigate(action)
        }

        var imageView = holder.view.findViewById<ImageView>(R.id.imageViewKost)
        var progressBar = holder.view.findViewById<ProgressBar>(R.id.progressBar)
        imageView.loadImage(kostList[position].picture, progressBar)
    }

    fun updateKostList(newKostList: ArrayList<Kost>){
        kostList.clear()
        kostList.addAll(newKostList)
        notifyDataSetChanged()
    }
}