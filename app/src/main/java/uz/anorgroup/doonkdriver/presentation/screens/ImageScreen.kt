package uz.anorgroup.doonkdriver.presentation.screens

import android.net.Uri
import android.os.Bundle
import android.view.View
import androidx.core.net.toUri
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import uz.anorgroup.doonkdriver.R
import uz.anorgroup.doonkdriver.databinding.ScreenImageBinding
import uz.anorgroup.doonkdriver.presentation.adapters.ImageAdapter

@AndroidEntryPoint
class ImageScreen : Fragment(R.layout.screen_image) {
    private val listUri = ArrayList<Uri>()
    private val bind by viewBinding(ScreenImageBinding::bind)
    private val adapterPhoto = ImageAdapter()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments?.getString("array")?.let { refromater(it) }
        bind.listView.adapter = adapterPhoto
        bind.listView.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        adapterPhoto.submitList(listUri)
    }

    private fun refromater(str: String) {
        val array = str.split("###")
        for (i in array.indices) {
            listUri.add(array[0].toUri())
        }
    }
}