package com.example.first_android_project;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ContactAdapter extends RecyclerView.Adapter<ContactAdapter.ContactViewHolder> {
    interface OnStateClickListener{
        void onStateClick(Contact contact, int position);
    }
    LayoutInflater inflater;
    List<Contact> contactList;
    Context context;
    final OnStateClickListener onClickListener;

    public ContactAdapter(Context context, List<Contact> contactList, OnStateClickListener onClickListener) {
        this.context = context;
        this.inflater = LayoutInflater.from(context);
        this.contactList = contactList;
        this.onClickListener = onClickListener;
    }

    @NonNull
    @Override
    public ContactViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.contact_list,parent,false);
        return new ContactViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ContactViewHolder holder, int position) {
        Contact contact = contactList.get(position);
        holder.avatar.setImageResource(contact.getAvatar());
        holder.name.setText(contact.getName());
        holder.number.setText(contact.getNumber());
        int pos = position;

        holder.itemView.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v){
               onClickListener.onStateClick(contact , pos);
            }
        });

    }

    @Override
    public int getItemCount() {
        return contactList.size();
    }
/////////////////////////////////////////////////////////////////////////////////////////////////////
    static class ContactViewHolder extends RecyclerView.ViewHolder{
        ImageView avatar;
        TextView name , number;
        public ContactViewHolder(@NonNull View itemView) {
            super(itemView);
            avatar = itemView.findViewById(R.id.avatar);
            name = itemView.findViewById(R.id.name);
            number = itemView.findViewById(R.id.number);
        }
    }
}
