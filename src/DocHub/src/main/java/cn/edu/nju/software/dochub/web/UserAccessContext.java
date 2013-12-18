package cn.edu.nju.software.dochub.web;

public class UserAccessContext {
	private String userName = null;
	private int userId=-1;
	private short permissionLevel=-1;
	
	public UserAccessContext(String userName, int userId, short permissionLevel) {
		this.userName = userName;
		this.userId = userId;
		this.permissionLevel = permissionLevel;
	}
	
	public String getUserName() {
		return userName;
	}
	public int getUserId() {
		return userId;
	}
	public short getPermissionLevel() {
		return permissionLevel;
	}
}
