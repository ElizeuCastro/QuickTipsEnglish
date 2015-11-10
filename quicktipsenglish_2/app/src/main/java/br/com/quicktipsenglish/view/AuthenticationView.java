package br.com.quicktipsenglish.view;

/**
 * Created by elizeucastro on 06/11/15.
 */
public interface AuthenticationView {

    void loginFail();

    void loginSuccess();

    void registerFail();

    void registerSuccess();

    void registerConnectionFail();

    void showLoading(String message);

    void userExists();

    void userNotExits();

    void blankFields();

    void emailInvalid();
}
