package com.ideeastudios.coin.market;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ideeastudios.coin.market.retrofit.Datum;

import java.util.List;

public class CryptoListAdapter extends RecyclerView.Adapter<CryptoListAdapter.ViewHolder> {

    private List<Datum> mData;
    private ItemClickListener mClickListener;

    // data is passed into the constructor
    CryptoListAdapter(List<Datum> data) {
        this.mData = data;
    }

    // Usually involves inflating a layout from XML and returning the holder
    // inflates the row layout from xml when needed
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.crypto_list_item, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    // Involves populating data into the item through holder
    // binds the data to the TextView in each row
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        // Get the data model based on position
        Datum datum = mData.get(position);

        // Set item views based on your views and data model
        TextView name = holder.name;
        name.setText(datum.getName() + " (" + datum.getSymbol() + ")");

        TextView price = holder.price;
        price.setText("Price: $" + String.format("%,f", datum.getQuote().getUSD().getPrice()));

        TextView marketCap = holder.marketCap;
        marketCap.setText("Market Cap: $" + String.format("%,d", Math.round(datum.getQuote().getUSD().getMarketCap())));

        TextView volume24h = holder.volume24h;
        volume24h.setText("Volume/24h: $" + String.format("%,d", Math.round(datum.getQuote().getUSD().getVolume24h())));

        TextView textView1h = holder.textView1h;
        textView1h.setText(String.format("1h: %.2f", datum.getQuote().getUSD().getPercentChange1h()) + "%");

        TextView textView24h = holder.textView24h;
        textView24h.setText(String.format("24h: %.2f", datum.getQuote().getUSD().getPercentChange24h()) + "%");

        TextView textView7d = holder.textView7d;
        textView7d.setText(String.format("7d: %.2f", datum.getQuote().getUSD().getPercentChange7d()) + "%");

    }

    // Returns the total count of items in the list
    // total number of rows
    @Override
    public int getItemCount() {
        return mData.size();
    }

    // Provide a direct reference to each of the views within a data item
    // Used to cache the views within the item layout for fast access
    // stores and recycles views as they are scrolled off screen
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        // Your holder should contain a member variable
        // for any view that will be set as you render a row
        TextView name;
        TextView price;
        TextView marketCap;
        TextView volume24h;
        TextView textView1h;
        TextView textView24h;
        TextView textView7d;

        // We also create a constructor that accepts the entire item row
        // and does the view lookups to find each subview
        ViewHolder(View itemView) {
            // Stores the itemView in a public final member variable that can be used
            // to access the context from any ViewHolder instance.
            super(itemView);

            name = itemView.findViewById(R.id.name);
            price = itemView.findViewById(R.id.price);
            marketCap = itemView.findViewById(R.id.marketCap);
            volume24h = itemView.findViewById(R.id.volume24h);
            textView1h = itemView.findViewById(R.id.textView1h);
            textView24h = itemView.findViewById(R.id.textView24h);
            textView7d = itemView.findViewById(R.id.textView7d);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (mClickListener != null) mClickListener.onItemClick(view, getAdapterPosition());
        }
    }

    // convenience method for getting data at click position
    Datum getItem(int id) {
        return mData.get(id);
    }

    // allows clicks events to be caught
    void setClickListener(ItemClickListener itemClickListener) {
        this.mClickListener = itemClickListener;
    }

    // parent activity will implement this method to respond to click events
    public interface ItemClickListener {
        void onItemClick(View view, int position);
    }
}