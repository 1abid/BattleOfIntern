package abid.challenge.ritetag.com.ritetagchallenge.trendingHashTag;

import abid.challenge.ritetag.com.ritetagchallenge.data.User;
import abid.challenge.ritetag.com.ritetagchallenge.data.UserDataSource;
import abid.challenge.ritetag.com.ritetagchallenge.login.TwitterLoginPresenter;
import abid.challenge.ritetag.com.ritetagchallenge.rest.responseModels.AccessToken;
import abid.challenge.ritetag.com.ritetagchallenge.rest.responseModels.HashTagResponse;
import abid.challenge.ritetag.com.ritetagchallenge.rest.responseModels.endPoints.ApiEndPoints;
import abid.challenge.ritetag.com.ritetagchallenge.rest.responseModels.services.RestServiceGenerator;
import android.content.Context;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.compat.BuildConfig;
import android.util.Log;
import com.google.gson.GsonBuilder;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by VutkaBilai on 7/29/17.
 * mail : la4508@gmail.com
 */

public class TrendingHashTagPresenter implements TrendingHashTagContract.Presenter {

  private final UserDataSource mDataSource ;

  private final TrendingHashTagContract.view mTrendHashTagView ;

  public TrendingHashTagPresenter(@NonNull UserDataSource mDataSource,
      @NonNull TrendingHashTagContract.view mTrendHashTagView) {
    this.mDataSource = checkNotNull(mDataSource , "user data source can not be null");
    this.mTrendHashTagView = checkNotNull(mTrendHashTagView , "view can not be null");

    mTrendHashTagView.setPresenter(this);
  }


  @Override public void start() {

    loadHashTag();
  }

  private void loadHashTag() {
    mTrendHashTagView.showProgress(true);

    AccessToken savedAccessToken = getAccessToken();

    ApiEndPoints apiEndPoints = RestServiceGenerator.createService(ApiEndPoints.class , savedAccessToken,mTrendHashTagView.shareContext());

    Call<HashTagResponse> hashTagCall = apiEndPoints.getTrendingHashTags(1, 1);

    hashTagCall.enqueue(new Callback<HashTagResponse>() {
      @Override
      public void onResponse(Call<HashTagResponse> call, Response<HashTagResponse> response) {
        mTrendHashTagView.showProgress(false);
        Log.w("tag response ",new GsonBuilder().setPrettyPrinting().create().toJson(response));
        if(response.code() == 200)
          mTrendHashTagView.showTags(response.body().getTags());

      }

      @Override public void onFailure(Call<HashTagResponse> call, Throwable t) {

      }
    });
  }

  private AccessToken getAccessToken() {
    final SharedPreferences prefs = mTrendHashTagView.shareContext().getSharedPreferences(
        BuildConfig.APPLICATION_ID, Context.MODE_PRIVATE);
    AccessToken token =new AccessToken();

    token.setAccessToken(prefs.getString("oauth.accesstoken" , ""));
    token.setTokenType(prefs.getString("oauth.tokentype" , ""));
    token.setRefreshToken(prefs.getString("oauth.refreshtoken" , ""));
    token.setClientID(TwitterLoginPresenter.API_OAUTH_CLIENTID);
    token.setClientSecret(TwitterLoginPresenter.API_OAUTH_CLIENTSECRET);

    return token ;
  }

  @Override public void logOutUser() {
    mDataSource.logOut(new UserDataSource.UserLogoutCallback() {
      @Override public void onUserLogout() {
        mTrendHashTagView.showInitialActivity();
      }
    });
  }
}
