package be.anonymous.berlinclock.domain.service

import be.anonymous.berlinclock.domain.data.DigitalTime

class BerlinClockConverterServiceImpl : BerlinClockConverterService {

    override fun getSingleMinutesRow(digitalTime: DigitalTime): String {
        val amountOfMinutes = digitalTime.minutes % 5
        return "Y".repeat(amountOfMinutes) + "O".repeat(4 - amountOfMinutes)
    }

}