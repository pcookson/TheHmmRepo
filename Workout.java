package com.example.appBackEnd;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Stack;

import android.app.Activity;
import android.database.Cursor;
import android.media.MediaPlayer;
import android.net.Uri;
import android.provider.MediaStore;

public class Workout extends Activity{

	private int runningBpm;
	private long time;
	private Song currentSong;
	private Map<String, Song> songMap;
	private Stack<Song> prevStack;
	private Stack<Song> nextStack;

	public Workout(){
		this.songMap = new HashMap<String, Song>();
		prevStack = new Stack<Song>();
		nextStack = new Stack<Song>();
		start();
	}

	public void start(){
		String[] STAR = { "*" };
		Uri allSongUri = MediaStore.Audio.Media.INTERNAL_CONTENT_URI;
		String selection = MediaStore.Audio.Media.IS_MUSIC + " !=0";
		@SuppressWarnings("deprecation")
		Cursor cursor = managedQuery(allSongUri, STAR, selection, null, null);
		if(cursor.moveToFirst()){
			do {
				String songName = cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.DISPLAY_NAME));
				int songId = cursor.getInt(cursor.getColumnIndex(MediaStore.Audio.Media._ID));
				String fullPath = cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.DATA));

				String albumName = cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.ALBUM));
				int albumId = cursor.getInt(cursor.getColumnIndex(MediaStore.Audio.Media.ALBUM_ID));

				String artistName = cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.ARTIST));
				int artistId = cursor.getInt(cursor.getColumnIndex(MediaStore.Audio.Media.ARTIST_ID));


				Song newSong = new Song(artistName, artistId, albumName, albumId, songName, songId, fullPath);
				songMap.put(artistName, newSong);
			}while(cursor.moveToNext());
		}
		cursor.close();


	}

	public void end(){

	}

	public void setBpm(int runningBpm){
		this.runningBpm = runningBpm;

	}

	public int getRunningBpm(){
		return runningBpm;	
	}

	public void setTime(long time){
		this.time=time;

	}

	public long getTime(){
		return time;
	}

	public void setCurrentSong(Song song){
		this.currentSong = song;
	}

	public Song getCurrentSong(){
		return currentSong;
	}

	public void getMoreSongs(){
		
	}
	
	public void play(){
		MediaPlayer mp = new MediaPlayer();
		try {
			mp.setDataSource(getCurrentSong().getSongPath());
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalStateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void pause(){
		//function to pause song
	}

	public void resume(){
		//funtion to resume song
	}

	public void getNextSong(){
		//gets next song in songs stack
		if(getCurrentSong()!=null){
			prevStack.push(getCurrentSong());
		}
		setCurrentSong(nextStack.pop()); 
	}

	public void prevSong(){
		//gets prev song in stack
		nextStack.push(getCurrentSong());
		setCurrentSong(prevStack.pop()) ;

	}

	private void makeSongList(int runningBpm){
		int lowerThreshold = (int) (0.80 * runningBpm);
		int upperThreshold = (int) (1.2 * runningBpm);
		Iterator<Entry<String, Song>> it = songMap.entrySet().iterator();


		while(it.hasNext()){
			Song selectedSong = it.next().getValue();
			int selectedBpm = selectedSong.getBpm();
			if(selectedBpm > lowerThreshold && 
					selectedBpm < upperThreshold){
				nextStack.push(selectedSong);
			}


		}

	}


}
