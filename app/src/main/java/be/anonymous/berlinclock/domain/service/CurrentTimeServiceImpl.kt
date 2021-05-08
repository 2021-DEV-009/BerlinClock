package be.anonymous.berlinclock.domain.service

import java.util.*
import javax.inject.Inject

class CurrentTimeServiceImpl @Inject constructor()
    : CurrentTimeService {

    override fun getCurrentTime(): Calendar {
        return Calendar.getInstance()
    }

}