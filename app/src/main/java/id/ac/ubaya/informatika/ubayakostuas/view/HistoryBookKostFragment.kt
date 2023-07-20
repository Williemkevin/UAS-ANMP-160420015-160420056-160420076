package id.ac.ubaya.informatika.ubayakostuas.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import id.ac.ubaya.informatika.ubayakostuas.R
import id.ac.ubaya.informatika.ubayakostuas.viewmodel.ListViewModel

class HistoryBookKostFragment : Fragment() {
    private lateinit var viewModel:ListViewModel
    private val bookListAdapter = HistoryBookKostAdapter(arrayListOf())

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_history_book_kost, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this).get(ListViewModel::class.java)
        viewModel.getHistoryBookKost(1)

        val recView = view.findViewById<RecyclerView>(R.id.recViewListBookKost)
        recView.layoutManager = GridLayoutManager(context, 1)
        recView.adapter = bookListAdapter

        observeViewModel()

        view.findViewById<SwipeRefreshLayout>(R.id.refreshLayoutHistoryBook).setOnRefreshListener {
            recView.visibility = View.GONE
            view.findViewById<TextView>(R.id.txtErrorBookKost).visibility = View.GONE
            view.findViewById<ProgressBar>(R.id.progressLoadBookKost).visibility = View.VISIBLE
            viewModel.getHistoryBookKost(1)
            view.findViewById<SwipeRefreshLayout>(R.id.refreshLayoutHistoryBook).isRefreshing = false
        }
    }

    fun observeViewModel(){
        viewModel.kostsLD.observe(viewLifecycleOwner, Observer {
            val txtError = view?.findViewById<TextView>(R.id.txtErrorBookKost)
            val recView = view?.findViewById<RecyclerView>(R.id.recViewListBookKost)
            val progressLoad = view?.findViewById<ProgressBar>(R.id.progressLoadBookKost)
            if(it.isEmpty()) {
                txtError?.visibility = View.VISIBLE
                recView?.visibility = View.GONE
                progressLoad?.visibility = View.VISIBLE
            } else {
                txtError?.visibility = View.GONE
                recView?.visibility = View.VISIBLE
                progressLoad?.visibility = View.GONE
            }
        })
    }
}