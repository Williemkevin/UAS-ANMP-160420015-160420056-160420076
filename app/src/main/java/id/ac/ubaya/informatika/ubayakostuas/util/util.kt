package id.ac.ubaya.informatika.ubayakostuas.util

import android.content.Context
import android.view.View
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.room.Room
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import id.ac.ubaya.informatika.ubayakostuas.R
import id.ac.ubaya.informatika.ubayakostuas.model.KostDatabase
import java.text.DecimalFormat
import java.text.SimpleDateFormat
import java.util.*

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
    val db = Room.databaseBuilder(context, KostDatabase::class.java, DB_NAME).addMigrations(
        MIGRATION_1_2, MIGRATION_2_3, MIGRATION_3_4, MIGRATION_4_5)
        .build()
    return db
}

val MIGRATION_1_2 = object : Migration(1, 2) {
    override fun migrate(database: SupportSQLiteDatabase) {
        // Drop the existing table
        database.execSQL("DROP TABLE IF EXISTS users")

        // Recreate the table with the new structure
        database.execSQL(
            "CREATE TABLE users(idUser INTEGER PRIMARY KEY NOT NULL, name TEXT NOT NULL, email TEXT NOT NULL, phone TEXT NOT NULL, gender INTEGER NOT NULL DEFAULT 3, password TEXT, username TEXT);")
    }
}
val MIGRATION_2_3 = object : Migration(2, 3) {
    override fun migrate(database: SupportSQLiteDatabase) {
        database.execSQL(
            ""
        )
    }
}
val MIGRATION_3_4 = object : Migration(3, 4) {
    override fun migrate(database: SupportSQLiteDatabase) {
        database.execSQL(
            "ALTER TABLE users ADD COLUMN picture TEXT DEFAULT 'https://media.istockphoto.com/id/1338134336/photo/headshot-portrait-african-30s-man-smile-look-at-camera.jpg?b=1&s=170667a&w=0&k=20&c=j-oMdWCMLx5rIx-_W33o3q3aW9CiAWEvv9XrJQ3fTMU='"
        )
    }
}
val MIGRATION_4_5 = object : Migration(4, 5) {
    override fun migrate(database: SupportSQLiteDatabase) {
        database.execSQL(
            "CREATE TABLE user_book_kost(bookingId INTEGER PRIMARY KEY NOT NULL, tanggalMasuk INTEGER NOT NULL, lamaSewa INTEGER NOT NULL, userId INTEGER NOT NULL, kostId INTEGER NOT NULL);")
    }
}


@BindingAdapter("android:imageUrl", "android:progressBar")
fun loadPhotoURL(view:ImageView, url: String?, pb:ProgressBar){
    view.loadImage(url, pb)
}

@BindingAdapter("android:hargaFormat")
fun format(textView: TextView, harga: Int) {
    val decimalFormat = DecimalFormat("##,###")
    val formattedHarga = "Rp. " + decimalFormat.format(harga)
    textView.text = formattedHarga
}

@BindingAdapter("android:startBooking")
fun startBooking(textView: TextView, tanggal: Int) {
    val date = tanggal.toLong() * 1000
    val dateFormat = SimpleDateFormat("dd MMMM yyyy", Locale.getDefault())
    textView.text = dateFormat.format(Date(date))
}

@BindingAdapter("android:lamaSewa", "android:bookingAwal")
fun tanggalBerakhir(textView: TextView, lamaSewa: Int, bookingAwal:Int) {
    if(lamaSewa == 0){
        val c = Calendar.getInstance()
        c.timeInMillis = bookingAwal.toLong() * 1000
        c.add(Calendar.MONTH, 1)
        val dateFormat = SimpleDateFormat("dd MMMM yyyy", Locale.getDefault())
        textView.text = dateFormat.format(c.time)
    } else if(lamaSewa == 2){
        val c = Calendar.getInstance()
        c.timeInMillis = bookingAwal.toLong() * 1000
        c.add(Calendar.WEEK_OF_MONTH, 1)
        val dateFormat = SimpleDateFormat("dd MMMM yyyy", Locale.getDefault())
        textView.text = dateFormat.format(c.time)
    }
}