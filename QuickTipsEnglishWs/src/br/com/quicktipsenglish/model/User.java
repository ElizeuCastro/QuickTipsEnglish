package br.com.quicktipsenglish.model;

public class User {

	private Integer id;
	private String email;
	private String nickName;
	private String password;

	public User() {
	}

	public User(String email, String nickName, String password) {
		super();
		this.email = email;
		this.nickName = nickName;
		this.password = password;
	}

	public User(Integer id, String email, String nickName, String password) {
		super();
		this.id = id;
		this.email = email;
		this.nickName = nickName;
		this.password = password;
	}

	public User(String nickName, String password) {
		this.nickName = nickName;
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public String getNickName() {
		return nickName;
	}

	public String getPassword() {
		return password;
	}

	public boolean isSaved() {
		return this.id > -1;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

}
