package id.ac.ubaya.informatika.ubayakostuas.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.google.android.material.textfield.TextInputEditText
import id.ac.ubaya.informatika.ubayakostuas.R
import id.ac.ubaya.informatika.ubayakostuas.databinding.FragmentProfileBinding
import id.ac.ubaya.informatika.ubayakostuas.model.Global
import id.ac.ubaya.informatika.ubayakostuas.model.User
import id.ac.ubaya.informatika.ubayakostuas.util.loadImage
import id.ac.ubaya.informatika.ubayakostuas.viewmodel.UserViewModel

class ProfileFragment : Fragment(), ProfileInterface {
    private lateinit var viewModel: UserViewModel
    private lateinit var dataBinding:FragmentProfileBinding
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

        dataBinding.radioListener = this
        dataBinding.save = this
        dataBinding.aboutListener = this

        viewModel = ViewModelProvider(this).get(UserViewModel::class.java)
        viewModel.getData(Global.id)

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
}