package com.udinus.project.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.udinus.project.R;
import com.udinus.project.activity.AboutActivity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class AdapterFitur extends ArrayAdapter {


    private String[] Headline;
    private String[] Subhead;
    private Context context;

    public AdapterFitur(@NonNull Context context, String[] headline, String[] subhead) {
        super(context, R.layout.activity_adapter_fitur, headline);

        this.Headline = headline;
        this.Subhead = subhead;
        this.context = context;
    }


    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater layoutInflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.activity_adapter_fitur, null, true);

        TextView headline = view.findViewById(R.id.text_headline);
        TextView subhead = view.findViewById(R.id.text_subhead);


        headline.setText(Headline[position]);
        subhead.setText(Subhead[position]);
        return view;
    }
}