package com.hmmApp.src;

public class Workout {
	
	private int bpm;
	private long time;
	private Song currentSong;
	private SongHashMap Songs;

	public Workout(){

	}

	public void start(){

	}

	public void end(){

	}

	public void setBpm(int bpm){
		this.bpm = bpm;
	}

	public int getBpm(){
		return bpm;	
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
