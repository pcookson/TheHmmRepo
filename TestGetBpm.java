
public class TestGetBpm {
	
	public static void main(String args[]){
		BpmThruHttp bpmGetter = new BpmThruHttp();
		
		int bpm = bpmGetter.getBpm("billy talent", "red flag");
		System.out.println(bpm);
	}

} 
