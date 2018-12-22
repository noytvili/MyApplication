package com.example.doriyaspielman.myapplication;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class LoginScreen extends AppCompatActivity {
    public Button login;
    public Button signIn;
    private EditText emailInput;
    private EditText passowrdInput;
    boolean loginIsOk = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        emailInput = (EditText)findViewById(R.id.emailInput);
        passowrdInput = (EditText)findViewById(R.id.passwordInput);
    }
    public void onClickLoginButton(View view) {
        final String emailInputString = emailInput.getText().toString();
        final String passwordInputString = passowrdInput.getText().toString();
        final DatabaseReference db = FirebaseDatabase.getInstance().getReference();

            db.child("Users").child(emailInputString.replace(".", "|").toLowerCase()).addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    if(dataSnapshot.exists()) {
                        User user;
                        user = dataSnapshot.getValue(User.class);
                        Log.d("user: "+user.getEmail(),"messege");
                        Log.d("user pass: "+user.getPassword(),"messege");
                        Log.d("user_input: "+emailInputString,"messege");
                        Log.d("user_input_pass: "+passwordInputString,"messege");
                        if (passwordInputString.equals(user.getPassword())) {
                            loginIsOk = true;
                        }
                    }
                }
                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {}
                }
             );
        if(loginIsOk) {
            Intent i = new Intent(this,StoreScreen.class);
            startActivity(i);
        }
    }


    public void OnClickSignInButton(View v) {
        Intent i = new Intent(this,RegisterScreen.class);
        startActivity(i);


    }
}


