package Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.user.musicplayer.R;

import java.io.IOException;
import java.util.ArrayList;

import Model.SongInfo;

public class SongAdapter extends RecyclerView.Adapter<SongAdapter.SongHolder> {
    ArrayList<SongInfo> _songs;
    Context context;

    public SongAdapter(Context context,ArrayList<SongInfo> _songs) {
        this._songs = _songs;
        this.context = context;
    }
    //TODO add another button click listener for stop button
    OnItemClickListner onItemClickListner;
    public interface OnItemClickListner{
        void onItemClick(Button b,View v ,SongInfo obj,int position) ;
    }
public void setOnItemClickListner(OnItemClickListner onItemClickListner){
        this.onItemClickListner=onItemClickListner;
}
    //create layout from row
    @Override
    public SongHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.list_row,parent,false);
        return new SongHolder(view);
    }
    //to perform action on any objects in any view
    @Override
    public void onBindViewHolder(final SongHolder holder, final int position) {
        final SongInfo sinfo=_songs.get(position);
        holder.songName.setText(sinfo.songName);
        holder.artistName.setText(sinfo.artistName);
        holder.actionBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(onItemClickListner !=null){

                        onItemClickListner.onItemClick(holder.actionBtn,v,sinfo,position);

                }
            }
        });
        holder.stopBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemClickListner.onItemClick(holder.stopBtn,v,sinfo,position);
            }
        });


    }
    //gives you the count of item
    @Override
    public int getItemCount() {
        return _songs.size();
    }

    public class SongHolder extends RecyclerView.ViewHolder {
        TextView songName,artistName;
        Button actionBtn;
        Button stopBtn;
        public SongHolder(View itemView) {
            super(itemView);
            songName=(TextView)itemView.findViewById(R.id.SongName);
            artistName=(TextView)itemView.findViewById(R.id.ArtistName);
            actionBtn=(Button) itemView.findViewById(R.id.ActionBtn);
            stopBtn=(Button)itemView.findViewById(R.id.StopBtn);
        }
    }
}
