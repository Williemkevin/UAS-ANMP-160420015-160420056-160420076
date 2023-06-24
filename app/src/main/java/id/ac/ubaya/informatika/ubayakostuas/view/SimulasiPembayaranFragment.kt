package id.ac.ubaya.informatika.ubayakostuas.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView
import id.ac.ubaya.informatika.ubayakostuas.R
import java.text.NumberFormat
import java.util.*

class SimulasiPembayaranFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_simulasi_pembayaran, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val harga_bulan = SimulasiPembayaranFragmentArgs.fromBundle(requireArguments()).hargaPerBulan
        val hargaBulan = Integer.parseInt(harga_bulan)
        val harga_bulan_fix = NumberFormat.getNumberInstance(Locale.US).format(hargaBulan).toString()

        val harga_bulan_deposit = Integer.parseInt(harga_bulan) + 200000
        val harga_bulan_deposit_fix = NumberFormat.getNumberInstance(Locale.US).format(harga_bulan_deposit)

        val harga_minggu = SimulasiPembayaranFragmentArgs.fromBundle(requireArguments()).hargaPerMinggu
        val hargaMinggu = Integer.parseInt(harga_minggu)
        val harga_minggu_fix = NumberFormat.getNumberInstance(Locale.US).format(hargaMinggu).toString()

        val harga_minggu_deposit = Integer.parseInt(harga_minggu) + 100000
        val harga_minggu_deposit_fix = NumberFormat.getNumberInstance(Locale.US).format(harga_minggu_deposit)

        var dp_bulan = Integer.parseInt(harga_bulan) * 0.3
        var dp_bulan_fix = NumberFormat.getNumberInstance(Locale.US).format(dp_bulan)

        var dp_minggu = Integer.parseInt(harga_minggu) * 0.3
        var dp_minggu_fix = NumberFormat.getNumberInstance(Locale.US).format(dp_minggu)

        var sisa_bulan = (Integer.parseInt(harga_bulan) - dp_bulan) + 200000
        var sisa_bulan_fix = NumberFormat.getNumberInstance(Locale.US).format(sisa_bulan)

        var sisa_minggu = (Integer.parseInt(harga_minggu) - dp_minggu) + 100000
        var sisa_minggu_fix = NumberFormat.getNumberInstance(Locale.US).format(sisa_minggu)

        view.findViewById<RadioGroup>(R.id.radioGroupSim).setOnCheckedChangeListener { radioGroup, i ->
            when (i){
                R.id.rdoBulan -> {
                    view.findViewById<TextView>(R.id.txtDP).text = "Rp$dp_bulan_fix"
                    view.findViewById<TextView>(R.id.txtPelunasan).text = "Rp$sisa_bulan_fix"
                    view.findViewById<TextView>(R.id.txtPembayaranFull).text = "Rp$harga_bulan_deposit_fix"
                    view.findViewById<TextView>(R.id.txtHargaSewaPer).text = "Rp$harga_bulan_fix"
                    view.findViewById<TextView>(R.id.txtTotalSelanjutnya).text = "Rp$harga_bulan_fix"
                    view.findViewById<TextView>(R.id.txtHargaSewaPerSelanjutnya).text = "Harga Sewa Per Bulan"
                }
                R.id.rdoMinggu -> {
                    view.findViewById<TextView>(R.id.txtDP).text = "Rp$dp_minggu_fix"
                    view.findViewById<TextView>(R.id.txtPelunasan).text = "Rp$sisa_minggu_fix"
                    view.findViewById<TextView>(R.id.txtPembayaranFull).text = "Rp$harga_minggu_deposit_fix"
                    view.findViewById<TextView>(R.id.txtHargaSewaPer).text = "Rp$harga_minggu_fix"
                    view.findViewById<TextView>(R.id.txtTotalSelanjutnya).text = "Rp$harga_minggu_fix"
                    view.findViewById<TextView>(R.id.txtHargaSewaPerSelanjutnya).text = "Harga Sewa Per Minggu"
                }
            }
        }
        if (view.findViewById<RadioButton>(R.id.rdoBulan).isChecked){
            view.findViewById<TextView>(R.id.txtDP).text = "Rp$dp_bulan_fix"
            view.findViewById<TextView>(R.id.txtPelunasan).text = "Rp$sisa_bulan_fix"
            view.findViewById<TextView>(R.id.txtPembayaranFull).text = "Rp$harga_bulan_deposit_fix"
            view.findViewById<TextView>(R.id.txtHargaSewaPer).text = "Rp$harga_bulan_fix"
            view.findViewById<TextView>(R.id.txtTotalSelanjutnya).text = "Rp$harga_bulan_fix"
        }
    }
}