package com.ndunga.contactroom.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.util.Log;

import com.ndunga.contactroom.R;
import com.ndunga.contactroom.databinding.ActivityMainBinding;
import com.ndunga.contactroom.model.Contact;
import com.ndunga.contactroom.model.ContactViewModel;

public class MainActivity extends AppCompatActivity {

    private ContactViewModel contactViewModel;
    private ActivityMainBinding binding;

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


    }
}