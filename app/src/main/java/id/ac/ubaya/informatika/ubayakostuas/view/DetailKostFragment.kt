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
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import id.ac.ubaya.informatika.ubayakostuas.R
import id.ac.ubaya.informatika.ubayakostuas.util.loadImage
import id.ac.ubaya.informatika.ubayakostuas.viewmodel.DetailViewModel
import java.text.NumberFormat
import java.util.*

class DetailKostFragment : Fragment() {
    private lateinit var detailModel: DetailViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_detail_kost, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.findViewById<Button>(R.id.btnContactUs).setOnClickListener {
            val action = DetailKostFragmentDirections.actionContactFragment(detailModel.kostLD.value?.email.toString(), detailModel.kostLD.value?.phone.toString())
            Navigation.findNavController(it).navigate(action)
        }

        view.findViewById<TextView>(R.id.txtSimulasi).setOnClickListener {
            val action = DetailKostFragmentDirections.actionSimulasiFragment(detailModel.kostLD.value?.harga_per_bulan.toString(), detailModel.kostLD.value?.harga_per_minggu.toString())
            Navigation.findNavController(it).navigate(action)
        }

        val kostId = DetailKostFragmentArgs.fromBundle(requireArguments()).kostId

        detailModel = ViewModelProvider(this).get(DetailViewModel::class.java)
        detailModel.fetch(kostId)

        observeViewModel()

    }

    fun observeViewModel(){
        detailModel.kostLD.observe(viewLifecycleOwner, Observer {
            val pb = view?.findViewById<ProgressBar>(R.id.progressBarDetail)
            view?.findViewById<ImageView>(R.id.imgDetail)?.loadImage(detailModel.kostLD.value?.picture,pb!!)
            view?.findViewById<TextView>(R.id.txtNamaDetail)?.text = detailModel.kostLD.value?.nama
            view?.findViewById<TextView>(R.id.txtAlamatDetail)?.text = detailModel.kostLD.value?.alamat
            view?.findViewById<TextView>(R.id.txtKostType)?.text = detailModel.kostLD.value?.typeKost
            view?.findViewById<TextView>(R.id.txtPet)?.text = detailModel.kostLD.value?.bawaHewan
            view?.findViewById<TextView>(R.id.txtFacility)?.text = detailModel.kostLD.value?.fasilitas
            var harga = NumberFormat.getNumberInstance(Locale.US).format(detailModel.kostLD.value?.harga_per_bulan)
            view?.findViewById<TextView>(R.id.txtHargaKostDetail)?.text = "Rp. $harga / bulan"
            view?.findViewById<TextView>(R.id.txtDescription)?.text = detailModel.kostLD.value?.deskripsi
        })
    }
}