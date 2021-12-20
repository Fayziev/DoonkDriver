package uz.anorgroup.doonkdriver.presentation.screens

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.google.android.material.timepicker.MaterialTimePicker
import com.google.android.material.timepicker.TimeFormat
import dagger.hilt.android.AndroidEntryPoint
import uz.anorgroup.doonkdriver.R
import uz.anorgroup.doonkdriver.databinding.ScreenWhenBinding
import java.util.*

@AndroidEntryPoint
class WhenScreen : Fragment(R.layout.screen_when) {
    private val bind by viewBinding(ScreenWhenBinding::bind)


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bind.clock.setOnClickListener {
            val timePick = MaterialTimePicker.Builder().setTimeFormat(TimeFormat.CLOCK_12H)
                .setTimeFormat(TimeFormat.CLOCK_24H)
                .setMinute(60).setTitleText("Choose Time").build()
            timePick.show(childFragmentManager, "To do")


            timePick.addOnPositiveButtonClickListener {
//                if (timePick.minute < 10) {
//                    binding.timeTitle.text = "${timePick.hour}:0${timePick.minute}"
//                } else {
//
//                    binding.timeTitle.text = "${timePick.hour}:${timePick.minute}"
//                }
            }
        }
        bind.calendar.setOnClickListener {
            val calendar = Calendar.getInstance()
            var time = ""
            val YEAR = calendar.get(Calendar.YEAR)
            val MONTH = calendar.get(Calendar.MONTH)
            val DATE = calendar.get(Calendar.DAY_OF_MONTH)
            val dialogPicker = android.app.DatePickerDialog(
                requireContext(),
                { a, year, month, date ->
                    time = "$date/${month + 1}/${year}"
                }, YEAR, MONTH, DATE
            )
            dialogPicker.show()
        }

        bind.nextBt.setOnClickListener {
            findNavController().navigate(R.id.action_whenScreen_to_vehicleScreen)
        }

    }
}