/**
 * handle all the json data
 * @author zhangjie
 * @version 1.0
 * @since 2012-2-13
 * Created on 2012-2-13
 *
 * TODO To handle the json data
 * Window - Preferences - Java - Code Style - Code Templates
 */
package together.connectivity;
 


import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import together.models.EventMsg;
import together.models.UserMsg;

public class JsonHandler {
	 
	private List<EventMsg> eMsgs;
	private List<UserMsg> uMsgs;
	private EventMsg eMsg;
	private UserMsg uMsg;

	 
	/**
	 * default constructor
	 * */
	public JsonHandler() {

	}


	/**
	 * get a list of more apps
	 * 
	 * @param json
	 *            json data
	 * @return a List of More apps
	 * @throws JSONException
	 * */
	 
	public List<EventMsg> getMessages(String json) throws JSONException {
		eMsgs = new ArrayList<EventMsg>();
		JSONObject object = new JSONObject("{\"star\":" + json + "}");
		JSONArray array = object.getJSONArray("star");
		int length = array.length();
		for (int i = 0; i < length; i++) {
//			JSONObject object1 = array.getJSONObject(i);
//			String obj2 = object1.getString("star");
			JSONObject obj = new JSONObject(array.getJSONObject(i).getString("star"));
			eMsg = new EventMsg();
			//TODO 获取正确信息
//			msg.setEvent(obj.getString("event"));
//			msg.setId(obj.getString("id"));
//			msg.setName(obj.getString("name"));
//			msg.setTime(obj.getString("time"));
			eMsgs.add(eMsg);
		}
		return eMsgs;
	}
	
	public List<EventMsg> getEventMessages(String json, String type) throws JSONException {
		eMsgs = new ArrayList<EventMsg>();
		JSONObject object = new JSONObject(json);
		JSONArray array = object.getJSONArray(type);
		int length = array.length();
		for (int i = 0; i < length; i++) {
			JSONObject obj = array.getJSONObject(i);
			eMsg = new EventMsg();
			eMsg.setEid(obj.getString("eid"));
			eMsg.setEname(obj.getString("place"));
			eMsg.setUid(obj.getString("uid"));
			eMsg.setType(obj.getString("type"));
			eMsg.setDescription(obj.getString("description"));
			eMsg.setLongitude(obj.getString("longitude"));
			eMsg.setLatitude(obj.getString("latitude"));
			eMsg.setStartDate(obj.getString("startDate"));
			eMsg.setStartTime(obj.getString("startTime"));
			eMsg.setEndDate(obj.getString("endDate"));
			eMsg.setEndTime(obj.getString("endTime"));
			eMsgs.add(eMsg);
		}
		return eMsgs;
	}
	
	public List<UserMsg> getUserMessages(String json, String type) throws JSONException {
		uMsgs = new ArrayList<UserMsg>();
		JSONObject object = new JSONObject(json);
		JSONArray array = object.getJSONArray(type);
		int length = array.length();
		for (int i = 0; i < length; i++) {
			JSONObject obj = array.getJSONObject(i);
			uMsg = new UserMsg();
			uMsg.setUid(obj.getString("uid"));
			uMsgs.add(uMsg);
		}
		return uMsgs;
	}
	
}