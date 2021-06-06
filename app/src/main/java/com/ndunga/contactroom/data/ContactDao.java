package com.ndunga.contactroom.data;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.ndunga.contactroom.model.Contact;

import java.util.List;

@Dao
public interface ContactDao {

    //Peforms all the CRUD

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(Contact contact);

    @Query("DELETE FROM contact_table")
    void deleteAll();

    @Query("SELECT * FROM contact_table")
    LiveData<List<Contact>> getAllContacts();


}
