package com.movinghead333.quicknotes;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class AddNoteActivity extends AppCompatActivity {

    public static final String EXTRA_TITLE = "EXTRA_TITLE";
    public static final String EXTRA_DESCRIPTION = "DESCRIPTION";
    private EditText titleEditText;
    private EditText descriptionEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_note);
        titleEditText = (EditText)findViewById(R.id.title_edittext);
        descriptionEditText = (EditText)findViewById(R.id.description_edittext);
        Intent intent = getIntent();
        if(intent.getBooleanExtra(MainActivity.SEND_IS_EDIT_TO_ADD_NOTE_ACTIVTY, false)){
            titleEditText.setText(intent.getStringExtra(MainActivity.SEND_TITLE_TO_ADD_NOTE_ACTIVITY));
            descriptionEditText.setText(intent.getStringExtra(MainActivity.SEND_DESCRIPTION_TO_ADD_NOTE_ACTIVTY));
        }
    }

    public void onFinishedButtonPress(View view){
        Intent returnIntent = new Intent();
        String title = titleEditText.getText().toString();
        if(title.equals("")){
            setResult(Activity.RESULT_CANCELED);
            finish();
        }
        returnIntent.putExtra(EXTRA_TITLE, title);
        String description = descriptionEditText.getText().toString();
        returnIntent.putExtra(EXTRA_DESCRIPTION, description);
        setResult(Activity.RESULT_OK, returnIntent);
        finish();
    }
}
