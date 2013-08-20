package com.hmmApp.src;

import java.util.HashMap;
import java.util.Map;

public class Workout {
	
	private int runningBpm;
	private long time;
	private Song currentSong;
	private Map<String, Song> songMap;

	public Workout(){
		this.songMap = new HashMap<String, Song>();
		start();
	}

	public void start(){
		//checks to see if there is any songs saved to formatted txt file
		//puts those songs into songMap
		//get all songs from disk
		//if song not in songMap then calls song.httpGetBpm
		//then saves song information in formatted txt file
		//
		
	}

	public void end(){

	}

	public void setBpm(int runningBpm){
		this.runningBpm = runningBpm;
	}

	public int getBpm(){
		return runningBpm;	
	}
	
	public void setTime(long time){
		this.time=time;
		
	}
	
	public long getTime(){
		return time;
	}
	
	public void setSong(Song song){
		this.currentSong = song;
	}
	
	public Song getSong(){
		return currentSong;
	}
	
	public void getMoreSongs(){
		//This will be a function that either refreshes the songs map or adds to it
		
	}
	
	public void pause(){
		//function to pause song
	}
	
	public void resume(){
		//funtion to resume song
	}
	
	public void nextSong(){
		//gets next song in songs map
	}
	
	public void prevSong(){
		//gets prev song in songs map
	}

}
