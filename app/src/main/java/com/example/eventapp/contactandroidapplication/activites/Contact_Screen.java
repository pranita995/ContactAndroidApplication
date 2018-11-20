package com.example.eventapp.contactandroidapplication.activites;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.example.eventapp.contactandroidapplication.RealmHelper;
import com.example.eventapp.contactandroidapplication.model.ContactDataModel;
import com.example.eventapp.contactandroidapplication.adapter.CustomAdapter;
import com.example.eventapp.contactandroidapplication.R;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

import io.realm.Realm;
import io.realm.RealmConfiguration;

public class Contact_Screen extends AppCompatActivity {

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
        setContentView(R.layout.activity_contact__screen);

        Realm.init(getApplicationContext());
        config=new RealmConfiguration.Builder()
                .name("MyCart.realm")
                .deleteRealmIfMigrationNeeded()
                .build();
        Realm.setDefaultConfiguration(config);
        realm= Realm.getDefaultInstance();
        helper=new RealmHelper(realm);

        recyclerView = (RecyclerView) findViewById(R.id.listview1);

        JSONObject json = new JSONObject();
        JSONArray items = json.optJSONArray("ContactArrays");
        Log.d("JSONARRAY+++++", String.valueOf(items));
        contactList=new ArrayList<>();

        Intent b = getIntent();
        ArrayList<String> Array=b.getStringArrayListExtra("Array1");
        ArrayList<String> Array1=b.getStringArrayListExtra("Array2");

        Log.d("+++++", String.valueOf(Array));
        Log.d("+++++", String.valueOf(Array1));


        customAdapter=new CustomAdapter(Array,Array1,this);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager
                (this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setAdapter(customAdapter);

    }
}
