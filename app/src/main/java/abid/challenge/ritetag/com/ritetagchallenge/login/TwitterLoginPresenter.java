package abid.challenge.ritetag.com.ritetagchallenge.login;

import com.twitter.sdk.android.core.identity.TwitterLoginButton;

/**
 * Created by VutkaBilai on 7/28/17.
 * mail : la4508@gmail.com
 */

public class TwitterLoginPresenter implements TwitterLoginContract.Presenter {


  @Override public void start() {
    checkUserExists();
  }

  private void checkUserExists() {

  }

  @Override public void setTwitterCallback(TwitterLoginButton twitterLoginButton) {

  }
}
