package com.example.ddfshhh.bakingprojectjsontosqlite;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.example.ddfshhh.bakingprojectjsontosqlite.data.DatabaseHelper;
import com.example.ddfshhh.bakingprojectjsontosqlite.data.JsonLoader;
import com.example.ddfshhh.bakingprojectjsontosqlite.model.Recipe;
import com.j256.ormlite.dao.Dao;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.sql.SQLException;

import nl.elastique.poetry.json.JsonPathException;
import nl.elastique.poetry.json.JsonPathResolver;
import nl.elastique.poetry.json.JsonPersister;

public class MainActivity extends AppCompatActivity {

    private static final String sTag = "MainActivity";

    private DatabaseHelper mDatabaseHelper;

    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = (TextView) findViewById(R.id.recipes);

        mDatabaseHelper = DatabaseHelper.getHelper(this);

        // Call persistJson() on a background method (quick and dirty through a new Thread)
        new Thread(new Runnable() {
            @Override
            public void run() {
                persistJson();
                readDatabase();
            }
        }).start();
    }

    private void persistJson() {
        try {
            Log.d(sTag, "persisting json into database");

//            // Load JSON
//            JSONObject json = JsonLoader.loadObject(this, R.raw.baking);
//
//            // Get child arrays from JSON
//            JSONArray recipes_json = JsonPathResolver.resolveArray(json, "recipes");
            JSONArray recipes_json = JsonLoader.fetchJsonArray(this, R.raw.baking2);

            // Persist arrays to database
            JsonPersister persister = new JsonPersister(mDatabaseHelper.getWritableDatabase());
            persister.persistArray(Recipe.class, recipes_json);
//        } catch (IOException | JSONException | JsonPathException e) {
        } catch (IOException | JSONException e) {
            e.printStackTrace();
        }
    }

    private void readDatabase() {
        try {
            Dao<Recipe, Integer> recipe_dao = mDatabaseHelper.getDao(Recipe.class);

            // Get user 1 with its tags and groups
            Recipe recipe1 = recipe_dao.queryForId(1);
            final String recipeSummary1 = String.format("'%s' has %d ingredient(s) and %d step(s)",
                    recipe1.getName(),
                    recipe1.getIngredients().size(),
                    recipe1.getSteps().size());

            Recipe recipe2 = recipe_dao.queryForId(2);
            final String recipeSummary2 = String.format("'%s' has %d ingredient(s) and %d step(s)",
                    recipe2.getName(),
                    recipe2.getIngredients().size(),
                    recipe2.getSteps().size());

            Recipe recipe3 = recipe_dao.queryForId(3);
            final String recipeSummary3 = String.format("'%s' has %d ingredient(s) and %d step(s)",
                    recipe3.getName(),
                    recipe3.getIngredients().size(),
                    recipe3.getSteps().size());

            Recipe recipe4 = recipe_dao.queryForId(1);
            final String recipeSummary4 = String.format("'%s' has %d ingredient(s) and %d step(s)",
                    recipe4.getName(),
                    recipe4.getIngredients().size(),
                    recipe4.getSteps().size());

            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    textView.setText(recipeSummary1 + "\n" + recipeSummary2 + "\n" + recipeSummary3 + "\n" + recipeSummary4 + "\n");
                }
            });
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onDestroy() {
        DatabaseHelper.releaseHelper();
        mDatabaseHelper = null;
        super.onDestroy();
    }
}
