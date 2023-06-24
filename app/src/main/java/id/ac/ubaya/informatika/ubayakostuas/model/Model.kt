package id.ac.ubaya.informatika.ubayakostuas.model

import com.google.gson.annotations.SerializedName

data class Kost(
    val id:String?,
    val nama:String?,
    val alamat:String?,
    @SerializedName("harga/bulan")
    val harga_per_bulan:Int?,
    @SerializedName("harga/minggu")
    val harga_per_minggu:Int?,
    val picture:String?,
    val fasilitas:String?,
    @SerializedName("type_kost")
    val typeKost:String?,
    @SerializedName("bawa_hewan")
    val bawaHewan:String?,
    val deskripsi:String?,
    val email:String?,
    @SerializedName("phone_number")
    val phone:String?,
    @SerializedName("jumlah_kamar")
    val jumlahKamar:Int?,
    @SerializedName("kamar_terisi")
    val kamarTerisi:Int?
)