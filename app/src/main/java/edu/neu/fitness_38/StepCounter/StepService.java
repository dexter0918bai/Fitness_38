package edu.neu.fitness_38.StepCounter;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.Service;
import android.content.Intent;
import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Binder;
import android.os.Build;
import android.os.IBinder;
import android.util.Log;

import edu.neu.fitness_38.R;

public class StepService extends Service implements SensorEventListener {
    static final int LOCATION_SERVICE = 100;


    private int buSu = 0;
    private SensorManager sensorManager;
    private StepCount mStepCount;
    private StepUi mCallback;
    private static int stepSensorType = -1;
    private boolean hasRecord = false;
    private int previousStep = 0;

    private StepBinder stepBinder = new StepBinder();

    public StepService() {
    }

    @Override
    public void onCreate() {
        super.onCreate();
        new Thread(() -> goStep()).start();
    }

    private void goStep() {
        if (sensorManager != null) {
            sensorManager = null;
        }
        int versionCodes = Build.VERSION.SDK_INT;
        sensorManager = (SensorManager) this.getSystemService(SENSOR_SERVICE);
        if (versionCodes >= 19) {
            setStepCount();
        } else {
            setListener();
        }
    }

    private int haveStepCount = 0;

    private void setStepCount() {
        Sensor countSensor = sensorManager.getDefaultSensor(Sensor.TYPE_STEP_COUNTER);
        Sensor detectorSensor = sensorManager.getDefaultSensor(Sensor.TYPE_STEP_DETECTOR);
        if (countSensor != null) {
            stepSensorType = Sensor.TYPE_STEP_COUNTER;
            sensorManager.registerListener(this, countSensor, SensorManager.SENSOR_DELAY_NORMAL);
        } else if (detectorSensor != null) {
            stepSensorType = Sensor.TYPE_STEP_DETECTOR;
            sensorManager.registerListener(this, detectorSensor, SensorManager.SENSOR_DELAY_NORMAL);
        } else {
            setListener();
        }
    }


    private void setListener() {
        mStepCount = new StepCount();
        mStepCount.setSteps(buSu);
        Sensor sensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        sensorManager.registerListener(mStepCount.getStepDetector(), sensor, SensorManager.SENSOR_DELAY_UI);
        mStepCount.initListener(new StepPassListener() {
            @Override
            public void stepChanged(int steps) {
                buSu = steps;
                setStep(0);
            }

            @Override
            public void stepFinish() {

            }
        });
    }

    private void setStep(int thisStepCount) {
        if (mCallback != null) {
            mCallback.onStepListener(thisStepCount);
        }
    }

    @Override
    public IBinder onBind(Intent intent) {
        return stepBinder;
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        if (stepSensorType == Sensor.TYPE_STEP_COUNTER) {
            int tempStep = (int) event.values[0];
            if (!hasRecord) {
                hasRecord = true;
                haveStepCount = tempStep;
            } else {
                int thisStepCount = tempStep - haveStepCount;
                int thisStep = thisStepCount - previousStep;
                buSu += (thisStep);
                previousStep = thisStepCount;
                setStep(thisStepCount);
            }
        } else if (stepSensorType == Sensor.TYPE_STEP_DETECTOR) {
            if (event.values[0] == 1.0) {
                buSu++;
            }
        }
    }

    static final String LOCATION_CHANNEL = "location_channel";


    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

    public class StepBinder extends Binder {
        public StepService getService() {
            return StepService.this;
        }
    }

    public void setCallback(StepUi paramICallback) {
        this.mCallback = paramICallback;
    }

    private NotificationManager manager;

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        createLocationForegroundNotification();
        return START_STICKY;
    }

    private void createLocationForegroundNotification() {
        NotificationChannel notificationChannel = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            notificationChannel = new NotificationChannel(LOCATION_CHANNEL,
                    NOTIFICATION_CHANNEL, NotificationManager.IMPORTANCE_HIGH);
            notificationChannel.enableLights(true);
            notificationChannel.setLightColor(Color.RED);
            notificationChannel.setShowBadge(true);
            notificationChannel.setLockscreenVisibility(Notification.VISIBILITY_PUBLIC);
            manager.createNotificationChannel(notificationChannel);
        }

        Notification notification =
                null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            notification = new Notification.Builder(this)
                    .setSmallIcon(R.mipmap.ic_launcher)
                    .setContentText("start")
                    .setChannelId(LOCATION_CHANNEL)
                    .build();
        }
        startForeground(LOCATION_SERVICE, notification);
    }

    @Override
    public boolean onUnbind(Intent intent) {
        return super.onUnbind(intent);
    }

    static final String NOTIFICATION_CHANNEL = "fitness38";
    private static final String TAG = StepService.class.getSimpleName();

}
