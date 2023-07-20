package id.ac.ubaya.informatika.ubayakostuas.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import id.ac.ubaya.informatika.ubayakostuas.databinding.HistoryListItemBinding
import id.ac.ubaya.informatika.ubayakostuas.databinding.KostListItemBinding
import id.ac.ubaya.informatika.ubayakostuas.model.Kost
import id.ac.ubaya.informatika.ubayakostuas.model.UserBookKost
import id.ac.ubaya.informatika.ubayakostuas.model.UserBookKostWithKost

class HistoryBookKostAdapter(val historyKost:ArrayList<UserBookKostWithKost>) : RecyclerView.Adapter<HistoryBookKostAdapter.HistoryBookViewHolder>(), HistoryKostInterface {
    class HistoryBookViewHolder(var view: HistoryListItemBinding) : RecyclerView.ViewHolder(view.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HistoryBookViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = HistoryListItemBinding.inflate(inflater, parent, false)

        return HistoryBookViewHolder(view)
    }

    override fun getItemCount(): Int {
        return historyKost.size
    }
    override fun onBindViewHolder(holder: HistoryBookViewHolder, position: Int) {
        holder.view.bookKostWithKost = historyKost[position]
    }

    fun updateHistoryKost(newHistoryList: List<UserBookKostWithKost>){
        historyKost.clear()
        historyKost.addAll(newHistoryList)
        notifyDataSetChanged()
    }
}