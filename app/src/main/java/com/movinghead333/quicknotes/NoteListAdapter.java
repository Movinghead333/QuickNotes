package com.movinghead333.quicknotes;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class NoteListAdapter extends RecyclerView.Adapter<NoteListAdapter.ViewHolder>{

    private List<Note> notes;
    private CustomItemClickListener listener;

    public static class ViewHolder extends RecyclerView.ViewHolder{
        public TextView textView;
        public ViewHolder(TextView tv){
            super(tv);
            textView = tv;
        }
    }

    public NoteListAdapter(CustomItemClickListener listener){
        this.listener = listener;
    }

    @Override
    public NoteListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        TextView tv = (TextView) LayoutInflater.from(parent.getContext())
                .inflate(R.layout.note_list_item, parent, false);
        final ViewHolder vh = new ViewHolder(tv);
        tv.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                listener.onItemClick(v, vh.getPosition());
            }
        });
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

