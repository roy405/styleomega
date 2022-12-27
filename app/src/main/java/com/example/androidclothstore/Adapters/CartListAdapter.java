package com.example.androidclothstore.Adapters;


import android.annotation.SuppressLint;
import android.view.View;
import android.widget.BaseAdapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.androidclothstore.Models.Orders;
import com.example.androidclothstore.Models.Users;
import com.example.androidclothstore.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class CartListAdapter extends BaseAdapter implements View.OnClickListener {

    Context cartContext;
    ArrayList<Orders> cartlist;
    ViewItem viewItem;
    String userEmail, productQuantity, cartProductName;
    Long cartId;

    public CartListAdapter(Context cartContext, ArrayList<Orders> cartlist, String userEmail, String productQuantity) {
        this.cartContext = cartContext;
        this.cartlist = cartlist;
        this.userEmail = userEmail;
        this.productQuantity = productQuantity;

    }

    @Override
    public int getCount()
    {
        return this.cartlist.size();
    }

    @Override
    public Object getItem(int position) {
        return this.cartlist.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup viewGroup) {
        if(convertView == null){

            viewItem = new ViewItem();

            LayoutInflater layoutInflater = (LayoutInflater)this.cartContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(R.layout.custom_cartlayout, null);



            List<Users> users = Users.listAll(Users.class);
            for(Users user: users) {
                if (userEmail.equals(user.getEmail())) {

                    viewItem.cartProductImage = (ImageView) convertView.findViewById(R.id.cartProductImage);
                    // Picasso.get().load(cartlist.get(+ position).getProduct().getScaledImage()).into(viewItem.cartProductImage);


                    viewItem.cartProductName = (TextView) convertView.findViewById(R.id.cartProductName);
                    viewItem.cartProductName.setText(cartlist.get(+ position).getProduct().getName());


                    viewItem.cartProductQuantity = (TextView) convertView.findViewById(R.id.cartProductQuantity);
                    viewItem.cartProductQuantity.setText(productQuantity);


                    viewItem.cartProductPrice = (TextView) convertView.findViewById(R.id.cartProductPrice);
                    viewItem.cartProductPrice.setText(Integer.toString(cartlist.get(+ position).getProduct().getPrice()));


                    viewItem.cartProductClear = (ImageButton) convertView.findViewById(R.id.cartProductClear);
                    viewItem.cartProductClear.setOnClickListener(this);

                    ArrayList<Orders> carts = (ArrayList<Orders>) Orders.listAll(Orders.class);

                }
            }


            convertView.setTag(viewItem);

        }
        else
        {
            viewItem = (CartListAdapter.ViewItem) convertView.getTag();
        }

        viewItem.cartProductImage = (ImageView) convertView.findViewById(R.id.cartProductImage);
        viewItem.cartProductName = (TextView) convertView.findViewById(R.id.cartProductName);
        viewItem.cartProductQuantity = (TextView) convertView.findViewById(R.id.cartProductQuantity);
        viewItem.cartProductPrice = (TextView) convertView.findViewById(R.id.cartProductPrice);
        viewItem.cartProductClear = (ImageButton) convertView.findViewById(R.id.cartProductClear);
        viewItem.cartProductClear.setTag(position);


        return convertView;
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View view) {
        Integer position = (Integer) view.getTag();
        switch(view.getId())
        {
            case R.id.cartProductClear:
                removeProduct();
                break;
        }
    }

    public void removeProduct(){
        cartProductName = viewItem.cartProductName.getText().toString();

        List<Orders> ordertablelist = Orders.listAll(Orders.class);
        for(Orders order: ordertablelist){
            if(cartProductName.equals(order.getProduct().getName())){
                cartId =  order.getId();
            }
        }

        List<Orders> orderlist = Orders.listAll(Orders.class);
        for(Orders order: orderlist){
            if(cartId.equals(order.getId())){
                Orders orders = Orders.findById(Orders.class, cartId);
                orders.delete();
            }

        }
    }

    static class ViewItem{
        ImageView cartProductImage;
        TextView cartProductName;
        TextView cartProductQuantity;
        TextView cartProductPrice;
        ImageButton cartProductClear;
    }
}
