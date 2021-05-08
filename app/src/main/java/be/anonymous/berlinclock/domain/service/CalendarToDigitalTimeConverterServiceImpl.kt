package be.anonymous.berlinclock.domain.service

import be.anonymous.berlinclock.domain.data.DigitalTime
import java.util.*
import javax.inject.Inject

class CalendarToDigitalTimeConverterServiceImpl @Inject constructor()
    : CalendarToDigitalTimeConverterService {

    override fun convert(calendar: Calendar): DigitalTime {
        return DigitalTime(
                hours = calendar.get(Calendar.HOUR_OF_DAY),
                minutes = calendar.get(Calendar.MINUTE),
                seconds = calendar.get(Calendar.SECOND)
        )
    }

}