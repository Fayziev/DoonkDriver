package uz.anorgroup.doonkdriver.data.pref

import android.content.Context
import android.content.Context.MODE_PRIVATE
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MyPref @Inject constructor(private val context: Context) {

    private val pref = context.getSharedPreferences("cache", MODE_PRIVATE)

    var phoneNumber: String
        set(value) {
            pref.edit().putString("phone", value).apply()
        }
        get() = pref.getString("phone", "")!!
    var name: String
        set(value) {
            pref.edit().putString("name", value).apply()
        }
        get() = pref.getString("name", "")!!
    var surname: String
        set(value) {
            pref.edit().putString("surname", value).apply()
        }
        get() = pref.getString("surname", "")!!
    var accessToken: String
        set(value) {
            pref.edit().putString("token", value).apply()
        }
        get() = pref.getString("token", "")!!
    var startScreen: Boolean
        set(value) {
            pref.edit().putBoolean("startScreen", value).apply()
        }
        get() = pref.getBoolean("startScreen", false)
    var image: String
        set(value) {
            pref.edit().putString("image", value).apply()
        }
        get() = pref.getString("image", "")!!

}