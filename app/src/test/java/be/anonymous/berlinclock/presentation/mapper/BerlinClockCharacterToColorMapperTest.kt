package be.anonymous.berlinclock.presentation.mapper

import be.anonymous.berlinclock.R
import be.anonymous.berlinclock.presentation.exception.ColorMapperException
import org.junit.Assert
import org.junit.Test

class BerlinClockCharacterToColorMapperTest {

    private val mapper = BerlinClockCharacterToColorResMapperImpl()

    @Test
    fun map_whenR_returnRedColorRes() {
        // Act
        val colorRes = mapper.map('R')

        // Assert
        Assert.assertEquals(R.color.clock_red, colorRes)
    }

    @Test
    fun map_whenY_returnYellowColorRes() {
        // Act
        val colorRes = mapper.map('Y')

        // Assert
        Assert.assertEquals(R.color.clock_yellow, colorRes)
    }

    @Test
    fun map_whenO_returnOffColorRes() {
        // Act
        val colorRes = mapper.map('O')

        // Assert
        Assert.assertEquals(R.color.clock_off, colorRes)
    }

    @Test(expected = ColorMapperException::class)
    fun map_whenInvalidCharacter_throwException() {
        // Act
        mapper.map('X')
    }

}