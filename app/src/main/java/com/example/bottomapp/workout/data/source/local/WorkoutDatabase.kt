package com.example.bottomapp.workout.data.source.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.bottomapp.workout.data.source.local.enteties.Exercise
import com.example.bottomapp.workout.data.source.local.enteties.TrainingSet
import com.example.bottomapp.workout.data.source.local.enteties.Workout


@Database(entities = [Workout::class, Exercise::class, TrainingSet::class], version = 1)
abstract class WorkoutDatabase : RoomDatabase() {
    abstract fun getDao(): WorkoutDao

    companion object {
        @Volatile
        private var INSTANCE: WorkoutDatabase? = null
        fun getWorkoutDatabase(context: Context): WorkoutDatabase = synchronized(this) {
            return INSTANCE ?: Room.databaseBuilder(
                context = context,
                klass = WorkoutDatabase::class.java,
                name = "workout_database"
            ).build().also { INSTANCE = it }
        }
    }
}