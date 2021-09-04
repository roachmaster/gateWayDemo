package com.leonardo.rocha.gatewayDemo.model;

public class Post {

	private int id;
	private String foo;
	private String bar;
	private int userId;
	
	public Post() {	
	}
	
	public Post(int id, String foo, String bar, int userId) {
		this.id = id;
		this.foo = foo;
		this.bar = bar;
		this.userId = userId;
	}
	
	public String getFoo() {
		return foo;
	}
	public void setFoo(String foo) {
		this.foo = foo;
	}
	public String getBar() {
		return bar;
	}
	public void setBar(String bar) {
		this.bar = bar;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
}
