package id.ac.ubaya.informatika.ubayakostuas.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.RadioGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.google.android.material.textfield.TextInputEditText
import id.ac.ubaya.informatika.ubayakostuas.R
import id.ac.ubaya.informatika.ubayakostuas.databinding.FragmentRegisterBinding
import id.ac.ubaya.informatika.ubayakostuas.model.User
import id.ac.ubaya.informatika.ubayakostuas.viewmodel.UserViewModel


class RegisterFragment : Fragment(), RegisterInterface {
    private lateinit var userViewModel: UserViewModel
    private lateinit var dataBinding:FragmentRegisterBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        dataBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_register, container, false)
        return dataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        userViewModel = ViewModelProvider(this).get(UserViewModel::class.java)

        dataBinding.user = User("","","","","",3)
        dataBinding.register = this
        dataBinding.radioListener = this
    }

    override fun onRadioClick(v: View, gender: Int, obj: User) {
        obj.gender = gender
    }

    override fun onRegisterClick(v: View, obj: User) {
        val list = listOf(dataBinding.user!!)
        userViewModel.addUser(list)
        Toast.makeText(v.context, "Registration Success", Toast.LENGTH_LONG).show()
        Navigation.findNavController(v).popBackStack()
    }
}