package com.example.newz;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class signin extends AppCompatActivity {

    Button signup;
    EditText email;
    EditText pass;
    Button login ;
    private FirebaseAuth mAuth;
    private ProgressDialog mprogressdialog;
    Animation lefttoright,downtoup,uptodown;
    ImageView appsign;
    FirebaseUser crnt;
    String currentuser;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin);
        email=(EditText)findViewById(R.id.emailtext);
        pass=(EditText)findViewById(R.id.passwordtext);
        login=(Button)findViewById(R.id.signin);
        mAuth = FirebaseAuth.getInstance();
        mprogressdialog=new ProgressDialog(this);
        mprogressdialog.setTitle("Logging In");
        mprogressdialog.setMessage("Please wait while we log you in");
        mprogressdialog.setCanceledOnTouchOutside(false);
        appsign = (ImageView) findViewById(R.id.applogo);

        lefttoright = AnimationUtils.loadAnimation(this,R.anim.lefttoright);
        downtoup = AnimationUtils.loadAnimation(this,R.anim.downtoup);
        uptodown = AnimationUtils.loadAnimation(this,R.anim.uptodown);


        signup = (Button) findViewById(R.id.signuofromin);

        login.setAnimation(lefttoright);
        signup.setAnimation(downtoup);
        appsign.setAnimation(uptodown);

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(signin.this, signup.class);
                startActivity(intent);
            }
        });
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String passcode = pass.getText().toString();
                final String emaill=email.getText().toString();
                mprogressdialog.show();

                if(!TextUtils.isEmpty(passcode)&&!TextUtils.isEmpty(emaill))
                {
                    mAuth.signInWithEmailAndPassword(emaill,passcode).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful())
                            {
                                mprogressdialog.dismiss();
                                Intent loginsucess = new Intent(signin.this,category.class);
                                startActivity(loginsucess);
                                finish();

                            }
                            else
                            {
                                mprogressdialog.hide();
                                String error = task.getException().getMessage();
                                Toast.makeText(signin.this,""+error,Toast.LENGTH_LONG).show();
                            }
                        }

                    });


                }
                else
                {
                    if (TextUtils.isEmpty(passcode))
                    {
                        mprogressdialog.hide();
                        Toast.makeText(signin.this,"Enter Password",Toast.LENGTH_LONG).show();
                    }
                    else {
                        mprogressdialog.hide();
                        Toast.makeText(signin.this,"Enter Email",Toast.LENGTH_LONG).show();
                    }
                }



            }
        });



    }
}
