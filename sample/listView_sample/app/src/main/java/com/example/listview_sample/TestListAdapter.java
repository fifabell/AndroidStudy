package com.example.listview_sample;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class TestListAdapter extends BaseAdapter {
    // BaseAdapter == 내장함수

    private LinearLayout layout_base;
    private TextView itemName;
    Callback callback;

    Context context;
    ArrayList<TestItem> itemArrayList;

    public TestListAdapter(Context context, ArrayList<TestItem> itemArrayList, Callback callback) {
        this.context = context;
        this.itemArrayList = itemArrayList;
        this.callback = callback;
    }

    @Override
    public int getCount() {
        return itemArrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return itemArrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        convertView = LayoutInflater.from(context).inflate(R.layout.custom_list_item, null);
        layout_base = convertView.findViewById(R.id.layout_item);

        if(position ==0) {
            layout_base.setBackgroundColor(context.getResources().getColor(R.color.colorPrimary));
        } else if (position%2 == 0) {
            layout_base.setBackgroundColor(context.getResources().getColor(R.color.skyBlue));
        } else {
            layout_base.setBackgroundColor(context.getResources().getColor(R.color.grey));
        }

        itemName = convertView.findViewById(R.id.item_name);

        itemName.setText(itemArrayList.get(position).getItemName());

        /*
        // 1번 방법 )
        itemName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context, itemArrayList.get(position).getItemName(),Toast.LENGTH_SHORT).show();
            }
        });

        */

        // 2번 방법 )
        itemName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (position > 0) {
                    callback.run(itemArrayList.get(position).getItemName());
                }

            }
        });



        return convertView;
    }
}
