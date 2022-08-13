package edu.neu.fitness_38.StepCounter;

import android.util.Log;

public class StepCount implements StepCountListener {

    private long timeOfThisPeak = 0;
    private StepPassListener stepPassListener;
    private StepDetector stepDetector;

    public StepCount() {
        stepDetector = new StepDetector();
        stepDetector.initListener(this);
    }

    @Override
    public void countStep() {
        this.timeOfLastPeak = this.timeOfThisPeak;
        this.timeOfThisPeak = System.currentTimeMillis();
        Log.i("countStep", "传感器数据刷新回调");
//        notifyListener();
        if (this.timeOfThisPeak - this.timeOfLastPeak <= 3000L) {
            if (this.count < 9) {
                this.count++;
            } else if (this.count == 9) {
                this.count++;
                this.mCount += this.count;
                notifyListener();
            } else {
                this.mCount++;
                notifyListener();
            }
        } else {//超时
            this.count = 1;//为1,不是0
        }

    }

    public void setSteps(int initNowBusu) {
        this.mCount = initNowBusu;//接收上层调用传递过来的当前步数
        this.count = 0;
        timeOfLastPeak = 0;
        timeOfThisPeak = 0;
        notifyListener();
    }

    private int mCount;
    private int count;
    private long timeOfLastPeak = 0;


    public StepDetector getStepDetector() {
        return stepDetector;
    }

    public void notifyListener() {
        if (this.stepPassListener != null) {
            this.stepPassListener.stepChanged(this.mCount);
        }
    }

    public void initListener(StepPassListener listener) {
        this.stepPassListener = listener;
    }
}