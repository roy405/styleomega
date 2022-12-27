package com.example.androidclothstore.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.androidclothstore.Models.Reviews;
import com.example.androidclothstore.R;

import java.util.ArrayList;

public class ReviewListAdapter extends BaseAdapter{
    private Context reviewContext;
    private ArrayList<Reviews> reviewsList;
    private ViewItem viewItem;

    public ReviewListAdapter(Context reviewContext, ArrayList<Reviews> reviewsList) {
        this.reviewContext = reviewContext;
        this.reviewsList = reviewsList;
    }

    @Override
    public int getCount()
    {
        return this.reviewsList.size();
    }

    @Override
    public Object getItem(int position)
    {
        return this.reviewsList.get(position);
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

            LayoutInflater layoutInflater = (LayoutInflater)this.reviewContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(R.layout.custom_reviewlayout, null);

            viewItem.showUsername = (TextView) convertView.findViewById(R.id.UserNameTexd);
            viewItem.showUsername.setText(reviewsList.get(+ position).getUserName());

            viewItem.showReview = (TextView) convertView.findViewById(R.id.ReviewText);
            viewItem.showReview.setText(reviewsList.get(+ position).getReview());

            viewItem.showDate = (TextView) convertView.findViewById(R.id.DateText);
            viewItem.showDate.setText(reviewsList.get(+ position).getReviewDate());



            convertView.setTag(viewItem);

        }
        else
        {
            viewItem = (ViewItem) convertView.getTag();
        }

        viewItem.showUsername = (TextView) convertView.findViewById(R.id.UserNameTexd);
        viewItem.showReview = (TextView) convertView.findViewById(R.id.ReviewText);
        viewItem.showDate = (TextView) convertView.findViewById(R.id.DateText);



        return convertView;
    }

    class ViewItem{
        TextView showUsername;
        TextView showReview;
        TextView showDate;
    }
}
