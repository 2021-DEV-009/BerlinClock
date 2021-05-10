package be.anonymous.berlinclock.domain.data

import be.anonymous.berlinclock.domain.exception.DigitalTimeException

/**
 * Represents a digital clock with hours, minutes and seconds.
 * Hours should be between 0 and 23 (inclusive)
 * Minutes should be between 0 and 59 (inclusive)
 * Seconds should be between 0 and 59 (inclusive)
 */
data class DigitalTime(
        val hours: Int,
        val minutes: Int,
        val seconds: Int
) {

    init {
        if (hours < 0 || hours > 23) {
            throw DigitalTimeException("hours should be between 0 and 23")
        }

        if (minutes < 0 || minutes > 59) {
            throw DigitalTimeException("minutes should be between 0 and 59")
        }

        if (seconds < 0 || seconds > 59) {
            throw DigitalTimeException("seconds should be between 0 and 59")
        }
    }

}