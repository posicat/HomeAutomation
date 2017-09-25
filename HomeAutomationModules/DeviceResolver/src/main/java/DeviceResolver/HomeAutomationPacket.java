package DeviceResolver;

import org.json.JSONArray;
import org.json.JSONObject;

public class HomeAutomationPacket {
	private static final String PACKET_DATA = "data";
	private static final String PACKET_SOURCE = "source";
	private static final String PACKET_DESTINATION = "destination";
	JSONObject jsonIn = null;
	JSONObject jsonOut = null;
	JSONObject jsonDataIn = null;
	JSONObject jsonDataOut = null;
	
	public HomeAutomationPacket(String returnPacketSource,String packet) {
		jsonIn= new JSONObject(packet);
		if (jsonIn.has(PACKET_DATA)) {
			jsonDataIn=jsonIn.getJSONObject(PACKET_DATA);
		}
		jsonOut = new JSONObject("{}");
		jsonDataOut = new JSONObject("{}");
		
		if (jsonIn.has(PACKET_SOURCE)) {
			//	Generate the default return header
			JSONArray destination = new JSONArray();
			destination.put(jsonIn.get(PACKET_SOURCE));
			jsonOut.putOnce(PACKET_DESTINATION, destination);
			jsonOut.putOnce(PACKET_SOURCE, returnPacketSource);
		}
	}

	public String getReturnPacket() {
		jsonOut.put(PACKET_DATA, jsonDataOut);
		return jsonOut.toString();
	}
	
	public JSONObject getDataIn( ) {
		return jsonDataIn;
	}

	public JSONObject getDataOut( ) {
		return jsonDataOut;
	}

	public JSONObject getIn( ) {
		return jsonIn;
	}
	
	public JSONObject getOut( ) {
		return jsonOut;
	}

	public boolean hasReturnData() {
		// TODO Auto-generated method stub
		return jsonDataOut.length() > 0;
	}
	
}
