package com.example.ddfshhh.bakingprojectjsontosqlite.model;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import nl.elastique.poetry.json.annotations.MapFrom;

@DatabaseTable
public class Step {
    /**
     * In the JSON data, the steps don't have ID data, so we shouldn't add "@MapFrom("id")".
     */
    @DatabaseField(id = true, columnName = "id")
    private int mId;

    /**
     * This column is the foreign key and maps to the "recipe" table.
     */
    @DatabaseField(foreign = true, columnName = "recipe_id")
    @MapFrom("recipe")
    private Recipe mRecipe;

    @DatabaseField(columnName = "shortDescription")
    private int mShortDescription;

    @DatabaseField(columnName = "description")
    private String mDescription;

    @DatabaseField(columnName = "videoURL")
    private String mVideoURL;

    @DatabaseField(columnName = "thumbnailURL")
    private String mThumbnailURL;
}
