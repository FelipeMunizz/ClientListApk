package com.femuniz.clientlist.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.femuniz.clientlist.R;
import com.femuniz.clientlist.configuration.RetrofitClient;
import com.femuniz.clientlist.services.UserService;
import com.femuniz.clientlist.models.WebResponse;
import com.femuniz.clientlist.models.UserModel;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_register);
    }

    public  void RegisterUser(View view){
        EditText inputUserName = findViewById(R.id.inputUserName);
        EditText inputUserEmail = findViewById(R.id.inputUserEmail);
        EditText inputUserPassword = findViewById(R.id.inputUserPassword);
        EditText inputUserConfirmedPassword = findViewById(R.id.inputConfirmedPassword);

        if(!PasswordValidator(inputUserPassword.getText().toString(),
                inputUserConfirmedPassword.getText().toString())){
            Toast.makeText(this, "As senhas devem ser iguais", Toast.LENGTH_LONG).show();
            return;
        }

        UserModel.User user = new UserModel.User();
        user.USER_NAME = inputUserName.getText().toString();
        user.USER_EMAIL = inputUserEmail.getText().toString();
        user.USER_PASSWORD = inputUserPassword.getText().toString();

        UserService userService = RetrofitClient.GetClientUser().create(UserService.class);
        Call<WebResponse<UserModel.User>> call = userService.RegisterUser(user);

        call.enqueue(new Callback<WebResponse<UserModel.User>>() {
            @Override
            public void onResponse(@NonNull Call<WebResponse<UserModel.User>> call, @NonNull Response<WebResponse<UserModel.User>> response) {
                if (response.isSuccessful() && response.body() != null){
                    WebResponse<UserModel.User> userWebResponse = response.body();
                    if(userWebResponse.success){
                        Toast.makeText(RegisterActivity.this, userWebResponse.message, Toast.LENGTH_LONG);

                        Intent intent = new Intent(RegisterActivity.this, MainActivity.class);
                        startActivity(intent);
                    }else{
                        Toast.makeText(RegisterActivity.this, userWebResponse.message, Toast.LENGTH_LONG);
                    }
                }
            }

            @Override
            public void onFailure(@NonNull Call<WebResponse<UserModel.User>> call, @NonNull Throwable t) {
                Toast.makeText(getApplicationContext(), "Error: " + t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }

    private boolean PasswordValidator(String password, String confirmedPassword){
        return password.equals(confirmedPassword);
    }
}
