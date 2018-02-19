package com.udacity.sandwichclub;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;
import com.udacity.sandwichclub.model.Sandwich;
import com.udacity.sandwichclub.utils.JsonUtils;

import java.util.Arrays;

public class DetailActivity extends AppCompatActivity {

    public static final String EXTRA_POSITION = "extra_position";
    private static final int DEFAULT_POSITION = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        ImageView ingredientsIv = findViewById(R.id.image_iv);

        Intent intent = getIntent();
        if (intent == null) {
            closeOnError();
        }

        int position = intent.getIntExtra(EXTRA_POSITION, DEFAULT_POSITION);
        if (position == DEFAULT_POSITION) {
            // EXTRA_POSITION not found in intent
            closeOnError();
            return;
        }

        Sandwich sandwich = JsonUtils.parseSandwichJson(getResources().getStringArray(R.array.sandwich_details)[position]);
        if (sandwich == null) {
            // Sandwich data unavailable
            closeOnError();
            return;
        }

        populateUI(sandwich);
        Picasso.with(this)
                .load(sandwich.getImage())
                .into(ingredientsIv);

        setTitle(sandwich.getMainName());
    }

    private void closeOnError() {
        finish();
        Toast.makeText(this, R.string.detail_error_message, Toast.LENGTH_SHORT).show();
    }

    private void populateUI(Sandwich sandwich) {
        TextView alsoKnownAsTv = findViewById(R.id.also_known_tv);
        String alsoKnownAsString = "";
        for (int i = 0; i < sandwich.getAlsoKnownAs().size(); i++){
            alsoKnownAsString += sandwich.getAlsoKnownAs().get(i) + ", ";
        }
        alsoKnownAsTv.setText(alsoKnownAsString);

        TextView ingredients = findViewById(R.id.ingredients_tv);
        ingredients.setText(sandwich.getIngredients().toString());
        TextView description = findViewById(R.id.description_tv);
        description.setText(sandwich.getDescription());
        TextView origin = findViewById(R.id.origin_tv);
        origin.setText(sandwich.getPlaceOfOrigin());

        //TODO correct displaying arrays
    }
}
