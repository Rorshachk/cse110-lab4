package com.example.lab5;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import java.util.List;

public class TodoListActivity extends AppCompatActivity {

    public RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_todo_list);

        TodoListItemDao todoListItemDao = TodoDatabase.getSingleton(this).todoListItemDao();
        List<TodoListItem> todoListItems = todoListItemDao.getAll();

        TodoListAdapter adapter = new TodoListAdapter();
        adapter.setHasStableIds(true);
        adapter.setTodoListItems(todoListItems);

        recyclerView = findViewById(R.id.todo_items);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

        adapter.setTodoListItems(TodoListItem.loadJSON(this, "demo_todos.json"));

//        List<TodoListItem> todos = TodoListItem.loadJSON(this, "demo_todos.json");
//        Log.d("TodoListActivity", todos.toString());
    }
}