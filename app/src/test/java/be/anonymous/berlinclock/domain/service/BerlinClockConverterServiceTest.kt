package be.anonymous.berlinclock.domain.service

import be.anonymous.berlinclock.domain.data.DigitalTime
import org.junit.Assert
import org.junit.Test

class BerlinClockConverterServiceTest {

    private val service = BerlinClockConverterServiceImpl()

    @Test
    fun getSingleMinutesRow_returnsMinutesString() {
        // Arrange
        val times = listOf(
                DigitalTime(hours = 0, minutes = 0, seconds = 0),
                DigitalTime(hours = 23, minutes = 59, seconds = 59),
                DigitalTime(hours = 12, minutes = 31, seconds = 0),
                DigitalTime(hours = 12, minutes = 32, seconds = 0),
                DigitalTime(hours = 12, minutes = 33, seconds = 0),
                DigitalTime(hours = 12, minutes = 34, seconds = 0),
                DigitalTime(hours = 12, minutes = 35, seconds = 0)
        )

        // Act
        val results = times.map {
            service.getSingleMinutesRow(it)
        }

        // Assert
        Assert.assertEquals(listOf(
                "OOOO",
                "YYYY",
                "YOOO",
                "YYOO",
                "YYYO",
                "YYYY",
                "OOOO"
        ), results)
    }
    

}