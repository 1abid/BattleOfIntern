package abid.challenge.ritetag.com.ritetagchallenge.login;

import abid.challenge.ritetag.com.ritetagchallenge.BasePresenter;
import abid.challenge.ritetag.com.ritetagchallenge.BaseView;
import com.twitter.sdk.android.core.identity.TwitterLoginButton;

/**
 * Created by VutkaBilai on 7/28/17.
 * mail : la4508@gmail.com
 */

public interface TwitterLoginContract {

  interface view extends BaseView<Presenter>{
    void goToNextActivity();
  }


  interface Presenter extends BasePresenter{
    void setTwitterCallback(TwitterLoginButton twitterLoginButton);
  }
}
