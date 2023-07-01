package id.ac.ubaya.informatika.ubayakostuas.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.RadioButton
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import id.ac.ubaya.informatika.ubayakostuas.R
import id.ac.ubaya.informatika.ubayakostuas.model.User
import id.ac.ubaya.informatika.ubayakostuas.viewmodel.UserViewModel

class SignUpFragment : Fragment() {
    private lateinit var viewModel: UserViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_sign_up, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(UserViewModel::class.java)
        val btnSignUp= view.findViewById<Button>(R.id.btnSignUp)
        btnSignUp.setOnClickListener {
            val email = view.findViewById<EditText>(R.id.editTextEmailSignUp)
            val username = view.findViewById<EditText>(R.id.editTextUsernameSignUp)
            val password = view.findViewById<EditText>(R.id.editTextPasswordSignUp)
            val phone = view.findViewById<EditText>(R.id.editTextPhoneSignUp)
            val name = view.findViewById<EditText>(R.id.editTextNameSignUp)

            var user = User(name.text.toString(),email.text.toString(),phone.text.toString(),username.text.toString(),password.text.toString(),)

            val list = listOf(user)
            viewModel.addUser(list)
            Toast.makeText(view.context, "Data added", Toast.LENGTH_LONG).show()
        }
    }
}