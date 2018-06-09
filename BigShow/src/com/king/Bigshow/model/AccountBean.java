package com.king.Bigshow.model;

public class AccountBean {
	private String User_Name;
    private String Password;
    private String UserType;
    public AccountBean(){}
    public AccountBean(String User_Name, String Password, String UserType) {
        this.User_Name = User_Name;
        this.Password = Password;
        this.UserType = UserType;
    }
	public String getUser_Name() {
		return User_Name;
	}
	public void setUser_Name(String user_Name) {
		User_Name = user_Name;
	}
	public String getPassword() {
		return Password;
	}
	public void setPassword(String password) {
		Password = password;
	}
	public String getUserType() {
		return UserType;
	}
	public void setUserType(String userType) {
		UserType = userType;
	}
}
