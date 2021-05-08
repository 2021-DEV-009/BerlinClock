package be.anonymous.berlinclock.di

import be.anonymous.berlinclock.presentation.mapper.BerlinClockCharacterToColorMapper
import be.anonymous.berlinclock.presentation.mapper.BerlinClockCharacterToColorMapperImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class PresentationModule {

    @Binds
    abstract fun bindBerlinClockCharacterToColorMapper(
            service: BerlinClockCharacterToColorMapperImpl
    ): BerlinClockCharacterToColorMapper

}