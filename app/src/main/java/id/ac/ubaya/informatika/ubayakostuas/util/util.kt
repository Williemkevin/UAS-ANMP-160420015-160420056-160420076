package id.ac.ubaya.informatika.ubayakostuas.util

import android.content.Context
import android.view.View
import android.widget.ImageView
import android.widget.ProgressBar
import androidx.databinding.BindingAdapter
import androidx.room.Room
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import id.ac.ubaya.informatika.ubayakostuas.R
import id.ac.ubaya.informatika.ubayakostuas.model.KostDatabase
import java.lang.Exception

fun ImageView.loadImage(url: String?, progressBar:ProgressBar){
    Picasso.get()
        .load(url)
        .resize(400,400)
        .centerCrop()
        .error(R.drawable.baseline_error_24)
        .into(this, object:Callback{
            override fun onSuccess() {
                progressBar.visibility = View.GONE
            }

            override fun onError(e: Exception?) {
            }

        })
}

val DB_NAME = "newkostdb"
fun buildDb(context: Context):KostDatabase {
    val db = Room.databaseBuilder(context, KostDatabase::class.java, DB_NAME)
        .build()
    return db
}

@BindingAdapter("android:imageUrl", "android:progressBar")
fun loadPhotoURL(view:ImageView, url: String, pb:ProgressBar){
    view.loadImage(url, pb)
}
