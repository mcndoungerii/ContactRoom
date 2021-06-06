package com.ndunga.contactroom.util;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.ndunga.contactroom.data.ContactDao;
import com.ndunga.contactroom.model.Contact;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {Contact.class},version = 1,exportSchema = false)
public abstract class ContactRoomDatabase extends RoomDatabase {

    //1.call the Dao
    public abstract ContactDao contactDao();

    //2.Initialize a volatile Instance

    private static volatile ContactRoomDatabase INSTANCE;

    public static final int N_THREADS = 4;
    //3.Instantiate Executors Service. - helps us to run things in a back thread.
    private static final ExecutorService databaseWriteExecutor = Executors.newFixedThreadPool(N_THREADS);

    //4.create getDatabase method

    public static ContactRoomDatabase getDatabase(final Context context) {
        if(INSTANCE == null) {
            synchronized (ContactRoomDatabase.class) {
                if(INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            ContactRoomDatabase.class,"contact_database").build();
                }
            }
        }

        return INSTANCE;
    }

}
