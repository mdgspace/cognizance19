package com.mdgiitr.karthik.cognizance19.utils;

import android.content.Context;
import android.content.SharedPreferences;

public class PreferenceHelper {

    private SharedPreferences prefs;

    public PreferenceHelper(Context context) {
        prefs = context.getSharedPreferences("prefs", Context.MODE_PRIVATE);
    }

    public String getToken() {
        return prefs.getString("token", "");
    }

    public void setToken(String token) {
        prefs.edit().putString("token", token).apply();
    }

    public boolean getLoginStatus() {
        return prefs.getBoolean("loginStatus", false);
    }

    public void setLoginStatus(boolean status) {
        prefs.edit().putBoolean("loginStatus", status).apply();
    }
}