package com.movinghead333.quicknotes;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_add_note, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch(item.getItemId()){
            case R.id.item_edit:
                setResult(Activity.RESULT_OK);
                finish();
                return true;
            case R.id.item_delete:
                AlertDialog.Builder adb = new AlertDialog.Builder(this);

                adb.setTitle(R.string.delete_current_note);

                adb.setIcon(android.R.drawable.ic_dialog_alert);

                adb.setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        setResult(Activity.RESULT_FIRST_USER);
                        finish();
                    } });
                adb.setNegativeButton(R.string.no, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                    } });
                adb.show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
