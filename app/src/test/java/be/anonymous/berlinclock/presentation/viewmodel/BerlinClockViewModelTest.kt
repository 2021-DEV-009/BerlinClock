package be.anonymous.berlinclock.presentation.viewmodel

import android.os.Build
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import be.anonymous.berlinclock.application.usecase.GetCurrentTimeAsBerlinClockUseCase
import be.anonymous.berlinclock.presentation.mapper.BerlinClockCharacterToColorMapper
import io.reactivex.rxjava3.core.BackpressureStrategy
import io.reactivex.rxjava3.core.Flowable
import io.reactivex.rxjava3.core.FlowableEmitter
import org.junit.Assert
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.ArgumentCaptor
import org.mockito.Mockito.*
import org.mockito.kotlin.anyOrNull
import org.mockito.kotlin.mock
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

@RunWith(RobolectricTestRunner::class)
@Config(sdk = [Build.VERSION_CODES.P])
class BerlinClockViewModelTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private val getCurrentTimeAsBerlinClockUseCase = mock<GetCurrentTimeAsBerlinClockUseCase>()
    private val berlinClockCharacterToColorMapper = mock<BerlinClockCharacterToColorMapper>()

    private val viewModel by lazy {
        BerlinClockViewModel(
                getCurrentTimeAsBerlinClockUseCase = getCurrentTimeAsBerlinClockUseCase,
                berlinClockCharacterToColorMapper = berlinClockCharacterToColorMapper
        )
    }

    @Test
    fun secondsLight_withoutCurrentBerlinClock_returnsNull() {
        // Arrange
        `when`(getCurrentTimeAsBerlinClockUseCase.execute()).thenReturn(Flowable.empty())

        // Act
        viewModel.secondsLight.observeForever { }

        // Assert
        Assert.assertEquals(null, viewModel.secondsLight.value)
    }

    @Test
    fun secondsLight_whenClockUpdates_secondsUpdate() {
        // Arrange
        mockThreeUpdates()
        val observer = mock<Observer<Int?>>()

        // Act
        viewModel.secondsLight.observeForever(observer)

        // Assert
        val argumentCaptor = ArgumentCaptor.forClass(Int::class.java)
        verify(observer, times(3)).onChanged(argumentCaptor.capture())
        Assert.assertEquals(listOf(0, 1, 2), argumentCaptor.allValues)
    }

    @Test
    fun secondsLight_withInvalidLength_returnsNegativeNumber() {
        // Arrange
        `when`(getCurrentTimeAsBerlinClockUseCase.execute()).thenReturn(Flowable.just(""))

        // Act
        viewModel.secondsLight.observeForever { }

        // Assert
        Assert.assertEquals(-1, viewModel.secondsLight.value)
    }

    @Test
    fun fiveHoursLights_withoutCurrentBerlinClock_returnsNull() {
        // Arrange
        `when`(getCurrentTimeAsBerlinClockUseCase.execute()).thenReturn(Flowable.empty())

        // Act
        viewModel.fiveHoursLights.observeForever { }

        // Assert
        Assert.assertEquals(null, viewModel.fiveHoursLights.value)
    }

    @Test
    fun fiveHoursLights_whenClockUpdates_fiveHoursUpdate() {
        // Arrange
        mockThreeUpdates()
        val observer = mock<Observer<List<Int>?>>()

        // Act
        viewModel.fiveHoursLights.observeForever(observer)

        // Assert
        val argumentCaptor = ArgumentCaptor.forClass(List::class.java as Class<List<Int>>)
        verify(observer, times(3)).onChanged(argumentCaptor.capture())
        Assert.assertEquals(listOf(
                (1..4).toList(),
                (2..5).toList(),
                (3..6).toList(),
        ), argumentCaptor.allValues)
    }

    @Test
    fun fiveHoursLights_withInvalidLength_returnsEmptyList() {
        // Arrange
        `when`(getCurrentTimeAsBerlinClockUseCase.execute()).thenReturn(Flowable.just(""))

        // Act
        viewModel.fiveHoursLights.observeForever { }

        // Assert
        Assert.assertEquals(0, viewModel.fiveHoursLights.value?.size)
    }

    @Test
    fun singleHoursLights_withoutCurrentBerlinClock_returnsNull() {
        // Arrange
        `when`(getCurrentTimeAsBerlinClockUseCase.execute()).thenReturn(Flowable.empty())

        // Act
        viewModel.singleHoursLights.observeForever { }

        // Assert
        Assert.assertEquals(null, viewModel.singleHoursLights.value)
    }

    @Test
    fun singleHoursLights_whenClockUpdates_singleHoursUpdate() {
        // Arrange
        mockThreeUpdates()
        val observer = mock<Observer<List<Int>?>>()

        // Act
        viewModel.singleHoursLights.observeForever(observer)

        // Assert
        val argumentCaptor = ArgumentCaptor.forClass(List::class.java as Class<List<Int>>)
        verify(observer, times(3)).onChanged(argumentCaptor.capture())
        Assert.assertEquals(listOf(
                (5..8).toList(),
                (6..9).toList(),
                (7..10).toList(),
        ), argumentCaptor.allValues)
    }

    @Test
    fun singleHoursLights_withInvalidLength_returnsEmptyList() {
        // Arrange
        `when`(getCurrentTimeAsBerlinClockUseCase.execute()).thenReturn(Flowable.just(""))

        // Act
        viewModel.singleHoursLights.observeForever { }

        // Assert
        Assert.assertEquals(0, viewModel.singleHoursLights.value?.size)
    }

    @Test
    fun fiveMinutesLights_withoutCurrentBerlinClock_returnsNull() {
        // Arrange
        `when`(getCurrentTimeAsBerlinClockUseCase.execute()).thenReturn(Flowable.empty())

        // Act
        viewModel.fiveMinutesLights.observeForever { }

        // Assert
        Assert.assertEquals(null, viewModel.fiveMinutesLights.value)
    }

    @Test
    fun fiveMinutesLights_whenClockUpdates_singleHoursUpdate() {
        // Arrange
        mockThreeUpdates()
        val observer = mock<Observer<List<Int>?>>()

        // Act
        viewModel.fiveMinutesLights.observeForever(observer)

        // Assert
        val argumentCaptor = ArgumentCaptor.forClass(List::class.java as Class<List<Int>>)
        verify(observer, times(3)).onChanged(argumentCaptor.capture())
        Assert.assertEquals(listOf(
                (9..19).toList(),
                (10..20).toList(),
                (11..21).toList(),
        ), argumentCaptor.allValues)
    }

    @Test
    fun fiveMinutesLights_withInvalidLength_returnsEmptyList() {
        // Arrange
        `when`(getCurrentTimeAsBerlinClockUseCase.execute()).thenReturn(Flowable.just(""))

        // Act
        viewModel.fiveMinutesLights.observeForever { }

        // Assert
        Assert.assertEquals(0, viewModel.fiveMinutesLights.value?.size)
    }

    @Test
    fun singleMinutesLights_withoutCurrentBerlinClock_returnsNull() {
        // Arrange
        `when`(getCurrentTimeAsBerlinClockUseCase.execute()).thenReturn(Flowable.empty())

        // Act
        viewModel.singleMinutesLights.observeForever { }

        // Assert
        Assert.assertEquals(null, viewModel.singleMinutesLights.value)
    }

    @Test
    fun singleMinutesLights_whenClockUpdates_singleHoursUpdate() {
        // Arrange
        mockThreeUpdates()
        val observer = mock<Observer<List<Int>?>>()

        // Act
        viewModel.singleMinutesLights.observeForever(observer)

        // Assert
        val argumentCaptor = ArgumentCaptor.forClass(List::class.java as Class<List<Int>>)
        verify(observer, times(3)).onChanged(argumentCaptor.capture())
        Assert.assertEquals(listOf(
                (20..23).toList(),
                (21..24).toList(),
                (22..25).toList(),
        ), argumentCaptor.allValues)
    }

    @Test
    fun singleMinutesLights_withInvalidLength_returnsEmptyList() {
        // Arrange
        `when`(getCurrentTimeAsBerlinClockUseCase.execute()).thenReturn(Flowable.just(""))

        // Act
        viewModel.singleMinutesLights.observeForever { }

        // Assert
        Assert.assertEquals(0, viewModel.singleMinutesLights.value?.size)
    }

    @Test
    fun allLiveDataSubscribed_mapperCalledOnceForEachCharacter() {
        var emitter: FlowableEmitter<String>? = null
        `when`(getCurrentTimeAsBerlinClockUseCase.execute()).thenReturn(Flowable.create({
            emitter = it
        }, BackpressureStrategy.LATEST))

        // Act
        viewModel.secondsLight.observeForever {}
        viewModel.fiveHoursLights.observeForever {}
        emitter?.onNext("A".repeat(24))
        emitter?.onNext("B".repeat(24))
        emitter?.onNext("C".repeat(24))

        // Assert
        verify(getCurrentTimeAsBerlinClockUseCase).execute()
        verify(berlinClockCharacterToColorMapper, times(24 * 3)).map(anyOrNull())
    }

    private fun mockThreeUpdates() {
        val alphabet = ('A'..'Z').toList()

        // A valid berlin clock exists out of 24 characters.
        // Use the characters of the alphabet, so it can be validated.
        `when`(getCurrentTimeAsBerlinClockUseCase.execute()).thenReturn(Flowable.fromArray(
                alphabet.subList(0, 24).joinToString(""),
                alphabet.subList(1, 25).joinToString(""),
                alphabet.subList(2, 26).joinToString("")
        ))

        // Mock the map function, which just returns the index of the character in the alphabet.
        `when`(berlinClockCharacterToColorMapper.map(anyOrNull())).thenAnswer {
            val inputChar = it.arguments.first() as Char
            return@thenAnswer alphabet.indexOf(inputChar)
        }
    }

}