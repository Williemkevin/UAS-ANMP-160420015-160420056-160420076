package id.ac.ubaya.informatika.ubayakostuas.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.google.gson.reflect.TypeToken
import id.ac.ubaya.informatika.ubayakostuas.model.Kost
import id.ac.ubaya.informatika.ubayakostuas.model.KostDatabase
import id.ac.ubaya.informatika.ubayakostuas.util.buildDb
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class DetailViewModel(application: Application) :AndroidViewModel(application), CoroutineScope {
    val kostLD = MutableLiveData<Kost>()
    private val job = Job()

    fun fetch(idKost: Int) {
        launch {
            val db = buildDb(getApplication())
            kostLD.postValue(db.kostDao().selectKost(idKost))
        }
    }

//    fun update(id: Int) {
//        launch {
//            val db = buildDb(getApplication())
//            db.kostDao().update(id)
//        }
//    }

    override val coroutineContext: CoroutineContext
        get() = job + Dispatchers.IO
}