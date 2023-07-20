package id.ac.ubaya.informatika.ubayakostuas.view

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import id.ac.ubaya.informatika.ubayakostuas.R
import id.ac.ubaya.informatika.ubayakostuas.databinding.FragmentProfileBinding
import id.ac.ubaya.informatika.ubayakostuas.model.User
import id.ac.ubaya.informatika.ubayakostuas.viewmodel.UserViewModel

class ProfileFragment : Fragment(), ProfileInterface {
    private lateinit var viewModel: UserViewModel
    private lateinit var dataBinding:FragmentProfileBinding
    private lateinit var sharedPreferences: SharedPreferences
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        dataBinding = DataBindingUtil.inflate<FragmentProfileBinding>(inflater, R.layout.fragment_profile, container, false)
        return dataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var sharedFile = "id.ac.ubaya.informatika.ubayakostuas"
        sharedPreferences = requireContext().getSharedPreferences(sharedFile, Context.MODE_PRIVATE)
        var idUser = sharedPreferences.getInt("idUser",0)

        dataBinding.radioListener = this
        dataBinding.save = this
        dataBinding.aboutListener = this
        dataBinding.logoutListener = this
        dataBinding.changePasswordListener = this

        viewModel = ViewModelProvider(this).get(UserViewModel::class.java)
        viewModel.getData(idUser)

        observeViewModel()
    }
    fun observeViewModel() {
        viewModel.userLD.observe(viewLifecycleOwner, Observer {
            dataBinding.user = it
        })
    }

    override fun onRadioClick(v: View, gender: Int, obj: User) {
        obj.gender = v.tag.toString().toInt()
    }

    override fun onUserSaveClick(v: View, obj: User) {
        viewModel.updateUser(obj)
        Toast.makeText(v.context, "User Updated", Toast.LENGTH_SHORT).show()
        Navigation.findNavController(v).popBackStack()
    }

    override fun aboutClick(v: View) {
        val action = ProfileFragmentDirections.actionAboutFragment()
        Navigation.findNavController(v).navigate(action)
    }

    override fun logoutClick(v: View) {
        val action = ProfileFragmentDirections.actionLoginFragment()
        Navigation.findNavController(v).navigate(action)
        var sharedFile = "id.ac.ubaya.informatika.ubayakostuas"
        sharedPreferences = requireContext().getSharedPreferences(sharedFile, Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.remove("login")
        editor.remove("idUser")
        editor.apply()
    }

    override fun changePasswordClick(v: View) {
        var sharedFile = "id.ac.ubaya.informatika.ubayakostuas"
        sharedPreferences = requireContext().getSharedPreferences(sharedFile, Context.MODE_PRIVATE)
        var idUser = sharedPreferences.getInt("idUser",0)

       val action = ProfileFragmentDirections.actionChangePassword(idUser)
       Navigation.findNavController(v).navigate(action)
    }
}