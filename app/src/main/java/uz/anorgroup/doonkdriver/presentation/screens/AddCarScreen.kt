package uz.anorgroup.doonkdriver.presentation.screens

import android.app.Activity
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import android.os.Parcelable
import android.view.View
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.github.dhaval2404.imagepicker.ImagePicker
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import okhttp3.ResponseBody
import uz.anorgroup.doonkdriver.R
import uz.anorgroup.doonkdriver.data.request.car.CreateCarRequest
import uz.anorgroup.doonkdriver.data.request.car.Photo
import uz.anorgroup.doonkdriver.databinding.ScreenCarAddBinding
import uz.anorgroup.doonkdriver.presentation.dialogs.BrandsBottomDialog
import uz.anorgroup.doonkdriver.presentation.dialogs.ModelBottomDialog
import uz.anorgroup.doonkdriver.presentation.viewmodel.car.CarCreateViewModel
import uz.anorgroup.doonkdriver.presentation.viewmodel.impl.car.CarCreateViewModelImpl
import uz.anorgroup.doonkdriver.utils.FileUtils.getPath
import uz.anorgroup.doonkdriver.utils.scope
import uz.anorgroup.doonkdriver.utils.showToast
import uz.anorgroup.doonkdriver.utils.timber
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.io.InputStream

@AndroidEntryPoint
class AddCarScreen : Fragment(R.layout.screen_car_add) {
    private val bind by viewBinding(ScreenCarAddBinding::bind)
    private var file: File? = null
    private var photosList = ArrayList<Photo>()
    private val viewModel: CarCreateViewModel by viewModels<CarCreateViewModelImpl>()
    private val startForProfileImageResult = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result: ActivityResult ->
        val resultCode = result.resultCode
        val data = result.data
        when (resultCode) {
            Activity.RESULT_OK -> {
                val fileUri = data?.data!!
                bind.carIcon.visibility=View.GONE
                bind.carCircle.setImageURI(fileUri)
                file = File(getPath(requireContext(), fileUri))
                val arrayString = file.toString().split('/')
                photosList.add(Photo(arrayString[9]))
                timber(arrayString[9])
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) = bind.scope {
        val bundle = requireArguments()
        val data = bundle.getParcelable<Parcelable>("data") as CreateCarRequest
        var brand = -1
        var model = -1

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
            if (brand != -1 && model != -1 && yearOfIssue.text!!.isNotEmpty()
                && color.text!!.isNotEmpty() && licensePlate.text!!.isNotEmpty()
            ) {
                val newData = CreateCarRequest(
                    data.carSeet, data.typeOfBody,
                    data.typeOfTransport, data.liftingCapacity, data.weight, brand, model,
                    color.text.toString(), yearOfIssue.text.toString(), photosList
                )
                viewModel.carCreate(newData)
                viewModel.successFlow.onEach {
                    showToast("Success")
                    findNavController().navigate(R.id.tripDetalisScreen)
                }.launchIn(lifecycleScope)
                viewModel.errorFlow.onEach {
                    showToast("Error")
                }.launchIn(lifecycleScope)
            } else {
                showToast("Fill in the blanks")
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

}

