package com.femuniz.clientlist.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.femuniz.clientlist.Adapter.ClientListAdapter;
import com.femuniz.clientlist.R;
import com.femuniz.clientlist.Utils.BaseActivity;
import com.femuniz.clientlist.configuration.RetrofitClient;
import com.femuniz.clientlist.models.Client;
import com.femuniz.clientlist.models.WebResponse;
import com.femuniz.clientlist.services.ClientService;
import com.femuniz.clientlist.services.TokenService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ClientListActivity extends BaseActivity {
    private Integer idUser;
    private ClientListAdapter adapter;
    private RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_client_list);

        String strIdUser = GetClaimsValue("IdUser");

        if(strIdUser != null || !strIdUser.isEmpty())
            idUser = Integer.parseInt(strIdUser);

        GetAllClient();
    }

    private void GetAllClient(){
        ClientService clientService = RetrofitClient.GetClientClient(tokenService.getToken()).create(ClientService.class);
        Call<List<Client>>  call = clientService.GetAllClient(idUser);

        call.enqueue(new Callback<List<Client>>(){

            @Override
            public void onResponse(Call<List<Client>> call, Response<List<Client>> response) {
                List<Client> clients = response.body();

                recyclerView = findViewById(R.id.recyclerViewClient);
                recyclerView.setLayoutManager(new LinearLayoutManager(ClientListActivity.this));

                adapter = new ClientListAdapter(clients, new ClientListAdapter.OnClienteClickListener() {
                    @Override
                    public void onEditClick(Client client) {

                    }

                    @Override
                    public void onDeleteClick(Client client) {

                    }
                });
                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onFailure(@NonNull Call<List<Client>> call, @NonNull Throwable t) {
                Toast.makeText(getApplicationContext(), "Error: " + t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }

    public void AddClient_click(View view){
        Intent intent = new Intent(ClientListActivity.this, AddClientActivity.class);
        startActivity(intent);
    }

    public void ClientListExitToApp(View view){
        redirectToLogin(this);
    }
}
