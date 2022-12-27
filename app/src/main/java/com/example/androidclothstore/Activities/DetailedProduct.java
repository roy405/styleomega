package com.example.androidclothstore.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.androidclothstore.Adapters.ReviewListAdapter;
import com.example.androidclothstore.Models.Products;
import com.example.androidclothstore.Models.Reviews;
import com.example.androidclothstore.Models.Users;
import com.example.androidclothstore.R;
import com.squareup.picasso.Picasso;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DetailedProduct extends AppCompatActivity {

    TextView productNameText, productAmountText, productPriceText, productDescriptionText;
    ImageView DetailedProductimage;
    ImageButton shareButton;
    Button AddToCart, PostReview;
    Spinner QuantitySpinner, ColorSpinner, SizeSpinner;
    EditText ReviewEditText;
    ListView ReviewListView;
    String extraString, extraUserEmail, userNameListed, productNameListed, dateToStr;
    Reviews review;
    SharedPreferences sharedPref;
    Context context;
    ArrayList<Reviews> reviewList;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailed_product);

        productNameText = (TextView) findViewById(R.id.detailProductName);
        productAmountText = (TextView) findViewById(R.id.DetailedProductAmount);
        productPriceText = (TextView) findViewById(R.id.DetailedProductPrice);
        productDescriptionText = (TextView) findViewById(R.id.DetailedProductDesc);

        DetailedProductimage = (ImageView) findViewById(R.id.detailProductImage);

        shareButton = (ImageButton) findViewById(R.id.DetailShared);

        QuantitySpinner = (Spinner) findViewById(R.id.DetailedQuantity);
        ColorSpinner = (Spinner) findViewById(R.id.DetailedColor);
        SizeSpinner = (Spinner) findViewById(R.id.DetailedSize);

        AddToCart = (Button) findViewById(R.id.AddToCart);
        PostReview = (Button) findViewById(R.id.AddComment);

        ReviewEditText = (EditText) findViewById(R.id.CustomerReviewEditText);

        ReviewListView = (ListView) findViewById(R.id.CustomerReviewListView);

        Intent intent = getIntent();
        extraString = intent.getStringExtra("ProductName");

        sharedPref = getSharedPreferences("Mypref", Context.MODE_PRIVATE);
        extraUserEmail = sharedPref.getString("email","");



        productInfo(extraString);

        shareButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view) {
                shareMethod();
            }
        });

        AddToCart.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view) {

                Intent cartIntent = new Intent(DetailedProduct.this, CartActivity.class);
                startActivity(cartIntent);
            }
        });

        PostReview.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view) {
                ReviewMethod(extraString,extraUserEmail);
                Toast.makeText(getApplicationContext(),"Review Posted!" , Toast.LENGTH_LONG).show();

            }
        });

        reviewList = (ArrayList<Reviews>) Reviews.listAll(Reviews.class);
        ReviewListAdapter reviewListAdapter = new ReviewListAdapter(getApplicationContext(), reviewList);
        ReviewListView.setAdapter(reviewListAdapter);
    }


    public void productInfo(String extraString) {
        List<Products> products = Products.listAll(Products.class);
        for (Products product : products) {
            if (extraString.equals(product.getName())) {
                String productName = product.getName();
                String productAmount = Integer.toString(product.getQuantity());
                String productPrice = Integer.toString(product.getPrice());
                String productDescription = product.getLongDescription();

                productNameText.setText(productName);
                productAmountText.setText(productAmount);
                productPriceText.setText(productPrice);
                productDescriptionText.setText(productDescription);
                Picasso.get().load(product.getFullImage()).into(DetailedProductimage);
            }

        }
    }

    public void shareMethod(){

        Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
        sharingIntent.setType("text/plain");
        String shareBody = "Here is the share content body";
        sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "Subject Here");
        sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, shareBody);
        startActivity(Intent.createChooser(sharingIntent, "Share via"));
    }

    public void ReviewMethod(String extraString,String extraUserEmail){
        List<Users> users = Users.listAll(Users.class);
        for(Users user : users) {
            if(extraUserEmail.equals(user.getEmail())){
                userNameListed = user.getFirstname();
            }

        }
        List<Products> products =  Products.listAll(Products.class);
        for(Products product: products){
            if(extraString.equals(product.getName())){
                productNameListed = product.getName();
            }

        }

        Date today = new Date();
        SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss a");
        dateToStr = format.format(today);


        review = new Reviews(ReviewEditText.getText().toString(), userNameListed, productNameListed, dateToStr );
        review.save();

    }
}
