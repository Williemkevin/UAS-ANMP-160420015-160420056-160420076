package id.ac.ubaya.informatika.ubayakostuas.view

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.google.android.material.textfield.TextInputEditText
import id.ac.ubaya.informatika.ubayakostuas.R
import id.ac.ubaya.informatika.ubayakostuas.model.Global
import id.ac.ubaya.informatika.ubayakostuas.viewmodel.UserViewModel

class ChangePasswordFragment : Fragment() {
    private lateinit var userViewModel:UserViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_change_password, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        userViewModel = ViewModelProvider(this).get(UserViewModel::class.java)

        val btnChange = view?.findViewById<Button>(R.id.button)
        btnChange?.setOnClickListener {
            var textInputCurrent = view?.findViewById<TextInputEditText>(R.id.editTextCurPass)
            var textInputNew = view?.findViewById<TextInputEditText>(R.id.editTextNewPass)
            var textInputConfirm = view?.findViewById<TextInputEditText>(R.id.editTextConfirmPass)

            userViewModel.changePassword(textInputCurrent?.text.toString(), textInputNew?.text.toString())
        }

    }
}