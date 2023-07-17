package id.ac.ubaya.informatika.ubayakostuas.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import id.ac.ubaya.informatika.ubayakostuas.R
import id.ac.ubaya.informatika.ubayakostuas.databinding.FragmentDetailKostBinding
import id.ac.ubaya.informatika.ubayakostuas.util.loadImage
import id.ac.ubaya.informatika.ubayakostuas.viewmodel.DetailViewModel
import java.text.NumberFormat
import java.util.Locale

class DetailKostFragment : Fragment(), DetailInterface {
    private lateinit var detailModel: DetailViewModel
    private lateinit var dataBinding:FragmentDetailKostBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        dataBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_detail_kost, container, false)
        dataBinding.bookingListener = this
        dataBinding.simulasiListener = this

        return dataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val idKost = DetailKostFragmentArgs.fromBundle(requireArguments()).kostId

        detailModel = ViewModelProvider(this).get(DetailViewModel::class.java)
        detailModel.fetch(idKost)

        observeViewModel()
    }

    fun observeViewModel(){
        detailModel.kostLD.observe(viewLifecycleOwner, Observer {
            dataBinding.kost = it
        })
    }

    override fun onContactClick(v: View) {
        val idKost = v.tag.toString().toInt()

        val action = DetailKostFragmentDirections.actionContactFragment(idKost)
        Navigation.findNavController(v).navigate(action)
    }

    override fun bookingClick(v: View) {
        val idKost = v.tag.toString().toInt()

        val action = DetailKostFragmentDirections.actionBookFragment(idKost)
        Navigation.findNavController(v).navigate(action)
    }
}