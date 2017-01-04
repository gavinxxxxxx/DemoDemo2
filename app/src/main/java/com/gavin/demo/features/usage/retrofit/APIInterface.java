package com.gavin.demo.features.usage.retrofit;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

/**
 * 这里是萌萌哒注释君
 *
 * @author gavin.xiong 2016/12/5  2016/12/5
 */
public interface APIInterface {

    /**
     * GET 请求
     */
    @GET("/users/{user}")
    Call<TestModel> testGet(@Path("user") String user);

    /**
     * GET 请求 - 带header
     */
    @GET("/users/{user}")
    Call<TestModel> testGet2(@Header("token") String token, @Path("user") String user);

    /**
     * GET 请求 - 一个参数
     */
    @GET("/users")
    Call<TestModel> testGetoneParam(@Query("username") String username);

    /**
     * GET 请求 - 多个参数
     */
    @GET("/users")
    Call<TestModel> testGetmanyParams(@QueryMap Map<String, String> params);

    /**
     * POST 请求
     *      请求参数 user 会转化成 Json 格式的对象发送到服务器。
     */
    @POST("/users/gavinxxxxxx")
    Call<TestModel> testPost(@Body User user);



    /***********************************  测试接口  **********************************/

    /**
     * GET 请求 - 获取用户信息
     */
    @GET("/api/user/{userId}")
    Call<ApiResult<User>> testGetUserInfo(@Path("userId") String userId);

    /**
     * POST 请求 -  修改昵称
     */
    @POST("/updateNickName@UserAction.action")
    Call<ApiResult<String>> testPostUpdateNickName(@Header("x-tn-token")String token, @Body UpdateUserNameBody body);


}