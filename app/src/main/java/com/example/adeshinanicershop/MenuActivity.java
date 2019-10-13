package com.example.adeshinanicershop;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import com.example.adeshinanicershop.model.CardItem;
import com.example.adeshinanicershop.model.ProductAdapter;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.PersistableBundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class MenuActivity extends AppCompatActivity {
    public static final String EXTRA_MESSAGE = "android.adeshinashop.extra.MESSAGE";
    private RecyclerView mRecyclerView;
    private ProductAdapter mAdapter;
    public LinkedList<CardItem> listOfItems;
    private int itemsInCart;
    private String selectedItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        listOfItems= new LinkedList<CardItem>();
        populateCard();

        mRecyclerView = findViewById(R.id.recyclerview);
        mAdapter = new ProductAdapter(this, listOfItems);

        mRecyclerView.setAdapter(mAdapter);
        // Give the RecyclerView a default layout manager.
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        //Handler for floating action button
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            String [] alertChoiceList = {getString(R.string.express_delivery), getString(R.string.regular_delivery), getString(R.string.no_hurry)};
            @Override
            public void onClick(View view) {
                AlertDialog.Builder myAlertBuilder = new
                        AlertDialog.Builder(MenuActivity.this);
                myAlertBuilder.setTitle("Choose a delivery option");
                myAlertBuilder.setSingleChoiceItems(alertChoiceList, 1, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        selectedItem = Integer.toString(which);
                    }
                });

                // Add the dialog buttons.
                myAlertBuilder.setPositiveButton("OK", new
                        DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                // User clicked OK button.
                                Toast.makeText(getApplicationContext(), "Pressed OK",
                                        Toast.LENGTH_SHORT).show();
                                LinearLayout upperLayout = findViewById(R.id.upper_layout);
                                upperLayout.setVisibility(View.VISIBLE);
                            }
                        });
                myAlertBuilder.setNegativeButton("Cancel", new
                        DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                // User cancelled the dialog.
                                Toast.makeText(getApplicationContext(), "Pressed Cancel",
                                        Toast.LENGTH_SHORT).show();
                            }
                        });

                myAlertBuilder.show();


            }
        });
    }

    //onCreate for menu
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.my_options_menu, menu);
        return true;
    }

    //Menu click handler
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_contact:
                Intent email = new Intent(android.content.Intent.ACTION_SEND);
                email.setType("application/octet-stream");
                startActivity(email);
                return true;
            case R.id.view_cart:
                String message = "Items in cart: " + calculateTotalQuantity() + " Total: " + calculateTotal();
                Toast toast = Toast.makeText(this, message, Toast.LENGTH_SHORT);
                toast.show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    //Method to get the subtotal and quantity before the activity is destroyed
    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        ArrayList<String> quantityAndSubtotal = new ArrayList<>();
        Log.d("Before ", listOfItems.get(0).getQuantity());


        for (int i = 0; i < listOfItems.size(); i++){
            quantityAndSubtotal.add(listOfItems.get(i).getQuantity());
            quantityAndSubtotal.add(listOfItems.get(i).getSubTotal());
        }

        savedInstanceState.putStringArrayList("Array", quantityAndSubtotal);
    }

    //Method to present the saved quantity and subtotal to the activity when the orientation is changed
    @Override
    public void onRestoreInstanceState(final Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        ArrayList<String> receiver = savedInstanceState.getStringArrayList("Array");
        Log.d("Test", receiver.get(0));
        Log.d("Test", receiver.get(1));
        Log.d("Test", receiver.get(2));

        int index = 0;
        for (int i = 0; i < listOfItems.size(); i++){
            listOfItems.get(i).setQuantity(receiver.get(index++));
            listOfItems.get(i).setSubTotal(receiver.get(index++));
        }
    }

    //Method to calculate the total
    private String calculateTotal(){
        Float totalAmount = 0F;
        for (int i = 0; i < listOfItems.size(); i++)
            totalAmount += Float.parseFloat(listOfItems.get(i).getSubTotal());

        return Float.toString(totalAmount);
    }

    //Method to calculate the total quantity bought
    private String calculateTotalQuantity(){
        int totalQuantity = 0;
        for (int i = 0; i < listOfItems.size(); i++)
            totalQuantity += Float.parseFloat(listOfItems.get(i).getQuantity());

        return Integer.toString(totalQuantity);
    }

    //Method to populate the arrayList of product items
    public void populateCard(){
        listOfItems.add(new CardItem(getResources().getString(R.string.description1_text),getResources().getString(R.string.item1_price), R.drawable.cloth, getResources().getString(R.string.item_name_1),getResources().getString(R.string.subTotal_text_1), getResources().getString(R.string.quantity1) ));
        listOfItems.add(new CardItem(getResources().getString(R.string.description2_text),getResources().getString(R.string.item2_price),R.drawable.shirt1,getResources().getString(R.string.item_name_2),getResources().getString(R.string.subTotal_text_2), getResources().getString(R.string.quantity2) ));
        listOfItems.add(new CardItem(getResources().getString(R.string.description3_text),getResources().getString(R.string.item3_price),R.drawable.shirt2,getResources().getString(R.string.item_name_3),getResources().getString(R.string.subTotal_text_3), getResources().getString(R.string.quantity3) ));
        listOfItems.add(new CardItem(getResources().getString(R.string.description4_text),getResources().getString(R.string.item4_price),R.drawable.jeans,getResources().getString(R.string.item_name_4),getResources().getString(R.string.subTotal_text_4), getResources().getString(R.string.quantity4) ));
        listOfItems.add(new CardItem(getResources().getString(R.string.description5_text),getResources().getString(R.string.item5_price), R.drawable.item5, getResources().getString(R.string.item_name_5), getResources().getString(R.string.subTotal_text_5), getResources().getString(R.string.quantity5)));
        listOfItems.add(new CardItem(getResources().getString(R.string.description6_text),getResources().getString(R.string.item6_price), R.drawable.item6, getResources().getString(R.string.item_name_6), getResources().getString(R.string.subTotal_text_6), getResources().getString(R.string.quantity6)));
        listOfItems.add(new CardItem(getResources().getString(R.string.description7_text),getResources().getString(R.string.item7_price), R.drawable.item7, getResources().getString(R.string.item_name_7), getResources().getString(R.string.subTotal_text_7), getResources().getString(R.string.quantity7)));
        listOfItems.add(new CardItem(getResources().getString(R.string.description8_text),getResources().getString(R.string.item8_price), R.drawable.item8, getResources().getString(R.string.item_name_8), getResources().getString(R.string.subTotal_text_8), getResources().getString(R.string.quantity8)));
        listOfItems.add(new CardItem(getResources().getString(R.string.description9_text),getResources().getString(R.string.item9_price), R.drawable.item9, getResources().getString(R.string.item_name_9), getResources().getString(R.string.subTotal_text_9), getResources().getString(R.string.quantity9)));
        listOfItems.add(new CardItem(getResources().getString(R.string.description10_text),getResources().getString(R.string.item10_price), R.drawable.item10, getResources().getString(R.string.item_name_10), getResources().getString(R.string.subTotal_text_10), getResources().getString(R.string.quantity10)));
    }

    //Method to launch checkout activity
    public void checkout(View view) {

        int quantity = Integer.parseInt(calculateTotalQuantity());
        if (quantity != 0){
            String[] myArray = new String[3];
            myArray[0] = Integer.toString(quantity);

            myArray[1] = selectedItem;
            myArray[2] = calculateTotal();


            Intent intent = new Intent(this, CheckoutActivity.class);
            intent.putExtra(EXTRA_MESSAGE, myArray);
            startActivity(intent);
        }
    }

    //Method to cancel
    public void cancel(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
