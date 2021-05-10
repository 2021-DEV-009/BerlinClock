package be.anonymous.berlinclock.domain.service

import org.junit.Assert
import org.junit.Test
import java.util.*

class CalendarToDigitalTimeConverterServiceTest {

    private val service = CalendarToDigitalTimeConverterServiceImpl()

    @Test
    fun convert_whenValidRandomCalendarPassed_returnsDateTime() {
        // Arrange
        val calendar = Calendar.getInstance().apply {
            set(Calendar.HOUR_OF_DAY, 13)
            set(Calendar.MINUTE, 12)
            set(Calendar.SECOND, 11)
        }

        // Act
        val digitalTime = service.convert(calendar)

        // Assert
        Assert.assertEquals(13, digitalTime.hours)
        Assert.assertEquals(12, digitalTime.minutes)
        Assert.assertEquals(11, digitalTime.seconds)
    }

    @Test
    fun convert_whenValidLowerBoundsCalendarPassed_returnsDateTime() {
        // Arrange
        val calendar = Calendar.getInstance().apply {
            set(Calendar.HOUR_OF_DAY, 0)
            set(Calendar.MINUTE, 0)
            set(Calendar.SECOND, 0)
        }

        // Act
        val digitalTime = service.convert(calendar)

        // Assert
        Assert.assertEquals(0, digitalTime.hours)
        Assert.assertEquals(0, digitalTime.minutes)
        Assert.assertEquals(0, digitalTime.seconds)
    }

    @Test
    fun convert_whenValidUpperBoundsCalendarPassed_returnsDateTime() {
        // Arrange
        val calendar = Calendar.getInstance().apply {
            set(Calendar.HOUR_OF_DAY, 23)
            set(Calendar.MINUTE, 59)
            set(Calendar.SECOND, 59)
        }

        // Act
        val digitalTime = service.convert(calendar)

        // Assert
        Assert.assertEquals(23, digitalTime.hours)
        Assert.assertEquals(59, digitalTime.minutes)
        Assert.assertEquals(59, digitalTime.seconds)
    }

}