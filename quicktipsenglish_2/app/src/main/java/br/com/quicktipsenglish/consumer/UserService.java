package br.com.quicktipsenglish.consumer;

import java.util.List;

import br.com.quicktipsenglish.model.User;
import retrofit.Call;
import retrofit.http.Field;
import retrofit.http.FormUrlEncoded;
import retrofit.http.GET;
import retrofit.http.POST;

/**
 * Created by elizeu on 06/11/15.
 */
public interface UserService {

    @FormUrlEncoded
    @POST("/QuickTipsEnglishWs/user/register")
    Call<User> register(@Field("email") String email, @Field("nick_name") String nickName, @Field("password") String password);

    @FormUrlEncoded
    @POST("/QuickTipsEnglishWs/user/login")
    Call<User> login(@Field("nick_name") String nickName, @Field("password") String password);
}
