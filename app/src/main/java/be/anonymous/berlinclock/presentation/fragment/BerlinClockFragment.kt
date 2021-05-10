package be.anonymous.berlinclock.presentation.fragment

import android.graphics.drawable.GradientDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.DrawableRes
import androidx.core.content.ContextCompat
import androidx.core.content.res.ResourcesCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import be.anonymous.berlinclock.R
import be.anonymous.berlinclock.presentation.viewmodel.BerlinClockViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class BerlinClockFragment : Fragment() {

    companion object {
        fun newInstance() = BerlinClockFragment()
    }

    private val viewModel: BerlinClockViewModel by viewModels()

    private var secondsView: View? = null
    private var fiveHourViews: List<View>? = null
    private var singleHourViews: List<View>? = null
    private var fiveMinutesViews: List<View>? = null
    private var singleMinutesViews: List<View>? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        return inflater.inflate(R.layout.fragment_berlin_clock, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Views
        secondsView = view.findViewById(R.id.view_seconds)
        fiveHourViews = listOf(
                view.findViewById(R.id.view_five_hours_1),
                view.findViewById(R.id.view_five_hours_2),
                view.findViewById(R.id.view_five_hours_3),
                view.findViewById(R.id.view_five_hours_4),
        )
        singleHourViews = listOf(
                view.findViewById(R.id.view_hours_1),
                view.findViewById(R.id.view_hours_2),
                view.findViewById(R.id.view_hours_3),
                view.findViewById(R.id.view_hours_4),
        )
        fiveMinutesViews = listOf(
                view.findViewById(R.id.view_five_minutes_1),
                view.findViewById(R.id.view_five_minutes_2),
                view.findViewById(R.id.view_five_minutes_3),
                view.findViewById(R.id.view_five_minutes_4),
                view.findViewById(R.id.view_five_minutes_5),
                view.findViewById(R.id.view_five_minutes_6),
                view.findViewById(R.id.view_five_minutes_7),
                view.findViewById(R.id.view_five_minutes_8),
                view.findViewById(R.id.view_five_minutes_9),
                view.findViewById(R.id.view_five_minutes_10),
                view.findViewById(R.id.view_five_minutes_11),
        )
        singleMinutesViews = listOf(
                view.findViewById(R.id.view_minutes_1),
                view.findViewById(R.id.view_minutes_2),
                view.findViewById(R.id.view_minutes_3),
                view.findViewById(R.id.view_minutes_4),
        )

        // ViewModel
        viewModel.secondsLight.observe(viewLifecycleOwner, { setViewColor(secondsView, it, LightShape.OVAL) })
        viewModel.fiveHoursLights.observe(viewLifecycleOwner, { setViewsColors(fiveHourViews, it) })
        viewModel.singleHoursLights.observe(viewLifecycleOwner, { setViewsColors(singleHourViews, it) })
        viewModel.fiveMinutesLights.observe(viewLifecycleOwner, { setViewsColors(fiveMinutesViews, it) })
        viewModel.singleMinutesLights.observe(viewLifecycleOwner, { setViewsColors(singleMinutesViews, it) })
    }

    private fun setViewsColors(views: List<View>?, colors: List<Int>?) {
        if (views == null || colors == null) return

        views.forEachIndexed { index, view ->
            setViewColor(view, colors[index])
        }
    }

    private fun setViewColor(view: View?, color: Int?, lightShape: LightShape = LightShape.RECTANGLE) {
        if (view == null || color == null) return
        val context = this.context ?: return

        val gradientDrawable = ResourcesCompat.getDrawable(resources, lightShape.drawableRes, null) as? GradientDrawable
        gradientDrawable?.setColor(ContextCompat.getColor(context, color))

        view.background = gradientDrawable
    }

    private enum class LightShape(@DrawableRes val drawableRes: Int) {
        RECTANGLE(R.drawable.clock_light_rectangle),
        OVAL(R.drawable.clock_light_oval)
    }

}