package com.femuniz.clientlist.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.femuniz.clientlist.R;
import com.femuniz.clientlist.Utils.BaseActivity;
import com.femuniz.clientlist.configuration.RetrofitClient;
import com.femuniz.clientlist.services.TokenService;
import com.femuniz.clientlist.services.UserService;
import com.femuniz.clientlist.models.WebResponse;
import com.femuniz.clientlist.models.UserModel;

import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private TokenService tokenService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        tokenService = new TokenService(this);
        String token = tokenService.getToken();

        if(token != null && !token.isEmpty())
            RedirectClientList();
    }

    public void Login(View view){
        EditText inputEmail = findViewById(R.id.inputEmailAddress);
        EditText inputPassword = findViewById(R.id.inputPassword);

        String email = inputEmail.getText().toString();
        String password = inputPassword.getText().toString();

        UserModel.LoginUser loginUser = new UserModel.LoginUser(email, password);

        UserService userService = RetrofitClient.GetClientUser().create(UserService.class);
        Call<WebResponse<Object>> call = userService.LoginUser(loginUser);

        call.enqueue(new Callback<>() {
            @Override
            public void onResponse(@NonNull Call<WebResponse<Object>> call, @NonNull Response<WebResponse<Object>> response) {
                WebResponse<Object> webResponse = response.body();
                if (response.isSuccessful() && response.body() != null) {
                    assert webResponse != null;

                    if (webResponse.success) {
                        Object data = webResponse.data;
                        Map<String, Object> dataMap = (Map<String, Object>) data;
                        String token = (String) dataMap.get("token");

                        tokenService.saveToken(token);

                        startActivity(new Intent(MainActivity.this, ClientListActivity.class));
                    }
                } else {
                    assert webResponse != null;

                    Toast.makeText(getApplicationContext(), webResponse.message, Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(@NonNull Call<WebResponse<Object>> call, @NonNull Throwable t) {
                Toast.makeText(getApplicationContext(), "Error: " + t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }

    public void Register(View view){
        startActivity(new Intent(MainActivity.this, RegisterActivity.class));
    }

    private void RedirectClientList(){
        startActivity(new Intent(MainActivity.this, ClientListActivity.class));
    }
}
