package com.udacity.sandwichclub.utils;
import com.udacity.sandwichclub.model.Sandwich;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class JsonUtils {

    public static Sandwich parseSandwichJson(String json) {

        Sandwich sandwich = null;
        try {
            JSONObject jsonObject = new JSONObject(json);
            sandwich = new Sandwich();
            JSONObject name = jsonObject.getJSONObject("name");
            sandwich.setMainName(name.getString("mainName"));
            JSONArray alsoKnownAs = name.getJSONArray("alsoKnownAs");
            List<String> alsoKnownAsList = new ArrayList<>();
            for (int i = 0; i < alsoKnownAs.length(); i++)
                alsoKnownAsList.add(alsoKnownAs.getString(i));
            sandwich.setAlsoKnownAs(alsoKnownAsList);
            sandwich.setPlaceOfOrigin(jsonObject.getString("placeOfOrigin"));
            sandwich.setDescription(jsonObject.getString("description"));
            sandwich.setImage(jsonObject.getString("image"));
            JSONArray ingredients = jsonObject.getJSONArray("ingredients");
            List<String> ingredientsList = new ArrayList<>();
            for(int i=0; i<ingredients.length(); i++)
                ingredientsList.add(ingredients.getString(i));
            sandwich.setIngredients(ingredientsList);
        } catch (JSONException jsonException) {
            jsonException.printStackTrace();
        }
        catch (NullPointerException npeException){
            npeException.printStackTrace();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return sandwich;
    }
}
