package be.anonymous.berlinclock.di

import be.anonymous.berlinclock.application.usecase.GetCurrentTimeAsBerlinClockUseCase
import be.anonymous.berlinclock.application.usecase.GetCurrentTimeAsBerlinClockUseCaseImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class UseCasesModule {

    @Binds
    abstract fun bindGetCurrentTimeAsBerlinClockUseCase(
            useCase: GetCurrentTimeAsBerlinClockUseCaseImpl
    ): GetCurrentTimeAsBerlinClockUseCase

}