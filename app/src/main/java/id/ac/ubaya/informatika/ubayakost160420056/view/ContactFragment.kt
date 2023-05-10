package id.ac.ubaya.informatika.ubayakost160420056.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import id.ac.ubaya.informatika.ubayakost160420056.R

class ContactFragment : BottomSheetDialogFragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_contact, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val emailKost = ContactFragmentArgs.fromBundle(requireArguments()).email
        val phoneKost = ContactFragmentArgs.fromBundle(requireArguments()).phoneNum

        view?.findViewById<TextView>(R.id.txtEmail)?.setText(emailKost)
        view?.findViewById<TextView>(R.id.txtPhoneNum)?.setText(phoneKost)
    }
}