package be.anonymous.berlinclock.presentation.mapper

import androidx.annotation.ColorRes
import be.anonymous.berlinclock.R
import be.anonymous.berlinclock.presentation.exception.ColorMapperException

class BerlinClockCharacterToColorResMapperImpl : BerlinClockCharacterToColorMapper {

    @ColorRes
    override fun map(char: Char): Int {
        return when (char) {
            'R' -> R.color.clock_red
            'Y' -> R.color.clock_yellow
            'O' -> R.color.clock_off
            else -> throw ColorMapperException("Invalid color character: $char")
        }
    }

}