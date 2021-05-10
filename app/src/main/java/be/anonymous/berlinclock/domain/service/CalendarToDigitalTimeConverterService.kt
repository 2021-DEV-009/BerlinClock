package be.anonymous.berlinclock.domain.service

import be.anonymous.berlinclock.domain.data.DigitalTime
import java.util.*

interface CalendarToDigitalTimeConverterService {

    fun convert(calendar: Calendar): DigitalTime

}