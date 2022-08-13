package edu.neu.fitness_38;

public class UserBean {
    private String id = "";
    private String gender = "";
    private String name = "";
    private String doB = "";
    private String height;
    private String weight;
    private String targetWeight;
    private int targetCalorie;
    private int targetStep ;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDoB() {
        return doB;
    }

    public void setDoB(String doB) {
        this.doB = doB;
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public String getTargetWeight() {
        return targetWeight;
    }

    public void setTargetWeight(String targetWeight) {
        this.targetWeight = targetWeight;
    }

    public int getTargetCalorie() {
        return targetCalorie;
    }

    public void setTargetCalorie(int targetCalorie) {
        this.targetCalorie = targetCalorie;
    }

    public int getTargetStep() {
        return targetStep;
    }

    public void setTargetStep(int targetStep) {
        this.targetStep = targetStep;
    }
}
