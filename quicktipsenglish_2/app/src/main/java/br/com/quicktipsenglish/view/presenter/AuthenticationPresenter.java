package br.com.quicktipsenglish.view.presenter;

import android.os.AsyncTask;

import java.io.IOException;

import br.com.quicktipsenglish.consumer.ServiceGenerator;
import br.com.quicktipsenglish.consumer.UserService;
import br.com.quicktipsenglish.model.User;
import br.com.quicktipsenglish.view.AuthenticationView;

/**
 * Created by elizeucastro on 06/11/15.
 */
public class AuthenticationPresenter {

    private AuthenticationView view;

    public AuthenticationPresenter(final AuthenticationView view) {
        this.view = view;
    }

    public void login(final String login, final String password) {

    }

    public void register(final String email, final String login, final String password) {

        new AsyncTask<Void, Void, User>() {

            @Override
            protected User doInBackground(Void... voids) {
                User user;
                try {
                    final UserService userService = ServiceGenerator.createService(UserService.class);
                    user = userService.register(email, login, password).execute().body();
                } catch (IOException e) {
                    user = new User();
                    new RuntimeException(e);
                }
                return user;
            }

            @Override
            protected void onPostExecute(User user) {
                if (user.isSaved()) {
                    view.registerSuccess();
                } else {
                    view.registerFail();
                }
            }
        }.execute();

    }
}
