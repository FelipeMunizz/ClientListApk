package com.femuniz.clientlist.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;

import com.femuniz.clientlist.R;
import com.femuniz.clientlist.Utils.BaseActivity;
import com.femuniz.clientlist.Utils.UtilitysActivity;
import com.femuniz.clientlist.configuration.RetrofitClient;
import com.femuniz.clientlist.models.Client;
import com.femuniz.clientlist.models.WebResponse;
import com.femuniz.clientlist.services.ClientService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddClientActivity extends BaseActivity {
    private Integer idUser;
    private Client clientEdit = null;
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

        String strIdUser = GetClaimsValue("IdUser");

        if(strIdUser != null || !strIdUser.isEmpty())
            idUser = Integer.parseInt(strIdUser);

        Intent intent = getIntent();
        clientEdit = intent.getParcelableExtra("client", Client.class);
        if(clientEdit != null)
            SetValuesClientEdit(clientEdit);
    }

    public void AddClient_Click(View view){
        Client client = GetClientModel();

        if(client.idClient > 0){
            RequestEditClient(client);
        }else{
            RequestAddClient(client);
        }

    }

    public void AddClientExitToApp(View view){
        redirectToLogin(this);
    }

    public void RedirectToClientList(View view){
        startActivity(new Intent(AddClientActivity.this, ClientListActivity.class));
    }

    private Client GetClientModel() {
        EditText inputNameClient = findViewById(R.id.inputClientName);
        EditText inputCpfClient = findViewById(R.id.inputCpfClient);
        EditText inputPhoneClient = findViewById(R.id.inputPhoneClient);
        EditText inputEmailClient = findViewById(R.id.inputEmailClient);
        EditText inputBirthDateClient = findViewById(R.id.inputBirthDateClient);
        EditText inputAddressClient = findViewById(R.id.inputAddressClient);
        EditText inputCepClient = findViewById(R.id.inputCpeClient);
        EditText inputCityClient = findViewById(R.id.inputCityClient);

        Client client = new Client();
        client.idUser = idUser;
        client.name = inputNameClient.getText().toString();
        client.cpf = inputCpfClient.getText().toString()
                .replace(".", "").replace("-", "");
        client.phoneNumber = inputPhoneClient.getText().toString()
                .replace("(", "").replace(")", "").replace("-", "");
        client.email = inputEmailClient.getText().toString();

        client.dateBirth = inputBirthDateClient.getText().toString();
        client.address = inputAddressClient.getText().toString();
        client.cep = inputCepClient.getText().toString().replace("-", "");
        client.city = inputCityClient.getText().toString();

        if(clientEdit != null && clientEdit.idClient > 0)
            client.idClient = clientEdit.idClient;

        return client;
    }

    private void SetValuesClientEdit(Client client) {
        EditText inputNameClient = findViewById(R.id.inputClientName);
        EditText inputCpfClient = findViewById(R.id.inputCpfClient);
        EditText inputPhoneClient = findViewById(R.id.inputPhoneClient);
        EditText inputEmailClient = findViewById(R.id.inputEmailClient);
        EditText inputBirthDateClient = findViewById(R.id.inputBirthDateClient);
        EditText inputAddressClient = findViewById(R.id.inputAddressClient);
        EditText inputCepClient = findViewById(R.id.inputCpeClient);
        EditText inputCityClient = findViewById(R.id.inputCityClient);

        if (client != null) {
            inputNameClient.setText(client.name!= null ? client.name : "");
            inputCpfClient.setText(client.cpf!= null ? client.cpf : "");
            inputPhoneClient.setText(client.phoneNumber != null ? client.phoneNumber : "");
            inputEmailClient.setText(client.email != null ? client.email : "");
            inputBirthDateClient.setText(client.dateBirth != null ? client.dateBirth : "");
            inputAddressClient.setText(client.address != null ? client.address : "");
            inputCepClient.setText(client.cep != null ? client.cep : "");
            inputCityClient.setText(client.city != null ? client.city : "");
        }
    }

    private void RequestAddClient(Client client){
        ClientService clientService = RetrofitClient.GetClientClient(tokenService.getToken()).create(ClientService.class);
        Call<WebResponse<Client>> call = clientService.AddClient(client);

        call.enqueue(new Callback<WebResponse<Client>>() {
            @Override
            public void onResponse(Call<WebResponse<Client>> call, Response<WebResponse<Client>> response) {
                if(response.body() != null){
                    WebResponse<Client> result = response.body();

                    Toast.makeText(getApplicationContext(), result.message, Toast.LENGTH_LONG).show();

                    startActivity(new Intent(AddClientActivity.this, ClientListActivity.class));
                }
            }

            @Override
            public void onFailure(Call<WebResponse<Client>> call, Throwable t) {
                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }

    private void RequestEditClient(Client client){
        ClientService clientService = RetrofitClient.GetClientClient(tokenService.getToken()).create(ClientService.class);
        Call<WebResponse<Client>> call = clientService.UpdateClient(client);

        call.enqueue(new Callback<WebResponse<Client>>() {
            @Override
            public void onResponse(Call<WebResponse<Client>> call, Response<WebResponse<Client>> response) {
                startActivity(new Intent(getApplicationContext(), ClientListActivity.class));

            }

            @Override
            public void onFailure(Call<WebResponse<Client>> call, Throwable throwable) {

            }
        });
    }
}
