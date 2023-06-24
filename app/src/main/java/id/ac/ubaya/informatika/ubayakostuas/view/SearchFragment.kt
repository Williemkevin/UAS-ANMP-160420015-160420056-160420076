package id.ac.ubaya.informatika.ubayakostuas.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import id.ac.ubaya.informatika.ubayakostuas.R
import id.ac.ubaya.informatika.ubayakostuas.model.Kost
import id.ac.ubaya.informatika.ubayakostuas.viewmodel.ListViewModel

class SearchFragment : Fragment() {
    private lateinit var viewModel: ListViewModel
    private val searchListAdapter = SearchAdapter(arrayListOf())
//    private var kostList = ArrayList<Kost>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_search, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this).get(ListViewModel::class.java)
        viewModel.refresh()

        val searchView = view.findViewById<android.widget.SearchView>(R.id.searchView)
//        searchView.setQuery("",false)
        searchView.setOnQueryTextListener(object : android.widget.SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }
            override fun onQueryTextChange(newText: String?): Boolean {
                filterKost(newText)
                return true
            }
        })

        val recView = view.findViewById<RecyclerView>(R.id.recyclerViewSearch)
        recView.layoutManager = LinearLayoutManager(context)
        recView.adapter = searchListAdapter

        observeViewModel()
    }

    fun observeViewModel(){
        viewModel.kostsLD.observe(viewLifecycleOwner, Observer {
            searchListAdapter.updateKostList(it)
        })
    }

    private fun filterKost(query : String?){
        if (query != null){
            val filteredKost = ArrayList<Kost>()
            for (i in searchListAdapter.kostList){
                if (i.nama?.lowercase()?.contains(query) == true){
                    filteredKost.add(i)
                }
            }

            if (!filteredKost.isEmpty()){
                searchListAdapter.setFilteredList(filteredKost)
            }
        }
    }
}