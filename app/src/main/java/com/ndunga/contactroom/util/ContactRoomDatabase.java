package com.ndunga.contactroom.util;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

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
    public static final ExecutorService databaseWriteExecutor = Executors.newFixedThreadPool(N_THREADS);

    //4.create getDatabase method

    public static ContactRoomDatabase getDatabase(final Context context) {
        if(INSTANCE == null) {
            synchronized (ContactRoomDatabase.class) {
                if(INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            ContactRoomDatabase.class,"contact_database").addCallback(sRoomDbCallback).build();
                }
            }
        }

        return INSTANCE;
    }

    public static final RoomDatabase.Callback sRoomDbCallback =
            new RoomDatabase.Callback(){
                @Override
                public void onCreate(@NonNull SupportSQLiteDatabase db) {
                    super.onCreate(db);

                    //call dbWriteExecutor - It helps us to write things in the back threads.
                    databaseWriteExecutor.execute(()->{
                        //access the dao from instance
                        ContactDao contactDao = INSTANCE.contactDao();

                        //deleteAll, before inserting.
                        contactDao.deleteAll();

                        //Insert data to our tables.
                        Contact contact = new Contact("Allen","Lawyer");

                        contactDao.insert(contact);

                        contact = new Contact("Bond","spy");
                        contactDao.insert(contact);
                    });
                }
            };

}
