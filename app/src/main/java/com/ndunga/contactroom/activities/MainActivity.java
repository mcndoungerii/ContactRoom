package com.ndunga.contactroom.activities;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.ndunga.contactroom.R;
import com.ndunga.contactroom.databinding.ActivityMainBinding;
import com.ndunga.contactroom.model.Contact;
import com.ndunga.contactroom.model.ContactViewModel;


public class MainActivity extends AppCompatActivity {

    public static final int NEW_CONTACT_REQUEST_CODE = 1;
    private ContactViewModel contactViewModel;
    private ActivityMainBinding binding;

    ActivityResultLauncher<Intent> someActivityResultLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            result -> {
                if (result.getResultCode() == Activity.RESULT_OK) {
                    // There are no request codes
                    Intent data = result.getData();
                    String name = data.getStringExtra(NewContact.NAME_REPLY);
                    String occupation = data.getStringExtra(NewContact.NAME_OCCUPATION);

                    Contact contact = new Contact(name,occupation);

                    ContactViewModel.insert(contact);


                    Log.d("Name::",data.getStringExtra(NewContact.NAME_REPLY));
                    Log.d("Occupation::",data.getStringExtra(NewContact.NAME_OCCUPATION));

                }
            });

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = DataBindingUtil.setContentView(this,R.layout.activity_main);

        contactViewModel = new ViewModelProvider.AndroidViewModelFactory(
                this.getApplication()).create(ContactViewModel.class);

        contactViewModel.getAllContacts().observe(this,contacts -> {
            StringBuilder builder = new StringBuilder();
            for(Contact contact: contacts) {
                builder.append("-").append(contact.getName()).append(" ").append(contact.getOccupation());
            }
            binding.textView.setText(builder.toString());
        });


        binding.addButtonFab.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this,NewContact.class);

            someActivityResultLauncher.launch(intent);


        });


    }

//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, @Nullable  Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//
//        if(requestCode == NEW_CONTACT_REQUEST_CODE && resultCode == RESULT_OK) {
//            Log.d("Name::",data.getStringExtra(NewContact.NAME_REPLY));
//            Log.d("Occupation::",data.getStringExtra(NewContact.NAME_OCCUPATION));
//        }
//    }
}