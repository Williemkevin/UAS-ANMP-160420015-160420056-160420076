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
import id.ac.ubaya.informatika.ubayakostuas.databinding.KostListItemBinding
import id.ac.ubaya.informatika.ubayakostuas.databinding.PopularListItemBinding
import id.ac.ubaya.informatika.ubayakostuas.model.Kost
import id.ac.ubaya.informatika.ubayakostuas.util.loadImage
import java.text.NumberFormat
import java.util.*
import kotlin.collections.ArrayList

class PopularListAdapter(val popularList:ArrayList<Kost>) : RecyclerView.Adapter<PopularListAdapter.PopularViewHolder>(), KostListInterface{
    class PopularViewHolder(var view: PopularListItemBinding) :RecyclerView.ViewHolder(view.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PopularListAdapter.PopularViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = PopularListItemBinding.inflate(inflater, parent, false)

        return PopularViewHolder(view)
    }

    override fun onBindViewHolder(holder: PopularViewHolder, position: Int) {
        holder.view.kost = popularList[position]
        holder.view.detailListener = this
    }

    override fun getItemCount(): Int {
       return popularList.size
    }

    fun updateKostList(newPopularList: List<Kost>){
        popularList.clear()
        popularList.addAll(newPopularList)
        notifyDataSetChanged()
    }

    override fun onDetailClick(v: View) {
        val idKost = v.tag.toString().toInt()
        val action = PopularKostFragmentDirections.actionDetailFromPopular(idKost)
        Navigation.findNavController(v).navigate(action)
    }
}