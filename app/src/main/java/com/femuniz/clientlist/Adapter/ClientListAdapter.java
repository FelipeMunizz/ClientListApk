package com.femuniz.clientlist.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.femuniz.clientlist.R;
import com.femuniz.clientlist.models.Client;

import java.util.List;

public class ClientListAdapter  extends RecyclerView.Adapter<ClientListAdapter.ClienteViewHolder> {
    private List<Client> clientList;
    private OnClienteClickListener listener;

    public interface OnClienteClickListener {
        void onEditClick(Client client);
        void onDeleteClick(Client client);
    }

    public ClientListAdapter(List<Client> clientList, OnClienteClickListener listener) {
        this.clientList = clientList;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ClientListAdapter.ClienteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_client, parent, false);
        return new ClienteViewHolder(view);    }

    @Override
    public void onBindViewHolder(@NonNull ClientListAdapter.ClienteViewHolder holder, int position) {
        Client client = clientList.get(position);
        holder.txtClientName.setText(client.name);

        holder.btnEditClient.setOnClickListener(v -> listener.onEditClick(client));
        holder.btnDeleteClient.setOnClickListener(v -> listener.onDeleteClick(client));
    }

    @Override
    public int getItemCount() {
        return clientList.size();
    }

    public static class ClienteViewHolder extends RecyclerView.ViewHolder {
        TextView txtClientName;
        ImageView btnEditClient, btnDeleteClient;

        public ClienteViewHolder(@NonNull View itemView) {
            super(itemView);
            txtClientName = itemView.findViewById(R.id.txtClientName);
            btnEditClient = itemView.findViewById(R.id.btnEditClient);
            btnDeleteClient = itemView.findViewById(R.id.btnDeleteClient);
        }
    }
}
