package com.ndunga.contactroom.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;
import android.util.Log;

import com.ndunga.contactroom.R;
import com.ndunga.contactroom.databinding.ActivityContactDetailsBinding;

public class ContactDetails extends AppCompatActivity {

    private ActivityContactDetailsBinding binding;

    private Bundle bundle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this,R.layout.activity_contact_details);

        //Instatiating Bundle.
        bundle = getIntent().getExtras();

        if(bundle != null) {
            String name = bundle.getString("name_det");
            String occupation = bundle.getString("occupation_det");

            //logs.
            //Log.d("name_det",name);
            //Log.d("occupation_det",occupation);

            binding.detNameTextView.setText(name);
            binding.detOccupationTextView.setText(occupation);
        }

    }
}