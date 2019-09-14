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

import java.util.ArrayList;
import java.util.List;

public class TravelListAdapter extends RecyclerView.Adapter<TravelListAdapter.travelViewHolder> {
    List<TravelItems> travelItemList;
    Context context;


    public TravelListAdapter(Context context) {
        this.travelItemList = new ArrayList<>();
        this.context = context;
    }

    public void travelItemAddAll(List<TravelItems> newTravelItem){
        int initSize = travelItemList.size();
        travelItemList.addAll(newTravelItem);
        notifyItemChanged(initSize,newTravelItem.size());
    }

    public String getLastTravelItemId() {
        return travelItemList.get(travelItemList.size()-1).getTravelItemId();
    }

    @NonNull
    @Override
    public travelViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View TravelItemView = LayoutInflater.from(context).inflate(R.layout.layout_travel_items,parent,false);
        return new travelViewHolder(TravelItemView);
    }

    @Override
    public void onBindViewHolder(@NonNull travelViewHolder holder, int position) {
        holder.travelItemName.setText(travelItemList.get(position).getTravelItemName());
        holder.travelItemDescription.setText(travelItemList.get(position).getTravelItemDescription());
        holder.travelItemPrice.setText(travelItemList.get(position).getTravelItemPrice());
    }

    @Override
    public int getItemCount() {
        return travelItemList.size();
    }

    public class travelViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView travelItemName,travelItemDescription,travelItemPrice;
        public travelViewHolder(@NonNull View itemView) {
            super(itemView);

            travelItemName =(TextView)itemView.findViewById(R.id.Itemname);
            travelItemDescription = (TextView)itemView.findViewById((R.id.Itemdescription));
            travelItemPrice = (TextView)itemView.findViewById((R.id.Itemprice));

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            Toast.makeText(view.getContext(),travelItemList.get(getAdapterPosition()).getTravelItemId(),Toast.LENGTH_SHORT).show();

            String itemName,itemDescription,itemPrice,itemId;
            itemName = travelItemList.get(getAdapterPosition()).getTravelItemName();
            itemPrice = travelItemList.get(getAdapterPosition()).getTravelItemPrice();
            itemDescription = travelItemList.get(getAdapterPosition()).getTravelItemDescription();
            itemId = travelItemList.get(getAdapterPosition()).getTravelItemId();

            Intent intent = new Intent(view.getContext(),travelItemDescription.class);
            intent.putExtra("itemName",itemName);
            intent.putExtra("itemPrice",itemPrice);
            intent.putExtra("itemDescription",itemDescription);
            intent.putExtra("itemId",itemId);
            view.getContext().startActivity(intent);
        }
    }
}
