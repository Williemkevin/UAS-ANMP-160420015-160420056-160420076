package id.ac.ubaya.informatika.ubayakostuas.model

import androidx.room.*


@Dao
interface KostDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(vararg kost: Kost)

    @Query("SELECT * FROM kosts")
    fun selectAllKost(): List<Kost>

    @Query("SELECT * FROM kosts WHERE idKost= :id")
    fun selectKost(id:Int): Kost

//    @Query("UPDATE kosts SET picture='https://www.desain.id/blog/storage/uploads/contents/371/ide-desain-rumah-kos-kosan-2-lantai.png' WHERE idKost = :id")
//    fun update(id:Int)

    @Delete
    fun deleteKost(kost: Kost)
}