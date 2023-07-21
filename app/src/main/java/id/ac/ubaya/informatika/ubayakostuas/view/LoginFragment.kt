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
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import com.google.android.material.textfield.TextInputEditText
import id.ac.ubaya.informatika.ubayakostuas.R
import id.ac.ubaya.informatika.ubayakostuas.databinding.FragmentLoginBinding
import id.ac.ubaya.informatika.ubayakostuas.databinding.FragmentRegisterBinding
import id.ac.ubaya.informatika.ubayakostuas.model.User
import id.ac.ubaya.informatika.ubayakostuas.viewmodel.UserViewModel

class LoginFragment : Fragment(), LoginInterface {
    private lateinit var userViewModel:UserViewModel
    private lateinit var dataBinding: FragmentLoginBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        dataBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_login, container, false)
        return dataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        userViewModel = ViewModelProvider(this).get(UserViewModel::class.java)

        dataBinding.user = User("","","","","",3, "https://media.istockphoto.com/id/1338134336/photo/headshot-portrait-african-30s-man-smile-look-at-camera.jpg?b=1&s=170667a&w=0&k=20&c=j-oMdWCMLx5rIx-_W33o3q3aW9CiAWEvv9XrJQ3fTMU=")
        dataBinding.login = this
        dataBinding.registerListener = this

    }

    override fun onLoginClick(v: View, obj: User) {
        var sharedFile = "id.ac.ubaya.informatika.ubayakostuas"
        val sharedPreferences = requireActivity().getSharedPreferences(sharedFile, Context.MODE_PRIVATE)

        if (dataBinding.user!!.email.toString() == "" || dataBinding.user!!.password.toString() == ""){
            Toast.makeText(context, "Masukkan Email dan Password", Toast.LENGTH_SHORT).show()
        } else {
            userViewModel.checkLogin(dataBinding.user!!.email.toString(), dataBinding.user!!.password.toString())
            userViewModel.checkLogin.observe(this) { checkLogin ->
                if (checkLogin) {
                    var editor: SharedPreferences.Editor = sharedPreferences.edit()
                    editor.putBoolean("login", true)
                    editor.apply()

                    userViewModel.userId.observe(viewLifecycleOwner) { userId ->
                        editor.putInt("idUser", userId)
                        editor.apply()
                    }

                    val action = LoginFragmentDirections.actionHome()
                    Navigation.findNavController(v).navigate(action)
                } else {
                    Toast.makeText(context, "Password Salah", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    override fun registerClick(v: View) {
        val action = LoginFragmentDirections.actionLoginFragmentToRegisterFragment()
        Navigation.findNavController(v).navigate(action)
    }
}