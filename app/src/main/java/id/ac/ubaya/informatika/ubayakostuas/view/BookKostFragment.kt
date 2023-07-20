package id.ac.ubaya.informatika.ubayakostuas.view

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView
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
    private lateinit var sharedPreferences: SharedPreferences

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

        val btnBook = view?.findViewById<Button>(R.id.btnBook)
        val rdoBulan = view?.findViewById<RadioButton>(R.id.rdoBookBulan)
        val radioGroupSewa = view?.findViewById<RadioGroup>(R.id.radioGroupSewa)
        detailModel.kostLD.observe(viewLifecycleOwner, Observer {
            radioGroupSewa!!.setOnCheckedChangeListener { _, checkedId ->
                val textHarga = view?.findViewById<TextView>(R.id.txtHargaBookKost)
                val radioButton = view?.findViewById<RadioButton>(checkedId)
                when (radioButton) {
                    view?.findViewById<RadioButton>(R.id.rdoBookBulan) -> textHarga?.text = String.format("Rp %,.2f", it.harga_per_bulan!!.toDouble())
                    view?.findViewById<RadioButton>(R.id.rdoBookMinggu) -> textHarga?.text = String.format("Rp %,.2f", it.harga_per_minggu!!.toDouble())
                }
            }
        })

        btnBook?.setOnClickListener {
            val sewa = if (rdoBulan!!.isChecked) 1 else 2

            detailModel = ViewModelProvider(this).get(DetailViewModel::class.java)
            val bookKost = UserBookKost(12,sewa, Global.id,idKost)
            detailModel.addBookKost(bookKost)
        }

        observeViewModel()
    }
    fun observeViewModel(){
        detailModel.kostLD.observe(viewLifecycleOwner, Observer {
            dataBinding.kost = it
        })
    }

}