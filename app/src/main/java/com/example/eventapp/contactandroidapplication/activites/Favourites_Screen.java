package com.example.eventapp.contactandroidapplication.activites;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.eventapp.contactandroidapplication.R;
import com.example.eventapp.contactandroidapplication.RealmHelper;
import com.example.eventapp.contactandroidapplication.adapter.CustomAdapter;
import com.example.eventapp.contactandroidapplication.model.ContactDataModel;

import java.util.ArrayList;

import io.realm.Realm;
import io.realm.RealmConfiguration;

public class Favourites_Screen extends AppCompatActivity {

    RecyclerView recyclerView ;
    CustomAdapter customAdapter;
    ArrayList<String> contactList;
    ContactDataModel contactDataModel;

    Realm realm;
    public static RealmConfiguration config;
    RealmHelper helper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favourites__screen);

        recyclerView = (RecyclerView) findViewById(R.id.favlist);

        Realm.init(getApplicationContext());
        config=new RealmConfiguration.Builder()
                .name("MyCart.realm")
                .deleteRealmIfMigrationNeeded()
                .build();
        Realm.setDefaultConfiguration(config);
        realm= Realm.getDefaultInstance();
        helper=new RealmHelper(realm);

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager
                (this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setAdapter(customAdapter);

    }
}
