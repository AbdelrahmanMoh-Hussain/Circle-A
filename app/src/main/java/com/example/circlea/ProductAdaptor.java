package com.example.circlea;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.io.Serializable;
import java.util.ArrayList;

public class ProductAdaptor extends RecyclerView.Adapter<ProductAdaptor.ViewHolder> {
    private ArrayList<Product> products=new ArrayList<>();
    private Context context;

    public void setProducts(ArrayList<Product> products) {
        this.products = products;
        notifyDataSetChanged();
    }

    public ProductAdaptor(Context context) {
        this.context=context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.product_layout,parent,false);
        ViewHolder holder =new ViewHolder(view);
        return holder;


    }



    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") final int position) {
        holder.productName.setText(products.get(position).getName());
        holder.productPrice.setText(products.get(position).getPrice());
        holder.layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(context, ProductActivity.class);
                /*i.putExtra("key", (Serializable) products.get(position));*/
                i.putExtra("productName", products.get(position).getName());
                i.putExtra("productImage", products.get(position).getImaageUrl());
                i.putExtra("productprice", products.get(position).getPrice());
                context.startActivity(i);
                Toast.makeText(context,products.get(position).getName(), Toast.LENGTH_LONG).show();
            }
        });
        Glide.with(context).asBitmap().override(310,310).load(products.get(position).getImaageUrl()).into(holder.photo);


    }

    @Override
    public int getItemCount() {
        return products.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView productName;
        private TextView productPrice;
        private RelativeLayout layout;
        private ImageView photo;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            productName=itemView.findViewById(R.id.product_name);
            productPrice=itemView.findViewById(R.id.product_price);
            layout=itemView.findViewById(R.id.prodoct_lay);
            photo=itemView.findViewById(R.id.product_image);

        }

    }
}

