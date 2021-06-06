package com.ndunga.contactroom.data;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.ndunga.contactroom.model.Contact;
import com.ndunga.contactroom.util.ContactRoomDatabase;

import java.util.List;

public class ContactRepository {

    private ContactDao contactDao;

    private LiveData<List<Contact>> allContacts;

    public ContactRepository(Application application) {
        //1.call room db
        ContactRoomDatabase db = ContactRoomDatabase.getDatabase(application);

        //2.overwrite dao
        contactDao = db.contactDao();

        //3.overwrite allcontacts.
        allContacts = contactDao.getAllContacts();
    }

    //This is  for getting all contacts data
    public LiveData<List<Contact>> getAllData(){ return  allContacts; }

    public void insert(Contact contact) {
        ContactRoomDatabase.databaseWriteExecutor.execute(()-> {
            contactDao.insert(contact);
        });
    }
}
