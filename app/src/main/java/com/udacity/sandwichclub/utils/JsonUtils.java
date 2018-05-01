package com.udacity.sandwichclub.utils;

import com.udacity.sandwichclub.model.Sandwich;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class JsonUtils {

    public static Sandwich parseSandwichJson(String json) {
        JSONObject sandwichJson = null;
        Sandwich sandwich = new Sandwich();
        try {
            sandwichJson = new JSONObject(json);
            JSONObject name = (JSONObject) sandwichJson.get("name");
            String mainName = name.getString("mainName");
            JSONArray alsoKnownAsJsonArray = name.getJSONArray("alsoKnownAs");
            List<String> alsoKnownAs = new ArrayList<>();
            for (int i=0;i<alsoKnownAsJsonArray.length();i++){
                alsoKnownAs.add(alsoKnownAsJsonArray.get(i).toString());
            }

            String placeOfOrigin = (String) sandwichJson.get("placeOfOrigin");
            String description = (String) sandwichJson.getString("description");
            String image = (String) sandwichJson.getString("image");
            JSONArray ingredientsJsonArray = sandwichJson.getJSONArray("ingredients");
            List<String> ingredients = new ArrayList<>();
            for (int i=0;i<ingredientsJsonArray.length();i++){
                ingredients.add(ingredientsJsonArray.get(i).toString());
            }

            sandwich.setAlsoKnownAs(alsoKnownAs);
            sandwich.setMainName(mainName);
            sandwich.setPlaceOfOrigin(placeOfOrigin);
            sandwich.setDescription(description);
            sandwich.setImage(image);
            sandwich.setIngredients(ingredients);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return sandwich;
    }
}
