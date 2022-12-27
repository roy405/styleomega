package com.example.androidclothstore.Adapters;

import android.widget.BaseAdapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.androidclothstore.Models.Orders;
import com.example.androidclothstore.Models.Users;
import com.example.androidclothstore.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class CheckoutListAdapter extends BaseAdapter {

    Context checkoutContext;
    ArrayList<Orders> orderList;
    ViewItem viewItem;
    String userEmail;

    public CheckoutListAdapter(Context checkoutContext, ArrayList<Orders> orderList, String userEmail) {
        this.checkoutContext = checkoutContext;
        this.orderList = orderList;
        this.userEmail = userEmail;
    }

    @Override
    public int getCount() {
        return this.orderList.size();
    }

    @Override
    public Object getItem(int position) {
        return this.orderList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup viewGroup) {
        if(convertView == null){
            viewItem = new ViewItem();

            LayoutInflater layoutInflater = (LayoutInflater)this.checkoutContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(R.layout.custom_checkoutlayout, null);

            List<Users> users = Users.listAll(Users.class);
            for(Users user: users) {
                if (userEmail.equals(user.getEmail())) {

                    viewItem.checkoutProductImage = (ImageView) convertView.findViewById(R.id.checkoutProductImage);
                    // Picasso.get().load(orderList.get(+ position).getProduct().getScaledImage()).into(viewItem.checkoutProductImage);

                    viewItem.checkoutProductName = (TextView) convertView.findViewById(R.id.checkoutProductName);
                    viewItem.checkoutProductName.setText(orderList.get(+ position).getProduct().getName());

                    viewItem.checkoutProductQuantity = (TextView) convertView.findViewById(R.id.checkoutProductQuantity);
                    viewItem.checkoutProductQuantity.setText(orderList.get(+ position).getQuantityAmount());

                    viewItem.checkoutProductPrice = (TextView) convertView.findViewById(R.id.checkoutProductPrice);
                    viewItem.checkoutProductPrice.setText(Integer.toString(orderList.get(+ position).getProduct().getPrice()));
                }
            }
            convertView.setTag(viewItem);

        }
        else{
            viewItem = (CheckoutListAdapter.ViewItem) convertView.getTag();
        }

        viewItem.checkoutProductImage = (ImageView) convertView.findViewById(R.id.checkoutProductImage);
        viewItem.checkoutProductName = (TextView) convertView.findViewById(R.id.checkoutProductName);
        viewItem.checkoutProductQuantity = (TextView) convertView.findViewById(R.id.checkoutProductQuantity);
        viewItem.checkoutProductPrice = (TextView) convertView.findViewById(R.id.checkoutProductPrice);
        return convertView;

    }

    static class ViewItem{
        ImageView checkoutProductImage;
        TextView checkoutProductName, checkoutProductQuantity, checkoutProductPrice;
    }
}
