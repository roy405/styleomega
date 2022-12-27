package com.example.androidclothstore.Fragments;

import androidx.fragment.app.Fragment;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.androidclothstore.Models.CompletedOrders;
import com.example.androidclothstore.R;

import java.util.ArrayList;


public class OrderFragment extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";


    private String mParam1;
    private String mParam2;
    ListView orderListView;
    ArrayList<CompletedOrders> placedOrderList;
    SharedPreferences shared;
    String emailShared;

    private OnFragmentInteractionListener mListener;

    public OrderFragment() {
        // Required empty public constructor
    }



    public static OrderFragment newInstance(String param1, String param2) {
        OrderFragment fragment = new OrderFragment();
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

    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_profile, container, false);

        orderListView = (ListView)rootView.findViewById(R.id.OrderedItemsListView);

        shared= getActivity().getSharedPreferences("Mypref", Context.MODE_PRIVATE);
        emailShared = shared.getString("email", "");

        placedOrderList = (ArrayList<CompletedOrders>) CompletedOrders.listAll(CompletedOrders.class);
        //PlacedOrderAdapter placedOrderAdapter = new PlacedOrderAdapter(placedOrderList, getActivity(), emailShared);
        //orderListView.setAdapter(placedOrderAdapter);

        return rootView;
    }


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
}
