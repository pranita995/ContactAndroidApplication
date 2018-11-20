package com.example.eventapp.contactandroidapplication;

import com.example.eventapp.contactandroidapplication.model.ContactDataModel;

import java.util.ArrayList;

import io.realm.Realm;
import io.realm.RealmResults;
import io.realm.exceptions.RealmException;

public class RealmHelper {
    Realm realm;
    RealmResults<ContactDataModel> contactDataModelRealmResults;
    Boolean saved;

    public RealmHelper(Realm realm) {
        this.realm = realm;
    }

    public Boolean save(final ContactDataModel contactDataModel) {
        if(contactDataModel==null)
        {
            saved=false;
        }
        else
        {
            realm.executeTransaction(new Realm.Transaction() {
                @Override
                public void execute(Realm realm) {
                    try {

                        ContactDataModel contact = realm.copyToRealm(contactDataModel);
                        saved = true;
                    }
                    catch (RealmException e)
                    {
                        e.printStackTrace();
                        saved=false;
                    }
                }
            });
        }
        return saved;
    }

    public void retrieveDB()
    {
        contactDataModelRealmResults=realm.where(ContactDataModel.class).findAll();

    }

    public ArrayList<ContactDataModel> justreferesh()
    {
        ArrayList<ContactDataModel> contactDataModelArrayList=new ArrayList<>();


        for(ContactDataModel s:contactDataModelRealmResults)
        {
            contactDataModelArrayList.add(s);

        }
        // return retrieve();
        return contactDataModelArrayList;
    }

}
