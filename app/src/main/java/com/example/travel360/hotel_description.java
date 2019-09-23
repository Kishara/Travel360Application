package com.example.travel360;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.travel360.model.Hotel;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Calendar;

public class hotel_description extends AppCompatActivity implements DatePickerDialog.OnDateSetListener{

    private TextView dateText;

    TextView txtdate;
    EditText txtrooms,txtadults,txtchild;
    Button bookroombtn;
    DatabaseReference dbRef;
    Hotel hotel;

    private void clearControls(){
        txtdate.setText("");
        txtrooms.setText("");
        txtadults.setText("");
        txtchild.setText("");

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hotel_description);

        dateText = findViewById(R.id.textView18);
        txtdate = findViewById(R.id.textView18);
        txtrooms = findViewById(R.id.editText3);
        txtadults = findViewById(R.id.editText7);
        txtchild = findViewById(R.id.editText8);
        bookroombtn = findViewById(R.id.button);

        hotel = new Hotel();

        bookroombtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dbRef = FirebaseDatabase.getInstance().getReference().child("Hotels");

                String hoteldate = txtdate.getText().toString();
                String hotelroom = txtrooms.getText().toString();
                String hoteladult = txtadults.getText().toString();
                String hotelchild = txtchild.getText().toString();

                try {
                    hotel.sethoteldate(hoteldate);
                    hotel.sethotelroom(hotelroom);
                    hotel.sethoteladult(hoteladult);
                    hotel.sethotelchild(hotelchild);

                    dbRef.push().setValue(hotel);

                    Toast.makeText(getApplicationContext(),"Welcome to Travel 360!",Toast.LENGTH_SHORT).show();
                    clearControls();


                }
                catch (NumberFormatException ex1){
                    Toast.makeText(getApplicationContext(),"Invalid Contact No!",Toast.LENGTH_SHORT).show();
                }
                openProfile();
            }
            private void openProfile(){
                Intent intent = new Intent(hotel_description.this,homePage.class);
                startActivity(intent);
            }
        });

        findViewById(R.id.button10).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDatePickerDailog();
            }
        });
    }

    private void showDatePickerDailog(){
        DatePickerDialog datePickerDialog = new DatePickerDialog(
                this,
                this,
                Calendar.getInstance().get(Calendar.YEAR),
                Calendar.getInstance().get(Calendar.MONTH),
                Calendar.getInstance().get(Calendar.DAY_OF_MONTH)
        );
        datePickerDialog.show();
    }

    @Override
    public void onDateSet(DatePicker datePicker, int year, int month, int dayOfMonth) {
        String date = "month/day/year: " + month + "/" + dayOfMonth + "/" + year;
        dateText.setText(date);
    }
}
