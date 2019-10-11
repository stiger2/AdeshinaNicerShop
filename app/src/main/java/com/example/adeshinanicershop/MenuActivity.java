package com.example.adeshinanicershop;

import android.os.Bundle;

import com.example.adeshinanicershop.model.CardItem;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;

import java.util.ArrayList;
import java.util.List;

public class MenuActivity extends AppCompatActivity {
    public static final String EXTRA_MESSAGE = "android.adeshinashop.extra.MESSAGE";
    ArrayList<CardItem> listOfItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        listOfItems= new ArrayList<CardItem>();
        populateCard();

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    public void populateCard(){
        listOfItems.add(new CardItem(getResources().getString(R.string.description1_text),getResources().getString(R.string.item1_price), R.drawable.cloth, getResources().getString(R.string.item_name_1),getResources().getString(R.string.subTotal_text_1), getResources().getString(R.string.quantity1) ));
        listOfItems.add(new CardItem(getResources().getString(R.string.description2_text),getResources().getString(R.string.item2_price),R.drawable.shirt1,getResources().getString(R.string.item_name_2),getResources().getString(R.string.subTotal_text_2), getResources().getString(R.string.quantity2) ));
        listOfItems.add(new CardItem(getResources().getString(R.string.description3_text),getResources().getString(R.string.item3_price),R.drawable.shirt2,getResources().getString(R.string.item_name_3),getResources().getString(R.string.subTotal_text_3), getResources().getString(R.string.quantity3) ));
        listOfItems.add(new CardItem(getResources().getString(R.string.description4_text),getResources().getString(R.string.item4_price),R.drawable.jeans,getResources().getString(R.string.item_name_4),getResources().getString(R.string.subTotal_text_4), getResources().getString(R.string.quantity4) ));
    }

}
