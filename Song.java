package com.example.appBackEnd;

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
	private String title;
	private int artistId;
	private String albumName;
	private int albumId;
	private int songId;
	private String songPath;
	private int bpm;


	public Song(String artist, int artistId, String albumName, int albumId, 
			String title, int songId, String songPath){
		this.artist = artist;
		this.artistId = artistId;
		this.albumName = albumName;
		this.albumId = albumId;
		this.title = title;
		this.songId = songId;
		this.songPath = songPath;
		this.bpm = httpGetBpm();
	}

	public int getArtistId() {
		return artistId;
	}

	public void setArtistId(int artistId) {
		this.artistId = artistId;
	}

	public String getAlbumName() {
		return albumName;
	}

	public void setAlbumName(String albumName) {
		this.albumName = albumName;
	}

	public int getAlbumId() {
		return albumId;
	}

	public void setAlbumId(int albumId) {
		this.albumId = albumId;
	}

	public int getSongId() {
		return songId;
	}

	public void setSongId(int songId) {
		this.songId = songId;
	}

	public String getSongPath() {
		return songPath;
	}

	public void setSongPath(String songPath) {
		this.songPath = songPath;
	}

	public String getArtist() {
		return artist;
	}

	public void setArtist(String artist) {
		this.artist = artist;
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

	private int httpGetBpm(){
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


	public String toString(){
		StringBuilder songInformation = new StringBuilder();
		String space = " ";
		songInformation.append(getTitle());
		songInformation.append(space);
		songInformation.append(getArtist());
		songInformation.append(space);
		songInformation.append(getBpm());
		return songInformation.toString();
	}
}
