package id.ac.ubaya.informatika.ubayakostuas.model

import androidx.room.*

@Dao
interface UserBookKostDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(vararg bookKost: UserBookKost)

    @Query("SELECT * FROM bookkost")
    fun selectAllBookKost(): List<Kost>

    @Query("SELECT * FROM kosts WHERE userid=:userId")
    fun selectBookKost(userId:Int)

    @Delete
    fun deleteKost(kost: Kost)
}