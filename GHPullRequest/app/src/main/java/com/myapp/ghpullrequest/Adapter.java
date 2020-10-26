package com.myapp.ghpullrequest;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


public class Adapter extends RecyclerView.Adapter<Adapter.MyViewHolder>{



    class MyViewHolder extends RecyclerView.ViewHolder {
        TextView textView, textView2, textView3, textView4;

        MyViewHolder(View view) {
            super(view);
            textView = view.findViewById(R.id.title);
            textView2 = view.findViewById(R.id.number);
            textView3= view.findViewById(R.id.created);
            textView4 = view.findViewById(R.id.status);
        }
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.layout_items, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        holder.textView.setText("PR Title: "+Constant.arrayList.get(position).getTitle());
        holder.textView2.setText("PR Number: #"+Constant.arrayList.get(position).getNumber());
        holder.textView3.setText("Date Created: "+Constant.arrayList.get(position).getCreated());
        holder.textView4.setText("Status: "+Constant.arrayList.get(position).getStatus());



    }

    @Override
    public long getItemId(int id) {
        return id;
    }

    @Override
    public int getItemCount() {
        return Constant.arrayList.size();
    }
}
