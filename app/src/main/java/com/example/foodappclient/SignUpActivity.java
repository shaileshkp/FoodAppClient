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

public class SignUpActivity extends AppCompatActivity {
    EditText etxtName, etxtPhno, etxtPass;
    Button btnSignUp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        etxtName = (EditText) findViewById(R.id.etxtName);
        etxtPhno = (EditText) findViewById(R.id.etxtPhno);
        etxtPass = (EditText) findViewById(R.id.etxtPass);
        btnSignUp = (Button) findViewById(R.id.btnSignUp);

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference dbuser = database.getReference("User");

        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final ProgressDialog mDialog = new ProgressDialog(SignUpActivity.this);
                mDialog.setMessage("Please wait..");
                mDialog.show();
                dbuser.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        if(dataSnapshot.child(etxtPhno.getText().toString()).exists()){
                            mDialog.dismiss();
                            Toast.makeText(SignUpActivity.this, "User already exist!!", Toast.LENGTH_SHORT).show();
                        } else {
                            User user = new User(etxtName.getText().toString().trim(), etxtPass.getText().toString().trim());
                            dbuser.child(etxtPhno.getText().toString().trim()).setValue(user);
                            mDialog.dismiss();
                            Toast.makeText(SignUpActivity.this, "Saved.", Toast.LENGTH_SHORT).show();
                            finish();
                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                        mDialog.dismiss();
                        Toast.makeText(SignUpActivity.this, "Failed.", Toast.LENGTH_SHORT).show();
                        finish();
                    }
                });
            }
        });
    }
}
