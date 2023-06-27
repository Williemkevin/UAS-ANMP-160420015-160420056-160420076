package id.ac.ubaya.informatika.ubayakostuas.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.room.Room
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.google.gson.Gson
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
    private val job = Job()

    fun addKost(list:List<Kost>) {
        launch {
            val db = buildDb(getApplication())
            db.kostDao().insertAll(*list.toTypedArray())
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