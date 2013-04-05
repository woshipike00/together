/*
 * Created on 2012-2-13
 *
 * TODO To describe a star
 * Window - Preferences - Java - Code Style - Code Templates
 */
package together.models;

public class EventMsg {
	private String id;
	private String username;
	private String event;
	private String url;
	private String large_url;
	private String time;
	
	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return username;
	}

	public void setName(String name) {
		this.username = name;
	}

	public String getEvent() {
		return event;
	}

	public void setEvent(String description) {
		this.event = description;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getLargeUrl() {
		return large_url;
	}

	public void setLargeUrl(String url) {
		this.large_url = url;
	}

	public void print() {
		System.out.println(id + "," + username);
	}
}
