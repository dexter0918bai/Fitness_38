package edu.neu.fitness_38;

/**
 * user class
 */
public class UserModel {
    private String id = "";
    private String gender = "";
    private String name = "";
    private String doB = "";
    private String height;
    private String weight;
    private String targetWeight;
    private int targetCalorie;
    private int targetStep ;

    public int getTargetCalorie(){
        return targetCalorie;
    }

    public  UserModel setTargetClorie(){
        this.targetCalorie = targetCalorie;
        return this;
    }

    public int getTargetStep(){
        return targetStep;
    }

    public UserModel setTargetStep(){
        this.targetStep = targetStep;
        return this;
    }


    public String getId() {
        return id;
    }

    public UserModel setId(String id) {
        this.id = id;
        return this;
    }

    public String getGender() {
        return gender;
    }

    public UserModel setGender(String gender) {
        this.gender = gender;
        return this;
    }

    public String getName() {
        return name;
    }

    public UserModel setName(String name) {
        this.name = name;
        return this;
    }

    public String getDoB() {
        return doB;
    }

    public UserModel setDoB(String doB) {
        this.doB = doB;
        return this;
    }

    public String getHeight() {
        return height;
    }

    public UserModel setHeight(String height) {
        this.height = height;
        return this;
    }

    public String getWeight() {
        return weight;
    }

    public UserModel setWeight(String weight) {
        this.weight = weight;
        return this;
    }

    public String getTargetWeight() {
        return targetWeight;
    }

    public UserModel setTargetWeight(String targetWeight) {
        this.targetWeight = targetWeight;
        return this;
    }
}
