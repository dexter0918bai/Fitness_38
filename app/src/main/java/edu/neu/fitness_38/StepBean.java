package edu.neu.fitness_38;

public class StepBean {
    private String date;
    private int stepCount;
    private float calories;

    private double Protein = 0.1;
    private double Fat = 0.1;
    private double Carb = 0.1;

    public double getProtein() {
        return Protein;
    }

    public void setProtein(double protein) {
        Protein = protein;
    }

    public double getFat() {
        return Fat;
    }

    public void setFat(double fat) {
        Fat = fat;
    }

    public double getCarb() {
        return Carb;
    }

    public void setCarb(double carb) {
        Carb = carb;
    }

    public float getCalories() {
        return calories;
    }

    public void setCalories(float calories) {
        this.calories = calories;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getStepCount() {
        return stepCount;
    }

    public void setStepCount(int stepCount) {
        this.stepCount = stepCount;
    }
}
