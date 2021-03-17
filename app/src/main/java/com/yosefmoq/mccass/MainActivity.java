package com.yosefmoq.mccass;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;

import com.andreseko.SweetAlert.SweetAlertDialog;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.yosefmoq.mccass.databinding.ActivityMainBinding;

import java.util.ArrayList;



public class MainActivity extends AppCompatActivity {
    RecyclerView rvItems;
    ActivityMainBinding activityMainBinding;
    FirebaseFirestore firebaseFirestore;
    CollectionReference collectionReference;
    SweetAlertDialog sweetAlertDialog;
    ArrayList<Contact> contacts = new ArrayList<>();
    ListAdapter listAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityMainBinding = ActivityMainBinding.inflate(getLayoutInflater());

        setContentView(activityMainBinding.getRoot());

        firebaseFirestore = FirebaseFirestore.getInstance();
        collectionReference = firebaseFirestore.collection(Constants.COLLECTION_CONTACT);

        listAdapter = new ListAdapter(MainActivity.this,contacts);
        activityMainBinding.rvList.setLayoutManager(new LinearLayoutManager(MainActivity.this));
        activityMainBinding.rvList.setAdapter(listAdapter);
        sweetAlertDialog = new SweetAlertDialog(MainActivity.this, SweetAlertDialog.PROGRESS_TYPE);
        sweetAlertDialog.create();
        sweetAlertDialog.show();
        collectionReference.addSnapshotListener((value, error) -> {
            ArrayList<Contact> contacts1 = new ArrayList<>();
            for (DocumentSnapshot documentSnapshot : value.getDocuments()) {
                Contact c = documentSnapshot.toObject(Contact.class);
                Log.v("ttt",c.toString());
                contacts1.add(c);
            }
            if (sweetAlertDialog.isShowing()) {
                sweetAlertDialog.hide();
            }

            listAdapter.update(contacts1);
        });

//        activityMainBinding.rvList.setLayoutManager(new LinearLayoutManager(this));
//        setContentView(R.layout.activity_main);
    }
}