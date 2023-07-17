package id.ac.ubaya.informatika.ubayakostuas.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import id.ac.ubaya.informatika.ubayakostuas.R
import id.ac.ubaya.informatika.ubayakostuas.model.Global
import id.ac.ubaya.informatika.ubayakostuas.viewmodel.ListViewModel


class KostListFragment : Fragment() {
    private lateinit var viewModel:ListViewModel
    private val kostListAdapter = KostListAdapter(arrayListOf())

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_kost_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

//        Toast.makeText(context, Global.id.toString(), Toast.LENGTH_SHORT).show()

        viewModel = ViewModelProvider(this).get(ListViewModel::class.java)
        viewModel.refresh()

        val recView = view.findViewById<RecyclerView>(R.id.recViewListKost)
        recView.layoutManager = GridLayoutManager(context, 2)
        recView.adapter = kostListAdapter

        observeViewModel()

        view.findViewById<SwipeRefreshLayout>(R.id.refreshLayout).setOnRefreshListener {
            recView.visibility = View.GONE
            view.findViewById<TextView>(R.id.txtErrorKost).visibility = View.GONE
            view.findViewById<ProgressBar>(R.id.progressLoadKost).visibility = View.VISIBLE
            viewModel.refresh()
            view.findViewById<SwipeRefreshLayout>(R.id.refreshLayout).isRefreshing = false
        }
    }

    fun observeViewModel(){
        viewModel.kostsLD.observe(viewLifecycleOwner, Observer {
            kostListAdapter.updateKostList(it)
            val txtError = view?.findViewById<TextView>(R.id.txtErrorKost)
            val recView = view?.findViewById<RecyclerView>(R.id.recViewListKost)
            val progressLoad = view?.findViewById<ProgressBar>(R.id.progressLoadKost)
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