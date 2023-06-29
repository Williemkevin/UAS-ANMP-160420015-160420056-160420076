package id.ac.ubaya.informatika.ubayakostuas.model

import androidx.room.*


@Dao
interface KostDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(vararg kost: Kost)

    @Query("SELECT * FROM kosts")
    fun selectAllKost(): List<Kost>

    @Query("SELECT * FROM kosts WHERE ((kamar_terisi * 100)/jumlah_kamar)>=70")
    fun selectPopularKost(): List<Kost>

    @Query("SELECT * FROM kosts WHERE idKost= :id")
    fun selectKost(id:Int): Kost

    @Query("UPDATE kosts SET harga_bulan=:hargaBulan, harga_minggu=:hargaMinggu WHERE idKost = :id")
    fun updatePrice(hargaBulan:Int, hargaMinggu:Int, id:Int)

    @Delete
    fun deleteKost(kost: Kost)

}