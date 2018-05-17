package com.movinghead333.quicknotes;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class NoteListAdapter extends RecyclerView.Adapter<NoteListAdapter.ViewHolder>{

    private List<Note> notes;


    public static class ViewHolder extends RecyclerView.ViewHolder{
        public TextView textView;
        public ViewHolder(TextView tv){
            super(tv);
            textView = tv;
        }
    }

    public NoteListAdapter(){

    }

    @Override
    public NoteListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        TextView tv = (TextView) LayoutInflater.from(parent.getContext())
                .inflate(R.layout.note_list_item, parent, false);
        ViewHolder vh = new ViewHolder(tv);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position){
        if(notes != null){
            Note current = notes.get(position);
            holder.textView.setText(current.noteTitle);
        }else{
            holder.textView.setText("no notes");
        }
    }

    @Override
    public int getItemCount(){
        if(notes != null){
            return notes.size();
        }else{
            return 0;
        }
    }


    void setNotes(List<Note> notes){
        this.notes = notes;
        notifyDataSetChanged();
    }
}

