package com.example.foodappclient;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.foodappclient.model.User;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class SignInActivity extends AppCompatActivity {

    EditText etxtPhno, etxtPass;
    Button btnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        etxtPass = (EditText) findViewById(R.id.etxtPass);
        etxtPhno = (EditText) findViewById(R.id.etxtPhno);
        btnLogin = (Button) findViewById(R.id.btnLogin);


        FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference dbuser = database.getReference("User");

        btnLogin.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                final ProgressDialog mDialog = new ProgressDialog(SignInActivity.this);
                mDialog.setMessage("Please wait..");
                mDialog.show();
                dbuser.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {

                        if(dataSnapshot.child(etxtPhno.getText().toString()).exists()){


                            User user = dataSnapshot.child(etxtPhno.getText().toString().trim()).getValue(User.class);
                            if(user.getPass().equals(etxtPass.getText().toString().trim())) {
                                mDialog.dismiss();
                                Toast.makeText(SignInActivity.this, "Login successful", Toast.LENGTH_SHORT).show();
                            }
                            else {
                                mDialog.dismiss();
                                Toast.makeText(SignInActivity.this, "Invalid password..", Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            mDialog.dismiss();
                            Toast.makeText(SignInActivity.this, "User not exist..", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                        mDialog.dismiss();
                    }
                });
            }
        });
    }
}
