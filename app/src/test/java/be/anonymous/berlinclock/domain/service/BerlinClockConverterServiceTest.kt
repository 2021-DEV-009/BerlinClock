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

    @Test
    fun getFiveMinutesRow_returnsFiveMinutesString() {
        // Arrange
        val times = listOf(
                DigitalTime(hours = 0, minutes = 0, seconds = 0),
                DigitalTime(hours = 23, minutes = 59, seconds = 59),
                DigitalTime(hours = 12, minutes = 4, seconds = 0),
                DigitalTime(hours = 12, minutes = 23, seconds = 0),
                DigitalTime(hours = 12, minutes = 35, seconds = 0),
        )

        // Act
        val results = times.map {
            service.getFiveMinutesRow(it)
        }

        // Assert
        Assert.assertEquals(listOf(
                "OOOOOOOOOOO",
                "YYRYYRYYRYY",
                "OOOOOOOOOOO",
                "YYRYOOOOOOO",
                "YYRYYRYOOOO",
        ), results)
    }

    @Test
    fun getSingleHoursRow_returnsSingleHoursString() {
        // Arrange
        val times = listOf(
                DigitalTime(hours = 0, minutes = 0, seconds = 0),
                DigitalTime(hours = 23, minutes = 59, seconds = 59),
                DigitalTime(hours = 6, minutes = 4, seconds = 0),
                DigitalTime(hours = 2, minutes = 4, seconds = 0),
                DigitalTime(hours = 8, minutes = 23, seconds = 0),
                DigitalTime(hours = 14, minutes = 35, seconds = 0),
        )

        // Act
        val results = times.map {
            service.getSingleHoursRow(it)
        }

        // Assert
        Assert.assertEquals(listOf(
                "OOOO",
                "RRRO",
                "ROOO",
                "RROO",
                "RRRO",
                "RRRR",
        ), results)
    }

    @Test
    fun getFiveHoursRow_returnsFiveHoursString() {
        // Arrange
        val times = listOf(
                DigitalTime(hours = 0, minutes = 0, seconds = 0),
                DigitalTime(hours = 23, minutes = 59, seconds = 59),
                DigitalTime(hours = 2, minutes = 4, seconds = 0),
                DigitalTime(hours = 8, minutes = 23, seconds = 0),
                DigitalTime(hours = 16, minutes = 35, seconds = 0),
        )

        // Act
        val results = times.map {
            service.getFiveHoursHoursRow(it)
        }

        // Assert
        Assert.assertEquals(listOf(
                "OOOO",
                "RRRR",
                "OOOO",
                "ROOO",
                "RRRO",
        ), results)
    }

    @Test
    fun getSecondsStatus_returnsSecondsString() {
        // Arrange
        val times = listOf(
                DigitalTime(hours = 0, minutes = 0, seconds = 0),
                DigitalTime(hours = 23, minutes = 59, seconds = 59),
        )

        // Act
        val results = times.map {
            service.getSecondsStatus(it)
        }

        // Assert
        Assert.assertEquals(listOf(
                "Y",
                "O"
        ), results)
    }

}