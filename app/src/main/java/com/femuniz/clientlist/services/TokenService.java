package com.femuniz.clientlist.services;

import android.content.Context;
import android.content.SharedPreferences;

public class TokenService {
    private static final String PREF_NAME = "AppPrefs";
    private static final String TOKEN_KEY = "authToken";

    private final SharedPreferences sharedPreferences;

    // Construtor para inicializar o SharedPreferences
    public TokenService(Context context) {
        this.sharedPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
    }

    public void saveToken(String token) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(TOKEN_KEY, token);
        editor.apply();
    }

    public String getToken() {
        return sharedPreferences.getString(TOKEN_KEY, null);
    }

    public void clearToken() {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.remove(TOKEN_KEY);
        editor.apply();
    }
}
