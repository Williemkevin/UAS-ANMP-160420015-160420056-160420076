package id.ac.ubaya.informatika.ubayakostuas.view

import android.view.View
import id.ac.ubaya.informatika.ubayakostuas.model.Kost

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