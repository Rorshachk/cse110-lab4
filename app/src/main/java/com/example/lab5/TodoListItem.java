package com.example.lab5;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.lang.reflect.Type;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class TodoListItem {
    public long id = 0;
    public String text;
    public boolean completed;
    public int order;

    TodoListItem(String text, boolean completed, int order){
        this.text = text;
        this.completed = completed;
        this.order = order;
    }

    public static List<TodoListItem> loadJSON(Context context, String path){
        try {
            InputStream input = context.getAssets().open(path);
            Reader reader = new InputStreamReader(input);
            Gson gson = new Gson();
            Type type = new TypeToken<List<TodoListItem>>() {}.getType();

            return gson.fromJson(reader, type);
        }
        catch(IOException e){
            e.printStackTrace();
            return Collections.emptyList();
        }
    }
}
