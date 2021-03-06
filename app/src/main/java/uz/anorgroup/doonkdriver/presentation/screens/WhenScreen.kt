package uz.anorgroup.doonkdriver.presentation.screens

import android.annotation.SuppressLint
import android.os.Bundle
import android.os.Parcelable
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.google.android.material.datepicker.CalendarConstraints
import com.google.android.material.datepicker.DateValidatorPointForward
import com.google.android.material.datepicker.MaterialDatePicker
import com.google.android.material.timepicker.MaterialTimePicker
import com.google.android.material.timepicker.TimeFormat
import dagger.hilt.android.AndroidEntryPoint
import uz.anorgroup.doonkdriver.R
import uz.anorgroup.doonkdriver.data.request.car.CreateOrderRequest
import uz.anorgroup.doonkdriver.data.request.car.Parcel
import uz.anorgroup.doonkdriver.data.request.car.Passanger
import uz.anorgroup.doonkdriver.databinding.ScreenWhenBinding
import uz.anorgroup.doonkdriver.utils.scope
import java.text.SimpleDateFormat
import java.util.*

@AndroidEntryPoint
class WhenScreen : Fragment(R.layout.screen_when) {
    private val bind by viewBinding(ScreenWhenBinding::bind)
    private var dateSelected = ""
    private val outputDateFormat = SimpleDateFormat("dd MMMM", Locale.getDefault())
    private val outputDateFormat2 = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
    private var calendarDate: String? = null
    private var date = ""
    private var hour = 0
    private var timeDate: String? = null
    private var minute = 0

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) = bind.scope {
        val bundle = requireArguments()
        val data = bundle.getParcelable<Parcelable>("data2") as CreateOrderRequest
        calendarOpen.setOnClickListener {
            showCalendarPicker()
        }
        timeOpen.setOnClickListener {
            showTimePicker()
        }
        nextBt.setOnClickListener {
            if (timeDate.toString().isNotEmpty() && calendarDate.toString().isNotEmpty()) {
                error1.visibility = View.GONE
                error2.visibility = View.GONE
                val bundleNew = Bundle()
                bundleNew.putParcelable(
                    "data2",
                    CreateOrderRequest(Parcel(), Passanger(data.passanger.car, data.passanger.address, "${date}T${timeDate}:29+05:00"))
                )
                findNavController().navigate(R.id.action_whenScreen_to_seatScreen, bundleNew)
            } else {
                error1.visibility = View.VISIBLE
                error2.visibility = View.VISIBLE
            }
        }
    }

    private fun showCalendarPicker() {
        val calendarConstraints = CalendarConstraints.Builder()
            .setValidator(DateValidatorPointForward.now())
            .build()

        val picker = MaterialDatePicker.Builder.datePicker()
            .setTitleText("Select Date")
            .setCalendarConstraints(calendarConstraints)
            .setSelection(MaterialDatePicker.todayInUtcMilliseconds())
            .build()
        picker.addOnPositiveButtonClickListener { datePicker ->
            outputDateFormat.format(datePicker).also { dateSelected = it }
            outputDateFormat2.format(datePicker).also { date = it }
            bind.whereTimeOut.text = dateSelected
        }
        picker.show(childFragmentManager, "Gita")
    }

    @SuppressLint("SetTextI18n")
    private fun showTimePicker() {

        val _hourCurrent = SimpleDateFormat("HH", Locale.getDefault())
        val hourCurrent = _hourCurrent.format(Date())

        val _minuteCurrent = SimpleDateFormat("mm", Locale.getDefault())
        val minuteCurrent = _minuteCurrent.format(Date())

        val picker = MaterialTimePicker.Builder()
            .setTimeFormat(TimeFormat.CLOCK_24H)
            .setHour(hourCurrent.toInt())
            .setMinute(minuteCurrent.toInt())
            .setTitleText("Select Alarm Time")
            .build()

        picker.show(childFragmentManager, "foxandroid")

        picker.addOnPositiveButtonClickListener {
            timeDate =
                String.format("%02d", picker.hour) + ":" + String.format("%02d", picker.minute)
            hour = picker.hour
            minute = picker.minute
            bind.whenTimeCome.text = timeDate
        }
    }
}