package com.example.retrofitappdemo;

import android.content.Context;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

import retrofit2.Callback;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.ViewHolder> {

    private Context context;
    private List<NewsData> str;

    public CustomAdapter(Context context, List<NewsData> str) {
        this.context = context;
        this.str = str;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardviewlayout,parent,false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {
        holder.nname.setText(str.get(position).getName());
        holder.ndate.setText(str.get(position).getDate());
        holder.nsource.setText(str.get(position).getSource());
        Picasso.get().load(str.get(position).getImage()).into(holder.nimage);
        holder.nname.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                View myView = LayoutInflater.from(context).inflate(R.layout.displaynews, null);
                final AlertDialog.Builder builder = new AlertDialog.Builder(context,android.R.style.Theme_Material_Light_NoActionBar);
                builder.setView(myView).show();
                ImageView newsimg;
                TextView newsname, newssource, newsdate,newsdesc;
                newsimg = myView.findViewById(R.id.newsimg);
                newsname = myView.findViewById(R.id.newsname);
                newssource = myView.findViewById(R.id.newssource);
                newsdate = myView.findViewById(R.id.newsdate);
                newsdesc = myView.findViewById(R.id.newsdesc);

                newsname.setText(str.get(position).getName());
                newsdesc.setText(str.get(position).getDescription());
                newssource.setText(str.get(position).getSource());
                newsdate.setText(str.get(position).getDate());
                Picasso.get().load(str.get(position).getImage()).into(newsimg);
            }
        });
    }

    @Override
    public int getItemCount() {
        return str.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView nname;
        public ImageView nimage;
        public TextView ndate;
        public TextView nsource;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            nname = itemView.findViewById(R.id.postname);
            nimage = itemView.findViewById(R.id.postimg);
            ndate = itemView.findViewById(R.id.date);
            nsource = itemView.findViewById(R.id.source);
        }
    }
}
