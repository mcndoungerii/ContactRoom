package com.ndunga.contactroom.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.util.Log;

import com.ndunga.contactroom.R;
import com.ndunga.contactroom.databinding.ActivityContactDetailsBinding;
import com.ndunga.contactroom.model.Contact;
import com.ndunga.contactroom.model.ContactViewModel;

public class ContactDetails extends AppCompatActivity {

    private ActivityContactDetailsBinding binding;

    private Bundle bundle;

    private ContactViewModel contactViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this,R.layout.activity_contact_details);

        contactViewModel = new ViewModelProvider.AndroidViewModelFactory(this.getApplication()).create(ContactViewModel.class);

        //Instatiating Bundle.
        bundle = getIntent().getExtras();

        if(bundle != null) {
            int id = bundle.getInt(MainActivity.CONTACT_ID);

            contactViewModel.get(id).observe(this, contact -> {
                binding.detNameTextView.setText(contact.getName());
                binding.detOccupationTextView.setText(contact.getOccupation());
            });



        }

    }
}