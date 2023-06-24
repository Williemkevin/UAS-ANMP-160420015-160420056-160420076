package id.ac.ubaya.informatika.ubayakostuas.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import id.ac.ubaya.informatika.ubayakostuas.model.Kost

class DetailViewModel(application: Application) :AndroidViewModel(application) {
    val kostLD = MutableLiveData<Kost>()
    val TAG = "volleyTag"
    private var queue: RequestQueue? = null

    fun fetch(kostID : String) {
        queue = Volley.newRequestQueue(getApplication())
        val url = "http://10.0.2.2/anmp/getKost.php"
        val stringRequest = StringRequest(
            Request.Method.GET, url, {
                val sType = object : TypeToken<ArrayList<Kost>>() { }.type
                val result = Gson().fromJson<ArrayList<Kost>>(it, sType)
                for (item in 0 until result.count()) if (result.get(item).id == kostID) kostLD.value = result.get(item)
                Log.d("showdetail", it)
            },
            {
                Log.d("showdetail", it.toString())
            })

        stringRequest.tag = TAG
        queue?.add(stringRequest)
    }
}