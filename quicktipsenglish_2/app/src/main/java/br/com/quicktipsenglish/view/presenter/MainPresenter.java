package br.com.quicktipsenglish.view.presenter;

import android.content.Context;
import android.os.AsyncTask;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import br.com.quicktipsenglish.cache.TipsCache;
import br.com.quicktipsenglish.consumer.ServiceGenerator;
import br.com.quicktipsenglish.consumer.UserResource;
import br.com.quicktipsenglish.model.Menu;
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

                //    UserResource userResource = ServiceGenerator.createService(UserResource.class);
                  //  userResource.getTips();


//                    RequestQueue queue = Volley.newRequestQueue(context);
//                    String url = "http://192.168.1.5:8080/QuickTipsEnglishWs/tips";
//
//                    StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
//                            new Response.Listener<String>() {
//                                @Override
//                                public void onResponse(String response) {
//                                    response.getClass();
//                                }
//                            }, new Response.ErrorListener() {
//                        @Override
//                        public void onErrorResponse(VolleyError error) {
//                            error.getMessage();
//                        }
//                    });
//                    stringRequest.setRetryPolicy(new DefaultRetryPolicy(5000,
//                            DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
//                            DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
//                    queue.add(stringRequest);


                    URL url = new URL("http://192.168.1.5:8080/QuickTipsEnglishWs/tips");
                    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                    conn.setReadTimeout(10000 /* milliseconds */);
                    conn.setConnectTimeout(15000 /* milliseconds */);
                    conn.setRequestMethod("GET");
                    conn.setDoInput(true);
                    // Starts the query
                    conn.connect();
                    conn.getResponseMessage();


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
                    callback.onSuccess();
                } else {
                    callback.onFail();
                }
            }
        }.execute();

    }

    private List<Menu> retrieveMenus(List<Tip> tips) {
        final List<Menu> menus = new ArrayList<>();
        for (final Tip tip : tips) {
            menus.add(new Menu(tip.getType(), tip.getTypeDescription()));
        }
        return menus;
    }


}



