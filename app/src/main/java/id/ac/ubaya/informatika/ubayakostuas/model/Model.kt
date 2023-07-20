package id.ac.ubaya.informatika.ubayakostuas.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date

@Entity("kosts")
data class Kost(
    @ColumnInfo(name = "name")
    val nama:String?,
    @ColumnInfo(name = "alamat")
    val alamat:String?,
    @ColumnInfo(name = "harga_bulan")
    val harga_per_bulan:Int?,
    @ColumnInfo(name = "harga_minggu")
    val harga_per_minggu:Int?,
    @ColumnInfo(name = "picture")
    val picture:String?,
    @ColumnInfo(name = "fasilitas")
    val fasilitas:String?,
    @ColumnInfo(name = "type_kost")
    val typeKost:String?,
    @ColumnInfo(name = "bawa_hewan")
    val bawaHewan:String?,
    @ColumnInfo(name = "deskripsi")
    val deskripsi:String?,
    @ColumnInfo(name = "email")
    val email:String?,
    @ColumnInfo(name = "phone")
    val phone:String?,
    @ColumnInfo(name = "jumlah_kamar")
    val jumlahKamar:Int?,
    @ColumnInfo(name = "kamar_terisi")
    val kamarTerisi:Int?
){
    @PrimaryKey(autoGenerate = true)
    var idKost:Int = 0
}

@Entity("users")
data class User(
    @ColumnInfo(name = "name")
    var nama:String?,
    @ColumnInfo(name = "email")
    var email:String?,
    @ColumnInfo(name = "phone")
    var telepon:String?,
    @ColumnInfo(name = "username")
    var username:String?,
    @ColumnInfo(name = "password")
    var password:String?,
    @ColumnInfo(name = "gender")
    var gender:Int?,
    @ColumnInfo(name = "picture")
    var picture:String?
){
    @PrimaryKey(autoGenerate = true)
    var idUser:Int = 0
}

@Entity("user_book_kost")
data class UserBookKost(
    @ColumnInfo(name = "tanggalMasuk")
    var tanggalMasuk: Int,
    @ColumnInfo(name = "lamaSewa")
    var lamaSewa: Int,
    @ColumnInfo(name = "userId")
    var userId: Int,
    @ColumnInfo(name = "kostId")
    var kostId: Int,
){
    @PrimaryKey(autoGenerate = true)
    var bookingId:Int = 0
}