package be.anonymous.berlinclock.application.usecase

import be.anonymous.berlinclock.domain.data.DigitalTime
import be.anonymous.berlinclock.domain.service.BerlinClockConverterService
import be.anonymous.berlinclock.domain.service.CalendarToDigitalTimeConverterService
import be.anonymous.berlinclock.domain.service.CurrentTimeService
import io.reactivex.rxjava3.plugins.RxJavaPlugins
import io.reactivex.rxjava3.schedulers.TestScheduler
import io.reactivex.rxjava3.subscribers.TestSubscriber
import org.junit.Test
import org.mockito.internal.verification.Times
import org.mockito.kotlin.*
import java.util.*
import java.util.concurrent.TimeUnit

class GetCurrentTimeAsBerlinClockUseCaseTest {

    private val currentTimeService = mock<CurrentTimeService> {
        on { getCurrentTime() } doReturn Calendar.getInstance()
    }
    private val calendarToDigitalTimeConverterService = mock<CalendarToDigitalTimeConverterService> {
        on { convert(any()) } doReturn DigitalTime(hours = 0, minutes = 0, seconds = 0)
    }
    private val berlinClockConverterService = mock<BerlinClockConverterService> {
        on { getFullBerlinClock(any()) } doReturnConsecutively listOf("A", "B", "C")
    }

    private val useCase = GetCurrentTimeAsBerlinClockUseCaseImpl(
            currentTimeService,
            calendarToDigitalTimeConverterService,
            berlinClockConverterService
    )

    @Test
    fun executeFlowable_returnsFirstValue() {
        // Arrange
        val testScheduler = TestScheduler()
        val testSubscriber = TestSubscriber<String>()
        RxJavaPlugins.setComputationSchedulerHandler { testScheduler }

        // Act
        useCase.execute().subscribe(testSubscriber)
        testScheduler.advanceTimeBy(1, TimeUnit.NANOSECONDS)

        // Assert
        verify(currentTimeService).getCurrentTime()
        verify(calendarToDigitalTimeConverterService).convert(any())
        testSubscriber.assertValues("A")
    }

    @Test
    fun executeFlowable_returnsEverySecond() {
        // Arrange
        val testScheduler = TestScheduler()
        val testSubscriber = TestSubscriber<String>()
        RxJavaPlugins.setComputationSchedulerHandler { testScheduler }

        // Act
        useCase.execute().subscribe(testSubscriber)
        testScheduler.advanceTimeBy(2, TimeUnit.SECONDS)

        // Assert
        verify(currentTimeService, Times(3)).getCurrentTime()
        verify(calendarToDigitalTimeConverterService, Times(3)).convert(any())
        testSubscriber.assertValues("A", "B", "C")
    }

}