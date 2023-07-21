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
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.google.android.material.textfield.TextInputEditText
import id.ac.ubaya.informatika.ubayakostuas.R
import id.ac.ubaya.informatika.ubayakostuas.viewmodel.UserViewModel

class ChangePasswordFragment : Fragment() {

    private lateinit var sharedPreferences: SharedPreferences
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

        var sharedFile = "id.ac.ubaya.informatika.ubayakostuas"
        sharedPreferences = requireContext().getSharedPreferences(sharedFile, Context.MODE_PRIVATE)
        var idUser = sharedPreferences.getInt("idUser", 0)

        val btnChange = view?.findViewById<Button>(R.id.btnChangePass)

        btnChange?.setOnClickListener {
            var textInputCurrent = view?.findViewById<TextInputEditText>(R.id.editTextCurPass)
            var textInputNew = view?.findViewById<TextInputEditText>(R.id.editTextNewPass)
            var textInputConfirm = view?.findViewById<TextInputEditText>(R.id.editTextConfirmPass)
            if (textInputNew?.text.toString() != textInputConfirm?.text.toString()) {
                Toast.makeText(context,"New Password and Confirm New Password not same", Toast.LENGTH_SHORT).show()
            } else {
                userViewModel.getData(idUser)
                userViewModel.userLD.observe(viewLifecycleOwner, Observer {
                    if (it.password == textInputCurrent?.text.toString()) {
                        userViewModel.changePassword(textInputNew?.text.toString(), idUser)
                        Toast.makeText(context, "Change Password Success", Toast.LENGTH_SHORT).show()

                        val action = ChangePasswordFragmentDirections.actionChangePasswordFragmentToItemProfile()
                        Navigation.findNavController(view).navigate(action)
                    } else {
                        Toast.makeText(context, "Current Password is wrong", Toast.LENGTH_SHORT).show()
                    }
                })
            }
        }
    }
    }