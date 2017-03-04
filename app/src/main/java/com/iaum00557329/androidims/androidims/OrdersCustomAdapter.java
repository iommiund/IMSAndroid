package com.iaum00557329.androidims.androidims;

import android.content.Context;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class OrdersCustomAdapter extends RecyclerView.Adapter<OrdersCustomAdapter.ViewHolder>{
    private List<OrderList> list;
    private Context mCtx;

    public OrdersCustomAdapter(List<OrderList> list, Context mCtx) {
        this.list = list;
        this.mCtx = mCtx;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.orders_list, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final OrdersCustomAdapter.ViewHolder holder, int position) {
        OrderList orderList = list.get(position);
        holder.textViewHead.setText(orderList.getHead());
        holder.textViewDesc.setText(orderList.getDesc());

        holder.buttonViewOption.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //creating a popup menu
                PopupMenu popup = new PopupMenu(mCtx, holder.buttonViewOption);
                //inflating menu from xml resource
                popup.inflate(R.menu.orders_menu);
                //adding click listener
                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {

                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        switch (item.getItemId()) {
                            case R.id.navigate:
                                //handle navigate click
                                break;
                            case R.id.complete:
                                //handle complete click
                                break;
                        }
                        return false;
                    }
                });
                //displaying the popup
                popup.show();

            }
        });
    }


    @Override
    public int getItemCount() {
        return list.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView textViewHead;
        public TextView textViewDesc;
        public TextView buttonViewOption;

        public ViewHolder(View itemView) {
            super(itemView);

            textViewHead = (TextView) itemView.findViewById(R.id.textViewHead);
            textViewDesc = (TextView) itemView.findViewById(R.id.textViewDesc);
            buttonViewOption = (TextView) itemView.findViewById(R.id.textViewOptions);
        }
    }
}
