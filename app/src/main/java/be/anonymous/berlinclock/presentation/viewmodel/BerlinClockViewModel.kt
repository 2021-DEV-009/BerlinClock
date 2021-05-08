package be.anonymous.berlinclock.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.toLiveData
import be.anonymous.berlinclock.application.usecase.GetCurrentTimeAsBerlinClockUseCase
import be.anonymous.berlinclock.presentation.mapper.BerlinClockCharacterToColorMapper
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.core.Flowable
import javax.inject.Inject

@HiltViewModel
class BerlinClockViewModel @Inject constructor(
        private val getCurrentTimeAsBerlinClockUseCase: GetCurrentTimeAsBerlinClockUseCase,
        private val berlinClockCharacterToColorMapper: BerlinClockCharacterToColorMapper
) : ViewModel() {

    // Observables

    private val berlinClockColorsList = getBerlinClockAsColorList()

    // LiveData

    val secondsLight: LiveData<Int?> = berlinClockColorsList.map { it.first() }
            .distinctUntilChanged()
            .onErrorReturn { -1 }
            .toLiveData()
    val fiveHoursLights: LiveData<List<Int>?> = getBerlinClockPartAsColorList(1, 5).toLiveData()
    val singleHoursLights: LiveData<List<Int>?> = getBerlinClockPartAsColorList(5, 9).toLiveData()
    val fiveMinutesLights: LiveData<List<Int>?> = getBerlinClockPartAsColorList(9, 20).toLiveData()
    val singleMinutesLights: LiveData<List<Int>?> = getBerlinClockPartAsColorList(20, 24).toLiveData()

    // Observable helpers

    private fun getBerlinClockAsColorList(): Flowable<List<Int>> {
        return getCurrentTimeAsBerlinClockUseCase.execute()
                .distinctUntilChanged()
                .map { berlinClockString ->
                    berlinClockString.toCharArray().map { char ->
                        berlinClockCharacterToColorMapper.map(char)
                    }
                }
                .replay(1)
                .refCount()
    }

    private fun getBerlinClockPartAsColorList(startIndex: Int, endIndex: Int): Flowable<List<Int>> {
        return berlinClockColorsList
                .map { it.subList(startIndex, endIndex) }
                .onErrorReturn { listOf() }
                .distinctUntilChanged()
    }

}