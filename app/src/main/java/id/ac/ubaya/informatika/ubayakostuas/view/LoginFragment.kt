package id.ac.ubaya.informatika.ubayakostuas.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.google.android.material.textfield.TextInputEditText
import id.ac.ubaya.informatika.ubayakostuas.R
import id.ac.ubaya.informatika.ubayakostuas.viewmodel.UserViewModel

class LoginFragment : Fragment() {
    private lateinit var userViewModel:UserViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_login, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        userViewModel = ViewModelProvider(this).get(UserViewModel::class.java)
        var textInputEmail = view?.findViewById<TextInputEditText>(R.id.editTextEmailLogin)
        var textInputPassword = view?.findViewById<TextInputEditText>(R.id.editTextPasswordLogin)

        userViewModel.getUser(textInputEmail.toString())

        userViewModel.userLD.observe(viewLifecycleOwner, Observer {
            Toast.makeText(context, it.nama, Toast.LENGTH_SHORT).show()
            if (it.password == textInputPassword.toString()){
                Toast.makeText(context, it.password, Toast.LENGTH_SHORT).show()
            }
        })

        val btnToSignUp= view.findViewById<Button>(R.id.btnToSignUp)
//        btnToSignUp.setOnClickListener {
//            val action = LoginFragmentDirections.signUpFragmentDirections()
//            Navigation.findNavController(it).navigate(action)
//        }
    }
}