package com.example.newz;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.bumptech.glide.Glide;

import com.example.newz.News;
import com.example.newz.R;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.lsjwzh.widget.recyclerviewpager.RecyclerViewPager;
import com.squareup.picasso.Picasso;


import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    DatabaseReference mdatabse;
    private TextView text;
    private ArrayList<String> mNames = new ArrayList<>();
    private ArrayList<Integer> mimages = new ArrayList<>();
    private RecyclerViewPager muserslist;
    private View mViewmain;
    FirebaseUser crnt;
    String currentuser;
    private RecyclerView mPeopleRV;
    private DatabaseReference mDatabase;
    private DatabaseReference ted;
    private FirebaseRecyclerAdapter<News, MainActivity.NewsViewHolder> mPeopleRVAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        crnt = FirebaseAuth.getInstance().getCurrentUser();
        currentuser = crnt.getUid().toString();
        ted = FirebaseDatabase.getInstance().getReference().child("Users");
        ted.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Integer hindi = dataSnapshot.child(currentuser).child("Hindi").getValue(Integer.class);
                Integer world = dataSnapshot.child(currentuser).child("World").getValue(Integer.class);
                Integer politics = dataSnapshot.child(currentuser).child("Politics").getValue(Integer.class);
                Integer tech     = dataSnapshot.child(currentuser).child("Tech").getValue(Integer.class);
                Integer sports = dataSnapshot.child(currentuser).child("Sports").getValue(Integer.class);
                Integer btown = dataSnapshot.child(currentuser).child("Btown").getValue(Integer.class);
                Integer weather = dataSnapshot.child(currentuser).child("Weather").getValue(Integer.class);

                getImages(hindi,world,politics,tech,sports,btown,weather );
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        setTitle("News");
        FirebaseApp.initializeApp(MainActivity.this);
        mDatabase = FirebaseDatabase.getInstance().getReference().child("News");
        mDatabase.keepSynced(true);
        mPeopleRV = (RecyclerView) findViewById(R.id.swipeuprecycler);


        DatabaseReference personsRef = FirebaseDatabase.getInstance().getReference().child("News");
        Query personsQuery = personsRef.orderByKey();

        mPeopleRV.hasFixedSize();
        mPeopleRV.setLayoutManager(new LinearLayoutManager(this));

        FirebaseRecyclerOptions personsOptions = new FirebaseRecyclerOptions.Builder<News>().setQuery(personsQuery, News.class).build();

        mPeopleRVAdapter = new FirebaseRecyclerAdapter<News, NewsViewHolder>(personsOptions) {
            @Override
            protected void onBindViewHolder(MainActivity.NewsViewHolder holder, final int position, final News model) {
                holder.setTitle(model.getTitle());
                holder.setDesc(model.getDesc());
                holder.setImage(getBaseContext(), model.getImage());

            }

            @Override
            public MainActivity.NewsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

                View view = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.news_row, parent, false);

                return new MainActivity.NewsViewHolder(view);
            }
        };

        mPeopleRV.setAdapter(mPeopleRVAdapter);

    }
    private void getImages(Integer hindi,Integer world,Integer politics,Integer tech,Integer sports,Integer btown, Integer weather){

        Log.d(TAG, "initImageBitmaps: preparing bitmaps.");

        if( hindi == 1)
        {
            mNames.add("HINDI");
            mimages.add(R.drawable.hindipush);
        }
        if( world == 1)
        {
            mNames.add("WORLD");
            mimages.add(R.drawable.worldpush);
        }
        if( politics == 1)
        {
            mNames.add("politics");
            mimages.add(R.drawable.politicspush);

        }  if( tech == 1)
        {
            mNames.add("TECH");
            mimages.add(R.drawable.techpush);
        }  if( sports == 1)
        {
            mNames.add("SPORTS");
            mimages.add(R.drawable.sportspush);
        }  if( btown == 1)
        {
            mNames.add("BTOWN");
            mimages.add(R.drawable.btownpush);
        }
        if( weather == 1)
        {
            mNames.add("WEATHER");
            mimages.add(R.drawable.weatherpush);
        }

        mNames.add("ADD");
        mimages.add(R.drawable.addpush);

        initRecyclerView();

    }

    private void initRecyclerView(){
        Log.d(TAG, "initRecyclerView: init recyclerview");

        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(layoutManager);
        RecyclerViewAdapter adapter = new RecyclerViewAdapter(this, mNames, mimages);
        recyclerView.setAdapter(adapter);
    }


    @Override
    public void onStart() {
        super.onStart();
        mPeopleRVAdapter.startListening();
    }

    @Override
    public void onStop() {
        super.onStop();
        mPeopleRVAdapter.stopListening();


    }

    public static class NewsViewHolder extends RecyclerView.ViewHolder{
        View mView;
        public NewsViewHolder(View itemView){
            super(itemView);
            mView = itemView;
        }
        public void setTitle(String title){
            TextView post_title = (TextView)mView.findViewById(R.id.post_title);
            post_title.setText(title);
        }
        public void setDesc(String desc){
            TextView post_desc = (TextView)mView.findViewById(R.id.post_desc);
            post_desc.setText(desc);
        }

        public void setImage(Context ctx, String image) {
            ImageView post_image = (ImageView) mView.findViewById(R.id.post_image);
            Picasso.with(ctx).load(image).placeholder(R.drawable.imagereplace).into(post_image);
        }
    }
}



