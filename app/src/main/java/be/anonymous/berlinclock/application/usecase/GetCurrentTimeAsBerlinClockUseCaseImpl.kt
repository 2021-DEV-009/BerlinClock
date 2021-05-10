package be.anonymous.berlinclock.application.usecase

import be.anonymous.berlinclock.domain.service.BerlinClockConverterService
import be.anonymous.berlinclock.domain.service.CalendarToDigitalTimeConverterService
import be.anonymous.berlinclock.domain.service.CurrentTimeService
import io.reactivex.rxjava3.core.Flowable
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class GetCurrentTimeAsBerlinClockUseCaseImpl @Inject constructor(
        private val currentTimeService: CurrentTimeService,
        private val calendarToDigitalTimeConverterService: CalendarToDigitalTimeConverterService,
        private val berlinClockConverterService: BerlinClockConverterService
) : GetCurrentTimeAsBerlinClockUseCase {

    override fun execute(): Flowable<String> {
        return Flowable.interval(0, 1, TimeUnit.SECONDS)
                .map {
                    val calendar = currentTimeService.getCurrentTime()
                    val digitalTime = calendarToDigitalTimeConverterService.convert(calendar)
                    berlinClockConverterService.getFullBerlinClock(digitalTime)
                }
    }

}