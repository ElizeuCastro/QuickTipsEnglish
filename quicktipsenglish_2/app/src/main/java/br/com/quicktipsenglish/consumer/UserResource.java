package br.com.quicktipsenglish.consumer;

import com.squareup.okhttp.Response;

import java.util.List;

import br.com.quicktipsenglish.model.Category;
import retrofit.Call;
import retrofit.Callback;
import retrofit.http.GET;

/**
 * Created by elizeu on 06/11/15.
 */
public interface UserResource {

    @GET("/tips")
    Call<List<Category>> getTips();
}
