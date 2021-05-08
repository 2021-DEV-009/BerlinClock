package be.anonymous.berlinclock.domain.service

import java.util.*

class CurrentTimeServiceImpl : CurrentTimeService {

    override fun getCurrentTime(): Calendar {
        return Calendar.getInstance()
    }

}