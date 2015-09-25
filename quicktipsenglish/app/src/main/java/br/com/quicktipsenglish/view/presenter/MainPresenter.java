package br.com.quicktipsenglish.view.presenter;

import android.content.Context;
import android.os.AsyncTask;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import br.com.quicktipsenglish.cache.TipsCache;
import br.com.quicktipsenglish.model.Tip;

public class MainPresenter {

    public interface Callback {

        void onSuccess();

        void onFail();

    }

    public void load(final Context context, final Callback callback) {
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
                } catch (IOException ex) {
                    return Boolean.FALSE;
                }
                return Boolean.TRUE;
            }

            @Override
            protected void onPostExecute(final Boolean result) {
                if (result) {
                    callback.onSuccess();
                } else {
                    callback.onFail();
                }
            }
        }.execute();

    }


}



