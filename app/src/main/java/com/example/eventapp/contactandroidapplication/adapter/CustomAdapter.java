package com.example.eventapp.contactandroidapplication.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.eventapp.contactandroidapplication.R;

import java.util.ArrayList;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.MyViewHolder> {

private Context mContext;
private ArrayList<String> nameList,numberList;

public class MyViewHolder extends RecyclerView.ViewHolder {
    public TextView title, count;
    public ImageView thumbnail;
    public Button deleteBtn, favBtn;


    public MyViewHolder(View view) {
        super(view);
        title = (TextView) view.findViewById(R.id.name);
        count = (TextView) view.findViewById(R.id.number);
        deleteBtn=(Button) view.findViewById(R.id.delete);
        favBtn=(Button) view.findViewById(R.id.fav);

    }
}


    public CustomAdapter(ArrayList<String> numberarray, ArrayList<String> namearray, Context context) {
        this.mContext = mContext;
        this.numberList = numberarray;
        this.nameList = namearray;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.contact_list_itemview, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {

        String name = nameList.get(position);
        String number = numberList.get(position);
        holder.title.setText(name);
        holder.count.setText(number);

        holder.deleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                deleteItem(position);
            }
        });

        holder.favBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(mContext, "This Contact is Added as Favorites", Toast.LENGTH_SHORT).show();
            }
        });

    }

    void deleteItem(int index) {
        nameList.remove(index);
        numberList.remove(index);
        notifyItemRemoved(index);
    }



    @Override
    public int getItemCount() {
        return nameList.size();
    }
}