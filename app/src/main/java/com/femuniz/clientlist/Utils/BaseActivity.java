package com.femuniz.clientlist.Utils;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.femuniz.clientlist.activity.MainActivity;
import com.femuniz.clientlist.services.TokenService;

public class BaseActivity extends AppCompatActivity {
    public TokenService tokenService;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        tokenService = new TokenService(this);

        if (!isTokenValid(this)) {
            redirectToLogin(this);
        }
    }

    public String GetClaimsValue(String claim){
        String token = tokenService.getToken();
        return JWTUtils.GetClaimsValue(token, claim);
    }

    public boolean isTokenValid(Context context) {
        String token = tokenService.getToken();
        return token != null && !token.isEmpty();
    }

    public void redirectToLogin(Context context) {
        tokenService.clearToken();
        Intent intent = new Intent(context, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        context.startActivity(intent);
    }
}
