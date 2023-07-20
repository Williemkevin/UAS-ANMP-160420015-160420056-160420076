package id.ac.ubaya.informatika.ubayakostuas.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import id.ac.ubaya.informatika.ubayakostuas.databinding.KostListItemBinding
import id.ac.ubaya.informatika.ubayakostuas.model.Kost

class HistoryBookKostAdapter(val kostList:ArrayList<Kost>) : RecyclerView.Adapter<HistoryBookKostAdapter.HistoryBookViewHolder>(), HistoryKostInterface {
        class HistoryBookViewHolder(var view: KostListItemBinding) : RecyclerView.ViewHolder(view.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HistoryBookKostAdapter.HistoryBookViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = KostListItemBinding.inflate(inflater, parent, false)

        return HistoryBookKostAdapter.HistoryBookViewHolder(view)
    }

    override fun getItemCount(): Int {
        return kostList.size
    }

    override fun onBindViewHolder(holder: HistoryBookKostAdapter.HistoryBookViewHolder, position: Int) {
        holder.view.kost = kostList[position]
    }

    fun updateKostList(newKostList: List<Kost>){
        kostList.clear()
        kostList.addAll(newKostList)
        notifyDataSetChanged()
    }

}