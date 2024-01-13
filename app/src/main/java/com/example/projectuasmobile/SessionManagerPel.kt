package com.example.projectuasmobile

import android.content.Context
import android.content.SharedPreferences

class SessionManagerPel (context: Context) {

    private val sharedPreferences: SharedPreferences =
        context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
    private val editor: SharedPreferences.Editor = sharedPreferences.edit()

    companion object {
        private const val PREF_NAME = "UserSession"
        private const val KEY_USER_ID_PEL = "userIdPel"
        private const val KEY_USER_EMAIL_PEL = "emailPel"
        private const val KEY_USER_USERNAME_PEL = "usernamePel"
    }

    fun saveUserSessionPel(userIdPel: String, userEmailPel: String, usernamePel: String) {
        editor.putString(KEY_USER_ID_PEL, userIdPel)
        editor.putString(KEY_USER_EMAIL_PEL, userEmailPel)
        editor.putString(KEY_USER_USERNAME_PEL, usernamePel)

        editor.apply()
    }

    fun isLoggedInPel(): Boolean {
        return sharedPreferences.contains(KEY_USER_ID_PEL)
    }


    fun getUserIdPel(): String? {
        return sharedPreferences.getString(KEY_USER_ID_PEL, null)
    }

    fun getUserEmailPel(): String? {
        return sharedPreferences.getString(KEY_USER_EMAIL_PEL, null)
    }

    fun getUserNamePel(): String? {
        return sharedPreferences.getString(KEY_USER_USERNAME_PEL, null)
    }


    fun clearSessionPel() {
        editor.clear()
        editor.apply()
    }

}