package com.example.circlea;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
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
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class CategoriesAdapter extends RecyclerView.Adapter<CategoriesAdapter.ViewHolder> {
    private ArrayList<Categories> categories=new ArrayList<>();
    private Context context;

    public void setCategories(ArrayList<Categories> categories) {
        this.categories = categories;
        notifyDataSetChanged();
    }

    public CategoriesAdapter(Context context) {
        this.context=context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.category_layout,parent,false);
        ViewHolder holder=new ViewHolder(view);
        return holder;


    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") final int position) {
        holder.txtname.setText(categories.get(position).getName());

        holder.layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(context,category.class);
                i.putExtra("category",categories.get(position).getName());
                context.startActivity(i);
                Toast.makeText(context,categories.get(position).getName(), Toast.LENGTH_LONG).show();
            }
        });
        Glide.with(context).asBitmap().override(310,310).load(categories.get(position).getImageUrl()).into(holder.photo);


    }

    @Override
    public int getItemCount() {
        return categories.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView txtname;
        private RelativeLayout layout;
        private ImageView photo;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtname=itemView.findViewById(R.id.category_name);
            layout=itemView.findViewById(R.id.first);
            photo=itemView.findViewById(R.id.category_image);

        }

    }
}
