package com.xyj.tencent.wechat.model.protocol;

/**
 * @author WJQ
 */
import com.google.gson.JsonObject;
import com.xyj.tencent.common.base.Const;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Query;

/** Retrofit接口 */
public interface IHttpService {

    String HOST_URL =  Const.HOST ;



    int TYPE_LOGIN = 5;
    int TYPE_LOGINTICKET = 6;
    int TYPE_LOGINFRIENDGROUP = 7;
    int TYPE_CONSTDATA = 8;

    @GET("home")
    Call<JsonObject> getHomeData();

    @POST("createOrder")
    @FormUrlEncoded
    Call<JsonObject> createOrder(
            @Field("payInfo") String payInfo);

    @POST("orderList")
    @FormUrlEncoded
    Call<JsonObject> getOrderList(
            @Field("username") String username,
            @Field("token") String token);

    @GET("login")
    Call<JsonObject> login(
            @Query("type") int type,
            @Query("phone") String phone);


    @POST("api/1.0/account/login")
    @FormUrlEncoded
    Call<JsonObject> login(@FieldMap Map<String,String> fieldMap);




    @GET("api/1.0/im/ticket")
    @Headers({
            "content-type: application/x-www-form-urlencoded",
    })
    Call<JsonObject> loginTicket(@Header("ticket") String ticket);


    @GET("api/1.0/groups/wechatFriend/find/ticket")
    @Headers({
            "content-type: application/x-www-form-urlencoded",
    })
    Call<JsonObject> loginFriendGroup(@Header("ticket") String ticket);

    @GET("api/1.0/groups/wechatFriend/find/ticket")
    @Headers({
            "content-type: application/x-www-form-urlencoded",
    })
    Call<JsonObject> contactData(@Header("ticket") String ticket);



    @GET("shopCategory")
    Call<JsonObject> getShopCategory();

    @GET("orderBy")
    Call<JsonObject> getOrderBy();

    @GET("shopDetail")
    Call<JsonObject> getShopDetail(@Query("shopId") int shopId);

    @POST("shopList")
    @FormUrlEncoded
    Call<JsonObject> getShopList(
            @FieldMap Map<String, Object> map);
}
