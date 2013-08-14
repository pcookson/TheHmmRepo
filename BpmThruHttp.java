import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Vector;


public class BpmThruHttp {
	private int bpm;
	public BpmThruHttp(){
		
	}


	public int getBpm(String artist, String songName){
		HttpURLConnection connection = null;
		artist = artist.replaceAll(" ", "-");
		songName = songName.replaceAll(" ", "-");
		try {

			URL oracle = new URL("http://www.songbpm.com/"+artist+"/"+songName);
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

}
