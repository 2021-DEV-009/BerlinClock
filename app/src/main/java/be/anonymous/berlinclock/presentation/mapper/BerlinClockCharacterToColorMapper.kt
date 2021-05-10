package be.anonymous.berlinclock.presentation.mapper

/**
 * Convert a single berlin clock character to an Android UI color
 */
interface BerlinClockCharacterToColorMapper {

    fun map(char: Char): Int

}