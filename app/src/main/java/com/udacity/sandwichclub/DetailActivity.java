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

        String[] sandwiches = getResources().getStringArray(R.array.sandwich_details);
        String json = sandwiches[position];
        Sandwich sandwich = JsonUtils.parseSandwichJson(json);
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
        TextView alsoKnownAsTV = findViewById(R.id.also_known_tv);
        TextView ingredientsTV = findViewById(R.id.ingredients_tv);
        TextView placeOfOriginTV = findViewById(R.id.origin_tv);
        TextView descriptionTV = findViewById(R.id.description_tv);

        String alsoKnownAs = "";
        for(String aka : sandwich.getAlsoKnownAs()) {
            if(alsoKnownAs.equals("")) {
                alsoKnownAs += aka;
            } else {
                alsoKnownAs += ", " + aka;
            }

        }

        String ingredients = "";
        for(String in : sandwich.getIngredients()) {
            if(ingredients.equals("")) {
                ingredients += in;
            } else {
                ingredients += ", " +in;
            }
        }

        if(alsoKnownAs.length() > 0) {
            alsoKnownAsTV.setText(alsoKnownAs);
        }
        if(ingredientsTV.length() > 0) {
            ingredientsTV.setText(ingredients);
        }
        if(sandwich.getPlaceOfOrigin().length() > 0) {
            placeOfOriginTV.setText(sandwich.getPlaceOfOrigin());
        }
        if(sandwich.getDescription().length() > 0) {
            descriptionTV.setText(sandwich.getDescription());
        }

    }
}
