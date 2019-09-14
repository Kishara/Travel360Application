package com.example.travel360;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class travelItemDescriptionUpdate extends AppCompatActivity {
    String itemName,itemDescription,itemPrice,ItemId,userTel,itemQuantity;
    EditText txtUserTelNo,txtQuantity;
    Button update;
    DatabaseReference dbRef;
    TextView txtItemName,txtItemPrice,txtItemDesciption,txtItemId;
    CartItems cartItems = new CartItems();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_travel_item_description_update);

        Intent i =getIntent();
        itemName= i.getStringExtra("itemName");
        itemPrice= i.getStringExtra("itemPrice");
        itemDescription= i.getStringExtra("itemDescription");
        ItemId= i.getStringExtra("itemId");
        itemQuantity = i.getStringExtra("itemQuantity");
        userTel = i.getStringExtra("itemTel");


        txtUserTelNo = findViewById(R.id.txtUserTelNo);
        txtItemName =findViewById(R.id.item_name);
        txtItemPrice =findViewById(R.id.item_price);
        txtItemDesciption = findViewById(R.id.item_description);
        txtQuantity = findViewById(R.id.txtquantity);

        update = findViewById(R.id.btnUpdate);
        txtItemId = findViewById(R.id.item_no);

        txtItemName.setText(itemName);
        txtItemPrice.setText(itemPrice);
        txtItemDesciption.setText(itemDescription);
        txtItemId.setText(ItemId);
        txtUserTelNo.setText(userTel);
        txtQuantity.setText(itemQuantity);

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatabaseReference delRef = FirebaseDatabase.getInstance().getReference().child("CartItems").child(userTel);
                delRef.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        if(dataSnapshot.hasChild(ItemId)){
                            try {
                                cartItems.setCartItemId(txtItemId.getText().toString().trim());
                                cartItems.setCartItemDescription(txtItemDesciption.getText().toString().trim());
                                cartItems.setCartItemName(txtItemName.getText().toString().trim());
                               cartItems.setCartItemPrice(txtItemPrice.getText().toString().trim());
                                cartItems.setCartItemQuantity(txtQuantity.getText().toString().trim());
                               cartItems.setCartUserTelNo(txtUserTelNo.getText().toString().trim());

                                dbRef = FirebaseDatabase.getInstance().getReference().child("CartItems").child(userTel).child(ItemId);
                                dbRef.setValue(cartItems);

                                Toast.makeText(getApplicationContext(),"Data Update successfully",Toast.LENGTH_SHORT).show();

                            }catch (NumberFormatException e) {
                                Toast.makeText(getApplicationContext(), "Invalid Data", Toast.LENGTH_SHORT).show();
                            }
                            }
                        else
                            Toast.makeText(getApplicationContext(),"No Source to Update",Toast.LENGTH_SHORT).show();

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
            }
        });
    }
}
