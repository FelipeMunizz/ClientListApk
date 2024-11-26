package com.femuniz.clientlist.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;

import com.femuniz.clientlist.R;
import com.femuniz.clientlist.Utils.BaseActivity;

public class AddClientActivity extends BaseActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_add_client);
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
