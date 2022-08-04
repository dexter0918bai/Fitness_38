package edu.neu.fitness_38;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.HashMap;
import java.util.Map;

public class Register extends AppCompatActivity {

    public static final String TAG = "Register";
    EditText myName, myEmail, myPassword,myPhone;
    Button myRegister;
    TextView Login;
    FirebaseAuth myAuth;
    FirebaseUser myUser;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        myName = findViewById(R.id.Name);
        myEmail = findViewById(R.id.Email);
        myPassword = findViewById(R.id.Password);
        myPhone = findViewById(R.id.Phone);
        myRegister = findViewById(R.id.Register);

        myAuth = FirebaseAuth.getInstance();
        myUser = myAuth.getCurrentUser();
        progressBar = findViewById(R.id.Progress);
        Login = findViewById(R.id. Exist);


        if (myUser != null) {
            startActivity(new Intent(getApplicationContext(), MainActivity.class));
            finish();
        }

        myRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String email = myEmail.getText().toString().trim();
                String password = myPassword.getText().toString().trim();

                final String userName = myName.getText().toString();
                final String phone = myPhone.getText().toString();

                if(TextUtils.isEmpty(email)){
                    myEmail.setError("Email is Required.");
                    return;
                }

                if(TextUtils.isEmpty(password)){
                    myPassword.setError("Password is Required.");
                    return;
                }

                if(password.length() < 6){
                    myPassword.setError("Password at least 6 Characters");
                    return;
                }
                progressBar.setVisibility(View.VISIBLE);


                if(myAuth.getCurrentUser() != null){
                    startActivity(new Intent(getApplicationContext(),MainActivity.class));
                    finish();
                }

                myAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(Register.this, "Use Created! ", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getApplicationContext(),BasicInforActivity.class));

                        }else {
                            Toast.makeText(Register.this, "Something Wrong! " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                            progressBar.setVisibility(View.GONE);
                        }
                    }
                });
            }
        });



        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),Login.class));
            }
        });

    }
}