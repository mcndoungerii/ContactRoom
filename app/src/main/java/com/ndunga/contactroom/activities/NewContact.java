package com.ndunga.contactroom.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;
import com.ndunga.contactroom.R;
import com.ndunga.contactroom.databinding.ActivityNewContactBinding;
import com.ndunga.contactroom.model.Contact;
import com.ndunga.contactroom.model.ContactViewModel;

import static android.view.View.GONE;

public class NewContact extends AppCompatActivity {

    public static final String NAME_REPLY = "name_reply";
    public static final String NAME_OCCUPATION = "name_occupation";
    private ActivityNewContactBinding binding;

    private ContactViewModel contactViewModel;

    private Bundle bundle;
    private int contactId = 0;
    private Boolean isEdit = false;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this,R.layout.activity_new_contact);

        contactViewModel = new ViewModelProvider.AndroidViewModelFactory(this.getApplication()).create(ContactViewModel.class);

        bundle = getIntent().getExtras();

        binding.saveButton.setOnClickListener(v -> {
            Intent replyIntent = new Intent();

            if(!TextUtils.isEmpty(binding.etName.getText()) && !TextUtils.isEmpty(binding.etOccupation.getText())){

                String name = binding.etName.getText().toString();
                String occupation =binding.etOccupation.getText().toString();

                Contact contact = new Contact(name,occupation);


                //ContactViewModel.insert(contact);


                replyIntent.putExtra(NAME_REPLY,name);
                replyIntent.putExtra(NAME_OCCUPATION,occupation);

                setResult(RESULT_OK,replyIntent);



            }
            else {

                setResult(RESULT_CANCELED,replyIntent);
                Snackbar.make(v,"Fill the Fields",Snackbar.LENGTH_SHORT).show();
            }

            finish();



        });


        //get intent
        if(getIntent().hasExtra(MainActivity.CONTACT_ID)) {
            contactId = getIntent().getIntExtra(MainActivity.CONTACT_ID,0);

            contactViewModel.get(contactId).observe(this, contact -> {

                if(contact != null){
                    binding.etName.setText(contact.getName());
                    binding.etOccupation.setText(contact.getOccupation());
                }

            });

            isEdit = true;
        }

        //update btn
        binding.updateButton.setOnClickListener(v -> {
            String name = binding.etName.getText().toString();
            String occupation = binding.etOccupation.getText().toString();

            if(TextUtils.isEmpty(name) || TextUtils.isEmpty(occupation)){
                Snackbar.make(v,R.string.empty,Snackbar.LENGTH_SHORT).show();
            }
            else {
                Contact contact = new Contact();

                contact.setId(contactId);
                contact.setName(name);
                contact.setOccupation(occupation);

                //call update method.
                ContactViewModel.update(contact);
                finish();
            }
        });

        //check isEdit
        if(isEdit){
            binding.saveButton.setVisibility(GONE);
        }
        else {
            binding.updateButton.setVisibility(GONE);
            binding.deleteButton.setVisibility(GONE);

        }



    }




}