package be.anonymous.berlinclock.domain.service

import be.anonymous.berlinclock.domain.data.DigitalTime
import kotlin.math.floor

class BerlinClockConverterServiceImpl : BerlinClockConverterService {

    override fun getFullBerlinClock(digitalTime: DigitalTime): String {
        return getSecondsStatus(digitalTime) +
                getFiveHoursHoursRow(digitalTime) +
                getSingleHoursRow(digitalTime) +
                getFiveMinutesRow(digitalTime) +
                getSingleMinutesRow(digitalTime)
    }

    override fun getSingleMinutesRow(digitalTime: DigitalTime): String {
        val amountOfMinutes = digitalTime.minutes % 5
        return "Y".repeat(amountOfMinutes) + "O".repeat(4 - amountOfMinutes)
    }

    override fun getFiveMinutesRow(digitalTime: DigitalTime): String {
        val amountOf5MinuteBlocks = floor(digitalTime.minutes / 5.0).toInt()

        return (0 until 11).joinToString("") { i ->
            if (i < amountOf5MinuteBlocks) {
                if (i % 3 == 2) {
                    "R"
                } else {
                    "Y"
                }
            } else {
                "O"
            }
        }
    }

    override fun getSingleHoursRow(digitalTime: DigitalTime): String {
        val amountOfHours = digitalTime.hours % 5
        return "R".repeat(amountOfHours) + "O".repeat(4 - amountOfHours)
    }

    override fun getFiveHoursHoursRow(digitalTime: DigitalTime): String {
        val amountOf5HourBlocks = floor(digitalTime.hours / 5.0).toInt()
        return "R".repeat(amountOf5HourBlocks) + "O".repeat(4 - amountOf5HourBlocks)
    }

    override fun getSecondsStatus(digitalTime: DigitalTime): String {
        return if (digitalTime.seconds % 2 == 0) {
            "Y"
        } else {
            "O"
        }
    }
}