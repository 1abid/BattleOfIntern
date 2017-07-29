package abid.challenge.ritetag.com.ritetagchallenge.hashTagInfluence;

import abid.challenge.ritetag.com.ritetagchallenge.login.TwitterLoginPresenter;
import abid.challenge.ritetag.com.ritetagchallenge.rest.responseModels.AccessToken;
import abid.challenge.ritetag.com.ritetagchallenge.rest.responseModels.HashTagResponse;
import abid.challenge.ritetag.com.ritetagchallenge.rest.responseModels.InfluenceResponse;
import abid.challenge.ritetag.com.ritetagchallenge.rest.responseModels.endPoints.ApiEndPoints;
import abid.challenge.ritetag.com.ritetagchallenge.rest.responseModels.services.RestServiceGenerator;
import android.content.Context;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.compat.BuildConfig;
import android.support.design.widget.Snackbar;
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

public class InfluencePresenter implements InflueceContract.presenter {

  private final InflueceContract.view mTrendHashTagView ;
  
  private final String tag ;

  public InfluencePresenter(@NonNull InflueceContract.view mTrendHashTagView, @NonNull String tag) {
    this.mTrendHashTagView = checkNotNull(mTrendHashTagView , "view can not be null");
    this.tag = checkNotNull(tag , "selected tag can not be null");
    
    mTrendHashTagView.setPresenter(this);
  }

  @Override public void start() {
    loadInfluences();
  }

  private void loadInfluences() {
    mTrendHashTagView.showProgress(true);

    AccessToken savedAccessToken = getAccessToken();

    ApiEndPoints
        apiEndPoints = RestServiceGenerator.createService(ApiEndPoints.class , savedAccessToken,mTrendHashTagView.shareContext());

    Call<InfluenceResponse> influenceCall = apiEndPoints.getInfluences(tag);

    influenceCall.enqueue(new Callback<InfluenceResponse>() {
      @Override
      public void onResponse(Call<InfluenceResponse> call, Response<InfluenceResponse> response) {
        mTrendHashTagView.showProgress(false);
        //Log.w("influence response ",new GsonBuilder().setPrettyPrinting().create().toJson(response));
        if(response.code() == 500)
          mTrendHashTagView.showToast(Snackbar.make(mTrendHashTagView.getRootView() , "Internal Server Error" , Snackbar.LENGTH_INDEFINITE));
        if(response.code() == 200)
          mTrendHashTagView.showInfluence(response.body().getInfluencers());
      }

      @Override public void onFailure(Call<InfluenceResponse> call, Throwable t) {
        mTrendHashTagView.showProgress(false);
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
}
