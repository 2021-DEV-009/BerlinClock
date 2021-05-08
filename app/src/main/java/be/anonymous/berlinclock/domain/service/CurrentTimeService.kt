package be.anonymous.berlinclock.domain.service

import java.util.*

interface CurrentTimeService {

    fun getCurrentTime(): Calendar

}