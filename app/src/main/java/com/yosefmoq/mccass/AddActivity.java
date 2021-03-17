package com.yosefmoq.mccass;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.andreseko.SweetAlert.SweetAlertDialog;
import com.google.firebase.firestore.FirebaseFirestore;
import com.yosefmoq.mccass.databinding.ActivityAddBinding;

public class AddActivity extends AppCompatActivity {
    ActivityAddBinding activityAddBinding;
    FirebaseFirestore firebaseFirestore;
    SweetAlertDialog sweetAlertDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityAddBinding = ActivityAddBinding.inflate(getLayoutInflater());
        setContentView(activityAddBinding.getRoot());
        sweetAlertDialog = new SweetAlertDialog(this,SweetAlertDialog.PROGRESS_TYPE);
        firebaseFirestore = FirebaseFirestore.getInstance();
        activityAddBinding.btnAdd.setOnClickListener(v -> {
            sweetAlertDialog.show();
            String name = activityAddBinding.etName.getText().toString();
            String number = activityAddBinding.etPhone.getText().toString();
            String address = activityAddBinding.EtAddress.getText().toString();
            Contact contact = new Contact();
            contact.setName(name);
            contact.setAddress(address);
            contact.setNumber(number);
            firebaseFirestore.collection(Constants.COLLECTION_CONTACT).add(contact).addOnCompleteListener(command -> {
                if(command.isSuccessful()){
                    sweetAlertDialog.hide();
                    activityAddBinding.etName.setText("");
                    activityAddBinding.etPhone.setText("");
                    activityAddBinding.EtAddress.setText("");
                }
            });

        });


    }
}