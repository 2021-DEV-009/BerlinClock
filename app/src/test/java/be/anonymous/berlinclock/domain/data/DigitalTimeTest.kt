package be.anonymous.berlinclock.domain.data

import be.anonymous.berlinclock.domain.exception.DigitalTimeException
import org.junit.Assert
import org.junit.Test

class DigitalTimeTest {

    @Test
    fun whenValidLowerBoundsDigitalTimeIsCreated_noExceptionThrown() {
        // Act
        val digitalTime = DigitalTime(
                hours = 0,
                minutes = 0,
                seconds = 0
        )

        // Assert
        Assert.assertEquals(0, digitalTime.hours)
        Assert.assertEquals(0, digitalTime.minutes)
        Assert.assertEquals(0, digitalTime.seconds)
    }

    @Test
    fun whenValidUpperBoundsDigitalTimeIsCreated_noExceptionThrown() {
        // Act
        val digitalTime = DigitalTime(
                hours = 23,
                minutes = 59,
                seconds = 59
        )

        Assert.assertEquals(23, digitalTime.hours)
        Assert.assertEquals(59, digitalTime.minutes)
        Assert.assertEquals(59, digitalTime.seconds)
    }

    @Test(expected = DigitalTimeException::class)
    fun whenNegativeHoursIsPassed_exceptionThrown() {
        // Act
        DigitalTime(
                hours = -1,
                minutes = 0,
                seconds = 0
        )
    }

    @Test(expected = DigitalTimeException::class)
    fun whenOutOfRangeHoursIsPassed_exceptionThrown() {
        // Act
        DigitalTime(
                hours = 24,
                minutes = 0,
                seconds = 0
        )
    }

    @Test(expected = DigitalTimeException::class)
    fun whenNegativeMinutesIsPassed_exceptionThrown() {
        // Act
        DigitalTime(
                hours = 0,
                minutes = -1,
                seconds = 0
        )
    }

    @Test(expected = DigitalTimeException::class)
    fun whenOutOfRangeMinutesIsPassed_exceptionThrown() {
        // Act
        DigitalTime(
                hours = 0,
                minutes = 60,
                seconds = 0
        )
    }


    @Test(expected = DigitalTimeException::class)
    fun whenNegativeSecondsIsPassed_exceptionThrown() {
        // Act
        DigitalTime(
                hours = 0,
                minutes = 0,
                seconds = -1
        )
    }

    @Test(expected = DigitalTimeException::class)
    fun whenOutOfRangeSecondsIsPassed_exceptionThrown() {
        // Act
        DigitalTime(
                hours = 0,
                minutes = 0,
                seconds = 60
        )
    }

}