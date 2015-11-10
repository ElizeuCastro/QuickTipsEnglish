package br.com.quicktipsenglish.consumer;

import java.util.List;

import br.com.quicktipsenglish.model.Tips;
import retrofit.Call;
import retrofit.http.GET;

public interface TipsService {

    @GET("/QuickTipsEnglishWs/tips")
    Call<List<Tips>> getTips();
}
