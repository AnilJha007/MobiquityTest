package com.mobile.mobiquityassignment.service.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.mobile.mobiquityassignment.service.database.dao.CityDao
import com.mobile.mobiquityassignment.service.database.entity.City

@Database(entities = [City::class], version = 1, exportSchema = false)
abstract class MobiquityDatabase : RoomDatabase() {

    /**
     * Connects the database to the DAO.
     */
    abstract fun cityDao(): CityDao

    companion object {

        @Volatile
        private var INSTANCE: MobiquityDatabase? = null

        fun getInstance(context: Context): MobiquityDatabase {
            // Multiple threads can ask for the database at the same time, ensure we only initialize
            // it once by using synchronized. Only one thread may enter a synchronized block at a
            // time.
            synchronized(this) {

                // Copy the current value of INSTANCE to a local variable so Kotlin can smart cast.
                // Smart cast is only available to local variables.
                var instance = INSTANCE

                // If instance is `null` make a new database instance.
                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        MobiquityDatabase::class.java,
                        "Mobiquity_database"
                    )
                        // Wipes and rebuilds instead of migrating if no Migration object.
                        // Migration is not part of this lesson. You can learn more about
                        // migration with Room in this blog post:
                        .fallbackToDestructiveMigration()
                        .build()
                    // Assign INSTANCE to the newly created database.
                    INSTANCE = instance
                }

                // Return instance; smart cast to be non-null.
                return instance
            }
        }
    }
}