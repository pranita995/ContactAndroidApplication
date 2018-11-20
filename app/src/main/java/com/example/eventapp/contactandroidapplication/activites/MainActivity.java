package com.example.eventapp.contactandroidapplication.activites;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.provider.ContactsContract;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.eventapp.contactandroidapplication.R;
import com.example.eventapp.contactandroidapplication.model.ContactDataModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {


    ArrayList<String> nameList ;
    ArrayList<String> numberList ;
    ContactDataModel contactDataModel;
    Cursor cursor ;
    String name, phonenumber ;
    public  static final int RequestPermissionCode  = 1 ;
    Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);



        button = (Button)findViewById(R.id.button1);

        nameList = new ArrayList<String>();
        numberList = new ArrayList<String>();

        EnableRuntimePermission();

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                GetContactsIntoArrayList();



                /*arrayAdapter = new ArrayAdapter<String>(
                        MainActivity.this,
                        R.layout.contact_list_itemview,
                        R.id.textView, StoreContacts
                );*/






            }
        });

    }

    public void GetContactsIntoArrayList(){



        cursor = getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null,null, null, null);

        while (cursor.moveToNext()) {

            name = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));

            phonenumber = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));

           // StoreContacts.add(name + " "  + ":" + " " + phonenumber);

            nameList.add(name );
            numberList.add(phonenumber);


            contactDataModel=new ContactDataModel(name,phonenumber);


        }

        JSONObject json = new JSONObject();
        JSONObject json1 = new JSONObject();
        try {
            json.put("ContactArrays", new JSONArray(nameList));
            json1.put("ContactArrays", new JSONArray(numberList));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        String arrayList = json.toString();


        Intent intent=new Intent(getApplicationContext(), Contact_Screen.class);
        Bundle b = new Bundle();
        intent.putStringArrayListExtra("Array1", nameList);
        intent.putStringArrayListExtra("Array2", numberList);
        intent.putExtras(b);
        startActivity(intent);

        cursor.close();

    }

    public void EnableRuntimePermission(){

        if (ActivityCompat.shouldShowRequestPermissionRationale(
                MainActivity.this,
                Manifest.permission.READ_CONTACTS))
        {

            Toast.makeText(MainActivity.this,"CONTACTS permission allows us to Access CONTACTS app", Toast.LENGTH_LONG).show();

        } else {

            ActivityCompat.requestPermissions(MainActivity.this,new String[]{
                    Manifest.permission.READ_CONTACTS}, RequestPermissionCode);

        }
    }

    @Override
    public void onRequestPermissionsResult(int RC, String per[], int[] PResult) {

        switch (RC) {

            case RequestPermissionCode:

                if (PResult.length > 0 && PResult[0] == PackageManager.PERMISSION_GRANTED) {

                    Toast.makeText(MainActivity.this,"Permission Granted, Now your application can access CONTACTS.", Toast.LENGTH_LONG).show();

                } else {

                    Toast.makeText(MainActivity.this,"Permission Canceled, Now your application cannot access CONTACTS.", Toast.LENGTH_LONG).show();

                }
                break;
        }
    }


}

