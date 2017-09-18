package com.example.foodappclient.viewholder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.foodappclient.R;
import com.example.foodappclient.interfaces.ItemClickListener;

/**
 * Created by Shailesh on 9/19/2017.
 */

public class MenuViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    public TextView textMenuName;
    public ImageView imageView;

    private ItemClickListener itemClickListener;

    public MenuViewHolder(View itemView) {
        super(itemView);
        textMenuName = (TextView) itemView.findViewById(R.id.menu_itemName);
        imageView = (ImageView) itemView.findViewById(R.id.menu_imageView);

        itemView.setOnClickListener(this);

    }

    public void setItemClickListener(ItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    @Override
    public void onClick(View view) {

        itemClickListener.onClick(view, getAdapterPosition(),false);

    }
}
