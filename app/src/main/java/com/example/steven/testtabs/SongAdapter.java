package com.example.steven.testtabs;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

public class SongAdapter extends BaseAdapter {
    private SimplePlaylist songs;
    private LayoutInflater songInf;

    SongAdapter(Context c, SimplePlaylist theSongs){
        songs = theSongs;
        songInf = LayoutInflater.from(c);
    }

    @Override
    public int getCount() {
        return songs.size();
    }

    @Override
    public Object getItem(int arg0) {
        return songs.get(arg0);
    }

    @Override
    public long getItemId(int arg0) {
        return songs.get(arg0).getID();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        //map to song layout
        LinearLayout songLay = (LinearLayout)songInf.inflate
                (R.layout.song, parent, false);
        //get title and artist views
        TextView songView   = (TextView)songLay.findViewById(R.id.song_title);
        TextView artistView = (TextView)songLay.findViewById(R.id.song_artist);
        TextView albumView  = (TextView)songLay.findViewById(R.id.song_album);
        //get song using position
        Song currSong = songs.get(position);
        //get title and artist strings
        songView.setText(currSong.getTitle());
        artistView.setText(currSong.getArtist());
        albumView.setText(currSong.getAlbum());
        //set position as tag
        songLay.setTag(position);
        return songLay;
    }
}
