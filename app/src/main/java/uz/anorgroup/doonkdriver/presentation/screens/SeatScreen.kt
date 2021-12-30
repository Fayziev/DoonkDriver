package uz.anorgroup.doonkdriver.presentation.screens

import android.annotation.SuppressLint
import android.os.Bundle
import android.os.Parcelable
import android.view.View
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import uz.anorgroup.doonkdriver.R
import uz.anorgroup.doonkdriver.data.others.MyStatic
import uz.anorgroup.doonkdriver.data.request.car.OrderCreateRequest
import uz.anorgroup.doonkdriver.databinding.ScreenSeatBinding
import uz.anorgroup.doonkdriver.utils.scope

@AndroidEntryPoint
class SeatScreen : Fragment(R.layout.screen_seat) {
    private val bind by viewBinding(ScreenSeatBinding::bind)

    @SuppressLint("ResourceAsColor")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) = bind.scope {
        val bundle = requireArguments()
        val data = bundle.getParcelable<Parcelable>("data2") as OrderCreateRequest
        yes.setOnClickListener {
            yes.setBackgroundResource(R.drawable.little_circle_bt_bg)
            yes.setTextColor(ContextCompat.getColor(requireContext(), R.color.white))
            no.setBackgroundResource(R.drawable.little_white_circle_bt_bg)
            no.setTextColor(ContextCompat.getColor(requireContext(), R.color.black_transparent))
            circle3.isEnabled = true
            circle4.isEnabled = true
            MyStatic.client = true
        }
        no.setOnClickListener {
            no.setBackgroundResource(R.drawable.little_circle_bt_bg)
            no.setTextColor(ContextCompat.getColor(requireContext(), R.color.white))
            yes.setBackgroundResource(R.drawable.little_white_circle_bt_bg)
            yes.setTextColor(ContextCompat.getColor(requireContext(), R.color.black_transparent))
            circle3.isEnabled = false
            circle4.isEnabled = false
            MyStatic.client = false
        }

        circle1.setOnClickListener {
            circle1.setBackgroundResource(R.drawable.drawable_circle_green)
            circle1.setTextColor(ContextCompat.getColor(requireContext(), R.color.white))
            circle2.setBackgroundResource(R.drawable.drawable_circle_white)
            circle2.setTextColor(ContextCompat.getColor(requireContext(), R.color.black_transparent))
            circle3.setBackgroundResource(R.drawable.drawable_circle_white)
            circle3.setTextColor(ContextCompat.getColor(requireContext(), R.color.black_transparent))
            circle4.setBackgroundResource(R.drawable.drawable_circle_white)
            circle4.setTextColor(ContextCompat.getColor(requireContext(), R.color.black_transparent))
            MyStatic.client_count = 1
        }
        circle2.setOnClickListener {
            circle2.setBackgroundResource(R.drawable.drawable_circle_green)
            circle2.setTextColor(ContextCompat.getColor(requireContext(), R.color.white))
            circle1.setBackgroundResource(R.drawable.drawable_circle_white)
            circle1.setTextColor(ContextCompat.getColor(requireContext(), R.color.black_transparent))
            circle3.setBackgroundResource(R.drawable.drawable_circle_white)
            circle3.setTextColor(ContextCompat.getColor(requireContext(), R.color.black_transparent))
            circle4.setBackgroundResource(R.drawable.drawable_circle_white)
            circle4.setTextColor(ContextCompat.getColor(requireContext(), R.color.black_transparent))
            MyStatic.client_count = 1
        }
        circle3.setOnClickListener {
            circle3.setBackgroundResource(R.drawable.drawable_circle_green)
            circle3.setTextColor(ContextCompat.getColor(requireContext(), R.color.white))
            circle1.setBackgroundResource(R.drawable.drawable_circle_white)
            circle1.setTextColor(ContextCompat.getColor(requireContext(), R.color.black_transparent))
            circle2.setBackgroundResource(R.drawable.drawable_circle_white)
            circle2.setTextColor(ContextCompat.getColor(requireContext(), R.color.black_transparent))
            circle4.setBackgroundResource(R.drawable.drawable_circle_white)
            circle4.setTextColor(ContextCompat.getColor(requireContext(), R.color.black_transparent))
            if (MyStatic.client) {
                MyStatic.client_count = 3
            }
        }
        circle4.setOnClickListener {
            circle4.setBackgroundResource(R.drawable.drawable_circle_green)
            circle4.setTextColor(ContextCompat.getColor(requireContext(), R.color.white))
            circle1.setBackgroundResource(R.drawable.drawable_circle_white)
            circle1.setTextColor(ContextCompat.getColor(requireContext(), R.color.black_transparent))
            circle2.setBackgroundResource(R.drawable.drawable_circle_white)
            circle2.setTextColor(ContextCompat.getColor(requireContext(), R.color.black_transparent))
            circle3.setBackgroundResource(R.drawable.drawable_circle_white)
            circle3.setTextColor(ContextCompat.getColor(requireContext(), R.color.black_transparent))
            if (MyStatic.client) {
                MyStatic.client_count = 4
            }
        }

        nextBt.setOnClickListener {
            val bundleNew = Bundle()
            bundleNew.putParcelable("data2", OrderCreateRequest(data.car,data.address, data.dateOfDeparture, MyStatic.client_count))
            findNavController().navigate(R.id.action_seatScreen_to_tripInfoScreen,bundleNew)
        }
    }

}