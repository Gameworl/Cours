package com.example.tddna;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class UserAdapter extends ArrayAdapter<User> {
    public UserAdapter(Context context, List<User> allUsers) {
        super(context, R.layout.list_item, allUsers);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        @SuppressLint("ViewHolder") View rowView = inflater.inflate(R.layout.list_item, parent, false);

        TextView tvName = (TextView) rowView.findViewById(R.id.tvName);
        TextView tvNumber = (TextView) rowView.findViewById(R.id.tvNumber);

        final User user = getItem(position);
        tvName.setText(user.getName());
        tvNumber.setText(user.getNumber());

        return rowView;
    }

}

