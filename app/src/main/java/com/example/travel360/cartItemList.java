package com.example.travel360;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
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

public class cartItemList extends AppCompatActivity {
    String itemName,itemDescription,itemPrice,ItemId,UserTel;
    RecyclerView recyclerView;

    final int ITEM_LOAD_COUNT = 21;
    int total_item = 0,last_visible_item;
    CartListAdapter adapter;
    boolean isLoading = false,isMaxData = false;

    String last_node = "",last_key="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart_item_list);
        recyclerView = findViewById(R.id.TravelItemsRecycler_view);

        final Intent i =getIntent();
        itemName= i.getStringExtra("itemName");
        itemPrice= i.getStringExtra("itemPrice");
        itemDescription= i.getStringExtra("itemDescription");
        ItemId= i.getStringExtra("itemId");

        UserTel = i.getStringExtra("Tel");




        getLastKeyFromFirebase();

        final LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(),layoutManager.getOrientation());
        recyclerView.addItemDecoration(dividerItemDecoration);

        adapter = new CartListAdapter(this);
        recyclerView.setAdapter(adapter);

        getCatItems();

        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                total_item =layoutManager.getItemCount();
                last_visible_item =layoutManager.findLastCompletelyVisibleItemPosition();


                getCatItems();
                isLoading=true;
            }
        });
    }
    void getCatItems() {
        if (!isMaxData) {
            Query query = null;
            if (TextUtils.isEmpty(last_node))
                query = FirebaseDatabase.getInstance().getReference()
                        .child("CartItems").child(UserTel)
                        .orderByKey()
                        .limitToFirst(ITEM_LOAD_COUNT);

            else
                query = FirebaseDatabase.getInstance().getReference()
                        .child("CartItems").child(UserTel)
                        .orderByKey()
                        .startAt(last_node)
                        .limitToFirst(ITEM_LOAD_COUNT);

            query.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    if (dataSnapshot.hasChildren()) {
                        List<CartItems> cartItems = new ArrayList<>();
                        for (DataSnapshot itemsnapShot : dataSnapshot.getChildren()) {
                            cartItems.add(itemsnapShot.getValue(CartItems.class));
                        }
                        last_node = cartItems.get(cartItems.size() - 1).getCartItemId();

                        if (!last_node.equals(last_key))
                            cartItems.remove(cartItems.size() - 1);
                        else
                            last_node = "end";

                        adapter.cartItemsAddAll(cartItems);
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
                .child("CartItems").child(UserTel)
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
                Toast.makeText(cartItemList.this,"Cannot get last key",Toast.LENGTH_SHORT).show();
            }
        });
    }
}
