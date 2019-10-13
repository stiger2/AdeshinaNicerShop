package com.example.adeshinanicershop.model;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.adeshinanicershop.R;

import java.util.LinkedList;

import static androidx.constraintlayout.widget.Constraints.TAG;

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
        holder.lowerLayout.setVisibility(View.VISIBLE);




    }

    @Override
    public int getItemCount() {
        return mProductList.size();
    }

    class ProductViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView mTitleTextView;
        ImageView mImageView;
        TextView mPriceTextView;
        TextView mQuantity;
        TextView mDescription;
        TextView mSubTotal;
        Button increaseButton;
        Button decreaseButton;
        Button addToCart;

        LinearLayout lowerLayout;
        private final String LOG_TAG = ProductViewHolder.class.getSimpleName();
        String message;

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
            increaseButton = itemView.findViewById(R.id.button_plus);
            decreaseButton = itemView.findViewById(R.id.button_minus);
            addToCart = itemView.findViewById(R.id.addToCart_Button);
            lowerLayout = itemView.findViewById(R.id.lower_layout);
            increaseButton.setOnClickListener(this);
            decreaseButton.setOnClickListener(this);
            addToCart.setOnClickListener(this);
            this.mAdapter = adapter;
        }

        @Override
        public void onClick(View v) {
            int quantity;
            String subTotal;
            int position = getLayoutPosition();
            switch(v.getId()){
                case R.id.button_plus: //handler for the decrease button
                    quantity = Integer.parseInt(mQuantity.getText().toString());
                    if (quantity == 0)
                        lowerLayout.setVisibility(View.VISIBLE);
                    quantity++;
                    mQuantity.setText(Integer.toString(quantity));
                    message= mTitleTextView.getText() + " Added to cart. Price: " + mPriceTextView.getText();;
                    Log.d(LOG_TAG, message);
                    subTotal = String.format("%.2f", Float.parseFloat(mSubTotal.getText().toString()) + Float.parseFloat(mPriceTextView.getText().toString()));
                    mSubTotal.setText(subTotal);
                    mProductList.get(position).setQuantity(Integer.toString(quantity));
                    mProductList.get(position).setSubTotal(subTotal);
                    lowerLayout.setVisibility(View.VISIBLE);
                    break;
                case R.id.button_minus: //hander for the increase button
                    quantity = Integer.parseInt(mQuantity.getText().toString());

                    if(quantity != 0){
                        quantity--;
                        mQuantity.setText(Integer.toString(quantity));
                        message= mTitleTextView.getText() + " Removed from cart. Price: " + mPriceTextView.getText();
                        Log.d(LOG_TAG, message);
                        mSubTotal.setText(String.format("%.2f", Float.parseFloat(mSubTotal.getText().toString()) - Float.parseFloat(mPriceTextView.getText().toString())));
                    }
                    if (quantity == 0)
                        lowerLayout.setVisibility(View.INVISIBLE);
                    break;
                case R.id.addToCart_Button: //handler for the add to cart button
                    increaseButton.setEnabled(false);
                    decreaseButton.setEnabled(false);
                    addToCart.setText("ADDED");
                    addToCart.setEnabled(false);
            }







        }


    }
}
