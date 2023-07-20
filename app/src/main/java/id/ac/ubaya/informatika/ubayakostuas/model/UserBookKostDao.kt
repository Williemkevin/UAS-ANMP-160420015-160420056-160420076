package id.ac.ubaya.informatika.ubayakostuas.model

import androidx.room.*

@Dao
interface UserBookKostDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(vararg bookKost: UserBookKost)

    @Query("SELECT * FROM user_book_kost ubk inner join kosts k on ubk.idKost = k.idKost WHERE userId=:userId")
    fun selectBookKost(userId:Int): List<Kost>

    @Delete
    fun deleteKost(kost: Kost)
}