package com.example.androidclothstore.Fragments;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.TextView;
import androidx.fragment.app.Fragment;

import com.example.androidclothstore.Models.Users;
import com.example.androidclothstore.R;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ProfileFragment extends Fragment {

    //Declaring Variables
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private String mParam1;
    private String mParam2;
    String emailShared;
    SharedPreferences shared;
    private OnFragmentInteractionListener mListener;
    EditText firstNameText, lastNameText, emailText, passwordText, dOBText,shippingAddressText, billingAddressText;
    Button updateBtn;
    //Constructor for fragment
    public ProfileFragment() {

    }


    public ProfileFragment newInstance(String param1, String param2) {
        ProfileFragment fragment = new ProfileFragment();
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

    @Override //inflates the view
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_profile, container, false);
        firstNameText = (EditText)rootView.findViewById(R.id.profileFirstName);
        lastNameText=(EditText)rootView.findViewById(R.id.profileLastName);
        emailText=(EditText)rootView.findViewById(R.id.profileEmail);
        passwordText=(EditText)rootView.findViewById(R.id.profilePassword);
        dOBText=(EditText)rootView.findViewById(R.id.profileDateOfBirth);
        shippingAddressText=(EditText)rootView.findViewById(R.id.profileShippingAddress);
        billingAddressText=(EditText)rootView.findViewById(R.id.profileBillingAddress);

        updateBtn =(Button)rootView.findViewById(R.id.profileButton);

        updateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        shared= requireActivity().getSharedPreferences("Mypref", Context.MODE_PRIVATE);
        emailShared = shared.getString("email", "");
        profilePopulate(emailShared);

        updateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateUser(emailShared);
            }
        });


        return rootView;
    }


    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }



    @Override
    public void onDetach() { //
        super.onDetach();
        mListener = null;
    }


    public interface OnFragmentInteractionListener {

        void onFragmentInteraction(Uri uri);
    }

    public void profilePopulate(String emailShared){ //Populates the profile
        List<Users> users = Users.listAll(Users.class);
        for(Users user : users) {
            if(emailShared.equals(user.getEmail())){

                String firstname=user.getFirstname();
                String lastname=user.getLastname();
                String email=user.getEmail();
                String password=user.getPassword();
                String DOB = user.getDob();
                String shippingAddress = user.getShippingAddress();
                String billingAddress = user.getBillingAddress();
                Log.e("firstname",firstname);
                firstNameText.setText(firstname, TextView.BufferType.EDITABLE);
                lastNameText.setText(lastname, TextView.BufferType.EDITABLE);
                emailText.setText(email, TextView.BufferType.EDITABLE);
                passwordText.setText(password, TextView.BufferType.EDITABLE);
                dOBText.setText(DOB, TextView.BufferType.EDITABLE);
                shippingAddressText.setText(shippingAddress, TextView.BufferType.EDITABLE);
                billingAddressText.setText(billingAddress, TextView.BufferType.EDITABLE);

            }
        }
    }

    public void updateUser(String emailShared){
        List<Users> users = Users.listAll(Users.class);
        for(Users user : users) {
            if(emailShared.equals(user.getEmail())) {
                user.setFirstname(firstNameText.getText().toString());
                user.setLastname(lastNameText.getText().toString());
                user.setEmail(emailText.getText().toString());
                user.setPassword(passwordText.getText().toString());
                user.setDob(dOBText.getText().toString());
                user.setShippingAddress(shippingAddressText.getText().toString());
                user.setBillingAddress(billingAddressText.getText().toString());
                user.save();

            }

        }
    }
}
