package id.ac.ubaya.informatika.ubayakostuas.view

import android.content.Context
import android.content.SharedPreferences
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
import androidx.navigation.fragment.findNavController
import com.google.android.material.textfield.TextInputEditText
import id.ac.ubaya.informatika.ubayakostuas.R
import id.ac.ubaya.informatika.ubayakostuas.model.Global
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

        val txtRegister = view?.findViewById<TextView>(R.id.txtToRegister)
        txtRegister?.setOnClickListener{
            val navController = findNavController()
            navController.navigate(R.id.action_loginFragment_to_registerFragment)
        }

        val btnLogin = view?.findViewById<Button>(R.id.button)
        btnLogin?.setOnClickListener {
            var textInputEmail = view?.findViewById<TextInputEditText>(R.id.editTextEmailLogin)
            var textInputPassword = view?.findViewById<TextInputEditText>(R.id.editTextPasswordLogin)

            userViewModel.checkLogin(textInputEmail?.text.toString(), textInputPassword?.text.toString())
            userViewModel.checkLogin.observe(this) { checkLogin ->
                if (checkLogin) {
                    var sharedFile = "id.ac.ubaya.informatika.ubayakostuas"
                    val sharedPreferences = requireActivity().getSharedPreferences(sharedFile, Context.MODE_PRIVATE)
                    var editor: SharedPreferences.Editor = sharedPreferences.edit()
                    editor.putBoolean("login", true)

                    userViewModel.userId.observe(this) { userId ->
                        editor.putInt("idUser", userId)
                        Global.id = userId
                    }
                    editor.apply()

                    val navController = findNavController()
                    navController.navigateUp()
                } else {
                    Toast.makeText(context, "Password Salah", Toast.LENGTH_SHORT).show()
                }
            }
        }


//        userViewModel.userLD.observe(viewLifecycleOwner, Observer {
//            Toast.makeText(context, it.nama, Toast.LENGTH_SHORT).show()
//            if (it.password == textInputPassword.toString()){
//                Toast.makeText(context, it.password, Toast.LENGTH_SHORT).show()
//            }
//        })
    }
}