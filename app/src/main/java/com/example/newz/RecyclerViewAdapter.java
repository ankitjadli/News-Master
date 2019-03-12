package com.example.newz;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;


/**
 * Created by User on 2/12/2018.
 */

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {

    private static final String TAG = "RecyclerViewAdapter";

    //vars
    private ArrayList<String> mNames = new ArrayList<>();
    private ArrayList<Integer> mimages = new ArrayList<>();
    private Context mContext;


    public RecyclerViewAdapter(Context context, ArrayList<String> names, ArrayList<Integer> images) {
        mNames = names;
        mimages = images;
        mContext = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_listitem, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {

        Log.d(TAG, "onBindViewHolder: called.");

        holder.mPlace.setImageResource(mimages.get(position));
       // holder.name.setText(mNames.get(position));
        holder.mPlace.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Intent intent;
                final String nameofclass = mNames.get(position).toLowerCase();
                switch (nameofclass){
                    case "hindi":
                        intent =  new Intent(mContext, hndi.class);
                        mContext.startActivity(intent);
                        break;

                    case "tech":
                        intent =  new Intent(mContext, tech.class);
                        mContext.startActivity(intent);
                        break;

                    case "sports":
                        intent =  new Intent(mContext, sports.class);
                        mContext.startActivity(intent);
                        break;
                    case "weather":
                        intent =  new Intent(mContext, weather.class);
                        mContext.startActivity(intent);
                        break;

                    case "add":
                        intent =  new Intent(mContext, category.class);
                        mContext.startActivity(intent);
                        break;

                    case "politics":
                        intent =  new Intent(mContext, politics.class);
                        mContext.startActivity(intent);
                        break;

                    case "btown":
                        intent =  new Intent(mContext, btown.class);
                        mContext.startActivity(intent);
                        break;

                    case "world":
                        intent =  new Intent(mContext, world.class);
                        mContext.startActivity(intent);
                        break;
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return mNames.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        ImageView mPlace;
        TextView name;

        public ViewHolder(View itemView) {
            super(itemView);
            mPlace = itemView.findViewById(R.id.image_view);
        }
    }
}
