package com.example.travel360;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class signIn extends AppCompatActivity {

    private TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);


        textView = findViewById(R.id.createacc);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openSignUp();
            }

            private void openSignUp(){
                Intent intent = new Intent(signIn.this,signUp.class);
                startActivity(intent);
            }
        });
    }
}
