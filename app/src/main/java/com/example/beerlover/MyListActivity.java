package com.example.beerlover;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.List;

public class MyListActivity extends AppCompatActivity {

    ArrayList<BeerDB> beerViewModels;
    EditText desc;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_list);

        beerViewModels  = new ArrayList<>();

        MyDBHandler handler = new MyDBHandler(this,null,null,2);
        beerViewModels = handler.loadHandler();


//        Bundle extras = getIntent().getExtras();
        RecyclerView recyclerView = findViewById(R.id.recyclerViewList);
//        String  desc;
////        if(extras != null) {
////            setBeerViewModels(extras.getString("name"), extras.getString("details"),extras.getString("draught"));
////
////            if(!extras.getString("desc").isEmpty())
////                desc = extras.getString("desc");
////        }
        //ser recyclerview from scan

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

//    public void setBeerViewModels(String name, String details,  String draught)
//    {
//        beerViewModels.add(new BeerDB(name,1,details,draught));
//
//    }

}