package br.com.quicktipsenglish.model;

public class User {

    private Integer id;
    private String email;
    private String nickName;
    private String password;

    public User() {
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
        return this.id != null && this.id > -1;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
