package com.udacity.sandwichclub.utils;
import com.udacity.sandwichclub.model.Sandwich;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class JsonUtils {

    public static Sandwich parseSandwichJson(String json) {

        Sandwich sandwich = null;
        try {
            JSONObject jsonObject = new JSONObject(json);
            sandwich = new Sandwich();
            JSONObject name = jsonObject.getJSONObject("name");
            sandwich.setMainName(name.optString("mainName"));
            JSONArray alsoKnownAs = name.getJSONArray("alsoKnownAs");
            List<String> alsoKnownAsList = new ArrayList<>();
            for (int i = 0; i < alsoKnownAs.length(); i++)
                alsoKnownAsList.add(alsoKnownAs.optString(i));
            sandwich.setAlsoKnownAs(alsoKnownAsList);
            sandwich.setPlaceOfOrigin(jsonObject.optString("placeOfOrigin"));
            sandwich.setDescription(jsonObject.optString("description"));
            sandwich.setImage(jsonObject.optString("image"));
            JSONArray ingredients = jsonObject.getJSONArray("ingredients");
            List<String> ingredientsList = new ArrayList<>();
            for(int i=0; i<ingredients.length(); i++)
                ingredientsList.add(ingredients.optString(i));
            sandwich.setIngredients(ingredientsList);
        } catch (JSONException jsonException) {
            jsonException.printStackTrace();
        }
        return sandwich;
    }
}
