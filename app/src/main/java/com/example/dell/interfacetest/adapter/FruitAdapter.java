package com.example.dell.interfacetest.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Point;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.dell.interfacetest.R;
import com.example.dell.interfacetest.activitys.FruitActivity;
import com.example.dell.interfacetest.datas.Fruit;

import java.util.List;

public class FruitAdapter extends RecyclerView.Adapter<FruitAdapter.ViewHolder> {
    private Context mContext;
    private List<Fruit> mFruits;

    public FruitAdapter(List<Fruit> fruits) {
        mFruits = fruits;
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        CardView mCardView;
        ImageView mImageView;
        TextView fruitName;

        public ViewHolder(@NonNull View itemView) {
            super (itemView);
            mCardView = (CardView) itemView;
            mImageView = itemView.findViewById (R.id.fruit_iamge);
            fruitName = itemView.findViewById (R.id.fruit_name);

        }
    }

    public ViewHolder onCreateViewHolder(ViewGroup partent, int viewType) {
        if (mContext == null) {
            mContext = partent.getContext ();
        }

        View view = LayoutInflater.from (mContext).inflate (R.layout.fruit_item, partent, false);

        final ViewHolder holder = new ViewHolder (view);

        holder.mCardView.setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick(View view) {
                int position = holder.getAdapterPosition ();
                Fruit fruit = mFruits.get (position);
                Intent intent = new Intent (mContext, FruitActivity.class);
                intent.putExtra (FruitActivity.FRUIT_NAME, fruit.getName ());
                intent.putExtra (FruitActivity.FRUIT_IMAGE_ID, fruit.getImageUrl ());
                mContext.startActivity (intent);
            }
        });

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        Fruit fruit = mFruits.get (i);
        viewHolder.fruitName.setText (fruit.getName ());
        Glide.with (mContext).load (fruit.getImageUrl ()).into (viewHolder.mImageView);
        Log.d ("LIU", "onBindViewHolder: " + fruit.getImageUrl () + "    " + i);
    }

    @Override
    public int getItemCount() {
        return mFruits.size ();
    }

}
