package com.example.travel360;

import android.os.Bundle;
import android.text.TextUtils;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class travelItemList extends AppCompatActivity {

    RecyclerView recyclerView;
    ImageButton search;
    EditText searchtxt;
    final int ITEM_LOAD_COUNT = 21;
    int total_item = 0,last_visible_item;
    TravelListAdapter travelListAdapter;
    boolean isLoading = false,isMaxData = false;

    String last_node = "",last_key="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_travel_item_list);

        searchtxt =findViewById(R.id.travelItemsSearch_field);
        recyclerView = findViewById(R.id.TravelItemsRecycler_view);

        getLastKeyFromFirebase();

        final LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(),layoutManager.getOrientation());
        recyclerView.addItemDecoration(dividerItemDecoration);

        travelListAdapter = new TravelListAdapter(this);
        recyclerView.setAdapter(travelListAdapter);

        getUsers();
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                total_item =layoutManager.getItemCount();
                last_visible_item =layoutManager.findLastCompletelyVisibleItemPosition();


                getUsers();
                isLoading=true;
            }
        });

    }
    private void getUsers() {
        if (!isMaxData) {
            Query query = null;
            if (TextUtils.isEmpty(last_node))
                query = FirebaseDatabase.getInstance().getReference()
                        .child("TravelItems")
                        .orderByKey()
                        .limitToFirst(ITEM_LOAD_COUNT);

            else
                query = FirebaseDatabase.getInstance().getReference()
                        .child("TravelItems")
                        .orderByKey()
                        .startAt(last_node)
                        .limitToFirst(ITEM_LOAD_COUNT);

            query.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    if (dataSnapshot.hasChildren()) {
                        List<TravelItems> newItems = new ArrayList<>();
                        for (DataSnapshot itemsnapShot : dataSnapshot.getChildren()) {
                            newItems.add(itemsnapShot.getValue(TravelItems.class));
                        }
                        last_node = newItems.get(newItems.size() - 1).getTravelItemId();

                        if (!last_node.equals(last_key))
                            newItems.remove(newItems.size() - 1);
                        else
                            last_node = "end";

                        travelListAdapter.travelItemAddAll(newItems);
                        isLoading = false;
                    } else {
                        isLoading = false;
                        isMaxData = true;
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {
                    isLoading = false;
                }
            });

        }
    }


    private void getLastKeyFromFirebase() {

        Query getLastKey = FirebaseDatabase.getInstance().getReference()
                .child("TravelItems")
                .orderByKey()
                .limitToLast(1);

        getLastKey.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot lastkey : dataSnapshot.getChildren())
                    last_key = lastkey.getKey();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(travelItemList.this,"Cannot get last key",Toast.LENGTH_SHORT).show();
            }
        });
    }
}
