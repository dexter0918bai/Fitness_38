package edu.neu.fitness_38;

public class AllFood {
    public static String TODAY="";
    public static final String foodList="[{\n" +
            "\n" +
            "\t\t\"FoodName\": \"apple\",\n" +
            "\t\t\"foodCalorie\": 110,\n" +
            "\t\t\"Carb\": 29.3,\n" +
            "\t\t\"Fat\": 0.4,\n" +
            "\t\t\"Protein\": 0.6,\n" +
            "\t\t\"Unit\": \"per\"\n" +
            "\t},\n" +
            "\t{\n" +
            "\t\t\"FoodName\": \"orange\",\n" +
            "\t\t\"foodCalorie\": 30,\n" +
            "\t\t\"Carb\": 200,\n" +
            "\t\t\"Fat\": 300,\n" +
            "\t\t\"Protein\": 30,\n" +
            "\t\t\"Unit\": \"per\"\n" +
            "\t},\n" +
            "\t{\n" +
            "\t\t\"FoodName\": \"Grape\",\n" +
            "\t\t\"foodCalorie\": 69,\n" +
            "\t\t\"Carb\": 18.1,\n" +
            "\t\t\"Fat\": 0.2,\n" +
            "\t\t\"Protein\": 0.7,\n" +
            "\t\t\"Unit\": \"100g\"\n" +
            "\t},\n" +
            "\t{\n" +
            "\t\t\"FoodName\": \"banana\",\n" +
            "\t\t\"foodCalorie\": 105,\n" +
            "\t\t\"Carb\": 27,\n" +
            "\t\t\"Fat\": 0.4,\n" +
            "\t\t\"Protein\": 1.3,\n" +
            "\t\t\"Unit\": \"per\"\n" +
            "\t}, {\n" +
            "\t\t\"FoodName\": \"Avocado\",\n" +
            "\t\t\"foodCalorie\": 20,\n" +
            "\t\t\"Carb\": 0.6,\n" +
            "\t\t\"Fat\": 1.8,\n" +
            "\t\t\"Protein\": 0.6,\n" +
            "\t\t\"Unit\": \"10g\"\n" +
            "\t}, {\n" +
            "\t\t\"FoodName\": \"Watermelon\",\n" +
            "\t\t\"foodCalorie\": 30,\n" +
            "\t\t\"Carb\": 7.6,\n" +
            "\t\t\"Fat\": 0.1,\n" +
            "\t\t\"Protein\": 0.6,\n" +
            "\t\t\"Unit\": \"100g\"\n" +
            "\t}, {\n" +
            "\t\t\"FoodName\": \"Pineapple\",\n" +
            "\t\t\"foodCalorie\": 50,\n" +
            "\t\t\"Carb\": 13.1,\n" +
            "\t\t\"Fat\": 0.1,\n" +
            "\t\t\"Protein\": 0.5,\n" +
            "\t\t\"Unit\": \"100g\"\n" +
            "\t}, {\n" +
            "\t\t\"FoodName\": \"Pear\",\n" +
            "\t\t\"foodCalorie\": 95,\n" +
            "\t\t\"Carb\": 25.3,\n" +
            "\t\t\"Fat\": 0.2,\n" +
            "\t\t\"Protein\": 0.6,\n" +
            "\t\t\"Unit\": \"per\"\n" +
            "\t}, {\n" +
            "\t\t\"FoodName\": \"Strawberry\",\n" +
            "\t\t\"foodCalorie\": 32,\n" +
            "\t\t\"Carb\": 7.7,\n" +
            "\t\t\"Fat\": 0.3,\n" +
            "\t\t\"Protein\": 0.7,\n" +
            "\t\t\"Unit\": \"100g\"\n" +
            "\t}, {\n" +
            "\t\t\"FoodName\": \"Kiwi\",\n" +
            "\t\t\"foodCalorie\": 108,\n" +
            "\t\t\"Carb\": 26,\n" +
            "\t\t\"Fat\": 0.9,\n" +
            "\t\t\"Protein\": 2,\n" +
            "\t\t\"Unit\": \"per\"\n" +
            "\t}, {\n" +
            "\t\t\"FoodName\": \"Papaya\",\n" +
            "\t\t\"foodCalorie\": 43,\n" +
            "\t\t\"Carb\": 10.8,\n" +
            "\t\t\"Fat\": 0.3,\n" +
            "\t\t\"Protein\": 0.5,\n" +
            "\t\t\"Unit\": \"100g\"\n" +
            "\t}, {\n" +
            "\t\t\"FoodName\": \"Blueberry\",\n" +
            "\t\t\"foodCalorie\": 57,\n" +
            "\t\t\"Carb\": 14.5,\n" +
            "\t\t\"Fat\": 0.3,\n" +
            "\t\t\"Protein\": 0.7,\n" +
            "\t\t\"Unit\": \"100g\"\n" +
            "\t}, {\n" +
            "\t\t\"FoodName\": \"Blackberry\",\n" +
            "\t\t\"foodCalorie\": 43,\n" +
            "\t\t\"Carb\": 9.6,\n" +
            "\t\t\"Fat\": 0.5,\n" +
            "\t\t\"Protein\": 1.4,\n" +
            "\t\t\"Unit\": \"100g\"\n" +
            "\t}, {\n" +
            "\t\t\"FoodName\": \"Cherry\",\n" +
            "\t\t\"foodCalorie\": 63,\n" +
            "\t\t\"Carb\": 16,\n" +
            "\t\t\"Fat\": 0.2,\n" +
            "\t\t\"Protein\": 1.1,\n" +
            "\t\t\"Unit\": \"100g\"\n" +
            "\t}, {\n" +
            "\t\t\"FoodName\": \"Cherry tomato\",\n" +
            "\t\t\"foodCalorie\": 18,\n" +
            "\t\t\"Carb\": 3.9,\n" +
            "\t\t\"Fat\": 0.2,\n" +
            "\t\t\"Protein\": 0.9,\n" +
            "\t\t\"Unit\": \"100g\"\n" +
            "\t}, {\n" +
            "\t\t\"FoodName\": \"Tomato\",\n" +
            "\t\t\"foodCalorie\": 18,\n" +
            "\t\t\"Carb\": 3.9,\n" +
            "\t\t\"Fat\": 0.2,\n" +
            "\t\t\"Protein\": 0.9,\n" +
            "\t\t\"Unit\": \"100g\"\n" +
            "\t}, {\n" +
            "\t\t\"FoodName\": \"Pomelo\",\n" +
            "\t\t\"foodCalorie\": 38,\n" +
            "\t\t\"Carb\": 9.6,\n" +
            "\t\t\"Fat\": 0,\n" +
            "\t\t\"Protein\": 0.8,\n" +
            "\t\t\"Unit\": \"100g\"\n" +
            "\t}, {\n" +
            "\t\t\"FoodName\": \"Eggplant\",\n" +
            "\t\t\"foodCalorie\": 25,\n" +
            "\t\t\"Carb\": 5.9,\n" +
            "\t\t\"Fat\": 0.2,\n" +
            "\t\t\"Protein\": 1,\n" +
            "\t\t\"Unit\": \"100g\"\n" +
            "\t},\n" +
            "\t{\n" +
            "\t\t\"FoodName\": \"Squash\",\n" +
            "\t\t\"foodCalorie\": 16,\n" +
            "\t\t\"Carb\": 3.4,\n" +
            "\t\t\"Fat\": 0.2,\n" +
            "\t\t\"Protein\": 1.2,\n" +
            "\t\t\"Unit\": \"100g\"\n" +
            "\t},\n" +
            "\t{\n" +
            "\t\t\"FoodName\": \"Mini cucumber\",\n" +
            "\t\t\"foodCalorie\": 12,\n" +
            "\t\t\"Carb\": 2.4,\n" +
            "\t\t\"Fat\": 0,\n" +
            "\t\t\"Protein\": 1.2,\n" +
            "\t\t\"Unit\": \"100g\"\n" +
            "\n" +
            "\t}, {\n" +
            "\t\t\"FoodName\": \" Cucumber \",\n" +
            "\t\t\"foodCalorie\": 8,\n" +
            "\t\t\"Carb\": 1.9,\n" +
            "\t\t\"Fat\": 0.1,\n" +
            "\t\t\"Protein\": 0.3,\n" +
            "\t\t\"Unit\": \"100g\"\n" +
            "\t}, {\n" +
            "\t\t\"FoodName\": \"Lettuce\",\n" +
            "\t\t\"foodCalorie\": 14,\n" +
            "\t\t\"Carb\": 3,\n" +
            "\t\t\"Fat\": 0.1,\n" +
            "\t\t\"Protein\": 0.9,\n" +
            "\t\t\"Unit\": \"100g\"\n" +
            "\t}, {\n" +
            "\t\t\"FoodName\": \"Roman Lettuce\",\n" +
            "\t\t\"foodCalorie\": 15,\n" +
            "\t\t\"Carb\": 2.9,\n" +
            "\t\t\"Fat\": 0.2,\n" +
            "\t\t\"Protein\": 1.4,\n" +
            "\t\t\"Unit\": \"100g\"\n" +
            "\t}, {\n" +
            "\t\t\"FoodName\": \"Cabbage\",\n" +
            "\t\t\"foodCalorie\": 25,\n" +
            "\t\t\"Carb\": 5.8,\n" +
            "\t\t\"Fat\": 0.1,\n" +
            "\t\t\"Protein\": 1.3,\n" +
            "\t\t\"Unit\": \"100g\"\n" +
            "\t}, {\n" +
            "\t\t\"FoodName\": \"Napa cabbage\",\n" +
            "\t\t\"foodCalorie\": 100,\n" +
            "\t\t\"Carb\": 2.2,\n" +
            "\t\t\"Fat\": 0.2,\n" +
            "\t\t\"Protein\": 1.1,\n" +
            "\t\t\"Unit\": \"100g\"\n" +
            "\t}, {\n" +
            "\t\t\"FoodName\": \"Radish\",\n" +
            "\t\t\"foodCalorie\": 69,\n" +
            "\t\t\"Carb\": 18.1,\n" +
            "\t\t\"Fat\": 0.2,\n" +
            "\t\t\"Protein\": 0.7\n" +
            "\t}, {\n" +
            "\t\t\"FoodName\": \"Celery\",\n" +
            "\t\t\"foodCalorie\": 14,\n" +
            "\t\t\"Carb\": 3,\n" +
            "\t\t\"Fat\": 0.2,\n" +
            "\t\t\"Protein\": 0.7,\n" +
            "\t\t\"Unit\": \"100g\"\n" +
            "\t}, {\n" +
            "\t\t\"FoodName\": \"Broccoli\",\n" +
            "\t\t\"foodCalorie\": 34,\n" +
            "\t\t\"Carb\": 6.6,\n" +
            "\t\t\"Fat\": 0.4,\n" +
            "\t\t\"Protein\": 2.8,\n" +
            "\t\t\"Unit\": \"100g\"\n" +
            "\t}, {\n" +
            "\t\t\"FoodName\": \"Carrot\",\n" +
            "\t\t\"foodCalorie\": 41,\n" +
            "\t\t\"Carb\": 9.6,\n" +
            "\t\t\"Fat\": 0.2,\n" +
            "\t\t\"Protein\": 0.9,\n" +
            "\t\t\"Unit\": \"100g\"\n" +
            "\t}, {\n" +
            "\t\t\"FoodName\": \"Baby carrot\",\n" +
            "\t\t\"foodCalorie\": 35,\n" +
            "\t\t\"Carb\": 8.3,\n" +
            "\t\t\"Fat\": 0.1,\n" +
            "\t\t\"Protein\": 0.7,\n" +
            "\t\t\"Unit\": \"100g\"\n" +
            "\t}, {\n" +
            "\t\t\"FoodName\": \"Black Coffee\",\n" +
            "\t\t\"foodCalorie\":2 ,\n" +
            "\t\t\"Carb\": 0,\n" +
            "\t\t\"Fat\": 0,\n" +
            "\t\t\"Protein\": 0.2,\n" +
            "\"Unit\": \"200ml\"\n" +
            "\t}, {\n" +
            "\t\t\"FoodName\": \"Pumpkin\",\n" +
            "\t\t\"foodCalorie\": 26,\n" +
            "\t\t\"Carb\": 6.5,\n" +
            "\t\t\"Fat\": 0.1,\n" +
            "\t\t\"Protein\": 1,\n" +
            "\t\t\"Unit\": \"100g\"\n" +
            "\t}, {\n" +
            "\t\t\"FoodName\": \"Sweet potato\",\n" +
            "\t\t\"foodCalorie\": 90,\n" +
            "\t\t\"Carb\": 20.7,\n" +
            "\t\t\"Fat\": 0.2,\n" +
            "\t\t\"Protein\": 2,\n" +
            "\t\t\"Unit\": \"100g\"\n" +
            "\t}, {\n" +
            "\t\t\"FoodName\": \"Potato\",\n" +
            "\t\t\"foodCalorie\": 92,\n" +
            "\t\t\"Carb\": 21.1,\n" +
            "\t\t\"Fat\": 0.2,\n" +
            "\t\t\"Protein\": 2.1,\n" +
            "\t\t\"Unit\": \"100g\"\n" +
            "\t}, {\n" +
            "\t\t\"FoodName\": \"Chicory root\",\n" +
            "\t\t\"foodCalorie\": 72,\n" +
            "\t\t\"Carb\": 17.5,\n" +
            "\t\t\"Fat\": 0.2,\n" +
            "\t\t\"Protein\": 1.4,\n" +
            "\t\t\"Unit\": \"100g\"\n" +
            "\t}, {\n" +
            "\t\t\"FoodName\": \"Kale\",\n" +
            "\t\t\"foodCalorie\": 35,\n" +
            "\t\t\"Carb\": 4.4,\n" +
            "\t\t\"Fat\": 1.5,\n" +
            "\t\t\"Protein\": 2.9,\n" +
            "\t\t\"Unit\": \"100g\"\n" +
            "\t}, {\n" +
            "\t\t\"FoodName\": \"Bok choy\",\n" +
            "\t\t\"foodCalorie\": 13,\n" +
            "\t\t\"Carb\": 13,\n" +
            "\t\t\"Fat\": 0.2,\n" +
            "\t\t\"Protein\": 1.5,\n" +
            "\t\t\"Unit\": \"100g\"\n" +
            "\t}, {\n" +
            "\t\t\"FoodName\": \"Spinach\",\n" +
            "\t\t\"foodCalorie\": 23,\n" +
            "\t\t\"Carb\": 3.6,\n" +
            "\t\t\"Fat\": 0.4,\n" +
            "\t\t\"Protein\": 2.9,\n" +
            "\t\t\"Unit\": \"100g\"\n" +
            "\t}, {\n" +
            "\t\t\"FoodName\": \"Baby Spinach\",\n" +
            "\t\t\"foodCalorie\": 24,\n" +
            "\t\t\"Carb\": 5.9,\n" +
            "\t\t\"Fat\": 0,\n" +
            "\t\t\"Protein\": 3.5,\n" +
            "\t\t\"Unit\": \"100g\"\n" +
            "\t}, {\n" +
            "\t\t\"FoodName\": \"Onion\",\n" +
            "\t\t\"foodCalorie\": 40,\n" +
            "\t\t\"Carb\": 9.3,\n" +
            "\t\t\"Fat\": 0.1,\n" +
            "\t\t\"Protein\": 1.1,\n" +
            "\t\t\"Unit\": \"100g\"\n" +
            "\t}, {\n" +
            "\t\t\"FoodName\": \"Scrambled Egg\",\n" +
            "\t\t\"foodCalorie\": 91,\n" +
            "\t\t\"Carb\": 1,\n" +
            "\t\t\"Fat\": 6.7,\n" +
            "\t\t\"Protein\": 6.1,\n" +
            "\t\t\"Unit\": \"per\"\n" +
            "\t}, {\n" +
            "\t\t\"FoodName\": \"Boiled Egg\",\n" +
            "\t\t\"foodCalorie\": 77,\n" +
            "\t\t\"Carb\": 0.6,\n" +
            "\t\t\"Fat\": 5.3,\n" +
            "\t\t\"Protein\": 6.3,\n" +
            "\t\t\"Unit\": \"per\"\n" +
            "\t}, {\n" +
            "\t\t\"FoodName\": \"Olive oil\",\n" +
            "\t\t\"foodCalorie\": 40,\n" +
            "\t\t\"Carb\": 0,\n" +
            "\t\t\"Fat\": 3.5,\n" +
            "\t\t\"Protein\": 0,\n" +
            "\"Unit\": \"1tsp\"\n" +
            "\t}, {\n" +
            "\t\t\"FoodName\": \"Egg White\",\n" +
            "\t\t\"foodCalorie\": 52,\n" +
            "\t\t\"Carb\": 0.7,\n" +
            "\t\t\"Fat\": 0.2,\n" +
            "\t\t\"Protein\": 10.9,\n" +
            "\t\t\"Unit\": \"100g\"\n" +
            "\t}, {\n" +
            "\t\t\"FoodName\": \"Tuna\",\n" +
            "\t\t\"foodCalorie\": 144,\n" +
            "\t\t\"Carb\": 0,\n" +
            "\t\t\"Fat\": 4.9,\n" +
            "\t\t\"Protein\": 23.3,\n" +
            "\t\t\"Unit\": \"100g\"\n" +
            "\t}, {\n" +
            "\t\t\"FoodName\": \"Raw Salmon\",\n" +
            "\t\t\"foodCalorie\": 127,\n" +
            "\t\t\"Carb\": 0,\n" +
            "\t\t\"Fat\": 4.4,\n" +
            "\t\t\"Protein\": 20.5,\n" +
            "\t\t\"Unit\": \"100g\"\n" +
            "\t}, {\n" +
            "\t\t\"FoodName\": \"Scallops\",\n" +
            "\t\t\"foodCalorie\": 111,\n" +
            "\t\t\"Carb\": 5.4,\n" +
            "\t\t\"Fat\": 0.8,\n" +
            "\t\t\"Protein\": 20.5,\n" +
            "\t\t\"Unit\": \"100g\"\n" +
            "\t}, {\n" +
            "\t\t\"FoodName\": \"Shrimp\",\n" +
            "\t\t\"foodCalorie\": 85,\n" +
            "\t\t\"Carb\": 0,\n" +
            "\t\t\"Fat\": 0.5,\n" +
            "\t\t\"Protein\": 20.1,\n" +
            "\t\t\"Unit\": \"100g\"\n" +
            "\t}, {\n" +
            "\t\t\"FoodName\": \"Steak\",\n" +
            "\t\t\"foodCalorie\": 243,\n" +
            "\t\t\"Carb\": 0,\n" +
            "\t\t\"Fat\": 14.2,\n" +
            "\t\t\"Protein\": 27,\n" +
            "\t\t\"Unit\": \"100g\"\n" +
            "\t}, {\n" +
            "\t\t\"FoodName\": \"Sirloin Steak\",\n" +
            "\t\t\"foodCalorie\": 195,\n" +
            "\t\t\"Carb\": 0,\n" +
            "\t\t\"Fat\": 8.5,\n" +
            "\t\t\"Protein\": 29.8,\n" +
            "\t\t\"Unit\": \"100g\"\n" +
            "\t}, {\n" +
            "\t\t\"FoodName\": \"Cauliflower\",\n" +
            "\t\t\"foodCalorie\": 25,\n" +
            "\t\t\"Carb\": 5,\n" +
            "\t\t\"Fat\": 0.3,\n" +
            "\t\t\"Protein\": 1.9,\n" +
            "\t\t\"Unit\": \"100g\"\n" +
            "\t}, {\n" +
            "\t\t\"FoodName\": \"Chicken breast\",\n" +
            "\t\t\"foodCalorie\": 108,\n" +
            "\t\t\"Carb\": 0,\n" +
            "\t\t\"Fat\": 3,\n" +
            "\t\t\"Protein\": 20.3,\n" +
            "\t\t\"Unit\": \"100g\"\n" +
            "\t},\n" +
            "\t{\n" +
            "\t\t\"FoodName\": \"Chicken leg\",\n" +
            "\t\t\"foodCalorie\": 210,\n" +
            "\t\t\"Carb\": 0,\n" +
            "\t\t\"Fat\": 10.3,\n" +
            "\t\t\"Protein\": 27.4,\n" +
            "\t\t\"Unit\": \"100g\"\n" +
            "\t}, {\n" +
            "\t\t\"FoodName\": \"White rice\",\n" +
            "\t\t\"foodCalorie\": 130,\n" +
            "\t\t\"Carb\": 28.6,\n" +
            "\t\t\"Fat\": 0.2,\n" +
            "\t\t\"Protein\": 2.4,\n" +
            "\t\t\"Unit\": \"100g\"\n" +
            "\t}, {\n" +
            "\t\t\"FoodName\": \"Chicken Wing\",\n" +
            "\t\t\"foodCalorie\": 254,\n" +
            "\t\t\"Carb\": 0,\n" +
            "\t\t\"Fat\": 16.9,\n" +
            "\t\t\"Protein\": 23.8,\n" +
            "\t\t\"Unit\": \"100g\"\n" +
            "\t}, {\n" +
            "\t\t\"FoodName\": \"Brown Rice\",\n" +
            "\t\t\"foodCalorie\": 367,\n" +
            "\t\t\"Carb\": 76.2,\n" +
            "\t\t\"Fat\": 3.2,\n" +
            "\t\t\"Protein\": 7.5,\n" +
            "\t\t\"Unit\": \"100g\"\n" +
            "\t}, {\n" +
            "\t\t\"FoodName\": \"Cauliflower Rice\",\n" +
            "\t\t\"foodCalorie\": 64,\n" +
            "\t\t\"Carb\": 4.7,\n" +
            "\t\t\"Fat\": 4.8,\n" +
            "\t\t\"Protein\": 1.8,\n" +
            "\t\t\"Unit\": \"100g\"\n" +
            "\t}, {\n" +
            "\t\t\"FoodName\": \"White Bread\",\n" +
            "\t\t\"foodCalorie\": 67,\n" +
            "\t\t\"Carb\": 12.4,\n" +
            "\t\t\"Fat\": 0.8,\n" +
            "\t\t\"Protein\": 2.2,\n" +
            "\t\t\"Unit\": \"per slice\"\n" +
            "\t}, {\n" +
            "\t\t\"FoodName\": \"Whole wheat bread\",\n" +
            "\t\t\"foodCalorie\": 71,\n" +
            "\t\t\"Carb\": 12,\n" +
            "\t\t\"Fat\": 1,\n" +
            "\t\t\"Protein\": 3.5,\n" +
            "\t\t\"Unit\": \"100g\"\n" +
            "\t}, {\n" +
            "\t\t\"FoodName\": \"Oatmeal\",\n" +
            "\t\t\"foodCalorie\": 71,\n" +
            "\t\t\"Carb\": 12,\n" +
            "\t\t\"Fat\": 1.5,\n" +
            "\t\t\"Protein\": 2.5,\n" +
            "\t\t\"Unit\": \"100g\"\n" +
            "\t}, {\n" +
            "\t\t\"FoodName\": \"Stoked purple potato\",\n" +
            "\t\t\"foodCalorie\": 150,\n" +
            "\t\t\"Carb\": 34,\n" +
            "\t\t\"Fat\": 0,\n" +
            "\t\t\"Protein\": 2,\n" +
            "\t\t\"Unit\": \"110g\"\n" +
            "\t}, {\n" +
            "\t\t\"FoodName\": \"Quinoa\",\n" +
            "\t\t\"foodCalorie\": 378,\n" +
            "\t\t\"Carb\": 71.1,\n" +
            "\t\t\"Fat\": 5.6,\n" +
            "\t\t\"Protein\": 11.1,\n" +
            "\t\t\"Unit\": \"100g\"\n" +
            "\t}, {\n" +
            "\t\t\"FoodName\": \"Corn \",\n" +
            "\t\t\"foodCalorie\": 86,\n" +
            "\t\t\"Carb\": 18.7,\n" +
            "\t\t\"Fat\": 1.4,\n" +
            "\t\t\"Protein\": 3.3,\n" +
            "\t\t\"Unit\": \"100g\"\n" +
            "\t}, {\n" +
            "\t\t\"FoodName\": \"Green beans\",\n" +
            "\t\t\"foodCalorie\": 31,\n" +
            "\t\t\"Carb\": 7,\n" +
            "\t\t\"Fat\": 0.2,\n" +
            "\t\t\"Protein\": 1.8,\n" +
            "\t\t\"Unit\": \"100g\"\n" +
            "\t}, {\n" +
            "\t\t\"FoodName\": \"Taro\",\n" +
            "\t\t\"foodCalorie\": 116,\n" +
            "\t\t\"Carb\": 27.5,\n" +
            "\t\t\"Fat\": 0.2,\n" +
            "\t\t\"Protein\": 1.6,\n" +
            "\t\t\"Unit\": \"100g\"\n" +
            "\t}, {\n" +
            "\t\t\"FoodName\": \"Lotus\",\n" +
            "\t\t\"foodCalorie\": 74,\n" +
            "\t\t\"Carb\": 17.2,\n" +
            "\t\t\"Fat\": 0.1,\n" +
            "\t\t\"Protein\": 2.6,\n" +
            "\t\t\"Unit\": \"100g\"\n" +
            "\t}, {\n" +
            "\t\t\"FoodName\": \"Soba\",\n" +
            "\t\t\"foodCalorie\": 100,\n" +
            "\t\t\"Carb\": 21.4,\n" +
            "\t\t\"Fat\": 0.1,\n" +
            "\t\t\"Protein\": 5.1,\n" +
            "\t\t\"Unit\": \"100g\"\n" +
            "\t}, {\n" +
            "\t\t\"FoodName\": \"Asparagus\",\n" +
            "\t\t\"foodCalorie\": 20,\n" +
            "\t\t\"Carb\": 3.9,\n" +
            "\t\t\"Fat\": 0.1,\n" +
            "\t\t\"Protein\": 2.2,\n" +
            "\t\t\"Unit\": \"100g\"\n" +
            "\t}\n" +
            "]";
}
