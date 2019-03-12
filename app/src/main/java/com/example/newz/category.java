package com.example.newz;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.airbnb.lottie.LottieAnimationView;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.iid.FirebaseInstanceId;

public class category extends AppCompatActivity {


    Button hindiadd;
    Button sportsadd;
    Button politicsadd;
    Button techadd;
    Button worldadd;
    Button weatheradd;
    Button btownadd;

    Button btowndel;
    Button hindidel;
    Button sportsdel;
    Button politicsdel;
    Button techdel;
    Button worlddel;
    Button weatherdel;

    LottieAnimationView lottie1;
    LottieAnimationView lottie2;
    LottieAnimationView lottie3;
    LottieAnimationView lottie4;
    LottieAnimationView lottie5;
    LottieAnimationView lottie6;
    LottieAnimationView lottie7;

    Button jump;
    FirebaseUser crnt;
    String currentuser;
    String user_token;
    DatabaseReference mdatabse;
    DatabaseReference sdatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);

        hindiadd = (Button) findViewById(R.id.hindiadd);
        sportsadd = (Button) findViewById(R.id.sportsadd);
        politicsadd = (Button) findViewById(R.id.politicsadd);
        techadd = (Button) findViewById(R.id.techadd);
        weatheradd = (Button) findViewById(R.id.weatheradd);
        btownadd = (Button) findViewById(R.id.btownadd);
        worldadd = (Button) findViewById(R.id.worldadd);

        hindidel = (Button) findViewById(R.id.hindidel);
        sportsdel = (Button) findViewById(R.id.sportsdel);
        politicsdel = (Button) findViewById(R.id.politicsdel);
        techdel = (Button) findViewById(R.id.techdel);
        weatherdel = (Button) findViewById(R.id.weatherdel);
        btowndel = (Button) findViewById(R.id.btowndel);
        worlddel = (Button) findViewById(R.id.worlddel);

        lottie1 = (LottieAnimationView) findViewById(R.id.lottie1);
        lottie2 = (LottieAnimationView) findViewById(R.id.lottie2);
        lottie3 = (LottieAnimationView) findViewById(R.id.lottie3);
        lottie4 = (LottieAnimationView) findViewById(R.id.lottie4);
        lottie5 = (LottieAnimationView) findViewById(R.id.lottie5);
        lottie6 = (LottieAnimationView) findViewById(R.id.lottie6);
        lottie7 = (LottieAnimationView) findViewById(R.id.lottie7);



        jump = (Button) findViewById(R.id.mainac);
        user_token = FirebaseInstanceId.getInstance().getToken();
        crnt = FirebaseAuth.getInstance().getCurrentUser();
        currentuser = crnt.getUid().toString();

        sdatabase = FirebaseDatabase.getInstance().getReference().child("Users").child(currentuser);

        sdatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Integer hindi = dataSnapshot.child("Hindi").getValue(Integer.class);
                Integer world = dataSnapshot.child("World").getValue(Integer.class);
                Integer politics = dataSnapshot.child("Politics").getValue(Integer.class);
                Integer tech = dataSnapshot.child("Tech").getValue(Integer.class);
                Integer weather = dataSnapshot.child("Weather").getValue(Integer.class);
                Integer sports = dataSnapshot.child("Sports").getValue(Integer.class);
                Integer btown = dataSnapshot.child("Btown").getValue(Integer.class);

                if(hindi==1)
                {
                    hindiadd.setVisibility(View.INVISIBLE);
                    hindidel.setVisibility(View.VISIBLE);
                }
                else
                {
                    hindiadd.setVisibility(View.VISIBLE);
                    hindidel.setVisibility(View.INVISIBLE);
                }

                if(world==1)
                {
                    worldadd.setVisibility(View.INVISIBLE);
                    worlddel.setVisibility(View.VISIBLE);
                }
                else
                {
                    worldadd.setVisibility(View.VISIBLE);
                    worlddel.setVisibility(View.INVISIBLE);
                }

                if(politics==1)
                {
                    politicsadd.setVisibility(View.INVISIBLE);
                    politicsdel.setVisibility(View.VISIBLE);
                }
                else
                {
                    politicsadd.setVisibility(View.VISIBLE);
                    politicsdel.setVisibility(View.INVISIBLE);
                }


                if(tech==1)
                {
                    techadd.setVisibility(View.INVISIBLE);
                    techdel.setVisibility(View.VISIBLE);
                }
                else
                {
                    techadd.setVisibility(View.VISIBLE);
                    techdel.setVisibility(View.INVISIBLE);
                }

                if(weather==1)
                {
                    weatheradd.setVisibility(View.INVISIBLE);
                   weatherdel.setVisibility(View.VISIBLE);
                }
                else
                {
                    weatheradd.setVisibility(View.VISIBLE);
                    weatherdel.setVisibility(View.INVISIBLE);
                }

                if(sports==1)
                {
                    sportsadd.setVisibility(View.INVISIBLE);
                    sportsdel.setVisibility(View.VISIBLE);
                }
                else
                {
                    sportsadd.setVisibility(View.VISIBLE);
                    sportsdel.setVisibility(View.INVISIBLE);
                }

                if(btown==1)
                {
                    btownadd.setVisibility(View.INVISIBLE);
                    btowndel.setVisibility(View.VISIBLE);
                }
                else
                {
                    btownadd.setVisibility(View.VISIBLE);
                    btowndel.setVisibility(View.INVISIBLE);
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        mdatabse = FirebaseDatabase.getInstance().getReference().child("Users").child(currentuser);


        hindiadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mdatabse.child("Hindi").setValue(1);
                lottie1.playAnimation();
                Toast.makeText(category.this,"Added ",Toast.LENGTH_LONG).show();
            }
        });

        hindidel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mdatabse.child("Hindi").setValue(0);
                lottie1.playAnimation();
                Toast.makeText(category.this,"Removed ",Toast.LENGTH_LONG).show();
            }
        });

        sportsadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mdatabse.child("Sports").setValue(1);
                lottie4.playAnimation();
                Toast.makeText(category.this,"Added ",Toast.LENGTH_LONG).show();
            }
        });

        sportsdel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mdatabse.child("Sports").setValue(0);
                lottie4.playAnimation();
                Toast.makeText(category.this,"Removed ",Toast.LENGTH_LONG).show();
            }
        });

        politicsadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mdatabse.child("Politics").setValue(1);
                lottie2.playAnimation();
                Toast.makeText(category.this,"Added ",Toast.LENGTH_LONG).show();
            }
        });
        politicsdel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mdatabse.child("Politics").setValue(0);
                lottie2.playAnimation();
                Toast.makeText(category.this,"Removed ",Toast.LENGTH_LONG).show();
            }
        });

        worldadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mdatabse.child("World").setValue(1);
                lottie7.playAnimation();
                Toast.makeText(category.this,"Added ",Toast.LENGTH_LONG).show();
            }
        });

        worlddel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mdatabse.child("World").setValue(0);
                lottie7.playAnimation();
                Toast.makeText(category.this,"Removed ",Toast.LENGTH_LONG).show();
            }
        });

        techadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mdatabse.child("Tech").setValue(1);
                lottie5.playAnimation();
                Toast.makeText(category.this,"Added ",Toast.LENGTH_LONG).show();
            }
        });

        techdel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mdatabse.child("Tech").setValue(0);
                lottie5.playAnimation();
                Toast.makeText(category.this,"Removed ",Toast.LENGTH_LONG).show();
            }
        });

        weatheradd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mdatabse.child("Weather").setValue(1);
                lottie3.playAnimation();
                Toast.makeText(category.this,"Added ",Toast.LENGTH_LONG).show();
            }
        });
        weatherdel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mdatabse.child("Weather").setValue(0);
                lottie3.playAnimation();
                Toast.makeText(category.this,"Removed ",Toast.LENGTH_LONG).show();
            }
        });

        btownadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mdatabse.child("Btown").setValue(1);
                lottie6.playAnimation();
                Toast.makeText(category.this,"Removed ",Toast.LENGTH_LONG).show();
            }
        });
        btowndel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mdatabse.child("Btown").setValue(0);
                lottie6.playAnimation();
                Toast.makeText(category.this,"Removed ",Toast.LENGTH_LONG).show();
            }
        });


        jump.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent loginsucess = new Intent(category.this, com.example.newz.MainActivity.class);
                startActivity(loginsucess);
                finish();
            }
        });
    }


}
