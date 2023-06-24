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
import androidx.navigation.Navigation
import com.google.android.material.textfield.TextInputEditText
import id.ac.ubaya.informatika.ubayakostuas.R
import id.ac.ubaya.informatika.ubayakostuas.model.Global
import id.ac.ubaya.informatika.ubayakostuas.util.loadImage

class ProfileFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_profile, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.findViewById<TextInputEditText>(R.id.txtNameProfile).setText(Global.name)
        view.findViewById<TextInputEditText>(R.id.txtEmailProfile).setText(Global.email)
        view.findViewById<TextInputEditText>(R.id.txtPhoneProfile).setText(Global.phone)
        if (Global.gender == "Male"){
            view.findViewById<RadioGroup>(R.id.radioGroup).check(R.id.rdoMale)
        } else if (Global.gender == "Female"){
            view.findViewById<RadioGroup>(R.id.radioGroup).check(R.id.rdoFemale)
        } else{
            view.findViewById<RadioGroup>(R.id.radioGroup).check(R.id.rdoOthers)
        }
        var imageView = view.findViewById<ImageView>(R.id.imageViewProfile)
        var progressBar = view.findViewById<ProgressBar>(R.id.progressBarProfile)
        imageView.loadImage(Global.picture, progressBar)

        view.findViewById<Button>(R.id.btnAbout).setOnClickListener {
            val action = ProfileFragmentDirections.actionAboutFragment()
            Navigation.findNavController(it).navigate(action)
        }

        view.findViewById<Button>(R.id.btnSaveProfile).setOnClickListener {
            Global.name = view.findViewById<TextInputEditText>(R.id.txtNameProfile).text.toString()
            Global.email = view.findViewById<TextInputEditText>(R.id.txtEmailProfile).text.toString()
            Global.phone = view.findViewById<TextInputEditText>(R.id.txtPhoneProfile).text.toString()
            if (view.findViewById<RadioButton>(R.id.rdoMale).isChecked){
                Global.gender = "Male"
            } else if(view.findViewById<RadioButton>(R.id.rdoFemale).isChecked){
                Global.gender = "Female"
            } else{
                Global.gender = "Others"
            }
            Toast.makeText(activity,"Success Update Profile", Toast.LENGTH_SHORT).show()
        }
    }
}