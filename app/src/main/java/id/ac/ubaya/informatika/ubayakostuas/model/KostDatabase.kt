package id.ac.ubaya.informatika.ubayakostuas.model

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import id.ac.ubaya.informatika.ubayakostuas.util.MIGRATION_1_2
import id.ac.ubaya.informatika.ubayakostuas.util.MIGRATION_2_3
import id.ac.ubaya.informatika.ubayakostuas.util.MIGRATION_3_4

//@Database(entities = arrayOf(Kost::class), arrayOf(User::class), version = 2)
@Database(entities = [Kost::class, User::class], version = 4)
abstract class KostDatabase:RoomDatabase() {
    abstract fun kostDao(): KostDao

    abstract fun userDao(): UserDao
    companion object {
        @Volatile private var instance: KostDatabase ?= null
        private val LOCK = Any()
        private fun buildDatabase(context:Context) =
            Room.databaseBuilder(context.applicationContext,KostDatabase::class.java,"newkostdb").addMigrations(
                MIGRATION_1_2, MIGRATION_2_3, MIGRATION_3_4).build()

        operator fun invoke(context:Context) {
            if(instance!=null) {
                synchronized(LOCK) {
                    instance ?: buildDatabase(context).also {
                        instance = it
                    }
                }
            }
        }
    }
}