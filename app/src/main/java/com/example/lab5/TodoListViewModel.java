package com.example.lab5;

import android.app.Application;
import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class TodoListViewModel extends AndroidViewModel {
    private LiveData<List<TodoListItem>> todoListItems;
    private final TodoListItemDao todoListItemDao;

    public TodoListViewModel(@NonNull Application application){
        super(application);
        Context context = getApplication().getApplicationContext();
        TodoDatabase db = TodoDatabase.getSingleton(context);
        todoListItemDao = db.todoListItemDao();
    }

    public LiveData<List<TodoListItem>> getTodoListItems(){
        if (todoListItems == null){
            loadUsers();
        }
        return todoListItems;
    }

    private  void loadUsers(){
        todoListItems = todoListItemDao.getAllLive();
    }
}
