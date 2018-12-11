package com.example.ddfshhh.bakingprojectjsontosqlite.data;

import android.content.Context;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringWriter;
import java.io.Writer;

public class JsonLoader {

    /**
     * This method demonstrates how to parse the JSON data if the root element is a JSONObject.
     * @param context
     * @param rawResId
     * @return
     * @throws IOException
     * @throws JSONException
     */
    public static JSONObject loadObject(Context context, int rawResId) throws IOException, JSONException {
        InputStream is = context.getResources().openRawResource(rawResId);
        Writer writer = new StringWriter();
        char[] buffer = new char[1024];
        try {
            Reader reader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
            int n;
            while ((n = reader.read(buffer)) != -1) {
                writer.write(buffer, 0, n);
            }
        } finally {
            is.close();
        }

        String jsonString = writer.toString();

        JSONTokener tokener = new JSONTokener(jsonString);

        return new JSONObject(tokener);
    }

    /**
     * This method demonstrates how to parse the JSON data if the root element is a JSONArray.
     * @param context
     * @param rawResId
     * @return
     * @throws IOException
     * @throws JSONException
     */
    public static JSONArray fetchJsonArray(Context context, int rawResId) throws IOException, JSONException {
        InputStream is = context.getResources().openRawResource(rawResId);
        Writer writer = new StringWriter();
        char[] buffer = new char[1024];
        try {
            Reader reader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
            int n;
            while ((n = reader.read(buffer)) != -1) {
                writer.write(buffer, 0, n);
            }
        } finally {
            is.close();
        }

        String jsonString = writer.toString();

        // Parse JSON

        JSONTokener tokener = new JSONTokener(jsonString);
        Object val = tokener.nextValue();
        if (!(val instanceof JSONArray)) {
            throw new JSONException("Expected JSONArray");
        }
        return (JSONArray) val;
    }
}
