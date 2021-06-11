package com.ndunga.contactroom.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;

import com.ndunga.contactroom.R;
import com.ndunga.contactroom.databinding.ActivityContactDetailsBinding;

public class ContactDetails extends AppCompatActivity {

    private ActivityContactDetailsBinding binding;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this,R.layout.activity_contact_details);

    }
}