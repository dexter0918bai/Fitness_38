package edu.neu.fitness_38;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
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

public class  Login extends AppCompatActivity {
    EditText  myEmail, myPassword;
    Button myLogin;
    TextView myCreat, forgetPassword;
    FirebaseAuth myAuth;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login2);

        myEmail = findViewById(R.id.Email);
        myPassword = findViewById(R.id.Password);
        progressBar = findViewById(R.id.pBar);
        myAuth = FirebaseAuth.getInstance();
        myLogin = findViewById(R.id.login);
        myCreat = findViewById(R.id.creatAccount);
        forgetPassword = findViewById(R.id.fPassword);

       myLogin.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               String email = myEmail.getText().toString().trim();
               String password = myPassword.getText().toString().trim();

               if(TextUtils.isEmpty(email)){
                   myEmail.setError("Email is Required.");
                   return;
               }

               if(TextUtils.isEmpty(password)){
                   myPassword.setError("Password is Required.");
                   return;
               }

               if(password.length() < 6){
                   myPassword.setError("Password At Least 6 letters");
                   return;
               }

               progressBar.setVisibility(View.VISIBLE);

               myAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                   @Override
                   public void onComplete(@NonNull Task<AuthResult> task) {
                       if(task.isSuccessful()){
                           Toast.makeText(Login.this, "Logged in Successfully", Toast.LENGTH_SHORT).show();
                          // startActivity(new Intent(getApplicationContext(),MainActivity.class));
                           startActivity(new Intent(getApplicationContext(), IntroPage.class));
                       }else {
                           Toast.makeText(Login.this, "Error ! " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                           progressBar.setVisibility(View.GONE);
                       }

                   }
               });

//               startActivity(new Intent(getApplicationContext(), IntroPage.class));
           }
       });

        myCreat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),Register.class));
            }
        });

        forgetPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final EditText resetMail = new EditText(v.getContext());
                final AlertDialog.Builder passwordResetDialog = new AlertDialog.Builder(v.getContext());
                passwordResetDialog.setTitle("Reset Password ?");
                passwordResetDialog.setMessage("Enter Your Email To Received Reset Link.");
                passwordResetDialog.setView(resetMail);

                passwordResetDialog.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // extract the email and send reset link
                        String mail = resetMail.getText().toString();
                        myAuth.sendPasswordResetEmail(mail).addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
                                Toast.makeText(Login.this, "Reset Link has been send!", Toast.LENGTH_SHORT).show();
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(Login.this, "Ops! Reset Link has not been send !" + e.getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        });

                    }
                });

                passwordResetDialog.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // close the dialog
                    }
                });

                passwordResetDialog.create().show();

            }
        });

    }
}