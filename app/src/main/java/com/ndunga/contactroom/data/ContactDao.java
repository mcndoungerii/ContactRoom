package com.ndunga.contactroom.data;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

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

    @Query("SELECT * FROM contact_table WHERE contact_table.id == :id")
    LiveData<Contact> get(int id);

    @Delete
    void delete(Contact contact);

    @Update
    void update(Contact contact);


}
