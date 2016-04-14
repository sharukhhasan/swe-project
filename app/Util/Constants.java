package Util;

import java.util.*;

/**
 * Created by Sharukh on 3/1/16.
 */
public class Constants {
    public static final String LOGIN_FAILED = "Email or password is incorrect";
    public static final String LOGIN_SUCCESS = "Successfully logged in";
    public static final String PASSWORD_NULL = "Password field is empty";

    // Signup form
    public static final String EMAIL_REQUIRED = "Email is required";
    public static final String EMAIL_INCORRECT = "Email is incorrect";
    public static final String FIRST_NAME_REQUIRED = "First name is required";
    public static final String LAST_NAME_REQUIRED = "Last name is required";
    public static final String PASSWORD_REQUIRED = "Password is required";
    public static final String COMFIRM_PASSWORD_REQUIRED = "Confirmation of password is required";
    public static final String PASSWORD_NOT_EQUAL = "Passwords are not the same";
    public static final String PASSWORD_INCORRECT = "Password is not correct";
    public static final String DOB_REQUIRED = "Date of birth is required";
    public static final String GENDER_REQUIRED = "Gender is required";

    // manager ids
    public static final int[] manager_ids = {12345678, 87654321, 8009580, 3849586, 9380495};

    // product categories
    public static final String[] all_categories = {"Books", "Movies, Music, & Games", "Electronics & Computers", "Home, Garden, & Tools",
                                                    "Beauty & Health", "Clothing, Shoes, & Jewelery", "Sports & Outdoors", "Automotive & Industrial"};
    public static final String[] books_category = {"Books", "Children's Books", "Textbooks", "Magazines"};
    public static final String[] movies_music_games_category = {"Movies & TV", "Blu-ray", "CDs & Vinyl", "Digital Music",
                                                                "Musical Instruments", "Headphones", "Video Games"};
    public static final String[] electronics_category = {"TV & Video", "Home Audio & Theater", "Camera, Photo, & Video",
                                                         "Cell Phones & Accessories", "Headphones", "Bluetooth & Wireless Speakers",
                                                         "Car Electronics", "Wearable Technology", "Laptops & Tablets",
                                                         "Desktops & Monitors", "Computer Accessories", "Computer Parts",
                                                         "Software", "Printers & Ink"};
    public static final String[] home_tools_category = {"Home", "Kitchen & Dining", "Furniture", "Bedding & Bath",
                                                        "Appliances", "Patio, Lawn & Garden", "Fine Art", "Arts, Crafts & Sewing",
                                                        "Pet Supplies", "Wedding Registry", "Home Improvement", "Power & Hand Tools",
                                                        "Lamps & Light Fixtures", "Kitchen & Bath Fixtures", "Hardware", "Smart Home"};
    public static final String[] beauty_health_category = {"Luxury Beauty", "Men’s Grooming", "Health, Household & Baby Care",
                                                           "Vitamins & Dietary Supplements", "Grocery & Gourmet Food", "Specialty Diets", "Wine"};
    public static final String[] clothes_category = {"Men", "Women", "Kids"};
    public static final String[] mens_clothes_category = {"Clothing", "Shoes", "Jewelry", "Watches", "Accessories", "Big & Tall", "Uniforms, Work & Safety"};
    public static final String[] women_clothes_category = {"Clothing", "Shoes", "Jewelry", "Watches", "Handbags & Wallets", "Accessories",
                                                            "Maternity", "Petite", "Plus-Size", "Uniforms, Work & Safety"};
    public static final String[] kids_clothes_category = {"Clothing", "Shoes", "Watches", "Accessories", "Jewelry", "School Uniforms"};
    public static final String[] sports_outdoor_category = {"Athletic Clothing", "Exercise & Fitness", "Hunting & Fishing",
                                                            "Team Sports", "Golf", "Leisure Sports & Game Room", "Sports Collectibles",
                                                            "Camping & Hiking", "Cycling", "Outdoor Clothing", "Scooters, Skateboards & Skates",
                                                            "Water Sports", "Winter Sports", "Climbing", "Accessories"};
    public static final String[] auto_industrial_category = {"Automotive Parts & Accessories", "Automotive Tools & Equipment",
                                                            "Car/Vehicle Electronics & GPS", "Tires & Wheels", "Motorcycle & Powersports",
                                                            "Industrial Supplies", "Lab & Scientific", "Janitorial", "Safety",
                                                            "Food Service", "Material Handling"};


    public static final Map<String, List<String>> categories = constructCategoriesMap();

    public static Map<String, List<String>> constructCategoriesMap()
    {
        Map<String, List<String>> returnMap = new HashMap<>();

        List<String[]> listSubCat = new ArrayList<>();
        listSubCat.add(books_category);
        listSubCat.add(movies_music_games_category);
        listSubCat.add(electronics_category);
        listSubCat.add(home_tools_category);
        listSubCat.add(beauty_health_category);
        listSubCat.add(clothes_category);
        listSubCat.add(sports_outdoor_category);
        listSubCat.add(auto_industrial_category);

        int subCounter = 0;
        for(String key : all_categories)
        {
            returnMap.put(key, Arrays.asList(listSubCat.get(subCounter)));
            subCounter++;
        }


        return returnMap;
    }

}

