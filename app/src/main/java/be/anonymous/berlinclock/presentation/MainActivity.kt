package be.anonymous.berlinclock.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import be.anonymous.berlinclock.R
import be.anonymous.berlinclock.presentation.fragment.BerlinClockFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                    .replace(R.id.container, BerlinClockFragment.newInstance())
                    .commitNow()
        }
    }

}