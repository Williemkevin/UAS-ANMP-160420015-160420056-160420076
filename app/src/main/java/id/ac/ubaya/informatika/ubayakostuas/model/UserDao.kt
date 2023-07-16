package id.ac.ubaya.informatika.ubayakostuas.model

import androidx.room.*

@Dao
interface UserDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertUser(vararg user: User)

    @Query("SELECT * FROM users")
    fun selectAllUser(): List<User>

    @Query("SELECT * FROM users WHERE email = :email LIMIT 1")
    fun getUserByEmail(email: String): User

    @Query("SELECT * FROM users WHERE idUser= :id")
    fun selectUser(id:Int): User

    @Query("UPDATE users SET name=:name, email=:email, phone=:telepon, gender=:gender WHERE idUser = :id")
    fun updateUser(name:String, email:String, telepon:String, gender:Int, id:Int)

    @Delete
    fun deleteUser(user: User)
}