package edu.neu.fitness_38;

public class FoodBean {
    private String FoodName;
    private Integer foodCalorie;
    private Double Carb;
    private Double Fat;
    private Double Protein;
    private String Unit;

    public String getFoodName() {
        return FoodName;
    }

    public void setFoodName(String foodName) {
        this.FoodName = foodName;
    }

    public Integer getFoodCalorie() {
        return foodCalorie;
    }

    public void setFoodCalorie(Integer foodCalorie) {
        this.foodCalorie = foodCalorie;
    }

    public Double getCarb() {
        return Carb;
    }

    public void setCarb(Double carb) {
        Carb = carb;
    }

    public Double getFat() {
        return Fat;
    }

    public void setFat(Double fat) {
        Fat = fat;
    }

    public Double getProtein() {
        return Protein;
    }

    public void setProtein(Double protein) {
        Protein = protein;
    }

    public String getUnit() {
        return Unit;
    }

    public void setUnit(String unit) {
        Unit = unit;
    }
}
