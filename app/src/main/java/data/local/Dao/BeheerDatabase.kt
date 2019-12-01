package data.local.Dao

import data.model.User


import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import data.model.Car

@Database(entities = arrayOf(Car::class, User::class), version = 1, exportSchema = false)
abstract class BeheerDatabase : RoomDatabase() {

    abstract fun userDao(): UserDao
    abstract fun carDao(): CarDao


    companion object {

        @Volatile
        private var INSTANCE: BeheerDatabase? = null

        fun getDatabase(context: Context): BeheerDatabase {

            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }

            synchronized(this) {

                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    BeheerDatabase::class.java, "beheer_database"
                ).build()

                INSTANCE = instance
                return instance
            }

        }
    }
}

