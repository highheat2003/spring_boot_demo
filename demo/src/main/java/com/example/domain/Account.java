package com.example.domain;

public class Account {
	String ac;
	String pw;
	String role;
	
	public String getAc() {
		return ac;
	}
	public void setAc(String ac) {
		this.ac = ac;
	}
	public String getPw() {
		return pw;
	}
	public void setPw(String pw) {
		this.pw = pw;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	@Override
	public String toString() {
		return "Account [ac=" + ac + ", pw=" + pw + ", role=" + role + "]";
	}
	
	
}
