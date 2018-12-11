package com.example.ddfshhh.bakingprojectjsontosqlite.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.example.ddfshhh.bakingprojectjsontosqlite.model.Ingredient;
import com.example.ddfshhh.bakingprojectjsontosqlite.model.Recipe;
import com.example.ddfshhh.bakingprojectjsontosqlite.model.Step;
import com.j256.ormlite.android.apptools.OpenHelperManager;
import com.j256.ormlite.support.ConnectionSource;

import nl.elastique.poetry.database.DatabaseConfiguration;

public class DatabaseHelper extends nl.elastique.poetry.database.DatabaseHelper {
    public final static DatabaseConfiguration sConfiguration = new DatabaseConfiguration(2, new Class<?>[]
            {
                    Recipe.class,
                    Ingredient.class,
                    Step.class
            });

    public DatabaseHelper(Context context) {
        super(context, sConfiguration);
    }

    public static DatabaseHelper getHelper(Context context) {
        return OpenHelperManager.getHelper(context, DatabaseHelper.class);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, ConnectionSource connectionSource, int oldVersion, int newVersion) {
        super.onUpgrade(db, connectionSource, oldVersion, newVersion);

        // When calling the parent class, the whole database is deleted and re-created.
        // Custom upgrade code goes here to override that behavior.
    }
}