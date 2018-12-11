package com.example.ddfshhh.bakingprojectjsontosqlite.model;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import nl.elastique.poetry.json.annotations.MapFrom;

@DatabaseTable
public class Ingredient {
    @DatabaseField(id = true, columnName = "id")
    @MapFrom("id")
    private int mId;

    /**
     * This column is the foreign key and maps to the "recipe" table.
     */
    @DatabaseField(foreign = true, columnName = "recipe_id")
    @MapFrom("recipe")
    private Recipe mRecipe;

    @DatabaseField(columnName = "quantity")
    private int mQuantity;

    @DatabaseField(columnName = "measure")
    private String mMeasure;

    @DatabaseField(columnName = "ingredient")
    private String mIngredient;

}
