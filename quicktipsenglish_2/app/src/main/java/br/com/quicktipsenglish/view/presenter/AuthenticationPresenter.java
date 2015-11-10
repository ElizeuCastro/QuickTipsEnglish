package br.com.quicktipsenglish.view.presenter;

import android.content.Context;
import android.os.AsyncTask;

import java.io.IOException;
import java.net.ConnectException;
import java.net.HttpURLConnection;

import br.com.quicktipsenglish.consumer.ServiceGenerator;
import br.com.quicktipsenglish.consumer.UserService;
import br.com.quicktipsenglish.model.User;
import br.com.quicktipsenglish.util.TipsPreferences;
import br.com.quicktipsenglish.util.ValidationUtil;
import br.com.quicktipsenglish.view.AuthenticationView;
import retrofit.Response;

public class AuthenticationPresenter {

    private AuthenticationView view;
    private Context context;

    public AuthenticationPresenter(final AuthenticationView view, final Context context) {
        this.view = view;
        this.context = context;
    }

    public void validateLastAuthentication() {
        final User user = TipsPreferences.getInstance(context).getSessionUser();
        if (user != null) {
            view.showLoading("Wait, authenticating user...");
            if (ValidationUtil.isNotEmpty(user.getNickName(), user.getPassword())) {
                login(user.getNickName(), user.getPassword());
            }
        }
    }

    public void login(final String nickName, final String password) {

        if (ValidationUtil.isEmpty(nickName, password)) {
            view.blankFields();
            return;
        }

        new AsyncTask<Void, Void, Integer>() {

            @Override
            protected Integer doInBackground(Void... voids) {
                int resultCode;
                try {
                    final UserService userService = ServiceGenerator.createService(UserService.class);
                    final Response<User> response = userService.login(nickName, password).execute();
                    resultCode = response.code();
                    if (resultCode == HttpURLConnection.HTTP_OK) {
                        final User user = response.body();
                        TipsPreferences.getInstance(context).setSessionUser(user);
                    }
                } catch (ConnectException e) {
                    resultCode = HttpURLConnection.HTTP_BAD_REQUEST;
                } catch (IOException e) {
                    resultCode = -1;
                }
                return resultCode;
            }

            @Override
            protected void onPostExecute(final Integer integer) {
                switch (integer) {
                    case HttpURLConnection.HTTP_OK:
                        view.loginSuccess();
                        break;
                    case HttpURLConnection.HTTP_NOT_FOUND:
                        view.userNotExits();
                        break;
                    case HttpURLConnection.HTTP_BAD_REQUEST:
                        view.registerConnectionFail();
                        break;
                    default:
                        view.loginFail();
                        break;
                }
            }
        }.execute();

    }

    public void register(final String email, final String login, final String password) {

        if (ValidationUtil.isEmpty(email, login, password)) {
            view.blankFields();
            return;
        }

        if (!ValidationUtil.isValidEmail(email)) {
            view.emailInvalid();
            return;
        }

        new AsyncTask<Void, Void, Integer>() {

            @Override
            protected Integer doInBackground(Void... voids) {
                int resultCode;
                try {
                    final UserService userService = ServiceGenerator.createService(UserService.class);
                    Response<User> response = userService.register(email, login, password).execute();
                    resultCode = response.code();
                    if (resultCode == HttpURLConnection.HTTP_OK) {
                        final User user = response.body();
                        TipsPreferences.getInstance(context).setSessionUser(user);
                    }
                } catch (ConnectException e) {
                    resultCode = HttpURLConnection.HTTP_BAD_REQUEST;
                } catch (IOException e) {
                    resultCode = -1;
                }
                return resultCode;
            }

            @Override
            protected void onPostExecute(Integer resultCode) {
                switch (resultCode) {
                    case HttpURLConnection.HTTP_OK:
                        view.registerSuccess();
                        break;
                    case HttpURLConnection.HTTP_CONFLICT:
                        view.userExists();
                        break;
                    case HttpURLConnection.HTTP_BAD_REQUEST:
                        view.registerConnectionFail();
                        break;
                    default:
                        view.registerFail();
                        break;
                }
            }
        }.execute();
    }
}
