package be.anonymous.berlinclock.domain.service

import be.anonymous.berlinclock.domain.data.DigitalTime

interface BerlinClockConverterService {

    /**
     * Returns a row of 4 characters indicating 1 minute blocks
     * Y = yellow
     * O = off
     */
    fun getSingleMinutesRow(digitalTime: DigitalTime): String

    /**
     * Returns a row of 11 characters indicating 5 minute block.
     * Every third lamp is red.
     * Y = yellow
     * R = red
     * O = off
     */
    fun getFiveMinutesRow(digitalTime: DigitalTime): String

}