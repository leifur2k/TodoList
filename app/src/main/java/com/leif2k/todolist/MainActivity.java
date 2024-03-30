package com.leif2k.todolist;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private FloatingActionButton floActButAdd;
    private Database database = Database.getInstance();
    private NotesAdapter notesAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();

        notesAdapter = new NotesAdapter();
        recyclerView.setAdapter(notesAdapter);

        floActButAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = AddNoteActivity.newIntent(MainActivity.this);
                startActivity(intent);
            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();

        showNotes();

    }

    private void initViews() {
        recyclerView = findViewById(R.id.recyclerView);
        floActButAdd = findViewById(R.id.floActButAdd);
    }

    private  void showNotes() {

        notesAdapter.setNotes(database.getNotes());

    }

}