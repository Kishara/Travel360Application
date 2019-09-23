package com.example.travel360;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.travel360.model.Hotel;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ShowDetailsHotels extends AppCompatActivity {

    /*TextView txtdate;
    EditText txtrooms,txtadults,txtchild;
    Button buttonShow,buttonAdd,buttonUpdate,buttondelete;
    DatabaseReference dbRef;

    Hotel hotel = new Hotel();

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    final DatabaseReference table_Item = database.getReference("Hotels");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_details_hotels);

        txtdate = findViewById(R.id.textView18);
        txtrooms = findViewById(R.id.editText3);
        txtadults = findViewById(R.id.editText7);
        txtchild = findViewById(R.id.editText8);
        buttonShow = findViewById(R.id.button14);
        buttonUpdate = findViewById(R.id.button1);
        buttondelete = findViewById(R.id.button15);

        buttonShow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                hotelshow();
            }
        });
        buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addBudget();
            }
        });
        buttonUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                budgetupdate();
            }
        });
        buttondelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                hoteldelete();
            }
        });

    }

    public void addBudget(){
        table_Item.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                hotel.sethoteldate(txtdate.getText().toString().trim());
                hotel.sethotelroom(txtrooms.getText().toString().trim());
                hotel.sethoteladult(txtadults.getText().toString());
                hotel.sethotelchild(txtchild.getText().toString());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
    public void hotelshow(){
        DatabaseReference readRef = FirebaseDatabase.getInstance().getReference().child("travel360-15f07").child("Hotels");
        readRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                try {
                    if (dataSnapshot.hasChildren()) {
                        txtdate.setText(dataSnapshot.child("Date").getValue().toString());
                        txtrooms.setText(dataSnapshot.child("Rooms").getValue().toString());
                        txtadults.setText(dataSnapshot.child("Adults").getValue().toString());
                        txtchild.setText(dataSnapshot.child("Child").getValue().toString());

                        Toast.makeText(getApplicationContext(), "Data Update Successfully", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(getApplicationContext(), "No Source to Display", Toast.LENGTH_SHORT).show();
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

    public void hoteldelete(){
        DatabaseReference delRef = FirebaseDatabase.getInstance().getReference().child("travel360-15f07");
        delRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                try {
                    if (dataSnapshot.hasChild(txtdate.getText().toString())) {

                        dbRef = FirebaseDatabase.getInstance().getReference().child("travel360-15f07").child("Hotels");
                        dbRef.removeValue();

                        Toast.makeText(getApplicationContext(), "Data Delete Successfully", Toast.LENGTH_SHORT).show();

                    } else {
                        Toast.makeText(getApplicationContext(), "No Source delete", Toast.LENGTH_SHORT).show();
                    }
                }catch (Exception e){
                    Toast.makeText(getApplicationContext(),"No Source delete",Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    public void budgetupdate(){
        DatabaseReference readRef = FirebaseDatabase.getInstance().getReference().child("travel360-15f07");
        readRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                try {
                    if (dataSnapshot.hasChild(txtdate.getText().toString())) {
                        hotel.sethoteldate(txtdate.getText().toString().trim());
                        hotel.sethotelroom(txtrooms.getText().toString().trim());
                        hotel.sethoteladult(txtadults.getText().toString());
                        hotel.sethotelchild(txtchild.getText().toString());


                        dbRef = FirebaseDatabase.getInstance().getReference().child("BudgetManager").child(txtdate.getText().toString());
                        dbRef.setValue(hotel);

                        Toast.makeText(getApplicationContext(),"Data update Successfully",Toast.LENGTH_SHORT).show();

                    }else{
                        Toast.makeText(getApplicationContext(),"Invalid Trip Name",Toast.LENGTH_SHORT).show();
                    }
                }catch (Exception e){
                    Toast.makeText(getApplicationContext(),"Invalid Operation",Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }*/
}
