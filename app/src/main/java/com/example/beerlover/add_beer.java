package com.example.beerlover;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import java.util.Random;

public class add_beer extends AppCompatActivity {

    TextView name, details ;
    EditText edName, edDetails;
    CheckBox draught;
    Button add;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_beer);
        name =  findViewById(R.id.txtName);
        details = findViewById(R.id.txtDetails);
        edDetails = findViewById(R.id.EdtxtDetails);
        edName = findViewById(R.id.EdtxtName);
        draught = findViewById(R.id.checkBox);
        add = findViewById(R.id.btnAddbeer);
        add.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {

                if(edName.getText().toString().isEmpty() || edDetails.getText().toString().isEmpty() ) {
                    Context context = getApplicationContext();
                    Toast.makeText(context, "Name or Details of beer is empty. Please fill it to continue!", Toast.LENGTH_LONG).show();
                }
                else
                    {
                        Intent add = new Intent(getApplicationContext(), MyListActivity.class);
//                        add.putExtra("name", edName.getText().toString().trim());
//                        add.putExtra("details",edDetails.getText().toString().trim());
                        String d = "bottle";
                        if(draught.isChecked())
                        {
                            d = "draught";
                        //    add.putExtra("draught","draught");
                        }
//                        else
//                            add.putExtra("draught","bottle");

                        MyDBHandler handler = new MyDBHandler(getApplicationContext(),null,null,4);
                        BeerDB b = new BeerDB(code(),edName.getText().toString().trim(),edDetails.getText().toString().trim() ,1,d);
                        handler.addHandler(b);
                        startActivity(add);
                    }
            }

        });

    }
    private String code()
    {
        Random r = new Random();
        Integer n = r.nextInt();
        return n.toString();
    }
}