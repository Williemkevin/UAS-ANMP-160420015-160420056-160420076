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
        return dataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

//        view.findViewById<Button>(R.id.btnContactUs).setOnClickListener {
//            val action = DetailKostFragmentDirections.actionContactFragment(detailModel.kostLD.value?.email.toString(), detailModel.kostLD.value?.phone.toString())
//            Navigation.findNavController(it).navigate(action)
//        }
//
//        view.findViewById<TextView>(R.id.txtSimulasi).setOnClickListener {
//            val action = DetailKostFragmentDirections.actionSimulasiFragment(detailModel.kostLD.value?.harga_per_bulan.toString(), detailModel.kostLD.value?.harga_per_minggu.toString())
//            Navigation.findNavController(it).navigate(action)
//        }
//
        val kostId = DetailKostFragmentArgs.fromBundle(requireArguments()).kostId

        detailModel = ViewModelProvider(this).get(DetailViewModel::class.java)
        detailModel.fetch(kostId)

        observeViewModel()

//        dataBinding.btnContactUs.setOnClickListener {
//            onContactUsClick(it)
//        }
    }

    fun observeViewModel(){
        detailModel.kostLD.observe(viewLifecycleOwner, Observer {
            dataBinding.kost = it
        })
    }

    override fun onContactUsClick(v: View) {
        val email = v.tag.toString()
        val phone = v.contentDescription.toString()

        val action = DetailKostFragmentDirections.actionContactFragment(email,phone)
        Navigation.findNavController(v).navigate(action)
    }
}