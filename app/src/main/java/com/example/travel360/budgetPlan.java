package com.example.travel360;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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
    EditText editTextTransportation,editTextHotelsandRestaurant,editTextBillsandTickets,editTextFoodandBavarage,editTextOther,tell;
    String Transportation,HotelsandRestaurant,BillsandTickets,FoodandBavarage,TextOther;

    ProgressBar progressBarTransportation, progressBarHotelsandRestaurant,progressBarbillsandTickets,progressBarFoodandBavarage,progressBarOther;
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


        editTextTransportation = findViewById(R.id.budgetEditTextTransportation);
        editTextHotelsandRestaurant = findViewById(R.id.budgetEditTextHotelsandRestaurant);
        editTextBillsandTickets = findViewById(R.id.budgetEditTextBillsandTickets);
        editTextFoodandBavarage = findViewById(R.id.budgetEditTextFoodandBavarage);
        editTextOther = findViewById(R.id.budgetEditTextOther);
        lableBudgetTotal = findViewById(R.id.budgetLabelTotal);
        buttonCalcTotal = findViewById(R.id.budgetCalcButton);
        buttonAdd = findViewById(R.id.budgetAddButton);
        buttonUpdate = findViewById(R.id.budgetUpdateButton);
        buttonDelete = findViewById(R.id.budgetDeleteButton);
        tell = findViewById(R.id.txtUserTelNo);


        progressBarTransportation = findViewById(R.id.budgetProgressBarTransportation);
        progressBarbillsandTickets = findViewById(R.id.budgetProgressBarBillsandTickets);
        progressBarFoodandBavarage =findViewById(R.id.budgetProgressBarFoodandBavarage);
        progressBarHotelsandRestaurant = findViewById(R.id.budgetProgressBarHotelsandRestaurant);
        progressBarOther = findViewById(R.id.budgetProgressBarOther);
    // value = Integer.parseInt(editTextTransportation.getText().toString());

        Transportation = editTextTransportation.getText().toString();
        HotelsandRestaurant = editTextHotelsandRestaurant.getText().toString();
        BillsandTickets = editTextBillsandTickets.getText().toString();
        FoodandBavarage = editTextFoodandBavarage.getText().toString();
        TextOther = editTextOther.getText().toString();

       //Integer total = Integer.parseInt(editTextTransportation.getText().toString()) + Integer.parseInt(editTextHotelsandRestaurant.getText().toString()) + Integer.parseInt(editTextBillsandTickets.getText().toString()) + Integer.parseInt(editTextFoodandBavarage.getText().toString()) + Integer.parseInt(editTextOther.getText().toString());

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

                Integer Trans =Integer.parseInt(editTextTransportation.getText().toString()) /1000;
                Integer Hotel =Integer.parseInt(editTextHotelsandRestaurant.getText().toString())/1000;
                Integer Bills =Integer.parseInt(editTextBillsandTickets.getText().toString())/1000;
                Integer Food =Integer.parseInt(editTextFoodandBavarage.getText().toString())/1000;
                Integer Other =Integer.parseInt(editTextOther.getText().toString())/1000;
                progressBarTransportation.setProgress(Trans);
                progressBarHotelsandRestaurant.setProgress(Hotel);
                progressBarbillsandTickets.setProgress(Bills);
                progressBarHotelsandRestaurant.setProgress(Food);
                progressBarOther.setProgress(Other);

                Integer total = Integer.parseInt(editTextTransportation.getText().toString()) + Integer.parseInt(editTextHotelsandRestaurant.getText().toString()) + Integer.parseInt(editTextBillsandTickets.getText().toString()) + Integer.parseInt(editTextFoodandBavarage.getText().toString()) + Integer.parseInt(editTextOther.getText().toString());

                lableBudgetTotal.setText(total.toString());

                budgetshow();
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
                    budgetManager.setBudgetUserTelNo(tell.getText().toString());
                    budgetManager.setBudgetTotal(lableBudgetTotal.getText().toString());
                    table_Item.child(budgetManager.getBudgetUserTelNo()).setValue(budgetManager);
                }catch (Exception e){
                    Toast.makeText(getApplicationContext(),"Data Insert Successfully",Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    public void budgetshow(){
        DatabaseReference readRef = FirebaseDatabase.getInstance().getReference().child("BudgetManager").child(tell.getText().toString());
        readRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.hasChildren()){
                    editTextTransportation.setText(dataSnapshot.child("budgetTransportation").getValue().toString());
                    editTextHotelsandRestaurant.setText(dataSnapshot.child("budgetHotelsandRestaurant").getValue().toString());
                    editTextBillsandTickets.setText(dataSnapshot.child("budgetBillsandTickets").getValue().toString());
                    editTextFoodandBavarage.setText(dataSnapshot.child("budgetFoodandBavarage").getValue().toString());
                    editTextOther.setText(dataSnapshot.child("budgetOther").getValue().toString());
                    tell.setText(dataSnapshot.child("budgetUserTelNo").getValue().toString());
                    lableBudgetTotal.setText(dataSnapshot.child("budgetTotal").getValue().toString());
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }



    public void budgetdelete(){
        DatabaseReference readRef = FirebaseDatabase.getInstance().getReference().child("BudgetManager");
        readRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.hasChild(tell.getText().toString())){
                    dbRef = FirebaseDatabase.getInstance().getReference().child("BudgetManager").child("0778989890");
                    dbRef.removeValue();


                    Toast.makeText(getApplicationContext(),"Data Delete Successfully",Toast.LENGTH_SHORT).show();
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
                    if (dataSnapshot.hasChild(tell.getText().toString())) {
                        budgetManager.setBudgetTransportation(editTextTransportation.getText().toString().trim());
                        budgetManager.setBudgetHotelsandRestaurant(editTextHotelsandRestaurant.getText().toString().trim());
                        budgetManager.setBudgetBillsandTickets(editTextBillsandTickets.getText().toString());
                        budgetManager.setBudgetFoodandBavarage(editTextFoodandBavarage.getText().toString());
                        budgetManager.setBudgetOther(editTextOther.getText().toString());
                        budgetManager.setBudgetUserTelNo(tell.getText().toString());

                        dbRef = FirebaseDatabase.getInstance().getReference().child("BudgetManager").child(tell.getText().toString());
                        dbRef.setValue(budgetManager);

                    }
                }catch (Exception e){
                    Toast.makeText(getApplicationContext(),"Data Insert Successfully",Toast.LENGTH_SHORT).show();
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
    }

    private boolean validate(){
        String tran = editTextHotelsandRestaurant.getText().toString().trim();
        Pattern price = Pattern.compile("^" + "(?=.*[0-9])" +"$");
        if(tran.isEmpty()){
            editTextHotelsandRestaurant.setError("Filed can't be empty");
            return false;
        }
        else if (!price.matcher(tran).matches()) {
            editTextHotelsandRestaurant.setError("Invalid empty");
            return false;
        }
        else{
            return  true;
        }


    }
}
