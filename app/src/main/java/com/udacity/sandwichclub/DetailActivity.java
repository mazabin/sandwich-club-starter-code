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

import butterknife.BindView;
import butterknife.ButterKnife;


public class DetailActivity extends AppCompatActivity {

    public static final String EXTRA_POSITION = "extra_position";
    private static final int DEFAULT_POSITION = -1;

    @BindView(R.id.also_known_tv) TextView alsoKnownAsTv;
    @BindView(R.id.description_tv) TextView descriptionTv;
    @BindView(R.id.ingredients_tv) TextView ingredientsTv;
    @BindView(R.id.origin_tv) TextView originTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        ButterKnife.bind(this);

        ImageView ingredientsIv = findViewById(R.id.image_iv);

        Intent intent = getIntent();
        if (intent == null) {
            closeOnError();
        }

        assert intent != null;
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

    /**
     * Fills TextViews of DetailActivity with values from corresponding fields in Sandwich.
     * @param sandwich An instance of Sandwich
     */
    private void populateUI(Sandwich sandwich) {
        alsoKnownAsTv.setText(sandwich.getAlsoKnownAs().toString().substring(1,sandwich.getAlsoKnownAs().toString().length()-1));
        descriptionTv.setText(sandwich.getDescription());
        ingredientsTv.setText(sandwich.getIngredients().toString().substring(1,sandwich.getIngredients().toString().length()-1));
        originTv.setText(sandwich.getPlaceOfOrigin());
    }
}
