package com.movinghead333.quicknotes;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class ShowNoteActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_note);
        Intent startIntent = getIntent();
        TextView title = (TextView)findViewById(R.id.sna_title);
        title.setText(startIntent.getStringExtra(MainActivity.SEND_TITLE_TO_SHOW_NOTE_ACTIVITY));
        TextView description = (TextView)findViewById(R.id.sna_description);
        description.setText(startIntent.getStringExtra(MainActivity.SEND_DESCRIPTION_TO_SHOW_NOTE_ACTIVITY));
    }
}
