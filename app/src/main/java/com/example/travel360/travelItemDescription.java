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

public class travelItemDescription extends AppCompatActivity {
    String itemName,itemDescription,itemPrice,ItemId;
    EditText userTelNo,quantity;
    Button book;
    String a;
    TextView txtItemName,txtItemPrice,txtItemDesciption,txtItemId;
    CartItems cartItems = new CartItems();

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    final DatabaseReference table_Item = database.getReference("CartItems");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_travel_item_description);
        Intent i =getIntent();
        itemName= i.getStringExtra("itemName");
        itemPrice= i.getStringExtra("itemPrice");
        itemDescription= i.getStringExtra("itemDescription");
        ItemId= i.getStringExtra("itemId");


        userTelNo = findViewById(R.id.txtUserTelNo);
        txtItemName =findViewById(R.id.item_name);
        txtItemPrice =findViewById(R.id.item_price);
        txtItemDesciption = findViewById(R.id.item_description);
        quantity = findViewById(R.id.txtquantity);

        book = findViewById(R.id.btnBook);
        txtItemId = findViewById(R.id.item_no);

        a = userTelNo.getText().toString();
        txtItemName.setText(itemName);
        txtItemPrice.setText(itemPrice);
        txtItemDesciption.setText(itemDescription);
        txtItemId.setText(ItemId);

        book.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AddtoCart();

                Intent intent = new Intent(travelItemDescription.this,cartItemList.class);
                intent.putExtra("Tel",userTelNo.getText().toString());
                startActivity(intent);
            }
        });
    }

    public void AddtoCart(){
        table_Item.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                cartItems.setCartItemId(txtItemId.getText().toString().trim());
                cartItems.setCartItemDescription(txtItemDesciption.getText().toString().trim());
                cartItems.setCartItemName(txtItemName.getText().toString().trim());
                cartItems.setCartItemPrice(txtItemPrice.getText().toString().trim());
                cartItems.setCartItemQuantity(quantity.getText().toString().trim());
                cartItems.setCartUserTelNo(userTelNo.getText().toString().trim());
                //cartItems.setCartItembuyDate(itemQuantity.getText().toString().trim());

                table_Item.child(cartItems.getCartUserTelNo()).child(cartItems.getCartItemId()).setValue(cartItems);

                Toast.makeText(getApplicationContext(),"Data Insert successfully",Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
