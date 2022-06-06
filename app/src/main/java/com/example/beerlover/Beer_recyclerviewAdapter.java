package com.example.beerlover;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class Beer_recyclerviewAdapter extends RecyclerView.Adapter<Beer_recyclerviewAdapter.MyViewHolder> {

    Context context;
    ArrayList<BeerViewModel> beerViewModels;

    @NonNull
    @Override
    public Beer_recyclerviewAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.recycler_view_row,parent,false);
        return new Beer_recyclerviewAdapter.MyViewHolder(view);
    }
    public Beer_recyclerviewAdapter(Context context, ArrayList<BeerViewModel> beerViewModels)
    {
        this.context = context;
        this.beerViewModels = beerViewModels;
    }

    @Override
    public void onBindViewHolder(@NonNull Beer_recyclerviewAdapter.MyViewHolder holder, int position) {
        // assign values to the views
        // based on the position of the recycler view
        System.out.println(beerViewModels.get(position).getBeerName() + " keno " + beerViewModels.get(position).getBeerDetails());
        holder.details.setText(beerViewModels.get(position).getBeerDetails());
        holder.name.setText(beerViewModels.get(position).getBeerName());


    }

    @Override
    public int getItemCount() {
        return beerViewModels.size();
    }

    public static class  MyViewHolder extends  RecyclerView.ViewHolder{

        TextView name, details;
        public MyViewHolder(@NonNull View itemView){
            super(itemView);
            name = itemView.findViewById(R.id.VtxtBeerName);
            details = itemView.findViewById(R.id.VtextBeerDetails);
        }
    }

}
