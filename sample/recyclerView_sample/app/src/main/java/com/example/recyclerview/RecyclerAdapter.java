package com.example.recyclerview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> {
    private ArrayList<RecyclerItem> mData = null;

    public RecyclerAdapter(ArrayList<RecyclerItem> mList) {
        mData = mList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // 뷰 홀더 객체 생성 후, 뷰홀더 객체 생성하여 리턴
        Context context = parent.getContext();
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View view = inflater.inflate(R.layout.recycler_item, parent, false);
        RecyclerAdapter.ViewHolder vh = new RecyclerAdapter.ViewHolder(view);

        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        // 데이터를 뷰홀더에 바인딩 / position에 해당하는 데이터를 뷰홀더의 아이템뷰에 표시

        RecyclerItem item = mData.get(position);

        holder.icon.setImageDrawable(item.getIconDrawable());
        holder.title.setText(item.getTitleStr());
        holder.desc.setText(item.getDescStr());
    }

    @Override
    public int getItemCount() {
        // 전체 아이템 갯수 리턴
        return mData.size();
    }

    //    6-step
    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView icon;
        TextView title;
        TextView desc;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            icon = itemView.findViewById(R.id.icon);
            title = itemView.findViewById(R.id.title);
            desc = itemView.findViewById(R.id.desc);
        }
    }
}
