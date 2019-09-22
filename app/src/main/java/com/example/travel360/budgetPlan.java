package com.example.travel360;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.regex.Pattern;

public class budgetPlan extends AppCompatActivity {
    EditText editTextTransportation,editTextHotelsandRestaurant,editTextBillsandTickets,editTextFoodandBavarage,editTextOther,editTextTripName,editTextSearchField;

    ProgressBar progressBarTransportation, progressBarHotelsandRestaurant,progressBarbillsandTickets,progressBarFoodandBavarage,progressBarOther;

    ImageButton buttonSearch;
    TextView lableBudgetTotal;

    Button buttonCalcTotal, buttonAdd,buttonDelete,buttonUpdate;

    DatabaseReference dbRef;



    BudgetManager budgetManager  = new BudgetManager();

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    final DatabaseReference table_Item = database.getReference("BudgetManager");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_budget_plan);

        //budgetshow();

        editTextTransportation = findViewById(R.id.budgetEditTextTransportation);
        editTextHotelsandRestaurant = findViewById(R.id.budgetEditTextHotelsandRestaurant);
        editTextBillsandTickets = findViewById(R.id.budgetEditTextBillsandTickets);
        editTextFoodandBavarage = findViewById(R.id.budgetEditTextFoodandBavarage);
        editTextOther = findViewById(R.id.budgetEditTextOther);
        lableBudgetTotal = findViewById(R.id.budgetLabelTotal);
        buttonCalcTotal = findViewById(R.id.budgetCalcButton);
        buttonAdd = findViewById(R.id.budgetInsertButton);
        buttonUpdate = findViewById(R.id.budgetUpdateButton);
        buttonDelete = findViewById(R.id.budgetDeleteButton);
        editTextTripName = findViewById(R.id.budgetEditTextTripName);
        buttonSearch = findViewById(R.id.TravelItemsSearch_btn);
        editTextSearchField = findViewById(R.id.travelItemsSearch_field);

        progressBarTransportation = findViewById(R.id.budgetProgressBarTransportation);
        progressBarbillsandTickets = findViewById(R.id.budgetProgressBarBillsandTickets);
        progressBarFoodandBavarage =findViewById(R.id.budgetProgressBarFoodandBavarage);
        progressBarHotelsandRestaurant = findViewById(R.id.budgetProgressBarHotelsandRestaurant);
        progressBarOther = findViewById(R.id.budgetProgressBarOther);

        buttonSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clearControls();
                budgetshow();
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
        buttonDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                budgetdelete();
            }
        });
        buttonCalcTotal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(validate()==true){
                                        Integer Trans = Integer.parseInt(editTextTransportation.getText().toString()) /100;
                                        Integer Hotel = Integer.parseInt(editTextHotelsandRestaurant.getText().toString())/1000;
                                        Integer Bills = Integer.parseInt(editTextBillsandTickets.getText().toString())/1000;
                                        Integer Food = Integer.parseInt(editTextFoodandBavarage.getText().toString())/1000;
                                        Integer Other = Integer.parseInt(editTextOther.getText().toString())/1000;

                                        progressBarTransportation.setProgress(Trans);
                                        progressBarHotelsandRestaurant.setProgress(Hotel);
                                        progressBarbillsandTickets.setProgress(Bills);
                                        progressBarFoodandBavarage.setProgress(Food);
                                        progressBarOther.setProgress(Other);

                                        Integer total = Integer.parseInt(editTextTransportation.getText().toString()) + Integer.parseInt(editTextHotelsandRestaurant.getText().toString()) + Integer.parseInt(editTextBillsandTickets.getText().toString()) + Integer.parseInt(editTextFoodandBavarage.getText().toString()) + Integer.parseInt(editTextOther.getText().toString());

                                        lableBudgetTotal.setText("Rs." + total.toString());


                }
            }
        });
    }

    public void addBudget(){

        table_Item.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

               try {
                    budgetManager.setBudgetTransportation(editTextTransportation.getText().toString().trim());
                    budgetManager.setBudgetHotelsandRestaurant(editTextHotelsandRestaurant.getText().toString().trim());
                    budgetManager.setBudgetBillsandTickets(editTextBillsandTickets.getText().toString());
                    budgetManager.setBudgetFoodandBavarage(editTextFoodandBavarage.getText().toString());
                    budgetManager.setBudgetOther(editTextOther.getText().toString());
                    budgetManager.setBudgetTripName(editTextTripName.getText().toString());
                    budgetManager.setBudgetTotal(lableBudgetTotal.getText().toString());
                    table_Item.child(budgetManager.getBudgetTripName()).setValue(budgetManager);

                    Toast.makeText(getApplicationContext(), "Data Update Successfully", Toast.LENGTH_SHORT).show();

                   }catch (Exception e){
                    Toast.makeText(getApplicationContext(),"Invalid Operation",Toast.LENGTH_SHORT).show();
                }
                //clearControls();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    public void budgetshow(){
        DatabaseReference delRef = FirebaseDatabase.getInstance().getReference().child("BudgetManager").child(editTextSearchField.getText().toString());
        delRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                try {
                    if (dataSnapshot.hasChildren()) {
                        editTextTransportation.setText(dataSnapshot.child("budgetTransportation").getValue().toString());
                        editTextHotelsandRestaurant.setText(dataSnapshot.child("budgetHotelsandRestaurant").getValue().toString());
                        editTextBillsandTickets.setText(dataSnapshot.child("budgetBillsandTickets").getValue().toString());
                        editTextFoodandBavarage.setText(dataSnapshot.child("budgetFoodandBavarage").getValue().toString());
                        editTextOther.setText(dataSnapshot.child("budgetOther").getValue().toString());
                        editTextTripName.setText(dataSnapshot.child("budgetTripName").getValue().toString());
                        lableBudgetTotal.setText(dataSnapshot.child("budgetTotal").getValue().toString());

                        Integer Trans = Integer.parseInt(dataSnapshot.child("budgetTransportation").getValue().toString()) /100;
                        Integer Hotel = Integer.parseInt(dataSnapshot.child("budgetHotelsandRestaurant").getValue().toString())/1000;
                        Integer Bills = Integer.parseInt(dataSnapshot.child("budgetBillsandTickets").getValue().toString())/1000;
                        Integer Food = Integer.parseInt(dataSnapshot.child("budgetFoodandBavarage").getValue().toString())/1000;
                        Integer Other = Integer.parseInt(dataSnapshot.child("budgetOther").getValue().toString())/1000;

                        progressBarTransportation.setProgress(Trans);
                        progressBarHotelsandRestaurant.setProgress(Hotel);
                        progressBarbillsandTickets.setProgress(Bills);
                        progressBarFoodandBavarage.setProgress(Food);
                        progressBarOther.setProgress(Other);

                        Toast.makeText(getApplicationContext(), "Data Update Successfully", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(getApplicationContext(), "Invalid Trip Name", Toast.LENGTH_SHORT).show();
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



    public void budgetdelete(){
        DatabaseReference delRef = FirebaseDatabase.getInstance().getReference().child("BudgetManager");
        delRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                try {
                    if (dataSnapshot.hasChild(editTextTripName.getText().toString())) {

                        dbRef = FirebaseDatabase.getInstance().getReference().child("BudgetManager").child(editTextSearchField.getText().toString());
                        dbRef.removeValue();

                        Toast.makeText(getApplicationContext(), "Data Delete Successfully", Toast.LENGTH_SHORT).show();

                    } else {
                        Toast.makeText(getApplicationContext(), "Invalid Trip Name", Toast.LENGTH_SHORT).show();
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

    public void budgetupdate(){
        DatabaseReference readRef = FirebaseDatabase.getInstance().getReference().child("BudgetManager");
        readRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                try {
                    if (dataSnapshot.hasChild(editTextTripName.getText().toString())) {
                        budgetManager.setBudgetTransportation(editTextTransportation.getText().toString().trim());
                        budgetManager.setBudgetHotelsandRestaurant(editTextHotelsandRestaurant.getText().toString().trim());
                        budgetManager.setBudgetBillsandTickets(editTextBillsandTickets.getText().toString());
                        budgetManager.setBudgetFoodandBavarage(editTextFoodandBavarage.getText().toString());
                        budgetManager.setBudgetOther(editTextOther.getText().toString());
                        budgetManager.setBudgetTripName(editTextTripName.getText().toString());

                        dbRef = FirebaseDatabase.getInstance().getReference().child("BudgetManager").child(editTextTripName.getText().toString());
                        dbRef.setValue(budgetManager);

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
    }

    public void clearControls(){
        editTextTransportation.setText("");
        editTextHotelsandRestaurant.setText("");
        editTextBillsandTickets.setText("");
        editTextFoodandBavarage.setText("");
        editTextOther.setText("");
        editTextTripName.setText("");
    }

    private boolean validate(){
        String tran = editTextTransportation.getText().toString().trim();
        String hotels = editTextHotelsandRestaurant.getText().toString().trim();
        String bills = editTextBillsandTickets.getText().toString().trim();
        String food = editTextFoodandBavarage.getText().toString().trim();
        String other = editTextOther.getText().toString().trim();
        String tripName = editTextTripName.getText().toString().trim();

        if(tran.isEmpty()){
            editTextTransportation.setError("Filed can't be empty");
            return false;
        }
        else if (hotels.isEmpty()) {
            editTextHotelsandRestaurant.setError("Filed can't be empty");
            return false;
        }
        else if (bills.isEmpty()) {
            editTextBillsandTickets.setError("Filed can't be empty");
            return false;
        }
        else if (food.isEmpty()) {
            editTextFoodandBavarage.setError("Filed can't be empty");
            return false;
        }
        else if (other.isEmpty()) {
            editTextOther.setError("Filed can't be empty");
            return false;
        }
        else if (tripName.isEmpty()) {
            editTextTripName.setError("Filed can't be empty");
            return false;
        }
        else{
            return  true;
        }
    }
}
