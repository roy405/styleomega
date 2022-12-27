package com.example.androidclothstore.Fragments;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.TextView;

import com.example.androidclothstore.Adapters.GridAdapter;
import com.example.androidclothstore.Models.Products;
import com.example.androidclothstore.Models.Tags;
import com.example.androidclothstore.R;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.orm.SugarRecord;
import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class ProductsFragment extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private String prodName;
    GridView grid;
    TextView productNameTextView;
    ArrayList<Products> products;
    SharedPreferences sharedProducts;
    private String mParam1;
    private String mParam2;
    Context product;
    public ProductsFragment con;
    private OnFragmentInteractionListener mListener;

    public ProductsFragment() {

    }


    public static ProductsFragment newInstance(String param1, String param2) {
        ProductsFragment fragment = new ProductsFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_products, container, false);
        Picasso.setSingletonInstance(new Picasso.Builder(getActivity()).build());
        grid = (GridView) rootView.findViewById(R.id.productsGrid);
        productNameTextView = (TextView) grid.findViewById(R.id.productName);
        product = getActivity();

        ProductsFragment productsFragment = new ProductsFragment();
        products = (ArrayList<Products>) Products.listAll(Products.class);
        if (products.isEmpty()) {
            generateProducts();
            generateTags();



          /*  for(Tags tag: tags) {
                Log.v("productTag", tag.getTagName());
            } */

             /*   } for(Products product : products) {
                    for(String x: product.getTags()) {
                        ProductTags pts = new ProductTags(product,x);
                        pts.save();
                    } */
        }


        GridAdapter gridAdapter = new GridAdapter(getActivity(), products,con);
        grid.setAdapter(gridAdapter);

        return rootView;

    }

//                Intent intent;
//                intent = new Intent(getContext(), DetailedProductsFragment.class);
//                startActivity(intent);


    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }


    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }


    public interface OnFragmentInteractionListener {

        void onFragmentInteraction(Uri uri);
    }



    private String readFromFileProducts() { //Function used to read from Products Json
        String filename = "products";
        StringBuilder sbJsonString = new StringBuilder();
        InputStream is = getResources().openRawResource(R.raw.products);
        int character;
        try {
            while((character = is.read()) !=-1) {
                sbJsonString.append((char) character);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return sbJsonString.toString();
    }

    private String readFromFileTags() { //Function used to read from Tags Json
        StringBuffer sbJsonString = new StringBuffer();
        InputStream is = getResources().openRawResource(R.raw.tags);
        int character;
        try {
            while((character = is.read()) !=-1) {
                sbJsonString.append((char) character);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return sbJsonString.toString();
    }

    public void generateProducts() { //Function used to convert Products Json into objects and save into Database

        Gson gson = new Gson();
        Type listType = new TypeToken<List<Products>>(){}.getType();
        final ArrayList<Products> productsJson = gson.fromJson(readFromFileProducts(), listType);
        SugarRecord.saveInTx(productsJson);

    }

    public void generateTags() { //Function used to convert Tags Json into objects and save into Database

        Gson gson = new Gson();
        Type listType = new TypeToken<List<Tags>>(){}.getType();
        final ArrayList<Tags> tagsJson = gson.fromJson(readFromFileTags(), listType);
        SugarRecord.saveInTx(tagsJson);

    }
}
