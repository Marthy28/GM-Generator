package com.example.dndcharactergenerator.utils.database

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
private object DatabaseModule {

    @Provides
    fun provideEmployeeDao(appDatabase: CharacterRoomDatabase): CharacterDao {
        return appDatabase.characterDao()
    }

    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext context: Context): CharacterRoomDatabase {
        return Room.databaseBuilder(
            context.applicationContext,
            CharacterRoomDatabase::class.java,
            "appDB"
        ).build()
    }

}