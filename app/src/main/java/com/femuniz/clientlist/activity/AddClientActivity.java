package com.femuniz.clientlist.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;

import com.femuniz.clientlist.R;
import com.femuniz.clientlist.Utils.BaseActivity;
import com.femuniz.clientlist.Utils.UtilitysActivity;

public class AddClientActivity extends BaseActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_add_client);

        EditText inputCpfClient = findViewById(R.id.inputCpfClient);
        EditText inputPhoneClient = findViewById(R.id.inputPhoneClient);
        EditText inputBirthDateClient = findViewById(R.id.inputBirthDateClient);
        EditText inputCpeClient = findViewById(R.id.inputCpeClient);
        UtilitysActivity.applyMask(inputCpfClient, "###.###.###-##");
        UtilitysActivity.applyMask(inputPhoneClient, "(##)#####-####");
        UtilitysActivity.applyMask(inputBirthDateClient, "##/##/####");
        UtilitysActivity.applyMask(inputCpeClient, "#####-###");
    }

    public void AddClient_Click(View view){

    }

    public void AddClientExitToApp(View view){
        redirectToLogin(this);
    }

    public void RedirectToClientList(View view){
        startActivity(new Intent(AddClientActivity.this, ClientListActivity.class));
    }
}
