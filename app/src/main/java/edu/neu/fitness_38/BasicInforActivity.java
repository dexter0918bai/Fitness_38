package edu.neu.fitness_38;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class BasicInforActivity extends AppCompatActivity {

    //private static final String TAG = BasicInforActivity.class.getSimpleName();

    private EditText userName, userDoB, userHeight, userWeight, userTargetWeight;
    private RadioGroup gender;
    private RadioButton genderSelect;
    Button confirm;
    FirebaseAuth myAuth;
    private DatabaseReference myData;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_basic_infor);

        myAuth = FirebaseAuth.getInstance();
        myData = FirebaseDatabase.getInstance().getReference();
        FirebaseUser user = myAuth.getCurrentUser();
        String userId = user.getUid();
        progressBar = findViewById(R.id.progress);

        userName = findViewById(R.id.Name);
        userDoB = findViewById(R.id.DoB);
        userHeight = findViewById(R.id.Height);
        userWeight = findViewById(R.id.CurrenWeight);
        userTargetWeight = findViewById(R.id.TargetWeight);
        confirm = findViewById(R.id.confirm);
        gender = findViewById(R.id.gender);

        confirm.setOnClickListener((v) -> {

            int checkedId = gender.getCheckedRadioButtonId();
            if (checkedId == -1) {
                //no button checked
                Toast.makeText(BasicInforActivity.this, "Need Your Gender", Toast.LENGTH_SHORT).show();
            }

            if (userName.getText().toString().length() == 0) {
                userName.setError("Name is required");
                return;
            }

            if (userDoB.getText().toString().length() == 0) {
                userDoB.setError("DoB is required");
                return;
            }

            if (userHeight.getText().toString().length() == 0) {
                userHeight.setError("Name is required");
                return;
            }

            if (userWeight.getText().toString().length() == 0) {
                userWeight.setError("Weight is required");
                return;
            }

            if (userTargetWeight.getText().toString().length() == 0) {
                userTargetWeight.setError("Target Weight is required");
                return;
            }

            progressBar.setVisibility(View.VISIBLE);
            confirm.setVisibility(View.GONE);
            //collect user's data
            UserModel userModel = new UserModel()
                    .setName(userName.getText().toString())
                    .setDoB(userDoB.getText().toString())
                    .setHeight(userHeight.getText().toString())
                    .setWeight(userWeight.getText().toString())
                    .setTargetWeight(userTargetWeight.getText().toString())
                    .setId(userId);
            if (checkedId == 0) {
                userModel.setGender("Male");
                myData.child("Users").child(userId).child("Gender").setValue("Male");
            } else {
                userModel.setGender("FeMale");
            }
            //start upload data
            myData.child("Users").child(userId).setValue(userModel)
                    .addOnCompleteListener(task -> {
                        //hide progress bar , show button
                        progressBar.setVisibility(View.GONE);
                        confirm.setVisibility(View.VISIBLE);
                        //no problem, jump tp intropage
                        if (task.getException() == null) {
                            Intent myIntent = new Intent(getApplicationContext(), IntroPage.class);
                            startActivity(myIntent);
                        } else {
                            Toast.makeText(BasicInforActivity.this, "Failed, please try again!", Toast.LENGTH_SHORT).show();
                        }
                    });
        });


    }

}

