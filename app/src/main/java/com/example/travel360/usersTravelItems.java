package com.example.travel360;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class usersTravelItems extends AppCompatActivity {

    EditText editTextItemName,editTextItemNo,ItemPrice,editTextDescription,contactNo,editTextsearch;
    Button buttonAdd,buttonDelete,buttonUpdate,buttonalllist;
    DatabaseReference dbRef;
    ImageButton buttonsearch;

    TravelItems travelItems = new TravelItems();
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    final DatabaseReference table_Item = database.getReference("TravelItems");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_users_travel_items);


        editTextItemName = findViewById(R.id.UserTravelEditTextItemName);
        editTextItemNo  = findViewById(R.id.UserTravelEditTextItemNo);
        ItemPrice = findViewById(R.id.UserTravelEditTextItemPrice);
        editTextDescription = findViewById(R.id.UserTravelEditTextItemDescription);
        contactNo = findViewById(R.id.UserTravelEditTextContactNo);
        editTextsearch = findViewById(R.id.travelItemsSearch_field);

        buttonalllist = findViewById(R.id.travelItemsAll_btn);
        buttonsearch = findViewById(R.id.UserTravelItemsSearch_btn);
        buttonAdd = findViewById(R.id.travelItemsInsertButton);
        buttonDelete = findViewById(R.id.travelItemsDeleteButton);
        buttonUpdate = findViewById(R.id.travelItemsUpdateButton);

        buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addTravelItems();
            }
        });

        buttonUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateTravelItems();
            }
        });

        buttonDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                deleteTravelItems();
            }
        });

        buttonsearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showTravelItems();
            }
        });

        buttonalllist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(usersTravelItems.this,travelItemList.class);
                startActivity(intent);
            }
        });
    }

    public void addTravelItems(){

        table_Item.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                try {

                    travelItems.setTravelItemName(editTextItemName.getText().toString().trim());
                    travelItems.setTravelItemId(editTextItemNo.getText().toString().trim());
                    travelItems.setTravelItemDescription(editTextDescription.getText().toString().trim());
                    travelItems.setTravelItemPrice(ItemPrice.getText().toString().trim());
                    travelItems.setTravelContactNumber(contactNo.getText().toString().trim());
                    table_Item.child(travelItems.getTravelItemId()).setValue(travelItems);

                    Toast.makeText(getApplicationContext(), "Data Update Successfully", Toast.LENGTH_SHORT).show();

                }catch (Exception e){
                    Toast.makeText(getApplicationContext(),"Invalid Operation",Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    public void deleteTravelItems(){
        DatabaseReference delRef = FirebaseDatabase.getInstance().getReference().child("TravelItems");
        delRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                try {
                    if (dataSnapshot.hasChild(editTextItemNo.getText().toString())) {

                        dbRef = FirebaseDatabase.getInstance().getReference().child("TravelItems").child(editTextItemNo.getText().toString());
                        dbRef.removeValue();

                        Toast.makeText(getApplicationContext(), "Data Delete Successfully", Toast.LENGTH_SHORT).show();

                    } else {
                        Toast.makeText(getApplicationContext(), "Invalid Trip Name", Toast.LENGTH_SHORT).show();
                    }
                }catch (Exception e){
                    Toast.makeText(getApplicationContext(),"Invalid Operation",Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    public void updateTravelItems(){
        DatabaseReference readRef = FirebaseDatabase.getInstance().getReference().child("TravelItems");
        readRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                try {

                    travelItems.setTravelItemName(editTextItemName.getText().toString().trim());
                    travelItems.setTravelItemId(editTextItemNo.getText().toString().trim());
                    travelItems.setTravelItemDescription(editTextDescription.getText().toString().trim());
                    travelItems.setTravelItemPrice(ItemPrice.getText().toString().trim());
                    travelItems.setTravelContactNumber(contactNo.getText().toString().trim());


                    dbRef = FirebaseDatabase.getInstance().getReference().child("TravelItems").child(editTextItemNo.getText().toString());
                    dbRef.setValue(travelItems);

                    Toast.makeText(getApplicationContext(),"Data update Successfully",Toast.LENGTH_SHORT).show();

                }catch (Exception e){
                    Toast.makeText(getApplicationContext(),"Invalid Operation",Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    public void showTravelItems(){
        DatabaseReference delRef = FirebaseDatabase.getInstance().getReference().child("TravelItems").child(editTextsearch.getText().toString());
        delRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                try {
                    if (dataSnapshot.hasChildren()) {

                        editTextItemName.setText(dataSnapshot.child("travelItemName").getValue().toString());
                        editTextItemNo.setText(dataSnapshot.child("travelItemId").getValue().toString());
                        ItemPrice.setText(dataSnapshot.child("travelItemPrice").getValue().toString());
                        editTextDescription.setText(dataSnapshot.child("travelItemDescription").getValue().toString());
                        contactNo.setText(dataSnapshot.child("travelContactNumber").getValue().toString());

                        Toast.makeText(getApplicationContext(), "Data Update Successfully", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(getApplicationContext(), "Invalid Trip Name", Toast.LENGTH_SHORT).show();
                    }
                }catch (Exception e){
                    Toast.makeText(getApplicationContext(),"Invalid Operation",Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
