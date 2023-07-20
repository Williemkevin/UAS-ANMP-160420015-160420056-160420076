package id.ac.ubaya.informatika.ubayakostuas.model

import androidx.room.*

@Dao
interface UserBookKostDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(vararg bookKost: UserBookKost)

//    @Query("SELECT * FROM user_book_kost WHERE idKost IN (SELECT idKost FROM kosts WHERE userId = :userId)")
//    fun selectBookKost(userId:Int): List<Kost>
    @Query("SELECT user_book_kost.*, kosts.* FROM user_book_kost INNER JOIN kosts ON user_book_kost.user_book_kost_id_kost = kosts.idKost WHERE user_book_kost.userId = :userId")
    fun selectBookKost(userId: Int): List<UserBookKostWithKost>

    @Delete
    fun deleteKost(kost: Kost)
}