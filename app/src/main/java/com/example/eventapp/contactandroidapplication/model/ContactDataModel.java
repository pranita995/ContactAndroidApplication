package com.example.eventapp.contactandroidapplication.model;

import io.realm.RealmObject;

public class ContactDataModel extends RealmObject {

    String Name, Number;

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getNumber() {
        return Number;
    }

    public void setNumber(String number) {
        Number = number;
    }

    public ContactDataModel(String name, String number) {
        Name = name;
        Number = number;
    }
}
