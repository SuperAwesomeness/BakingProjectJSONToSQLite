package com.example.ddfshhh.bakingprojectjsontosqlite.model;

import com.j256.ormlite.dao.ForeignCollection;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.field.ForeignCollectionField;
import com.j256.ormlite.table.DatabaseTable;

import nl.elastique.poetry.json.annotations.MapFrom;

@DatabaseTable
public class Recipe {
    @DatabaseField(id = true, columnName = "id")
    @MapFrom("id")
    private int mId;

    @DatabaseField(columnName = "name")
    @MapFrom("name")
    private String mName;

    @DatabaseField(columnName = "servings")
    @MapFrom("servings")
    private int mServings;

    @DatabaseField(columnName = "image")
    @MapFrom("image")
    private String mImage;

    @ForeignCollectionField(eager = true)
    public ForeignCollection<Ingredient> ingredients;

    @ForeignCollectionField(eager = true)
    public ForeignCollection<Step> steps;

    public int getId() {
        return mId;
    }

    public String getName() {
        return mName;
    }

    public ForeignCollection<Ingredient> getIngredients() {
        return ingredients;
    }

    public ForeignCollection<Step> getSteps() {
        return steps;
    }

}
