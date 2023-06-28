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

    @Delete
    fun deleteKost(kost: Kost)
}