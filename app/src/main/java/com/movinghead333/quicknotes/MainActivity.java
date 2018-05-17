package com.movinghead333.quicknotes;

import android.app.Activity;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    public NoteViewModel noteViewModel;
    private RecyclerView noteRecyclerView;
    private NoteListAdapter noteListAdapter;
    private RecyclerView.LayoutManager noteRecyclerViewLayoutManager;
    private final int REQUEST_CODE_ADD_NOTE_ACTIVITY = 1;
    public static final String SEND_TITLE_TO_SHOW_NOTE_ACTIVITY = "SEND_TITLE_TO_SHOW_NOTE_ACTIVITY";
    public static final String SEND_DESCRIPTION_TO_SHOW_NOTE_ACTIVITY = "SEND_DESCRIPTION_TO_SHOW_NOTE_ACTIVITY";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        noteViewModel = ViewModelProviders.of(this).get(NoteViewModel.class);

        noteRecyclerView = (RecyclerView)findViewById(R.id.notes_recyclerview);
        //recipeRecyclerView.setHasFixedSize(true);

        noteListAdapter = new NoteListAdapter(new CustomItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Note currentNote = noteViewModel.getAllNotes().getValue().get(position);
                Intent intent = new Intent(MainActivity.this, ShowNoteActivity.class);
                intent.putExtra(SEND_TITLE_TO_SHOW_NOTE_ACTIVITY, currentNote.noteTitle);
                intent.putExtra(SEND_DESCRIPTION_TO_SHOW_NOTE_ACTIVITY, currentNote.description);
                startActivity(intent);
            }
        });
        noteRecyclerView.setAdapter(noteListAdapter);

        noteRecyclerViewLayoutManager = new LinearLayoutManager(this);
        noteRecyclerView.setLayoutManager(noteRecyclerViewLayoutManager);




        noteViewModel.getAllNotes().observe(this, new Observer<List<Note>>() {
            @Override
            public void onChanged(@Nullable List<Note> notes) {
                //tv.setText(recipes.get(0).getRecipeName());

                noteListAdapter.setNotes(notes);//error
            }
        });


        /*
            Add new note using floatingActionButton
         */
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, AddNoteActivity.class);
                startActivityForResult(intent, REQUEST_CODE_ADD_NOTE_ACTIVITY);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        if(requestCode == REQUEST_CODE_ADD_NOTE_ACTIVITY){
            if(resultCode == Activity.RESULT_OK){
                Note note = new Note(data.getStringExtra(AddNoteActivity.EXTRA_TITLE),
                        data.getStringExtra(AddNoteActivity.EXTRA_DESCRIPTION));
                noteViewModel.insertNote(note);
            }else if(resultCode == Activity.RESULT_CANCELED){

            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
