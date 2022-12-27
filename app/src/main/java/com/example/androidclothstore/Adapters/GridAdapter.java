package com.example.androidclothstore.Adapters;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;


import com.example.androidclothstore.Activities.DetailedProduct;
import com.example.androidclothstore.Fragments.ProductsFragment;
import com.example.androidclothstore.Models.Products;
import com.example.androidclothstore.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;


public class GridAdapter extends BaseAdapter implements View.OnClickListener{
    private Context mContext;
    ProductsFragment con;
    private final ArrayList<Products> objects;
    private String logProductCheck;


    public GridAdapter(Context c, List<Products> objects,ProductsFragment con)
    {

        this.objects = (ArrayList<Products>) objects;
        this.mContext = c ;
        this.con=con;
    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return objects.size();
    }

    @Override
    public Object getItem(int position) {

        return this.objects.get(position);
    }

    @Override
    public long getItemId(int position) {

        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent)
    {
        ViewItem viewItem = null;

        if(convertView == null)
        {
            viewItem = new ViewItem();

            LayoutInflater layoutInflater = (LayoutInflater)this.mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(R.layout.custom_adapter, null);


            viewItem.textviewset = (TextView) convertView.findViewById(R.id.productName);
            viewItem.textviewset.setText(objects.get(+ position).getName());
            viewItem.textviewset.setOnClickListener(this);

            viewItem.imageViewset = (ImageView) convertView.findViewById(R.id.productImage);
            Picasso.get().load(objects.get(+ position).getScaledImage()).into(viewItem.imageViewset);


            convertView.setTag(viewItem);

        }
        else
        {
            viewItem = (ViewItem) convertView.getTag();
        }

        viewItem.textviewset = (TextView) convertView.findViewById(R.id.productName);
        viewItem.imageViewset = (ImageView) convertView.findViewById(R.id.productImage);

        viewItem.textviewset.setTag(position);

        return convertView;


    }


    public void onClick(View v) {
        Integer position = (Integer) v.getTag();
        switch (v.getId())
        {
            case R.id.productName:

                int IDlistSize = 20;

                Log.e("click position ", "" +objects.get(+ position).getName());
                Intent intent = new Intent(mContext, DetailedProduct.class);
                intent.putExtra("ProductName",objects.get(+ position).getName());
                mContext.startActivity(intent);
                break;
        }
    }
}

class ViewItem
{
    TextView textviewset;
    ImageView imageViewset;

}
