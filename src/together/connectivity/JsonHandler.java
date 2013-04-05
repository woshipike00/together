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

	 

	// private User user;

	/**
	 * default constructor
	 * */
	public JsonHandler() {

	}

	/**
	 * get user from json data
	 * 
	 * @param json
	 *            json data , string
	 * @return user instance
	 * @throws JSONException
	 * */
//	public User getUser(String json) throws JSONException {
//		JSONObject obj = new JSONObject(json);
//		User u = new User();
//		u.setUid(obj.getString("uid"));
//		u.setNickname(obj.getString("nickname"));
//		u.setAvatar(obj.getString("avatar"));
//		u.setGender(obj.getString("gender"));
//		u.setIntegrity(obj.getString("integrity"));
//		u.setPoint(obj.getString("point"));
//		u.setLocation(obj.getString("location"));
//		return u;
//	}

	/**
	 * get login user from json data
	 * 
	 * @param json
	 *            json data
	 * @return login user instance
	 * @throws JSONException
	 * */
//	public LoginUser getLoginUser(String json) throws JSONException {
//		JSONObject obj = new JSONObject(json);
//		LoginUser u = new LoginUser();
//		u.setUid(obj.getString("uid"));
//		u.setNickname(obj.getString("nickname"));
//		u.setGender(obj.getString("gender"));
//		u.setIntegrity(obj.getString("integrity"));
//		// System.out.println("integrity = " + obj.getString("integrity"));
//		u.setPoint(obj.getString("point"));
//		u.setLocation(obj.getString("location"));
//		u.setMobile(obj.getString("mobile"));
//		return u;
//	}

	/**
	 * get login user instance by user id
	 * 
	 * @param json
	 *            json data
	 * @return user instance
	 * @throws JSONException
	 *
	public LoginUser getUserById(String json) throws JSONException {
		JSONObject obj = new JSONObject(json);
		LoginUser u = new LoginUser();
		u.setUid(obj.getString("uid"));
		u.setUname(obj.getString("uname"));
		u.setNickname(obj.getString("nickname"));
		u.setAvatar(obj.getString("avatar"));
		u.setGender(obj.getString("gender"));
		u.setIntegrity(obj.getString("integrity"));
		u.setPoint(obj.getString("point"));
		u.setLocation(obj.getString("location"));
		u.setMobile(obj.getString("mobile"));
		return u;
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
//			msg.setUrl(obj.getString("thumbnail_url_small").trim());
//			msg.setLargeUrl(obj.getString("thumbnail_url_large").trim());
			msgs.add(msg);
		}
		return msgs;
	}
}