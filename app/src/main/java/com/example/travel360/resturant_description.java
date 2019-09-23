package com.example.travel360;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.travel360.model.Restaurant;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class resturant_description extends AppCompatActivity {

    EditText txtdesc;
    Button feedbackbtn;
    DatabaseReference dbRef;
    Restaurant restaurant;

    private void clearControls(){

        txtdesc.setText("");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resturant_description);

        txtdesc = findViewById(R.id.editText);
        feedbackbtn = findViewById(R.id.button11);

        restaurant = new Restaurant();

        feedbackbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dbRef = FirebaseDatabase.getInstance().getReference().child("Restaurants");

                String hoteldesc = txtdesc.getText().toString();

                try {

                    restaurant.sethoteldesc(hoteldesc);

                    dbRef.push().setValue(restaurant);

                    Toast.makeText(getApplicationContext(),"Welcome to Travel 360!",Toast.LENGTH_SHORT).show();
                    clearControls();


                }
                catch (NumberFormatException ex1){
                    Toast.makeText(getApplicationContext(),"Invalid Contact No!",Toast.LENGTH_SHORT).show();
                }
                openProfile();
            }
            private void openProfile(){
                Intent intent = new Intent(resturant_description.this,homePage.class);
                startActivity(intent);
            }
        });
    }
}
