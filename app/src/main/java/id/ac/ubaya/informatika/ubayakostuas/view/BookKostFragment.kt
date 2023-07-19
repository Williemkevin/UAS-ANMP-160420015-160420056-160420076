package id.ac.ubaya.informatika.ubayakostuas.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import id.ac.ubaya.informatika.ubayakostuas.R
import id.ac.ubaya.informatika.ubayakostuas.databinding.FragmentBookKostBinding
import id.ac.ubaya.informatika.ubayakostuas.model.Global
import id.ac.ubaya.informatika.ubayakostuas.model.UserBookKost
import id.ac.ubaya.informatika.ubayakostuas.viewmodel.DetailViewModel

class BookKostFragment : Fragment() {
    private lateinit var detailModel: DetailViewModel
    private lateinit var dataBinding: FragmentBookKostBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        dataBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_book_kost, container, false)
        return dataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val idKost = BookKostFragmentArgs.fromBundle(requireArguments()).idKost

        detailModel = ViewModelProvider(this).get(DetailViewModel::class.java)
        detailModel.fetch(idKost)

        observeViewModel()

        val btnBook = view?.findViewById<Button>(R.id.btnBook)

        btnBook?.setOnClickListener {
            detailModel = ViewModelProvider(this).get(DetailViewModel::class.java)
            val bookKost = UserBookKost(12,1, Global.id,15)
            detailModel.addBookKost(bookKost)
        }
    }
    fun observeViewModel(){
        detailModel.kostLD.observe(viewLifecycleOwner, Observer {
            dataBinding.kost = it
        })
    }

}