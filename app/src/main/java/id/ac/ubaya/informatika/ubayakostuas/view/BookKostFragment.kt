package id.ac.ubaya.informatika.ubayakostuas.view

import android.app.DatePickerDialog
import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import id.ac.ubaya.informatika.ubayakostuas.R
import id.ac.ubaya.informatika.ubayakostuas.databinding.FragmentBookKostBinding
import id.ac.ubaya.informatika.ubayakostuas.model.Global
import id.ac.ubaya.informatika.ubayakostuas.model.User
import id.ac.ubaya.informatika.ubayakostuas.model.UserBookKost
import id.ac.ubaya.informatika.ubayakostuas.util.NotifHelper
import id.ac.ubaya.informatika.ubayakostuas.viewmodel.DetailViewModel
import java.util.*

class BookKostFragment : Fragment(), DateClickListener, DatePickerDialog.OnDateSetListener, BookInterface {
    private lateinit var detailModel: DetailViewModel
    private lateinit var dataBinding: FragmentBookKostBinding
    private lateinit var sharedPreferences: SharedPreferences
    var year = 0
    var month = 0
    var day = 0

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

        dataBinding.bookKost = UserBookKost(0,0,0,0)

        dataBinding.dateListener = this
        dataBinding.bookListener = this
        dataBinding.radioListener = this

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

        observeViewModel()
    }
    fun observeViewModel(){
        detailModel.kostLD.observe(viewLifecycleOwner, Observer {
            dataBinding.kost = it
        })
    }

    override fun onDateSet(p0: DatePicker?, year: Int, month: Int, day: Int) {
        dataBinding!!.txtDate.setText(
            day.toString().padStart(2,'0') + "/" + month.toString().padStart(2,'0') + "/" + year
        )
        this.year = year
        this.month = month
        this.day = day
    }

    override fun onDateClck(v: View) {
        val c = Calendar.getInstance()
        val year = c.get(Calendar.YEAR)
        val month = c.get(Calendar.MONTH)
        val day = c.get(Calendar.DAY_OF_MONTH)
        activity?.let {
                it1 -> DatePickerDialog(it1, this, year, month, day).show()
        }
    }

    override fun onRadioClick(v: View, lamaSewa: Int, obj: UserBookKost) {
        obj.lamaSewa = lamaSewa
    }

    override fun onBookClick(v: View, obj: UserBookKost) {
        val c = Calendar.getInstance()
        c.set(year,month,day,0,0,0)
        var date = (c.timeInMillis/1000L).toInt()

        obj.userId = Global.id
        obj.tanggalMasuk = date
        obj.idKost = v.tag as Int

        val bookKost = UserBookKost(date,obj.lamaSewa, obj.userId, obj.idKost)

        detailModel.addBookKost(bookKost)
        Toast.makeText(v.context, "Booking Success", Toast.LENGTH_LONG).show()
        Navigation.findNavController(v).popBackStack()

        NotifHelper(v.context).createNotification("Booking Kost Berhasil", "Terimakasih sudah melakukan booking kost")
    }

}