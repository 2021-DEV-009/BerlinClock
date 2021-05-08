package be.anonymous.berlinclock.di

import be.anonymous.berlinclock.domain.service.*
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class ServicesModule {

    @Binds
    abstract fun bindCurrentTimeService(
            service: CurrentTimeServiceImpl
    ): CurrentTimeService

    @Binds
    abstract fun bindCalendarToDigitalTimeConverterService(
            service: CalendarToDigitalTimeConverterServiceImpl
    ): CalendarToDigitalTimeConverterService

    @Binds
    abstract fun bindBerlinClockConverterService(
            service: BerlinClockConverterServiceImpl
    ): BerlinClockConverterService

}