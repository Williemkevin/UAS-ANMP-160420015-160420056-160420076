package id.ac.ubaya.informatika.ubayakost160420056.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import id.ac.ubaya.informatika.ubayakost160420056.model.Kost

class PopularViewModel(application: Application) : AndroidViewModel(application) {
    val kostsLD = MutableLiveData<ArrayList<Kost>>()
    val kostLoadErrorLD = MutableLiveData<Boolean>()
    val loadingLD = MutableLiveData<Boolean>()

    val TAG = "volleyTag"
    private var queue: RequestQueue? = null

    fun refresh(){
        kostLoadErrorLD.value = false
        loadingLD.value = true

        queue = Volley.newRequestQueue(getApplication())
        val url = "http://10.0.2.2/anmp/getKost.php"
        val stringRequest = StringRequest(
            Request.Method.GET, url, {
                val sType = object : TypeToken<ArrayList<Kost>>() { }.type
                val result = Gson().fromJson<ArrayList<Kost>>(it, sType)
                val listKost = ArrayList<Kost>()
                for (item in 0 until result.count()){
                    if ((result.get(item).kamarTerisi!! * 100 / result.get(item).jumlahKamar!!) >= 70){
                        listKost += result.get(item)
                    }
                }
                loadingLD.value = false
                kostsLD.value = listKost

                Log.d("showvoley", listKost.toString())
            },
            {
                Log.d("showvoley", it.toString())
                kostLoadErrorLD.value = false
                loadingLD.value = false
            })
        stringRequest.tag = TAG
        queue?.add(stringRequest)
    }

    override fun onCleared() {
        super.onCleared()
        queue?.cancelAll(TAG)
    }
}