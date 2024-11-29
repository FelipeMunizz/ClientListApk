package com.femuniz.clientlist.models;

import android.os.Parcel;
import android.os.Parcelable;

public class Client implements Parcelable {
    public int idClient;
    public String name;
    public String cpf;
    public String phoneNumber;
    public String email;
    public String dateBirth;
    public String address;
    public String cep;
    public String city;
    public int idUser;

    // Construtor padrão
    public Client() {}

    // Construtor para Parcelable
    protected Client(Parcel in) {
        idClient = in.readInt();
        name = in.readString();
        cpf = in.readString();
        phoneNumber = in.readString();
        email = in.readString();
        dateBirth = in.readString();
        address = in.readString();
        cep = in.readString();
        city = in.readString();
        idUser = in.readInt();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(idClient);
        dest.writeString(name);
        dest.writeString(cpf);
        dest.writeString(phoneNumber);
        dest.writeString(email);
        dest.writeString(dateBirth);
        dest.writeString(address);
        dest.writeString(cep);
        dest.writeString(city);
        dest.writeInt(idUser);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    // Campo CREATOR necessário para Parcelable
    public static final Parcelable.Creator<Client> CREATOR = new Parcelable.Creator<Client>() {
        @Override
        public Client createFromParcel(Parcel in) {
            return new Client(in);
        }

        @Override
        public Client[] newArray(int size) {
            return new Client[size];
        }
    };
}