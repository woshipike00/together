package info.nemoworks.inmusic.models;

public class User {
	private String uid;
	private String nickname;
	private String gender;
	private String location;
	private String avatar;
	private String integrity;
	private String point;
	private String uname;

	public void print() {
		System.out.println("uid=" + uid + ",uname=" + uname);
	}

	public String getUname() {
		return uname;
	}

	public void setUname(String uname) {
		this.uname = uname;
	}

	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

	public String getIntegrity() {
		return integrity;
	}

	public void setIntegrity(String integrity) {
		this.integrity = integrity;
	}

	public String getPoint() {
		return point;
	}

	public void setPoint(String point) {
		this.point = point;
	}

}
