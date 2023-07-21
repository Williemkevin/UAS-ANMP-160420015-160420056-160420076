package id.ac.ubaya.informatika.ubayakostuas.view

import android.view.View
import id.ac.ubaya.informatika.ubayakostuas.model.Kost
import id.ac.ubaya.informatika.ubayakostuas.model.User
import id.ac.ubaya.informatika.ubayakostuas.model.UserBookKost
import java.text.DecimalFormat

interface KostListInterface {
    fun onDetailClick(v: View)
}
interface BookInterface {
    fun onRadioClick(v:View, lamaSewa:Int, obj:UserBookKost)

    fun onBookClick(v: View, obj: UserBookKost)
}
interface DetailInterface{
    fun onContactClick(v: View)
    fun bookingClick(v: View)
}
interface HistoryKostInterface {
    fun formatHarga(harga: Int): String? {
        val decimalFormat = DecimalFormat("##,###")
        return "Rp " + decimalFormat.format(harga)
    }
}
interface SimulasiInterface{
    fun onRadioClick(v: View, value:Int, obj:Kost)
}

interface RegisterInterface{
    fun onRadioClick(v:View, gender:Int, obj:User)
    fun onRegisterClick(v: View, obj: User)
}

interface ProfileInterface{
    fun onRadioClick(v:View, gender:Int, obj:User)
    fun onUserSaveClick(v: View, obj: User)
    fun aboutClick(v: View)
    fun logoutClick(v: View)
    fun changePasswordClick(v:View)
}

interface DateClickListener{
    fun onDateClck(v:View)
}

interface LoginInterface{
    fun onLoginClick(v: View, obj: User)

    fun registerClick(v: View)
}