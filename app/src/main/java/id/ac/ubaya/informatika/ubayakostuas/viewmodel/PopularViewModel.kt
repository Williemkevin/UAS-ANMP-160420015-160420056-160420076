package id.ac.ubaya.informatika.ubayakostuas.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import id.ac.ubaya.informatika.ubayakostuas.model.Kost
import id.ac.ubaya.informatika.ubayakostuas.util.buildDb
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import java.text.DecimalFormat
import kotlin.coroutines.CoroutineContext

class PopularViewModel(application: Application) : AndroidViewModel(application), CoroutineScope {
    val kostsLD = MutableLiveData<List<Kost>>()
    val kostLoadErrorLD = MutableLiveData<Boolean>()
    val loadingLD = MutableLiveData<Boolean>()

    private var job = Job()

    override val coroutineContext: CoroutineContext
        get() = job + Dispatchers.IO

    fun refresh(){
        loadingLD.value = true
        kostLoadErrorLD.value = false

        launch {
            val db = buildDb(getApplication())
            kostsLD.postValue(db.kostDao().selectPopularKost())
        }
    }


}