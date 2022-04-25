package com.example.lab5;

import androidx.room.Database;
import androidx.room.RoomDatabase;


@Database(entities = {TodoListItem.class}, version =1)
public  abstract class TodoDatabase extends RoomDatabase {
    public  abstract  TodoListItem todoListItem();
}