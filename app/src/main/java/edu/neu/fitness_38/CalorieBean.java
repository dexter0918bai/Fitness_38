package edu.neu.fitness_38;

public class CalorieBean {
    private String date;
    private int step;
    private double calorie;

    private double Protein = 0.01;
    private double Fat=0.01;
    private double Carb=0.01;

    public void setCalorie(double calorie) {
        this.calorie = calorie;
    }

    public Double getProtein() {
        return Protein;
    }

    public void setProtein(Double protein) {
        Protein = protein;
    }

    public Double getFat() {
        return Fat;
    }

    public void setFat(Double fat) {
        Fat = fat;
    }

    public Double getCarb() {
        return Carb;
    }

    public void setCarb(Double carb) {
        Carb = carb;
    }

    public Double getCalorie() {
        return calorie;
    }

    public void setCalorie(Double calorie) {
        this.calorie = calorie;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getStep() {
        return step;
    }

    public void setStep(int step) {
        this.step = step;
    }

    @Override
    public String toString() {
        return "FitnessBean{" +
                "date='" + date + '\'' +
                ", step=" + step +
                '}';
    }
}

