package id.ac.ubaya.informatika.ubayakostuas.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import id.ac.ubaya.informatika.ubayakostuas.R
import id.ac.ubaya.informatika.ubayakostuas.databinding.FragmentBookKostBinding
import id.ac.ubaya.informatika.ubayakostuas.databinding.FragmentDetailKostBinding
import id.ac.ubaya.informatika.ubayakostuas.model.Kost
import id.ac.ubaya.informatika.ubayakostuas.viewmodel.DetailViewModel

class BookKostFragment : Fragment(), BookInterface {
    private lateinit var bookModel: DetailViewModel
    private lateinit var dataBinding: FragmentBookKostBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        dataBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_book_kost, container, false)
        dataBinding.radioListener = this
        return dataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val idKost = BookKostFragmentArgs.fromBundle(requireArguments()).idKost

        bookModel = ViewModelProvider(this).get(DetailViewModel::class.java)
        bookModel.fetch(idKost)

        observeViewModel()

    }

    fun observeViewModel(){
        bookModel.kostLD.observe(viewLifecycleOwner, Observer {
            dataBinding.kost = it
        })
    }

    override fun onRadioClick(v: View, harga: Int, obj: Kost) {
//        obj.harga_per_bulan = v.tag.toString().toInt()
    }
}