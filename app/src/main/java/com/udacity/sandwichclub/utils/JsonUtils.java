package com.udacity.sandwichclub.utils;
import com.udacity.sandwichclub.model.Sandwich;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Arrays;

public class JsonUtils {

    public static Sandwich parseSandwichJson(String json) {

        Sandwich sandwich = null;
        try {
            JSONObject jsonObject = new JSONObject(json);
            sandwich = new Sandwich();
            JSONObject name = jsonObject.getJSONObject("name");
            sandwich.setMainName(name.getString("mainName"));
            JSONArray alsoKnownAs = name.getJSONArray("alsoKnownAs");
            sandwich.setAlsoKnownAs(Arrays.asList(alsoKnownAs.toString()));
            sandwich.setPlaceOfOrigin(jsonObject.getString("placeOfOrigin"));
            sandwich.setDescription(jsonObject.getString("description"));
            sandwich.setImage(jsonObject.getString("image"));
            sandwich.setIngredients(Arrays.asList(jsonObject.getJSONArray("ingredients").toString()));
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
