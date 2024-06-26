package com.leif2k.todolist;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class AddNoteActivity extends AppCompatActivity {

    private EditText editTextEnterNote;

    private RadioGroup radioGroupPriority;
    private RadioButton radioButtonLow;
    private RadioButton radioButtonMedium;
    private RadioButton radioButtonHigh;

    Button buttonSave;

    Database database = Database.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_note);
        initViews();

        buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveNote();
            }
        });

    }

    private void initViews() {
        editTextEnterNote = findViewById(R.id.editTextEnterNote);
        radioGroupPriority = findViewById(R.id.radioGroupPriority);
        radioButtonLow = findViewById(R.id.radioButtonLow);
        radioButtonMedium = findViewById(R.id.radioButtonMedium);
        radioButtonHigh = findViewById(R.id.radioButtonHigh);
        buttonSave = findViewById(R.id.buttonSave);
    }

    private void saveNote() {
        String text = editTextEnterNote.getText().toString().trim();
        int priority = getPriority();
        int id = database.getNotes().size();

        if(text.isEmpty()) {
            Toast.makeText(this, getString(R.string.error), Toast.LENGTH_LONG).show();
        } else {
            Note note = new Note(id, text, priority);
            database.add(note);
            finish();
        }
    }

    private int getPriority() {
        int priority;
        if(radioButtonLow.isChecked())
            priority = 0;
        else if(radioButtonMedium.isChecked())
            priority = 1;
        else
            priority = 2;

        return priority;
    }

    public static Intent newIntent(Context context) {
        return new Intent(context, AddNoteActivity.class);
    }

}