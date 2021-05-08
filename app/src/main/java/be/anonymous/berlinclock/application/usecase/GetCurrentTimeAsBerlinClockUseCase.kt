package be.anonymous.berlinclock.application.usecase

import io.reactivex.rxjava3.core.Flowable

/**
 * Returns the current time as a berlin clock string every second.
 */
interface GetCurrentTimeAsBerlinClockUseCase {

    fun execute(): Flowable<String>

}