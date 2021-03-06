package com.example.steven.testtabs;

import android.util.Log;

import java.util.ArrayList;

/**
 * Created by xflip on 11/20/2017.
 */

public class MediaStorage {
    private static final String TAG = "MediaStorage";

    private ArrayList<Song> songs;
    private ArrayList<SimplePlaylist> simplePlaylists;
    private ArrayList<CompoundPlaylist> compoundPlaylists;
    private CompoundPlaylist userPlaylists;
    public int startIndexOfUserPlaylists;


    MediaStorage() {
        songs = new ArrayList<>();
        simplePlaylists = new ArrayList<>();
        compoundPlaylists = new ArrayList<>();
        userPlaylists = new CompoundPlaylist("User Playlists");
        startIndexOfUserPlaylists = 0;
    }

    ArrayList<Song> getSongs() {
        return songs;
    }

    ArrayList<SimplePlaylist> getSimplePlaylists() {
        return simplePlaylists;
    }

    ArrayList<CompoundPlaylist> getCompoundPlaylists() {
        return compoundPlaylists;
    }

    CompoundPlaylist getUserPlaylists() {
        return userPlaylists;
    }

    public Song getSong(int index) {
        return songs.get(index);
    }

    public SimplePlaylist getSimplePlaylist(int index) {
        return simplePlaylists.get(index);
    }

    public CompoundPlaylist getCompoundPlaylist(int index) {
        return compoundPlaylists.get(index);
    }

    public SimplePlaylist getUserPlaylist(int index) {
        return userPlaylists.get(index);
    }

    /**
     * Used for creating new songs.
     * Automatically stores new song into song library object in AppCore
     *
     * @param songID Song ID
     * @param songName Song name
     * @param artist Song's artist
     * @param album Song's album
     * @return returns the new song object
     */
    Song createSong(Long songID, String songName, String artist, String album, Long albumID, String genre, String track) { // String coverPath, Long albumID
        Song newSong = new Song(songID, songName, artist, album, albumID, genre, track); //coverPath, albumID
        if(!songs.contains(newSong))
            songs.add(newSong);
        else {
            Log.w(TAG, "Attempted to create song which was previously created");
            Log.w(TAG, "Song name was: " + songName);
            Log.w(TAG, "Previously existing song's index: " + songs.indexOf(newSong));
        }
        return newSong;
    }

    /**
     * Used for creating new compound playlists.
     * Automatically stores new compound playlist into compound playlist collection in AppCore
     *
     * @param playlistName Name of Compound Playlist
     * @return returns new compound playlist
     */
    CompoundPlaylist createCompoundPlaylist(String playlistName) {
        CompoundPlaylist newCompoundPlaylist = new CompoundPlaylist(playlistName);
        if(!compoundPlaylists.contains(newCompoundPlaylist))
            compoundPlaylists.add(newCompoundPlaylist);
        else {
            Log.w(TAG, "Attempted to create duplicate Compound Playlist");
            Log.w(TAG, "Playlist name was: " + playlistName);
            Log.w(TAG, "Previously existing playlist's index: " + compoundPlaylists.indexOf(newCompoundPlaylist));
        }
        return newCompoundPlaylist;
    }

    /**
     * Used for creating new playlists.
     * Automatically stores new playlist into playlist collection in AppCore
     *
     * @param playlistName Name of Playlist
     * @return returns new playlist object
     */
    SimplePlaylist createSimplePlaylist(String playlistName) {
        SimplePlaylist newSimplePlaylist = new SimplePlaylist(playlistName);
        boolean duplicate = false;
        int index = 0;
        for(int i = 0; i < simplePlaylists.size(); i++) {
            if(simplePlaylists.get(i).getNameOfPlaylist() == playlistName) {
                duplicate = true;
                index = i;
                break;
            }
        }
        if(!duplicate) {
            simplePlaylists.add(newSimplePlaylist);
            return newSimplePlaylist;
        }
        else {
            Log.w(TAG, "Attempted to create duplicate Simple Playlist");
            Log.w(TAG, "Playlist name was: " + playlistName);
            Log.w(TAG, "Previously existing playlist's index: " + index);
        }
        return newSimplePlaylist;
    }

    /**
     * Used for creating new compound playlists.
     * Automatically stores new compound playlist into compound playlist collection in AppCore
     *
     * @param playlistName Name of Compound Playlist
     * @return returns new compound playlist
     */
    SimplePlaylist createUserPlaylist(String playlistName) {
        if(userPlaylists.isEmpty()) {
            startIndexOfUserPlaylists = simplePlaylists.size();
        }
        boolean duplicate = false;
        int index = 0;
        for(int i = 0; i < userPlaylists.size(); i++) {
            if(userPlaylists.get(i).getNameOfPlaylist() == playlistName) {
                duplicate = true;
                index = i;
                break;
            }
        }
        if(!duplicate) {
            SimplePlaylist newUserPlaylist = createSimplePlaylist(playlistName);
            userPlaylists.add(newUserPlaylist);
            newUserPlaylist.setIndexInUserPlaylist(userPlaylists.size() - 1);
            return newUserPlaylist;
        }
        else {
            Log.w(TAG, "Attempted to create duplicate User Playlist");
            Log.w(TAG, "Playlist name was: " + playlistName);
            Log.w(TAG, "Previously existing playlist's index: " + userPlaylists.get(index));
            return null;
        }
    }

    /**
     * Checks to see if there are any stored songs
     *
     * @return returns true if songs exist in this container and vice versa
     */
    public boolean isEmpty() {
        return songs.isEmpty();
    }
}