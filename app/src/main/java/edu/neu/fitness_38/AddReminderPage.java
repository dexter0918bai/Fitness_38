package edu.neu.fitness_38;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.google.gson.Gson;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;


public class AddReminderPage extends AppCompatActivity {

    private EditText description;
    private TextView myTextDisplayDate;
    private TextView myTextDisplayTime;
    private DatePickerDialog.OnDateSetListener myDateSetListener;
    private TimePickerDialog.OnTimeSetListener myTimeSetListener;
    private Button done;
    private Button cancel;
    private SharedPreferences mSharedPreference;
    private SharedPreferences.Editor mSharedEditor;
    private Gson gson;
    private ReminderObj reminder;
    private int id;
    private CheckBox isFinish;

    //date image view
    private ImageView displayDateImg;
    private ImageView displayTimeImg;


    // Some local variables needed for saving pictures to storage
    static final int REQUEST_IMAGE_CAPTURE = 1;
    String currentPhotoPath; //can be retrieved later
    String currentRecordingPath;

    //record activity result launcher
    ActivityResultLauncher<Intent> recordLauncher;

    //button animation.
    Animation scaleUp, scaleDown;

    List<ReminderObj> reminderList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_reminder_page);

        reminder = new ReminderObj();
        description = (EditText) findViewById(R.id.editTextTaskName);
        myTextDisplayDate = (TextView) findViewById(R.id.dateSelector);
        myTextDisplayTime = (TextView) findViewById(R.id.timeSelector);
        done = (Button) findViewById(R.id.saveData);
        cancel = findViewById(R.id.btn_cancel);
        isFinish = findViewById(R.id.checkBox_finish);

        displayTimeImg = (ImageView) findViewById(R.id.timeImageView);
        displayDateImg = (ImageView) findViewById(R.id.dataImageView);

        // set animaiton.
        scaleUp = AnimationUtils.loadAnimation(this, R.anim.scale_up);
        scaleDown = AnimationUtils.loadAnimation(this, R.anim.scale_down);

        // Initialization.
        initialSetting();

    }

    // Go back to the previous screen
    public void backtoMain(){
        System.out.println("back to main");
        String date = ((TextView)findViewById(R.id.dateSelector)).getText().toString();
        String time = ((TextView)findViewById(R.id.timeSelector)).getText().toString();
        String des = description.getText().toString();
        System.out.println("date: " + date);
        System.out.println("time: " + time);
        if("".equals(date) || "".equals(time) || "".equals(des)) {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setMessage("time, date or description cannot be empty")
                    .setCancelable(true)
                    .create()
                    .show();
            return;
        }

        if (isFinish.isChecked()) {
            if (id >= 0) {
                reminderList.remove(id);
            }

        } else {
            if (id >= 0) {
                System.out.println("id:" + id);
                reminderList.set(id, new ReminderObj(id, des, date, time));

            } else {
                int nextId = MyApplication.getNextId();
                reminderList.add(new ReminderObj(nextId, des, date, time));
                MyApplication.setNextId(++nextId);
            }
        }

        System.out.println("checkbox: " + isFinish.isChecked());

        int[] dateSplit = toIntArray(date.split("/"));
        int[] timeSplit = toIntArray(time.split(":"));
//        String des = mDescription.getText().toString();

        System.out.println(Arrays.toString(dateSplit));
        System.out.println(Arrays.toString(timeSplit));

        Intent intent = new Intent(this, Reminder.class);
        startActivity(intent);
    }

    //help converting string array to int array
    private int[] toIntArray(String[] strings) {
        int[] result = new int[strings.length];
        for(int i = 0; i < strings.length; i ++) {
            result[i] = Integer.parseInt(strings[i]);
        }
        return result;
    }

    /**
     * A helper method to set all functions in this activity. (Now set time and date clickable to
     * set time and date).
     *
     */
    private void initialSetting() {
        // set click listener to date text and image to open date setting dialog.
        myTextDisplayDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDateSelecotr();
            }
        });

        setTextViewAnimaiton(myTextDisplayDate);

        displayDateImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDateSelecotr();
            }
        });

        setImageViewAnimaiton(displayDateImg);



        // set date pick listener.
        myDateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                int showMonth = month + 1; // since the month is 0-based data.
                String dateString = String.format("%d/%d/%d", showMonth, day, year);
                reminder.date = dateString;
                myTextDisplayDate.setText(dateString);
            }
        };

        // set click listener to time text and image to open time setting dialog.
        myTextDisplayTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showTimeSelector();
            }
        });

        setTextViewAnimaiton(myTextDisplayTime);

        displayTimeImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showTimeSelector();
            }
        });

        setImageViewAnimaiton(displayTimeImg);

        // set time pick listener.
        myTimeSetListener = new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int hour, int minute) {
                String timeString = String.format("%d:%d", hour, minute);
                reminder.time = timeString;
                myTextDisplayTime.setText(timeString);
            }
        };



        // done button action.
        done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //call backtoMain before settingDone
                //do not call backtoMain twice
                backtoMain();
