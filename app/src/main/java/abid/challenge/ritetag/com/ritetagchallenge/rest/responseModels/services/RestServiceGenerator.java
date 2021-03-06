package abid.challenge.ritetag.com.ritetagchallenge.rest.responseModels.services;

import abid.challenge.ritetag.com.ritetagchallenge.rest.responseModels.AccessToken;
import abid.challenge.ritetag.com.ritetagchallenge.rest.responseModels.endPoints.ApiEndPoints;
import android.content.Context;
import android.content.SharedPreferences;
import android.support.compat.BuildConfig;
import java.io.IOException;
import okhttp3.Authenticator;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.Route;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by VutkaBilai on 7/29/17.
 * mail : la4508@gmail.com
 */

public class RestServiceGenerator {

  public static final String API_AUTH_BASE_URL = "https://ritekit.com/";
  public static  String API_BASE_URl = "https://api.ritekit.com/v1/";
  public static final String API_OAUTH_REDIRECT = "https://ritekit.com/";

  private static OkHttpClient.Builder httpClient;

  private static Retrofit.Builder builder;

  private static Context mContext;
  private static AccessToken mToken;

  public static <S> S createService(Class<S> serviceClass) {
    httpClient = new OkHttpClient.Builder();
    builder = new Retrofit.Builder()
        .baseUrl(API_AUTH_BASE_URL)
        .addConverterFactory(GsonConverterFactory.create());

    HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
    loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
    httpClient.addInterceptor(loggingInterceptor);

    OkHttpClient client = httpClient.build();
    Retrofit retrofit = builder.client(client).build();
    return retrofit.create(serviceClass);
  }

  public static <S> S createService(Class<S> serviceClass, AccessToken accessToken, Context c) {
    httpClient = new OkHttpClient.Builder();
    builder = new Retrofit.Builder()
        .baseUrl(API_BASE_URl)
        .addConverterFactory(GsonConverterFactory.create());

    HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
    loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
    httpClient.addInterceptor(loggingInterceptor);

    if(accessToken != null) {
      mContext = c;
      mToken = accessToken;
      final AccessToken token = accessToken;
      httpClient.addInterceptor(new Interceptor() {
        @Override
        public Response intercept(Chain chain) throws IOException {
          Request original = chain.request();

          Request.Builder requestBuilder = original.newBuilder()
              .header("Accept", "application/json")
              .header("Content-type", "application/json")
              .header("Authorization",
                  token.getTokenType() + " " + token.getAccessToken())
              .method(original.method(), original.body());

          Request request = requestBuilder.build();
          return chain.proceed(request);
        }
      });

      httpClient.authenticator(new Authenticator() {
        @Override
        public Request authenticate(Route route, Response response) throws IOException {
          if(responseCount(response) >= 2) {
            // If both the original call and the call with refreshed token failed,
            // it will probably keep failing, so don't try again.
            return null;
          }

          // We need a new client, since we don't want to make another call using our client with access token
          builder = new Retrofit.Builder()
              .baseUrl(API_AUTH_BASE_URL)
              .addConverterFactory(GsonConverterFactory.create());

          ApiEndPoints tokenClient = createService(ApiEndPoints.class);
          Call<AccessToken> call = tokenClient.getRefreshAccessToken(mToken.getRefreshToken(),
              mToken.getClientID(), mToken.getClientSecret(), API_OAUTH_REDIRECT,
              "refresh_token");
          try {
            retrofit2.Response<AccessToken> tokenResponse = call.execute();
            if(tokenResponse.code() == 200) {
              AccessToken newToken = tokenResponse.body();
              mToken = newToken;
              SharedPreferences prefs = mContext.getSharedPreferences(BuildConfig.APPLICATION_ID, Context.MODE_PRIVATE);
              prefs.edit().putBoolean("oauth.loggedin", true).apply();
              prefs.edit().putString("oauth.accesstoken", newToken.getAccessToken()).apply();
              prefs.edit().putString("oauth.refreshtoken", newToken.getRefreshToken()).apply();
              prefs.edit().putString("oauth.tokentype", newToken.getTokenType()).apply();

              return response.request().newBuilder()
                  .header("Authorization", newToken.getTokenType() + " " + newToken.getAccessToken())
                  .build();
            } else {
              return null;
            }
          } catch(IOException e) {
            return null;
          }
        }
      });
    }

    OkHttpClient client = httpClient.build();
    Retrofit retrofit = builder.client(client).build();
    return retrofit.create(serviceClass);
  }

  private static int responseCount(Response response) {
    int result = 1;
    while ((response = response.priorResponse()) != null) {
      result++;
    }
    return result;
  }

  public static void changeApiBaseUrl(String newApiBaseUrl) {
    API_BASE_URl = newApiBaseUrl;

    builder = new Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl(API_BASE_URl);
  }
}
