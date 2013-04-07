/*
 * Created on 2012-2-13
 *
 * TODO To describe a star
 * Window - Preferences - Java - Code Style - Code Templates
 */
package together.models;

public class EventMsg {
	private String eid;
	private String place;
	private String uid;
	private String type;
	private String description;
	private String longitude;
	private String latitude;
	private String startDate;
	private String StartTime;
	private String endDate;
	private String endTime;
	
	public String getEid() {
		return eid;
	}

	public void setEid(String eid) {
		this.eid = eid;
	}

	public String getPlace() {
		return place;
	}

	public void setEname(String ename) {
		this.place = ename;
	}

	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getLongitude() {
		return longitude;
	}

	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}

	public String getLatitude() {
		return latitude;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getStartTime() {
		return StartTime;
	}

	public void setStartTime(String startTime) {
		StartTime = startTime;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
//
//	public String getTime() {
//		return latitude;
//	}
//
//	public void setTime(String time) {
//		this.latitude = time;
//	}
//
//	public String getId() {
//		return eid;
//	}
//
//	public void setId(String id) {
//		this.eid = id;
//	}
//
//	public String getName() {
//		return ename;
//	}
//
//	public void setName(String name) {
//		this.ename = name;
//	}
//
//	public String getEvent() {
//		return uid;
//	}
//
//	public void setEvent(String description) {
//		this.uid = description;
//	}
//
//	public String getUrl() {
//		return type;
//	}
//
//	public void setUrl(String url) {
//		this.type = url;
//	}
//
//	public String getLargeUrl() {
//		return longitude;
//	}
//
//	public void setLargeUrl(String url) {
//		this.longitude = url;
//	}

	public void print() {
		System.out.println(eid + "," + place);
	}
}
