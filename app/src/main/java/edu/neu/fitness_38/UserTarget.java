package edu.neu.fitness_38;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class UserTarget extends AppCompatActivity {
    private EditText targetCalorie, targetStep;
    Button next;
    FirebaseAuth myAuth;
    private DatabaseReference myData;
    private ProgressBar progressBar;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_target);

        myAuth = FirebaseAuth.getInstance();
        myData = FirebaseDatabase.getInstance().getReference();
        FirebaseUser user = myAuth.getCurrentUser();
        String userId = user.getUid();
        progressBar = findViewById(R.id.progressBar);

        targetCalorie = findViewById(R.id.TargetCalorie);
        targetStep = findViewById(R.id.TargetStep);
        next = findViewById(R.id.next);

        next.setOnClickListener((v)->{
            if (targetCalorie.getText().toString().length() == 0) {
                targetCalorie.setError("Target Calorie is required");
                return;
            }

            if (targetStep.getText().toString().length() == 0) {
                targetStep.setError("Target Calorie is required");
                return;
            }

            progressBar.setVisibility(View.VISIBLE);
            next.setVisibility(View.GONE);

            Map<String,Object> data = new HashMap<>();
            data.put("targetCalorie",Integer.parseInt(targetCalorie.getText().toString()));
            data.put("targetStep",Integer.parseInt(targetStep.getText().toString()));
            myData.child("Users").child(userId).updateChildren(data).addOnCompleteListener(task -> {
                progressBar.setVisibility(View.GONE);
                next.setVisibility(View.VISIBLE);
                //no problem, jump tp intropage
                if (task.getException() == null) {
                    Intent myIntent = new Intent(getApplicationContext(), IntroPage.class);
                    startActivity(myIntent);
                } else {
                    Toast.makeText(UserTarget.this, "Failed, please try again!", Toast.LENGTH_SHORT).show();
                }

            });


        });


    }
}