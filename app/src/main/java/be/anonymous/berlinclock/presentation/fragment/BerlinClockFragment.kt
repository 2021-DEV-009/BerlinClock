package be.anonymous.berlinclock.presentation.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import be.anonymous.berlinclock.R
import be.anonymous.berlinclock.presentation.viewmodel.BerlinClockViewModel

class BerlinClockFragment : Fragment() {

    companion object {
        fun newInstance() = BerlinClockFragment()
    }

    private val viewModel: BerlinClockViewModel by viewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        return inflater.inflate(R.layout.fragment_berlin_clock, container, false)
    }

}