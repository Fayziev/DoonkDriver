package uz.anorgroup.doonkdriver.presentation.screens

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import uz.anorgroup.doonkdriver.R
import uz.anorgroup.doonkdriver.data.others.LocationAddData
import uz.anorgroup.doonkdriver.data.request.car.CarSeet
import uz.anorgroup.doonkdriver.data.request.car.CreateCarRequest2
import uz.anorgroup.doonkdriver.databinding.ScreenIntermediateBinding
import uz.anorgroup.doonkdriver.presentation.adapters.AddAdapter
import uz.anorgroup.doonkdriver.presentation.dialogs.CitysBottomDialog
import uz.anorgroup.doonkdriver.presentation.dialogs.StreetsBottomDialog
import uz.anorgroup.doonkdriver.presentation.viewmodel.car.CarCreateViewModel
import uz.anorgroup.doonkdriver.presentation.viewmodel.impl.car.CarCreateViewModelImpl
import uz.anorgroup.doonkdriver.utils.scope
import uz.anorgroup.doonkdriver.utils.showToast

@AndroidEntryPoint
class IntermediateScreen : Fragment(R.layout.screen_intermediate) {
    private val bind by viewBinding(ScreenIntermediateBinding::bind)
    private val adapter = AddAdapter()
    private val viewModel: CarCreateViewModel by viewModels<CarCreateViewModelImpl>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel.openTruckFlow.onEach {
            findNavController().navigate(R.id.action_screenIntermediate_to_whenScreen)
        }.launchIn(lifecycleScope)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) = bind.scope {
        val bundle2 = requireArguments()
        val data: CreateCarRequest2 = bundle2.getParcelable("data")!!
        listView.adapter = adapter
        listView.layoutManager = LinearLayoutManager(requireContext())
        backBtn.setOnClickListener {
            findNavController().popBackStack()
        }

        val bundle = Bundle()
        var id = "0"
        var qty = -1
        var position = -1
        val listLocation = ArrayList<CarSeet>()
        data.carSeet?.let { listLocation.addAll(it) }
        var bool = true

        adapter.setCityListener { pos ->
            val dialog = CitysBottomDialog()
            dialog.setListener {
                id = it.id.toString()
                bundle.putString("id", id)
                position = it.id
                dialog.dismiss()
                val mutableList = adapter.currentList.toMutableList()
                mutableList[pos] = mutableList[pos].copy(city = it.name)
                adapter.submitList(mutableList)
            }
            dialog.show(childFragmentManager, "CityDialog")
        }
        adapter.setStreetListener { pos ->
            val dialog = StreetsBottomDialog()
            dialog.arguments = bundle
            dialog.setListener {
                qty = it.id
                dialog.dismiss()
                val mutableList = adapter.currentList.toMutableList()
                mutableList[pos] = mutableList[pos].copy(street = it.name)
                adapter.submitList(mutableList)
            }
            dialog.show(childFragmentManager, "StreetDialog")
        }

        addCarBt.setOnClickListener {
            val mutableList = adapter.currentList.toMutableList()
            mutableList.add(LocationAddData(adapter.currentList.size, "", ""))
            adapter.submitList(mutableList)
            if (bool) {
                nextBt.text = requireContext().getString(R.string.dalee)
                bool = false
            }
        }
        nextBt.setOnClickListener {
            if (!bool) {
                if (position != -1 && qty != -1) {
                    listLocation.add(CarSeet(position, qty))
                    val dataNew =
                        CreateCarRequest2(
                            data.brand,
                            data.carModel,
                            data.color,
                            data.yearOfIssue,
                            data.photos,
                            data.typeOfBody,
                            data.typeOfTransport,
                            data.liftingCapacity,
                            data.weight,
                            listLocation
                        )
                    viewModel.carCreate(dataNew)
                    viewModel.progressFlow.onEach {
                        if (it) progress.show()
                        else progress.hide()
                    }.launchIn(lifecycleScope)
                    viewModel.successFlow.onEach {
                        showToast("Success")
                    }.launchIn(lifecycleScope)
                    viewModel.errorFlow.onEach {
                        showToast("Error")
                    }.launchIn(lifecycleScope)
                }
            } else {
                val dataNew =
                    CreateCarRequest2(
                        data.brand,
                        data.carModel,
                        data.color,
                        data.yearOfIssue,
                        data.photos,
                        data.typeOfBody,
                        data.typeOfTransport,
                        data.liftingCapacity,
                        data.weight,
                        data.carSeet
                    )
                viewModel.carCreate(dataNew)
                viewModel.progressFlow.onEach {
                    if (it) progress.show()
                    else progress.hide()
                }.launchIn(lifecycleScope)
                viewModel.successFlow.onEach {
                    showToast("Success")
                }.launchIn(lifecycleScope)
                viewModel.errorFlow.onEach {
                    showToast("Error")
                }.launchIn(lifecycleScope)
            }
        }
    }
}