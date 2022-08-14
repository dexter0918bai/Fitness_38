package edu.neu.fitness_38;

import android.content.DialogInterface;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class UserProfile extends AppCompatActivity {
    private FirebaseUser currentUser;
    private ImageView imageView;
    private LinearLayout linearLayout;
    private TextView NameP;
    private TextView DoBP;
    private TextView Height;
    private TextView CurrentWeight;
    private TextView TargetWeightP;
    private TextView targetStepP;
    private TextView targetCalorieP;
    private AlertDialog alertDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);
        initView();
        initData();
    }

    private void initData() {
        showLoadingDialog();
        currentUser = FirebaseAuth.getInstance().getCurrentUser();
        FirebaseDatabase.getInstance().getReference()
                .child("Users")
                .child(currentUser.getUid())
                .get()
                .addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DataSnapshot> task) {
                        dismissLoadingDialog();
                        if (!task.isSuccessful()) {
                            Log.e("firebase", "Error getting data", task.getException());
                        } else {
                            Log.d("firebase", String.valueOf(task.getResult().getValue()));
                            UserModel data = task.getResult().getValue(UserModel.class);
                            if (data!=null){
                                NameP.setText(data.getName()+"");
                                DoBP.setText(data.getDoB()+"");
                                Height.setText(data.getHeight()+" cm");
                                CurrentWeight.setText(data.getWeight()+" kg");
                                TargetWeightP.setText(data.getTargetWeight()+" kg");
                                targetStepP.setText(data.getTargetStep()+" steps");
                                targetCalorieP.setText(data.getTargetCalorie()+" cal");
                            }

                        }
                    }
                });

    }
    public void showLoadingDialog() {
        alertDialog = new AlertDialog.Builder(this).create();
        alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable());
        alertDialog.setCancelable(false);
        alertDialog.setOnKeyListener(new DialogInterface.OnKeyListener() {
            @Override
            public boolean onKey(DialogInterface dialog, int keyCode, KeyEvent event) {
                if (keyCode == KeyEvent.KEYCODE_SEARCH || keyCode == KeyEvent.KEYCODE_BACK)
                    return true;
                return false;
            }
        });
        alertDialog.show();
        alertDialog.setContentView(R.layout.loading_alert);
        alertDialog.setCanceledOnTouchOutside(false);
    }
    public void dismissLoadingDialog() {
        if (null != alertDialog && alertDialog.isShowing()) {
            alertDialog.dismiss();
        }
    }

    private void initView() {
        if (getSupportActionBar()!=null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        imageView = (ImageView) findViewById(R.id.imageView);
        linearLayout = (LinearLayout) findViewById(R.id.linearLayout);
        NameP = (TextView) findViewById(R.id.Name_p);
        DoBP = (TextView) findViewById(R.id.DoB_p);
        Height = (TextView) findViewById(R.id.Height);
        CurrentWeight = (TextView) findViewById(R.id.CurrenWeight);
        TargetWeightP = (TextView) findViewById(R.id.TargetWeight_p);
        targetStepP = (TextView) findViewById(R.id.targetStep_p);
        targetCalorieP = (TextView) findViewById(R.id.targetCalorie_p);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                this.finish(); // back button
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}