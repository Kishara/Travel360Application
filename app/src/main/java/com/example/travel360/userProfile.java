package com.example.travel360;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class userProfile extends AppCompatActivity {

    FirebaseAuth mAuth;
    FirebaseUser currentUser;

    Button editProfileBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);

        mAuth = FirebaseAuth.getInstance();
        currentUser = mAuth.getCurrentUser();

        displayProfile();

        editProfileBtn = findViewById(R.id.editProfile);

        editProfileBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent editProfileActivity = new Intent(getApplicationContext(), editProfile.class);
                startActivity(editProfileActivity);
            }
        });

    }

    public void displayProfile(){

        TextView uName = findViewById(R.id.userName);
        TextView uMail = findViewById(R.id.userMail);
        ImageView uImg = findViewById(R.id.userDp);

        uName.setText(currentUser.getDisplayName());
        uMail.setText(currentUser.getEmail());

        Glide.with(this).load(currentUser.getPhotoUrl()).into(uImg);

    }
}
