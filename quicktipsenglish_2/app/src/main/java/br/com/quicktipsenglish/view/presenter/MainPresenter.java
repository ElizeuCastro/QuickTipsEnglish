package br.com.quicktipsenglish.view.presenter;

import android.os.AsyncTask;

import java.io.IOException;
import java.net.ConnectException;
import java.util.ArrayList;
import java.util.List;

import br.com.quicktipsenglish.cache.TipsCache;
import br.com.quicktipsenglish.consumer.ServiceGenerator;
import br.com.quicktipsenglish.consumer.TipsService;
import br.com.quicktipsenglish.model.Menu;
import br.com.quicktipsenglish.model.Tips;
import br.com.quicktipsenglish.view.MainView;

public class MainPresenter {

    private MainView view;

    public MainPresenter(MainView view) {
        this.view = view;
    }

    public void getTips() {
        new AsyncTask<Void, Void, Boolean>() {

            private boolean connectionError;

            @Override
            protected Boolean doInBackground(Void... params) {
                try {
                    final TipsService tipsService = ServiceGenerator.createService(TipsService.class);
                    List<Tips> tips = tipsService.getTips().execute().body();
                    TipsCache.setTips(tips);
                    TipsCache.setMenus(retrieveMenus(tips));
                } catch (ConnectException e) {
                    connectionError = true;
                } catch (IOException ex) {
                    return Boolean.FALSE;
                }
                return Boolean.TRUE;
            }

            @Override
            protected void onPostExecute(final Boolean result) {
                if (connectionError) {
                    view.connectionFail();
                } else if (result) {
                    view.onSuccess();
                } else {
                    view.onFail();
                }
            }
        }.execute();

    }

    private List<Menu> retrieveMenus(List<Tips> tips) {
        final List<Menu> menus = new ArrayList<>();
        for (final Tips tip : tips) {
            menus.add(new Menu(tip.getId(), tip.getName()));
        }
        return menus;
    }

}



