package com.example.travel360;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import model.User;

public class signUp extends AppCompatActivity {


    EditText txtfname,txtlname,txtusername,txtcontact,txtpass1,txtpass2;
    Button button1;
    DatabaseReference dbRef;

    User usr;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);





        txtfname = findViewById(R.id.Fnametxt);
        txtlname = findViewById(R.id.Lnametxt);
        txtusername = findViewById(R.id.Usernametxt);
        txtcontact = findViewById(R.id.Contacttxt);
        txtpass1 = findViewById(R.id.Pass1txt);
        txtpass2 = findViewById(R.id.Pass2txt);
        button1 = findViewById(R.id.Create);
        usr = new User();



        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dbRef = FirebaseDatabase.getInstance().getReference().child("User");

                String userFname = txtfname.getText().toString();
                String userLname = txtlname.getText().toString();
                String userUsername = txtusername.getText().toString();
                String userpass1 =  txtpass1.getText().toString();
                String userpass2 =  txtpass2.getText().toString();
                try {


                    usr.setFname(userFname.trim());
                    usr.setLname(userLname.trim());
                    usr.setUsername(userUsername.trim());
                    usr.setContact(Integer.parseInt(txtcontact.getText().toString().trim()));
                    usr.setPass1(userpass1.trim());
                    usr.setPass2(userpass2.trim());

                    dbRef.push().setValue(usr);

                    Toast.makeText(getApplicationContext(),"Welcome to Travel 360!",Toast.LENGTH_SHORT).show();
                    clearControls();

                }
                catch (NumberFormatException ex1){
                    Toast.makeText(getApplicationContext(),"Invalid Contact No!",Toast.LENGTH_SHORT).show();
                }

                }

        });

    }

    private void clearControls(){
        txtfname.setText("");
        txtlname.setText("");
        txtusername.setText("");
        txtcontact.setText("");
        txtpass1.setText("");
        txtpass2.setText("");
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }

    private void FileChooser(){
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent,1);
    }
}
