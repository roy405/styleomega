package com.example.androidclothstore.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.example.androidclothstore.Adapters.CartListAdapter;
import com.example.androidclothstore.Models.Orders;
import com.example.androidclothstore.R;

import java.util.ArrayList;
import java.util.List;

public class CartActivity extends AppCompatActivity {

    ArrayList<Orders> orderList;
    ListView cartListView;
    Button cartButton;
    SharedPreferences sharedPref;
    String extraUserEmail, productQuantity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        cartListView = (ListView)findViewById(R.id.CartList);
        cartButton = (Button)findViewById(R.id.CartButton);

        cartButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CartActivity.this, CheckoutActivity.class);
                startActivity(intent);
            }
        });

        Intent intent = getIntent();
        productQuantity = intent.getStringExtra("productQuntity");

        sharedPref = getSharedPreferences("Mypref", Context.MODE_PRIVATE);
        extraUserEmail = sharedPref.getString("email","");

        List<Orders> orderTT = Orders.listAll(Orders.class);
        for(Orders order: orderTT){
            Log.e("quant", order.getQuantityAmount());
        }


        orderList = (ArrayList<Orders>) Orders.listAll(Orders.class);
        CartListAdapter cartListAdapter = new CartListAdapter(getApplicationContext(), orderList, extraUserEmail, productQuantity);
        cartListView.setAdapter(cartListAdapter);
    }
}