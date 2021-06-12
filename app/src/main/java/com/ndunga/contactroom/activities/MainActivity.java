package com.ndunga.contactroom.activities;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.ndunga.contactroom.R;
import com.ndunga.contactroom.adapter.RecyclerViewAdapter;
import com.ndunga.contactroom.databinding.ActivityMainBinding;
import com.ndunga.contactroom.model.Contact;
import com.ndunga.contactroom.model.ContactViewModel;


public class MainActivity extends AppCompatActivity implements RecyclerViewAdapter.onContactClickListener {

    public static final int NEW_CONTACT_REQUEST_CODE = 1;
    private ContactViewModel contactViewModel;
    private ActivityMainBinding binding;
    private RecyclerViewAdapter recyclerViewAdapter;

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

        //Seeting up Recycler view LayoutSize & LayoutManager
        binding.recyclerView.setHasFixedSize(true);
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(this));

        contactViewModel.getAllContacts().observe(this,contacts -> {
            recyclerViewAdapter = new RecyclerViewAdapter(contacts,MainActivity.this,this);

            //set up adapter
            binding.recyclerView.setAdapter(recyclerViewAdapter);
        });


        binding.addButtonFab.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this,NewContact.class);

            someActivityResultLauncher.launch(intent);


        });


    }


    @Override
    public void onContactClick(int position) {
        Contact contact = contactViewModel.getAllContacts().getValue().get(position);

        Log.d("onContact Click", contact.getName());

        Intent intent = new Intent(this,ContactDetails.class);

        intent.putExtra("name_det",contact.getName());
        intent.putExtra("occupation_det",contact.getOccupation());

        startActivity(intent);
    }
}