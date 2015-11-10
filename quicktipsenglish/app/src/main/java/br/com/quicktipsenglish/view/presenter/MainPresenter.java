package br.com.quicktipsenglish.view.presenter;

import android.content.Context;
import android.os.AsyncTask;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import br.com.quicktipsenglish.cache.TipsCache;
import br.com.quicktipsenglish.model.Menu;
import br.com.quicktipsenglish.model.Tip;
import br.com.quicktipsenglish.view.MainView;

public class MainPresenter {

    private int clickBack;
    private Context context;
    private MainView mainView;

    public MainPresenter(final Context context, final MainView mainView) {
        this.context = context;
        this.mainView = mainView;
    }

    public void load() {
        new AsyncTask<Void, Void, Boolean>() {

            @Override
            protected Boolean doInBackground(Void... params) {
                try {
                    final InputStream is = context.getAssets().open("tips.json");
                    final int size = is.available();
                    final byte[] buffer = new byte[size];
                    is.read(buffer);
                    is.close();
                    final String json = new String(buffer, "UTF-8");
                    TypeToken<List<Tip>> token = new TypeToken<List<Tip>>() {
                    };
                    final Gson gson = new Gson();
                    List<Tip> tips = gson.fromJson(json, token.getType());
                    TipsCache.setTips(tips);
                    TipsCache.setMenus(retrieveMenus(tips));
                } catch (IOException ex) {
                    return Boolean.FALSE;
                }
                return Boolean.TRUE;
            }

            @Override
            protected void onPostExecute(final Boolean result) {
                if (result) {
                    mainView.onSuccess();
                } else {
                    mainView.onFail();
                }
            }
        }.execute();

    }

    private List<Menu> retrieveMenus(List<Tip> tips) {
        final List<Menu> menus = new ArrayList<>();
        for (final Tip tip : tips) {
            menus.add(new Menu(tip.getType(), tip.getDescriptionBr(), tip.getDescriptionUs()));
        }
        return menus;
    }

    public void onBackPressed() {
        if (clickBack < 1) {
            clickBack++;
            mainView.showMessageToCloseApp();
        } else {
            clickBack = 0;
            mainView.finish();
        }
    }

    public void ClearBack() {
        clickBack = 0;
    }
}



