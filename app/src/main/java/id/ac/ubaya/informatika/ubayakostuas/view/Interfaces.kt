package id.ac.ubaya.informatika.ubayakostuas.view

import android.view.View
import id.ac.ubaya.informatika.ubayakostuas.model.Kost
import id.ac.ubaya.informatika.ubayakostuas.model.User

interface KostListInterface {
    fun onDetailClick(v: View)
}

interface DetailInterface{
    fun onContactUsClick(v: View)
    fun onSimulasiClick(v: View)
}

interface SimulasiInterface{
    fun onRadioClick(v: View, value:Int, obj:Kost)
}

interface RegisterInterface{
    fun onRadioClick(v:View, gender:Int, obj:User)
    fun onRegisterClick(v: View, obj: User)
}