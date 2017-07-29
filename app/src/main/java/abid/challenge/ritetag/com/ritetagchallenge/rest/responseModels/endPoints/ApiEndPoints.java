package abid.challenge.ritetag.com.ritetagchallenge.rest.responseModels.endPoints;

import abid.challenge.ritetag.com.ritetagchallenge.rest.responseModels.AccessToken;
import abid.challenge.ritetag.com.ritetagchallenge.rest.responseModels.HashTagResponse;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by VutkaBilai on 7/29/17.
 * mail : la4508@gmail.com
 */

public interface ApiEndPoints {

  @FormUrlEncoded
  @POST("/oauth/token") Call<AccessToken> getNewAccessToken(
      @Field("scope") String data,
      @Field("client_id") String clientId,
      @Field("client_secret") String clientSecret,
      @Field("grant_type") String grantType);

  @FormUrlEncoded
  @POST("/oauth/token")
  Call<AccessToken> getRefreshAccessToken(
      @Field("refresh_token") String refreshToken,
      @Field("client_id") String clientId,
      @Field("client_secret") String clientSecret,
      @Field("redirect_uri") String redirectUri,
      @Field("grant_type") String grantType);


  @GET("search/trending")
  Call<HashTagResponse> getTrendingHashTags(@Query("green") boolean green , @Query("latin") boolean latin);
}
