package com.example.travel360;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class homePage extends AppCompatActivity {

    Button showProfile,hoteldetails;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

        hoteldetails = findViewById(R.id.button12);
        showProfile = findViewById(R.id.viewProfileBtn);

        hoteldetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openProfile();
            }

            private void openProfile(){
                Intent intent = new Intent(homePage.this,ShowDetailsHotels.class);
                startActivity(intent);
            }
        });

        showProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openProfile();
            }
            private void openProfile(){
                Intent intent = new Intent(homePage.this,userProfile.class);
                startActivity(intent);
            }
            
        });
    }
}
