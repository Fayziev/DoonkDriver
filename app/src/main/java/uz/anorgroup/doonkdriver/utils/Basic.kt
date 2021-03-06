package uz.anorgroup.doonkdriver.utils

import android.content.Context
import android.util.Log
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import timber.log.Timber
import java.io.File

//fun String.startScreen(): StartScreenEnum {
//    return if (this == StartScreenEnum.LOGIN.name) StartScreenEnum.LOGIN
//    else StartScreenEnum.MAIN
//}

fun timber(message: String, tag: String = "TTT") {
    Timber.tag(tag).d(message)
}

fun myLog(message: String, tag: String = "TTT") {
    Log.d(tag, message)
}

fun Fragment.showToast(message: String) {
    Toast.makeText(this.requireContext(), message, Toast.LENGTH_SHORT).show()
}
fun <T : ViewBinding> T.scope(block : T.() ->Unit) {
    block(this)
}

fun File.toRequestData(): MultipartBody.Part {
    val requestFile = this.asRequestBody("image/jpeg".toMediaTypeOrNull())
    return MultipartBody.Part.createFormData("avatar", name, requestFile)
}

fun View.hideKeyboard(): Boolean {
    try {
        val inputMethodManager = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        return inputMethodManager.hideSoftInputFromWindow(windowToken, 0)
    } catch (ignored: RuntimeException) { }
    return false
}

// file
fun File.toFormData(partName: String = "file"): MultipartBody.Part {
    val request = asRequestBody("multipart/form-data".toMediaTypeOrNull())
    return MultipartBody.Part.createFormData(partName, name, request)
}

/*
0-Register
1-Enter
2-Main
 */