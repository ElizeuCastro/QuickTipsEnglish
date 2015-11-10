package br.com.quicktipsenglish.util;

import android.content.Context;
import android.content.SharedPreferences;

import br.com.quicktipsenglish.model.User;

public final class TipsPreferences {

    private static SharedPreferences sharedPreferences;
    private static SharedPreferences.Editor editor;
    private static TipsPreferences instance;

    private TipsPreferences(final Context context) {
        sharedPreferences = context.getSharedPreferences("TIPS", Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
    }

    public static TipsPreferences getInstance(final Context context) {
        if (instance == null) {
            instance = new TipsPreferences(context);
        }
        return instance;
    }

    public static void setSessionUser(final User user) {
        editor.putInt("ID", user.getId()).commit();
        editor.putString("EMAIL", user.getEmail()).commit();
        editor.putString("NICKNAME", user.getNickName()).commit();
        editor.putString("PASSWORD", user.getPassword()).commit();
    }

    public static User getSessionUser() {
        if (containsLastUser()) {
            final User user = new User();
            user.setId(sharedPreferences.getInt("ID", -1));
            user.setEmail(sharedPreferences.getString("EMAIL", ""));
            user.setNickName(sharedPreferences.getString("NICKNAME", ""));
            user.setPassword(sharedPreferences.getString("PASSWORD", ""));
            return user;
        }
        return null;
    }

    private static boolean containsLastUser() {
        return sharedPreferences.getInt("ID", -1) > -1;
    }

}
