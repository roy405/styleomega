package com.example.androidclothstore.Adapters;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.androidclothstore.Activities.InvoiceActivity;
import com.example.androidclothstore.Models.Payments;
import com.example.androidclothstore.R;

import java.util.ArrayList;

public class PaymentListAdapter extends BaseAdapter implements View.OnClickListener {

    Context paymentContext;
    ArrayList<Payments> paymentslist;
    ViewItem viewItem;
    String paymentTransferText, totalText;

    public PaymentListAdapter(Context paymentContext, ArrayList<Payments> paymentslist, String totalText) {
        this.paymentContext = paymentContext;
        this.paymentslist = paymentslist;
        this.totalText = totalText;
    }

    @Override
    public int getCount()
    {
        return this.paymentslist.size();
    }

    @Override
    public Object getItem(int position)
    {
        return this.paymentslist.get(position);
    }

    @Override
    public long getItemId(int position)
    {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup viewGroup)
    {
        if(convertView == null){

            viewItem = new ViewItem();

            LayoutInflater layoutInflater = (LayoutInflater)this.paymentContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(R.layout.custom_paymentlayout, null);

            viewItem.paymentText = (TextView) convertView.findViewById(R.id.PaymentTextView);
            viewItem.paymentText.setText(paymentslist.get(+ position).getName());
            viewItem.paymentText.setOnClickListener(this);

            convertView.setTag(viewItem);

        }
        else
        {
            viewItem = (ViewItem) convertView.getTag();
        }

        viewItem.paymentText = (TextView) convertView.findViewById(R.id.PaymentTextView);
        viewItem.paymentText.setText(paymentslist.get(+ position).getName());
        viewItem.paymentText.setTag(position);


        return convertView;
    }

    @Override
    public void onClick(View view) {
        Integer position = (Integer) view.getTag();

        Log.e("LogValue", String.valueOf(position));
        switch (view.getId())
        {
            case R.id.PaymentTextView:


                Intent intent = new Intent(paymentContext, InvoiceActivity.class);
                intent.putExtra("PaymentName", paymentslist.get(+ position).getName());
                intent.putExtra("sendTotalText", totalText);
                paymentContext.startActivity(intent);
                break;
        }
    }

    static class ViewItem {
        TextView paymentText;

    }
}
