package com.hmmApp.src;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Vector;

public class Song {

	private String artist;
	private String genre;
	private String title;
	private int bpm;
	

	public Song(String artist, String genre, String title){
		this.artist = artist;
		this.genre = genre;
		this.title = title;
		
	}

	public String getArtist() {
		return artist;
	}

	public void setArtist(String artist) {
		this.artist = artist;
	}

	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getBpm() {
		return bpm;
	}

	public void setBpm(int bpm) {
		this.bpm = bpm;
	}

	public int httpGetBpm(){
		HttpURLConnection connection = null;
		String artistName = artist.replaceAll(" ", "-");
		String songName = title.replaceAll(" ", "-");
		int bpm = 0;
		try {

			URL oracle = new URL("http://www.songbpm.com/"+artistName+"/"+songName);
			URLConnection yc = oracle.openConnection();
			BufferedReader in = new BufferedReader(new InputStreamReader(
					yc.getInputStream()));
			String inputLine;
			Vector<String> htmlVector = new Vector<String>();
			while ((inputLine = in.readLine()) != null) {
				htmlVector.add(inputLine);
				//System.out.println(inputLine);

			}
			in.close();
			String actualBpm = null;
			int i=0;
			while(htmlVector.get(i)!=null){
				if(htmlVector.get(i).compareTo("<div class='text'>BPM</div>")==0){
					actualBpm=htmlVector.get(i-1);
					break;
				}
				i++;
			}
			bpm = Integer.parseInt(actualBpm.split("<div class='number'>")[1].split("</div>")[0]);


		} catch (MalformedURLException e1) {
			e1.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		} finally {
			if(null != connection) { connection.disconnect(); }
		}
		return bpm;
	}


	public void genreGetBpm(){

	}
}
