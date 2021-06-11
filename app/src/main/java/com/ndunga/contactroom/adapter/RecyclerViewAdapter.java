package com.ndunga.contactroom.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.recyclerview.widget.RecyclerView;

import com.ndunga.contactroom.R;
import com.ndunga.contactroom.model.Contact;

import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {

    private final List<Contact> contactList;
    private final Context context;
    private onContactClickListener contactClickListener;

    public RecyclerViewAdapter(List<Contact> contactList, Context context, onContactClickListener onContactClickListener) {
        this.contactList = contactList;
        this.context = context;
        this.contactClickListener = onContactClickListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull  ViewGroup parent, int viewType) {

        //1. this is where we going to inflate layout

        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.contact_rows,parent,false);

        //2. return a ViewHolder object , and pass view
        return new ViewHolder(view,contactClickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull  RecyclerViewAdapter.ViewHolder holder, int position) {
        //since,we want to access the position of whats in contactList, and our Recycler view will know how to render the data
        Contact contact = contactList.get(position);

        //Access the Holder.
        holder.name.setText(contact.getName());
        holder.occupation.setText(contact.getOccupation());
    }

    @Override
    public int getItemCount() {
        return contactList.size();
    }

    //ViewHolder -- boost performance by rendering views in a Recycler View, this recycles view when user scrolls ups and down, left to right, and
    //You don't have to worry on the size of your data to be rendered.
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        private TextView name;
        private TextView occupation;
        private onContactClickListener onContactClickListener;
        public ViewHolder(@NonNull View itemView, onContactClickListener onContactClickListener) {

            super(itemView);

            name = itemView.findViewById(R.id.row_name_textview);
            occupation = itemView.findViewById(R.id.row_occupation_textview);
            this.onContactClickListener = onContactClickListener;

            itemView.setOnClickListener(this);


        }

        @Override
        public void onClick(View v) {

            onContactClickListener.onContactClick(getAdapterPosition());

        }
    }

    public interface onContactClickListener {
        void onContactClick(int position);
    }
}
