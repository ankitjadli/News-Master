package com.example.newz;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class firebaseac extends AppCompatActivity {

    public String hindi;
    DatabaseReference mdatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_firebaseac);

        mdatabase = FirebaseDatabase.getInstance().getReference();


        mdatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                TextView dec;
                TextView eql;
                TextView doublecheck;
                dec = (TextView)findViewById(R.id.decision);
                eql = (TextView)findViewById(R.id.equal);
                doublecheck = (TextView) findViewById(R.id.doublecheck);

                String hindidic = dataSnapshot.child("category").child("hindi").getValue().toString();
                if (hindidic.equals("yes"))
                {
                    hindi=hindidic;
                }

                dec.setText(hindidic);
                eql.setText(hindi);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }
}
