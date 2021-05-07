package be.anonymous.berlinclock.domain.service

import be.anonymous.berlinclock.domain.data.DigitalTime
import kotlin.math.floor

class BerlinClockConverterServiceImpl : BerlinClockConverterService {

    override fun getSingleMinutesRow(digitalTime: DigitalTime): String {
        val amountOfMinutes = digitalTime.minutes % 5
        return "Y".repeat(amountOfMinutes) + "O".repeat(4 - amountOfMinutes)
    }

    override fun getFiveMinutesRow(digitalTime: DigitalTime): String {
        val amountOf5MinuteBlocks = floor(digitalTime.minutes / 5.0).toInt()

        return (0 until 11).map { i ->
            if (i < amountOf5MinuteBlocks) {
                if (i % 3 == 2) {
                    "R"
                } else {
                    "Y"
                }
            } else {
                "O"
            }
        }.joinToString("")
    }

}