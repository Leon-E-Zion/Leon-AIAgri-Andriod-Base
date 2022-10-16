package com.example.comparetest.network;

import com.example.comparetest.activity.DetailsActivity;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface Service {

    //登录接口
    @POST(value = "user/login")
    Call<User> Login(
            @Body User user
    );
    //注册接口
    @POST(value = "user/register")
    Call<User> Register(
            @Body User user
    );

    //All
    @GET(value = "test/All")
    Call<ResponseBody> getAll();

    //block=1
    @GET(value = "test/block=1")
    Call<ResponseBody> getById1();

    //block=2
    @GET(value = "test/block=2")
    Call<ResponseBody> getById2();

    //block=3
    @GET(value = "test/block=3")
    Call<ResponseBody> getById3();

    //block=4
    @GET(value = "test/block=4")
    Call<ResponseBody> getById4();

    //得到详细信息，包括图片
    @FormUrlEncoded
    @POST(value = "/test/send")
    Call<ResponseBody> getImage(
            @Field("option") String option,
            @Field("comment") int id
    );

//    @GET(value = "captcha")
//    Call<code> getCode();
//
//    @POST(value = "login")
//    @FormUrlEncoded
//    Call<User> toLogin(
//            @Field("code") String code,
//            @Field("password")String password,
//            @Field("token")String token,
//            @Field("username")String username
//    );
//
//    @GET(value = "sys/register/list")
//    Call<data_person> show1(
//            @Query("username") String username,
//            @Header("authorization")String header
//    );
//
//    @POST(value = "sys/register/register")
//    @FormUrlEncoded
//    Call<Button> QianDao(
//            @Field("username") String username,
//            @Header("authorization") String header
//    );
//
//    @PUT(value = "sys/register/finishregister")
//    Call<Button> QianTui(
//            @Query("username") String username,
//            @Header("authorization") String header
//    );
//
//    @GET(value = "sys/user/current")
//    Call<User_person> showww( @Header("authorization") String header);
//
//    @GET(value = "location/map")
//    Call<String> getPosition();
}
