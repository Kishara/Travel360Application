package com.example.travel360;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.travel360.model.Vehicle;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class vehicleDetails extends AppCompatActivity {

    EditText txtvehname,txtvehaddress,txtvehcontactnumber,txtvehrentid;
    Button bookVehiclebtn;
    DatabaseReference dbRef;
    Vehicle vehicle;


    private void clearControls(){
        txtvehname.setText("");
        txtvehaddress.setText("");
        txtvehcontactnumber.setText("");
        txtvehrentid.setText("");

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vehicle_details);

        txtvehname = findViewById(R.id.detailsName);
        txtvehaddress = findViewById(R.id.detailsAddress);
        txtvehcontactnumber = findViewById(R.id.detailsContactnumber);
        txtvehrentid = findViewById(R.id.detailsRentid);
        bookVehiclebtn = findViewById(R.id.detailsBookvehicleBtn);

        vehicle = new Vehicle();

        bookVehiclebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dbRef = FirebaseDatabase.getInstance().getReference().child("Vehicale");

                String vehicalvehname = txtvehname.getText().toString();
                String vehicalvehaddres = txtvehaddress.getText().toString();
                String vehicalvehrentid = txtvehrentid.getText().toString();

                try {
                    vehicle.setNamere(vehicalvehname);
                    vehicle.setAddressre(vehicalvehaddres);
                    vehicle.setCtnumber(Integer.parseInt(txtvehcontactnumber.getText().toString().trim()));
                    vehicle.setRentId(vehicalvehrentid);

                    dbRef.push().setValue(vehicle);

                    Toast.makeText(getApplicationContext(),"Welcome to Travel 360!",Toast.LENGTH_SHORT).show();
                    clearControls();


                }
                catch (NumberFormatException ex1){
                    Toast.makeText(getApplicationContext(),"Invalid Contact No!",Toast.LENGTH_SHORT).show();
                }
                //openProfile();
            }
           /* private void openProfile(){
                Intent intent = new Intent(vehicleDetails.this,homePage.class);
                startActivity(intent);
            }*/


        });
    }









}



