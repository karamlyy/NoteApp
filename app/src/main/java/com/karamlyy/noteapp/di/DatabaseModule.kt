package com.karamlyy.noteapp.di

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import com.karamlyy.noteapp.data.AppDatabase
import com.karamlyy.noteapp.utilities.Constants.DATABASE_NAME
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {
    @Singleton
    @Provides
    fun provideAppDatabase(@ApplicationContext context: Context) = Room.databaseBuilder(
        context, AppDatabase::class.java, DATABASE_NAME
    ).build()

    @Singleton
    @Provides
    fun provideNoteDao(database: AppDatabase) = database.noteDao()
}