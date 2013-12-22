package cn.edu.nju.software.dochub.web;

public class UserAccessContext {
	private String userName = null;
	private String name=null;
	private int userId=-1;
	private short permissionLevel=-1;
	
	public UserAccessContext(String userName, String name,int userId, short permissionLevel) {
		this.userName = userName;
		this.name=name;
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

	public String getName() {
		return name;
	}
}
