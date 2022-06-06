package com.example.beerlover;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class MyListActivity extends AppCompatActivity {

    ArrayList<BeerViewModel> beerViewModels;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_list);

        beerViewModels  = new ArrayList<>();
        //fortoma apo vasi

        Bundle extras = getIntent().getExtras();
        RecyclerView recyclerView = findViewById(R.id.recyclerViewList);

        if(extras != null)
            setBeerViewModels(extras.getString("name"),extras.getString("details"));
        //ser recyclerview from scan
        System.out.println("on myList");
        if(!beerViewModels.isEmpty()) {
            Beer_recyclerviewAdapter adapter = new Beer_recyclerviewAdapter(this,beerViewModels);
            recyclerView.setAdapter(adapter);
            recyclerView.setLayoutManager(new LinearLayoutManager(this));
        }


    }
    public void onBackPressed()
    {
        Log.d("CDA", "onBackPressed Called");
        Intent i = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(i);
    }

    public void setBeerViewModels(String name, String details)
    {
        beerViewModels.add(new BeerViewModel(name,details));

    }
}