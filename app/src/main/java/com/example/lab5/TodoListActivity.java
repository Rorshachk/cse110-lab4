package com.example.lab5;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.List;

public class TodoListActivity extends AppCompatActivity {

    public RecyclerView recyclerView;

    private TodoListViewModel viewModel;
    private EditText newTodoText;
    private Button addTodoButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_todo_list);

        viewModel = new ViewModelProvider(this)
                .get(TodoListViewModel.class);


        TodoListAdapter adapter = new TodoListAdapter();
        adapter.setHasStableIds(true);
//        adapter.setTodoListItems(todoListItems);
        adapter.setOnCheckBoxClickedHandler(viewModel::toggleCompleted);
        adapter.setOnDeleteClickedHandler(viewModel::deleteItem);
        adapter.setOnTextEditedHandler(viewModel::updateText);
        viewModel.getTodoListItems().observe(this, adapter::setTodoListItems);


//        TodoListItemDao todoListItemDao = TodoDatabase.getSingleton(this).todoListItemDao();
//        List<TodoListItem> todoListItems = todoListItemDao.getAll();

//        TodoListAdapter adapter = new TodoListAdapter();


        recyclerView = findViewById(R.id.todo_items);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

//        adapter.setTodoListItems(TodoListItem.loadJSON(this, "demo_todos.json"));

//        List<TodoListItem> todos = TodoListItem.loadJSON(this, "demo_todos.json");
//        Log.d("TodoListActivity", todos.toString());
        this.newTodoText = this.findViewById(R.id.new_todo_text);
        this.addTodoButton = this.findViewById(R.id.add_todo_btn);
        addTodoButton.setOnClickListener(this::onAddTodoClicked);
    }

    private void onAddTodoClicked(View view) {
        String text = newTodoText.getText().toString();
        newTodoText.setText("");
        viewModel.createTodo(text);
    }
}