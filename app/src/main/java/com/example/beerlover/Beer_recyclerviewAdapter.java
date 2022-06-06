package com.example.beerlover;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class Beer_recyclerviewAdapter extends RecyclerView.Adapter<Beer_recyclerviewAdapter.MyViewHolder> {

    Context context;
    ArrayList<BeerDB> beerViewModels;

    @NonNull
    @Override
    public Beer_recyclerviewAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.recycler_view_row,parent,false);
        return new Beer_recyclerviewAdapter.MyViewHolder(view);
    }
    public Beer_recyclerviewAdapter(Context context, ArrayList<BeerDB> beerViewModels)
    {
        this.context = context;
        this.beerViewModels = beerViewModels;
    }

    @Override
    public void onBindViewHolder(@NonNull Beer_recyclerviewAdapter.MyViewHolder holder, int position) {
        // assign values to the views
        // based on the position of the recycler view
        holder.details.setText(beerViewModels.get(position).getDet());
        holder.name.setText(beerViewModels.get(position).getName());

        if(beerViewModels.get(position).getPic().equals("bottle"))
            holder.pic.setImageResource(R.drawable.bottle);
        else
            holder.pic.setImageResource(R.drawable.images);


    }

    @Override
    public int getItemCount() {
        return beerViewModels.size();
    }

    public static class  MyViewHolder extends  RecyclerView.ViewHolder{

        TextView name, details;
        ImageView pic ;
        public MyViewHolder(@NonNull View itemView){
            super(itemView);
            name = itemView.findViewById(R.id.VtxtBeerName);
            details = itemView.findViewById(R.id.VtextBeerDetails);

            pic = itemView.findViewById(R.id.VimageBeer);

        }
    }

}
