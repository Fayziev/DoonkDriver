package uz.anorgroup.doonkdriver.presentation.screens

import android.app.Activity
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Build
import android.os.Bundle
import android.view.View
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.github.dhaval2404.imagepicker.ImagePicker
import com.google.android.material.datepicker.CalendarConstraints
import com.google.android.material.datepicker.DateValidatorPointBackward
import com.google.android.material.datepicker.MaterialDatePicker
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import okhttp3.ResponseBody
import uz.anorgroup.doonkdriver.R
import uz.anorgroup.doonkdriver.data.request.car.CreateCarRequest2
import uz.anorgroup.doonkdriver.data.responce.car.Photo
import uz.anorgroup.doonkdriver.databinding.ScreenCarAddBinding
import uz.anorgroup.doonkdriver.presentation.dialogs.BrandsBottomDialog
import uz.anorgroup.doonkdriver.presentation.dialogs.ModelBottomDialog
import uz.anorgroup.doonkdriver.presentation.viewmodel.car.CarCreateViewModel
import uz.anorgroup.doonkdriver.presentation.viewmodel.car.ImageUploadViewModel
import uz.anorgroup.doonkdriver.presentation.viewmodel.impl.car.CarCreateViewModelImpl
import uz.anorgroup.doonkdriver.presentation.viewmodel.impl.car.ImageUploadViewModelImpl
import uz.anorgroup.doonkdriver.utils.FileUtils.getPath
import uz.anorgroup.doonkdriver.utils.scope
import uz.anorgroup.doonkdriver.utils.showToast
import uz.anorgroup.doonkdriver.utils.timber
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.io.InputStream
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

@AndroidEntryPoint
class AddCarScreen : Fragment(R.layout.screen_car_add) {
    private val bind by viewBinding(ScreenCarAddBinding::bind)
    private var file: File? = null
    private var photosList = ArrayList<Photo>()
    private val viewModel: CarCreateViewModel by viewModels<CarCreateViewModelImpl>()
    private var dateSelected = ""
    private val viewModelImage: ImageUploadViewModel by viewModels<ImageUploadViewModelImpl>()
    private val outputDateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())

    private val startForProfileImageResult = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result: ActivityResult ->
        val resultCode = result.resultCode
        val data = result.data
        when (resultCode) {
            Activity.RESULT_OK -> {
                val fileUri = data?.data!!
                bind.carIcon.visibility = View.GONE
                bind.carCircle.setImageURI(fileUri)
                file = File(getPath(requireContext(), fileUri))
                viewModelImage.imageUpload(file!!)
                viewModelImage.successFlow.onEach {
                    photosList.add(it.data)
                }.launchIn(lifecycleScope)
                viewModelImage.errorFlow.onEach {
                    showToast("Error")
                }.launchIn(lifecycleScope)
                viewModelImage.progressFlow.onEach {
                    if (it) bind.progress.show()
                    else bind.progress.hide()
                }.launchIn(lifecycleScope)
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.openTruckFlow.onEach {
            findNavController().navigate(R.id.action_addCarScreen_to_truckAddScreen)
        }.launchIn(lifecycleScope)
    }

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) = bind.scope {
//        val bundle = requireArguments()
//        val pos = bundle.getBoolean("pos")
//        val data = bundle.getParcelable<Parcelable>("data") as CreateCarRequest
        var brand = -1
        var model = -1

        backBtn.setOnClickListener {
            findNavController().popBackStack()
        }

        bind.loadCarLine.setOnClickListener {
            ImagePicker.with(requireActivity())
                .compress(1024)
                .crop()
                .maxResultSize(512, 512)
                .saveDir(File(requireContext().getExternalFilesDir(null)?.absolutePath, "MyImage"))
                .createIntent {
                    startForProfileImageResult.launch(it)
                }
        }

        godVipuska.setOnClickListener {
            showCalendarPicker()
        }
        tipTransportaLine.setOnClickListener {
            val dialog = BrandsBottomDialog()
            dialog.setListener {
                textMarka.text = it.name
                brand = it.id
                dialog.dismiss()
            }
            dialog.show(childFragmentManager, "brands")
        }

        tipKuzovaLine.setOnClickListener {
            val dialog = ModelBottomDialog()
            dialog.setListener {
                textModel.text = it.name
                model = it.id
                dialog.dismiss()
            }
            dialog.show(childFragmentManager, "models")
        }

        saveBtn.setOnClickListener {
            if (photosList.size == 0) {
                if (brand != -1 && model != -1 && godVipuska.text!!.isNotEmpty()
                    && color.text!!.isNotEmpty() && licensePlate.text!!.isNotEmpty()
                ) {
                    val newData = CreateCarRequest2(
                        brand, model,
                        color.text.toString(), "${dateSelected}T00:00:00Z", photosList
                    )
                    val bundle = Bundle()
                    bundle.putParcelable("data", newData)
                    findNavController().navigate(R.id.action_addCarScreen_to_truckAddScreen, bundle)
                } else {
                    errorText.text = "Fill in the blanks"
                    errorText.setTextColor(ContextCompat.getColor(requireContext(), R.color.red_500))
                }
            } else {
                errorText.text = "You must set the image"
                errorText.setTextColor(ContextCompat.getColor(requireContext(), R.color.red_500))
            }
        }
    }

    private fun downloadImage(body: ResponseBody): Boolean {
        return try {
            var hm: InputStream? = null
            var out: FileOutputStream? = null
            try {
                hm = body.byteStream()
                out = FileOutputStream(requireActivity().getExternalFilesDir(null).toString() + File.separator + "Android.jpg")
                var c: Int
                while (hm.read().also { c = it } != -1) run {
                    out.write(c)
                }
            } catch (e: IOException) {
                return false
            } finally {
                hm?.close()
                out?.close()
            }
            val width: Int
            val height: Int
            val bMap = BitmapFactory.decodeFile(requireActivity().getExternalFilesDir(null).toString() + File.separator + "Android.jpg")
            width = 2 * bMap.width
            height = 3 * bMap.height
            val bMap2 = Bitmap.createScaledBitmap(bMap, width, height, false)
            bind.carCircle.setImageBitmap(bMap2)
            true
        } catch (e: IOException) {
            false
        }
    }

    private fun showCalendarPicker() {
        val calendarConstraints = CalendarConstraints.Builder()
            .setValidator(DateValidatorPointBackward.now())
            .build()

        val picker = MaterialDatePicker.Builder.datePicker()
            .setTitleText("Select Date ")
            .setCalendarConstraints(calendarConstraints)
            .setSelection(MaterialDatePicker.todayInUtcMilliseconds())
            .build()
        picker.addOnPositiveButtonClickListener { date ->
            timber(date.toString())
            outputDateFormat.format(date).also { dateSelected = it }
            bind.godVipuska.setText(dateSelected)
        }
        picker.show(requireFragmentManager(), "Gita")
    }
}

