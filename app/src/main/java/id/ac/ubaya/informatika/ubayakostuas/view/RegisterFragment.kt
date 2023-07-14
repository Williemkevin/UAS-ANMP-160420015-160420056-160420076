package id.ac.ubaya.informatika.ubayakostuas.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.google.android.material.textfield.TextInputEditText
import id.ac.ubaya.informatika.ubayakostuas.R
import id.ac.ubaya.informatika.ubayakostuas.model.User
import id.ac.ubaya.informatika.ubayakostuas.viewmodel.UserViewModel


class RegisterFragment : Fragment() {

    private lateinit var userViewModel: UserViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_register, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        userViewModel = ViewModelProvider(this).get(UserViewModel::class.java)

        val btnRegister = view?.findViewById<Button>(R.id.btnRegister)
        btnRegister?.setOnClickListener {
            var txtName = view?.findViewById<TextInputEditText>(R.id.txtInputNameRegister)
            var txtEmail = view?.findViewById<TextInputEditText>(R.id.txtInputEmailRegister)
            var txtPhone = view?.findViewById<TextInputEditText>(R.id.txtInputPhoneRegister)
            var txtUsername = view?.findViewById<TextInputEditText>(R.id.txtInputUsernameRegister)
            var txtpassword = view?.findViewById<TextInputEditText>(R.id.txtInputPasswordlRegister)

            val selectedGender = view?.findViewById<RadioGroup>(R.id.radioGroup)?.checkedRadioButtonId
//            val radioButton = view?.findViewById<RadioButton>(selectedGender!!)

            var user = User(txtName.toString(),txtEmail.toString(),txtPhone.toString(),txtUsername.toString(),txtpassword.toString(), selectedGender)

            val listUser = listOf(user)
            userViewModel.addUser(listUser)

            Toast.makeText(view.context, "Register Success", Toast.LENGTH_LONG).show()
            Navigation.findNavController(it).popBackStack()
        }
    }
}