package com.example.dndcharactergenerator.utils

import com.example.dndcharactergenerator.utils.database.CharacterDao
import com.example.dndcharactergenerator.utils.database.CharacterRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Singleton
    @Provides
    fun provideEmployeeRepository(characterDao: CharacterDao): CharacterRepository {
        return CharacterRepository(characterDao)
    }
}