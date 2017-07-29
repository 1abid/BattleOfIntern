package abid.challenge.ritetag.com.ritetagchallenge.login;

import abid.challenge.ritetag.com.ritetagchallenge.R;
import abid.challenge.ritetag.com.ritetagchallenge.data.User;
import abid.challenge.ritetag.com.ritetagchallenge.data.UserDataSource;
import abid.challenge.ritetag.com.ritetagchallenge.rest.responseModels.AccessToken;
import abid.challenge.ritetag.com.ritetagchallenge.rest.responseModels.endPoints.ApiEndPoints;
import abid.challenge.ritetag.com.ritetagchallenge.rest.responseModels.services.RestServiceGenerator;
import android.content.Context;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.compat.BuildConfig;
import android.support.design.widget.BaseTransientBottomBar;
import android.support.design.widget.Snackbar;
import android.util.Log;
import android.view.View;
import android.widget.Toast;
import com.google.gson.GsonBuilder;
import com.twitter.sdk.android.core.Callback;
import com.twitter.sdk.android.core.Result;
import com.twitter.sdk.android.core.Twitter;
import com.twitter.sdk.android.core.TwitterException;
import com.twitter.sdk.android.core.TwitterSession;
import com.twitter.sdk.android.core.identity.TwitterLoginButton;
import retrofit2.Call;
import retrofit2.Response;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by VutkaBilai on 7/28/17.
 * mail : la4508@gmail.com
 */

public class TwitterLoginPresenter implements TwitterLoginContract.Presenter {

  private final UserDataSource mDataSource;

  private final TwitterLoginContract.view mTwitterLoginView;

  public static final String API_OAUTH_CLIENTID = "5c715bd5904082e5bedd541be634ab97c6b97fa9318a";
  public static final String API_OAUTH_CLIENTSECRET = "20fb505cbe0c2e91d18aed06b3cef7df304cf2b69b77cf93c1ccbfc19f58197b";

  public TwitterLoginPresenter(@NonNull UserDataSource mDataSource,
      @NonNull TwitterLoginContract.view mTwitterLoginView) {

    this.mDataSource = checkNotNull(mDataSource, "data source cannot be null");
    this.mTwitterLoginView = checkNotNull(mTwitterLoginView, "view cannot be null!");

    this.mTwitterLoginView.setPresenter(this);
  }

  @Override public void start() {
    mDataSource.isUserSaved(new UserDataSource.CheckUserExistsCallback() {
      @Override public void onUserFound(boolean found) {
        if (found)
          mTwitterLoginView.goToNextActivity();
      }
    });
  }

  @Override public void setTwitterCallback(TwitterLoginButton twitterLoginButton) {
    checkNotNull(twitterLoginButton, "Twitter button is null ");

    twitterLoginButton.setCallback(new Callback<TwitterSession>() {
      @Override public void success(Result<TwitterSession> result) {
        Log.d(getClass().getSimpleName() , "success twitter login :"+result.data.getUserName());
        getRiteTagAccessToken();
        RestServiceGenerator.changeApiBaseUrl(mTwitterLoginView.shareContext().getString(R.string.api_end_point));
        saveUser(result);
      }

      @Override public void failure(TwitterException exception) {
        Log.d(getClass().getSimpleName() , "failed twitter login :"+exception.getMessage());

      }
    });

  }

  private void getRiteTagAccessToken() {

    final SharedPreferences prefs = mTwitterLoginView.shareContext().getSharedPreferences(
        BuildConfig.APPLICATION_ID, Context.MODE_PRIVATE);

    ApiEndPoints client = RestServiceGenerator.createService(ApiEndPoints.class);
    Call<AccessToken> call = client.getNewAccessToken("data", API_OAUTH_CLIENTID,
        API_OAUTH_CLIENTSECRET, "client_credentials");
    call.enqueue(new retrofit2.Callback<AccessToken>() {
      @Override
      public void onResponse(Call<AccessToken> call, Response<AccessToken> response) {
        int statusCode = response.code();

        if(statusCode == 200) {
          AccessToken token = response.body();
          Log.i(getClass().getSimpleName() , "Accesss token "+token.toString());

          prefs.edit().putBoolean("oauth.loggedin", true).apply();
          prefs.edit().putString("oauth.accesstoken", token.getAccessToken()).apply();
          prefs.edit().putString("oauth.refreshtoken", token.getRefreshToken()).apply();
          prefs.edit().putString("oauth.tokentype", token.getTokenType()).apply();
        } else {
          // TODO Handle errors on a failed response
        }
      }

      @Override
      public void onFailure(Call<AccessToken> call, Throwable t) {
        // TODO Handle failure
      }
    });
  }



  private void saveUser(Result<TwitterSession> result ) {
    User user ;
    String userTwitterName = result.data.getUserName();
    long userTwitterId = result.data.getUserId();

    user = new User(userTwitterName , userTwitterId);
    mDataSource.saveUser(user, new UserDataSource.SaveUserCallback() {
      @Override public void onSuccessFullUserSave() {
        mTwitterLoginView.goToNextActivity();
      }
    });
  }
}
