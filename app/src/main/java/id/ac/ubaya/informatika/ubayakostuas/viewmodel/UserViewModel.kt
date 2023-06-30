package id.ac.ubaya.informatika.ubayakostuas.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.room.Room
import id.ac.ubaya.informatika.ubayakostuas.model.User
import id.ac.ubaya.informatika.ubayakostuas.util.buildDb
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import okhttp3.Dispatcher
import kotlin.coroutines.CoroutineContext

class UserViewModel(application: Application):AndroidViewModel(application), CoroutineScope {
    val userLD = MutableLiveData<User>()
    val userLoadErrorLD = MutableLiveData<Boolean>()
    val loadingLD = MutableLiveData<Boolean>()
    private var job = Job()

    override val coroutineContext: CoroutineContext
        get() = job + Dispatchers.IO

    fun getUser(email:String) {
        launch {
            val db = buildDb(getApplication())
            userLD.value =  db.userDao().getUserByEmail(email)
        }
    }

//    fun refresh(){
//        loadingLD.value = true
//        userLoadErrorLD.value = false
//
//        launch {
//            val db = buildDb(getApplication())
//            userLD.postValue(db.userDao().selectAllUser())
//        }
//    }
//
//    fun addUser(list: List<User>){
//        launch {
//            val db = buildDb(getApplication())
//            db.userDao().insertUser(*list.toTypedArray())
//        }
//    }
}