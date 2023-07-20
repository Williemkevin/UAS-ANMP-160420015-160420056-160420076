package id.ac.ubaya.informatika.ubayakostuas.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import id.ac.ubaya.informatika.ubayakostuas.model.Global
import id.ac.ubaya.informatika.ubayakostuas.model.User
import id.ac.ubaya.informatika.ubayakostuas.util.buildDb
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class UserViewModel(application: Application):AndroidViewModel(application), CoroutineScope {
    val checkLogin: MutableLiveData<Boolean> = MutableLiveData()
    val userId: MutableLiveData<Int> = MutableLiveData()
    val userLD = MutableLiveData<User>()

    private var job = Job()

    override val coroutineContext: CoroutineContext
        get() = job + Dispatchers.IO

    fun checkLogin(email:String, password:String) {
        launch {
            val db = buildDb(getApplication())
            val user =  db.userDao().getUserByEmail(email)
            checkLogin.postValue(password == user?.password ?: false)
            userId.postValue(user?.idUser)
        }
    }

    fun getData(id:Int) {
        launch {
            val db = buildDb(getApplication())
            userLD.postValue(db.userDao().selectUser(id))
        }
    }

    fun addUser(list: List<User>){
        launch {
            val db = buildDb(getApplication())
            db.userDao().insertUser(*list.toTypedArray())
        }
    }

    fun updateUser(user: User) {
        launch {
            val db = buildDb(getApplication())
            db.userDao().updateUser(user)
        }
    }

    fun changePassword(newPass:String, idUser: Int){
        launch {
            val db = buildDb(getApplication())
            val user =  db.userDao().changePasswordUser(newPass,idUser)
        }
    }
}