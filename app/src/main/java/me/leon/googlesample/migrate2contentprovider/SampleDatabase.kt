/*
 * Copyright (C) 2017 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package me.leon.googlesample.migrate2contentprovider


import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context
import android.support.annotation.VisibleForTesting


/**
 * The Room database.
 */
@Database(entities = arrayOf(Cheese::class), version = 1)
abstract class SampleDatabase : RoomDatabase() {

    /**
     * @return The DAO for the Cheese table.
     */
    abstract fun cheese(): CheeseDao

    /**
     * Inserts the dummy data into the database if it is currently empty.
     */
    private fun populateInitialData() {
        if (cheese().count() == 0) {
            val cheese = Cheese()
            beginTransaction()
            try {
                for (i in Cheese.CHEESES.indices) {
                    cheese.name = Cheese.CHEESES[i]
                    cheese().insert(cheese)
                }
                setTransactionSuccessful()
            } finally {
                endTransaction()
            }
        }
    }

    companion object {

        /** The only instance  */
        private var sInstance: SampleDatabase? = null

        /**
         * Gets the singleton instance of SampleDatabase.
         *
         * @param context The context.
         * @return The singleton instance of SampleDatabase.
         */
        @JvmStatic
        @Synchronized
        fun getInstance(context: Context): SampleDatabase {
            if (sInstance == null) {
                sInstance = Room
                    .databaseBuilder(context.applicationContext, SampleDatabase::class.java, "ex")
                    .build()
                sInstance!!.populateInitialData()
            }
            return sInstance as SampleDatabase
        }

        /**
         * Switches the internal implementation with an empty in-memory database.
         *
         * @param context The context.
         */
        @VisibleForTesting
        fun switchToInMemory(context: Context) {
            sInstance = Room.inMemoryDatabaseBuilder(
                context.applicationContext,
                SampleDatabase::class.java
            ).build()
        }
    }

}
