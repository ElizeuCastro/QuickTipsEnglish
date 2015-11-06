package test;

import com.google.gson.Gson;

import br.com.quicktipsenglish.model.User;

public class run {

	public static void main(String[] args) {
		User user = new User("tese", "tese", "tese");
		System.out.println(new Gson().toJson(user));
	}
}
