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
import id.ac.ubaya.informatika.ubayakostuas.databinding.PopularListItemBinding
import id.ac.ubaya.informatika.ubayakostuas.databinding.SearchListItemBinding
import id.ac.ubaya.informatika.ubayakostuas.model.Kost
import id.ac.ubaya.informatika.ubayakostuas.util.loadImage
import java.text.NumberFormat
import java.util.*
import kotlin.collections.ArrayList

class SearchAdapter(var kostList:ArrayList<Kost>):RecyclerView.Adapter<SearchAdapter.KostViewHolder>(), KostListInterface {
    class KostViewHolder(var view: SearchListItemBinding): RecyclerView.ViewHolder(view.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): KostViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = SearchListItemBinding.inflate(inflater, parent, false)

        return KostViewHolder(view)
    }

    override fun getItemCount(): Int {
        return kostList.size
    }

    override fun onBindViewHolder(holder: KostViewHolder, position: Int) {
        holder.view.kost = kostList[position]
        holder.view.detailListener = this
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

    override fun onDetailClick(v: View) {
        val idKost = v.tag.toString().toInt()
        val action = SearchFragmentDirections.actionDetailFromSearch(idKost)
        Navigation.findNavController(v).navigate(action)
    }
}