package com.ndunga.contactroom.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
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

public class NewContact extends AppCompatActivity {

    public static final String NAME_REPLY = "name_reply";
    public static final String NAME_OCCUPATION = "name_occupation";
    private ActivityNewContactBinding binding;

    private ContactViewModel contactViewModel;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this,R.layout.activity_new_contact);

        contactViewModel = new ViewModelProvider.AndroidViewModelFactory(this.getApplication()).create(ContactViewModel.class);



        binding.saveButton.setOnClickListener(v -> {
            Intent replyIntent = new Intent();

            if(!TextUtils.isEmpty(binding.etName.getText()) && !TextUtils.isEmpty(binding.etOccupation.getText())){

                String name = binding.etName.getText().toString();
                String occupation =binding.etOccupation.getText().toString();

                Contact contact = new Contact(name,occupation);


                //ContactViewModel.insert(contact);


                replyIntent.putExtra(NAME_REPLY,name);
                replyIntent.putExtra(NAME_OCCUPATION,name);

                setResult(RESULT_OK,replyIntent);



            }
            else {

                setResult(RESULT_CANCELED,replyIntent);
                Snackbar.make(v,"Fill the Fields",Snackbar.LENGTH_SHORT).show();
            }

            finish();

            hideKeyboard(v);


        });




    }

    private void hideKeyboard(View v) {
        //Hide Keyboard

        InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);

        inputMethodManager.hideSoftInputFromWindow(v.getWindowToken(),0);
    }


}