//                settingDone();
            }
        });

        setButtonAnimaiton(done);

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AddReminderPage.this, Reminder.class));
            }
        });

        reminderList = MyApplication.getReminderList();

        // create form or edit form
        Intent intent = getIntent();
        id = intent.getIntExtra("id", -1);
        ReminderObj reminderObj = null;
        if (id >= 0) {
            // edit form
            for (ReminderObj p: reminderList) {
                if (p.getId() == id) {
                    reminderObj = p;
                }
            }
            description.setText(reminderObj.getDescription());
            myTextDisplayDate.setText(String.valueOf(reminderObj.getDate()));
            myTextDisplayTime.setText(reminderObj.getTime());

        } else {
            // create form
        }

    }

    //Helper to set button animation.
    private void setButtonAnimaiton(Button btn) {
        btn.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
                    btn.startAnimation(scaleUp);
                } else if (motionEvent.getAction() == MotionEvent.ACTION_UP){
                    btn.startAnimation(scaleDown);
                }
                return false;
            }
        });
    }

    //Helper to set textView animation.
    private void setTextViewAnimaiton(TextView txt) {
        txt.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
                    txt.startAnimation(scaleUp);
                } else if (motionEvent.getAction() == MotionEvent.ACTION_UP){
                    txt.startAnimation(scaleDown);
                }
                return false;
            }
        });
    }

    //Helper to set textView animation.
    private void setImageViewAnimaiton(ImageView img) {
        img.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
                    img.startAnimation(scaleUp);
                } else if (motionEvent.getAction() == MotionEvent.ACTION_UP){
                    img.startAnimation(scaleDown);
                }
                return false;
            }
        });
    }


    // This is a helper method to show date selector screen.
    private void showDateSelecotr() {
        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        int day  = cal.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog dialog = new DatePickerDialog(AddReminderPage.this,
                android.R.style.Theme_Holo_Light_Dialog_MinWidth, myDateSetListener,
                year, month, day);

        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.show();
    }

    // This is a helper method to show time selector screen.
    private void showTimeSelector() {
        Calendar cal = Calendar.getInstance();
        int hour = cal.get(Calendar.HOUR_OF_DAY);
        int minute = cal.get(Calendar.MINUTE);
        TimePickerDialog dialog = new TimePickerDialog(AddReminderPage.this,
                android.R.style.Theme_Holo_Light_Dialog_MinWidth, myTimeSetListener,
                hour, minute, true);

        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.show();
    }

    /*
    Default path for the images:
    Android/data/edu.neu.madcourse.cs5520_sp22_final_project/files/Pictures
     */
    Toast toast;

    // Create temporary images files in the designated path
    // this temp image will be replaced by the image taken by the user
    private File createImageFile() throws IOException {
        // Create an image file name
        @SuppressLint("SimpleDateFormat") String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "JPEG_" + timeStamp + "_";

        File storageDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File image = File.createTempFile(
                imageFileName,   //prefix
                ".jpg",         // suffix
                storageDir      // directory
        );

        // Save a file: path for use with ACTION_VIEW intents
        currentPhotoPath = image.getAbsolutePath();
        return image;
    }


    /**
     * Handle user used back button.
     */
    @Override
    public void onBackPressed() {
        AlertDialog.Builder exitDialog = new AlertDialog.Builder(this);
        exitDialog.setMessage("Your changes will not be saved.\n" +
                "Do you want to back to main menu?");
//        exitDialog.setMessage("Auto save changes......");

        exitDialog.setPositiveButton("OK" , new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                // User clicked OK button
//                backtoMain();
//                settingDone();
                finish();
            }
        });
        exitDialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                // User cancelled the dialog, do nothing.
                dialog.cancel();
            }
        });
        exitDialog.create().show();
    }
}


