package com.example.adeshinanicershop.model;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.adeshinanicershop.R;

import java.util.LinkedList;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.
        ProductViewHolder>  {


    private final LinkedList<CardItem> mProductList;
    private LayoutInflater mInflater;

    public ProductAdapter(Context context, LinkedList<CardItem> ProductList) {
        mInflater = LayoutInflater.from(context);
        this.mProductList = ProductList;
    }

    @NonNull
    @Override
    public ProductAdapter.ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View mProductView = mInflater.inflate(R.layout.card_root_layout, parent, false);
        return new ProductViewHolder(mProductView, this);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductAdapter.ProductViewHolder holder, int position) {
        CardItem mCurrent = mProductList.get(position);
        holder.mTitleTextView.setText(mCurrent.getItemName());
        holder.mImageView.setImageResource(mCurrent.getImage());
        holder.mPriceTextView.setText(mCurrent.getPrice());
        holder.mQuantity.setText(mCurrent.getQuantity());
        holder.mDescription.setText(mCurrent.getDescription());
        holder.mSubTotal.setText(mCurrent.getSubTotal());


    }

    @Override
    public int getItemCount() {
        return mProductList.size();
    }

    class ProductViewHolder extends RecyclerView.ViewHolder {
        TextView mTitleTextView;
        ImageView mImageView;
        TextView mPriceTextView;
        TextView mQuantity;
        TextView mDescription;
        TextView mSubTotal;

        final ProductAdapter mAdapter;
        public CardView productItemView;
        public ProductViewHolder(@NonNull View itemView, ProductAdapter adapter) {
            super(itemView);
            productItemView = itemView.findViewById(R.id.card_generic);
            mTitleTextView = itemView.findViewById(R.id.title_textView);
            mPriceTextView = itemView.findViewById(R.id.price_textView);
            mImageView = itemView.findViewById(R.id.imageView);
            mQuantity = itemView.findViewById(R.id.quantity_textView);
            mDescription = itemView.findViewById(R.id.description1_textView);
            mSubTotal = itemView.findViewById(R.id.subtotal_textView);
            this.mAdapter = adapter;
        }
    }
}
