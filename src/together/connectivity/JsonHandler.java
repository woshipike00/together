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

import together.models.MessagePost;

public class JsonHandler {
	 
	private List<MessagePost> msgs;
	private MessagePost msg;

	 
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
	 
	public List<MessagePost> getMessages(String json) throws JSONException {
		msgs = new ArrayList<MessagePost>();
		JSONObject object = new JSONObject("{\"star\":" + json + "}");
		JSONArray array = object.getJSONArray("star");
		int length = array.length();
		for (int i = 0; i < length; i++) {
//			JSONObject object1 = array.getJSONObject(i);
//			String obj2 = object1.getString("star");
			JSONObject obj = new JSONObject(array.getJSONObject(i).getString("star"));
			msg = new MessagePost();
			msg.setEvent(obj.getString("event"));
			msg.setId(obj.getString("id"));
			msg.setName(obj.getString("name"));
			msg.setTime(obj.getString("time"));
			msgs.add(msg);
		}
		return msgs;
	}
}