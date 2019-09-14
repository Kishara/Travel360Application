package com.example.travel360;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class CartListAdapter extends RecyclerView.Adapter<CartListAdapter.CartViewHolder> {

    List<CartItems> cartList;
    Context context;
    DatabaseReference dbRef;


    public CartListAdapter(Context context) {
        this.cartList =  new ArrayList<>();
        this.context = context;
    }

    public void cartItemsAddAll(List<CartItems> newCartItem){
        int initSize = cartList.size();
        cartList.addAll(newCartItem);
        notifyItemChanged(initSize,newCartItem.size());
    }

    public String getLastCartItemId(){
        return cartList.get(cartList.size()-1).getCartItemId();
    }


    @NonNull
    @Override
    public CartViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(context).inflate(R.layout.layout_cart_items,parent,false);
        return new CartViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull CartViewHolder holder, int position) {
        holder.cartitemName.setText(cartList.get(position).getCartItemName());
        holder.cartitemDescription.setText(cartList.get(position).getCartItemDescription());
        holder.cartitemPrice.setText(cartList.get(position).getCartItemPrice());
    }

    @Override
    public int getItemCount() {
            return cartList.size();
    }

    public class CartViewHolder extends RecyclerView.ViewHolder {
        TextView cartitemName,cartitemDescription,cartitemPrice;
        public CartViewHolder(@NonNull View itemView) {
            super(itemView);

            cartitemName =(TextView)itemView.findViewById(R.id.Itemname);
            cartitemDescription = (TextView)itemView.findViewById((R.id.Itemdescription));
            cartitemPrice = (TextView)itemView.findViewById((R.id.Itemprice));

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String itemName,itemDescription,itemPrice,itemId,userTel,itemQuantity;

                    itemName = cartList.get(getAdapterPosition()).getCartItemName();
                    itemPrice = cartList.get(getAdapterPosition()).getCartItemPrice();
                    itemDescription = cartList.get(getAdapterPosition()).getCartItemDescription();
                    itemId = cartList.get(getAdapterPosition()).getCartItemId();
                    itemQuantity = cartList.get(getAdapterPosition()).getCartItemQuantity();
                    userTel = cartList.get(getAdapterPosition()).getCartUserTelNo();

                    Intent intent = new Intent(view.getContext(),travelItemDescriptionUpdate.class);
                    intent.putExtra("itemName",itemName);
                    intent.putExtra("itemPrice",itemPrice);
                    intent.putExtra("itemQuantity",itemQuantity);
                    intent.putExtra("itemTel",userTel);
                    intent.putExtra("itemDescription",itemDescription);
                    intent.putExtra("itemId",itemId);
                    view.getContext().startActivity(intent);
                }
            });
            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {

                    DatabaseReference delRef = FirebaseDatabase.getInstance().getReference().child("CartItems").child(cartList.get(getAdapterPosition()).getCartUserTelNo());
                    delRef.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            if(dataSnapshot.hasChild(cartList.get(getAdapterPosition()).getCartItemId())){
                                dbRef = FirebaseDatabase.getInstance().getReference().child("CartItems").child(cartList.get(getAdapterPosition()).getCartUserTelNo()).child(cartList.get(getAdapterPosition()).getCartItemId());
                                dbRef.removeValue();

                                Toast.makeText(context.getApplicationContext(),"Data Delete Successfully",Toast.LENGTH_SHORT).show();

                            }
                            else
                            Toast.makeText(context.getApplicationContext(),"No Source to Delete",Toast.LENGTH_SHORT).show();
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });
                    return false;
                }
            });
        }
    }
}
