package com.ndunga.contactroom.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.recyclerview.widget.RecyclerView;

import com.ndunga.contactroom.R;
import com.ndunga.contactroom.model.Contact;

import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {

    private LiveData<List<Contact>> contactList;
    private Context context;

    public RecyclerViewAdapter(LiveData<List<Contact>> contactList, Context context) {
        this.contactList = contactList;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull  ViewGroup parent, int viewType) {

        //1. this is where we going to inflate layout

        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.contact_rows,parent,false);

        //2. return a ViewHolder object , and pass view
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull  RecyclerViewAdapter.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    //ViewHolder -- boost performance by rendering views in a Recycler View, this recycles view when user scrolls ups and down, left to right, and
    //You don't have to worry on the size of your data to be rendered.
    public class ViewHolder extends RecyclerView.ViewHolder {
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
