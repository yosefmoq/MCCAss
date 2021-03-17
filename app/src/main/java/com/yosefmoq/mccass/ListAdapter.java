package com.yosefmoq.mccass;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.yosefmoq.mccass.databinding.ItemContactBinding;

import java.util.ArrayList;

public class ListAdapter extends RecyclerView.Adapter<ListAdapter.MyViewHolder> {
    Context context;
    ArrayList<Contact> contacts;

    public ListAdapter(Context context, ArrayList<Contact> contacts) {
        this.context = context;
        this.contacts = contacts;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyViewHolder(DataBindingUtil.inflate(LayoutInflater.from(context),R.layout.item_contact,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Contact c = contacts.get(position);
        holder.itemContactBinding.tvName.setText(c.getName());
        holder.itemContactBinding.tvAddress.setText(c.getAddress());
        holder.itemContactBinding.tvNumber.setText(c.getNumber());
    }

    @Override
    public int getItemCount() {
        return contacts.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        ItemContactBinding itemContactBinding;
        public MyViewHolder(ItemContactBinding itemContactBinding) {
            super(itemContactBinding.getRoot());
            this.itemContactBinding= itemContactBinding;
        }
    }
    public void update(ArrayList<Contact> contacts){
        this.contacts.clear();
        this.contacts.addAll(contacts);
        Log.v("ttt","size::"+contacts.size());
        Log.v("ttt","size::"+this.contacts.size());
        notifyDataSetChanged();
    }
}
