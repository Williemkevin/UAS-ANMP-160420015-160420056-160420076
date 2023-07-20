package id.ac.ubaya.informatika.ubayakostuas.model

import androidx.room.*

@Dao
interface UserBookKostDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(vararg bookKost: UserBookKost)

    @Query("SELECT * FROM kosts WHERE idKost IN (SELECT idKost FROM user_book_kost WHERE userId = :userId")
    fun selectBookKost(userId:Int): List<Kost>

    @Delete
    fun deleteKost(kost: Kost)
}