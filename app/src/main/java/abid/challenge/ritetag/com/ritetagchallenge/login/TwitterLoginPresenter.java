package abid.challenge.ritetag.com.ritetagchallenge.login;

import abid.challenge.ritetag.com.ritetagchallenge.R;
import abid.challenge.ritetag.com.ritetagchallenge.data.UserDataSource;
import android.support.annotation.NonNull;
import android.support.design.widget.BaseTransientBottomBar;
import android.support.design.widget.Snackbar;
import android.view.View;
import com.twitter.sdk.android.core.identity.TwitterLoginButton;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by VutkaBilai on 7/28/17.
 * mail : la4508@gmail.com
 */

public class TwitterLoginPresenter implements TwitterLoginContract.Presenter {

  private final UserDataSource mDataSource ;

  private final TwitterLoginContract.view mTwitterLoginView ;

  public TwitterLoginPresenter(@NonNull UserDataSource mDataSource,
      @NonNull TwitterLoginContract.view mTwitterLoginView) {

    this.mDataSource = checkNotNull(mDataSource, "data source cannot be null");
    this.mTwitterLoginView = checkNotNull(mTwitterLoginView, "view cannot be null!");

    this.mTwitterLoginView.setPresenter(this);
  }

  @Override public void start() {
     mDataSource.isUserSaved(new UserDataSource.CheckUserExistsCallback() {
      @Override public void onUserFound(boolean found) {
        if(!found) {
          mTwitterLoginView.showToast(Snackbar.make(mTwitterLoginView.getLogInButton(),
              mTwitterLoginView.shareContext().getString(R.string.not_saved_user),
              BaseTransientBottomBar.LENGTH_SHORT));
        }
        else {

          //TODO let the user enter to next level
        }
      }
    });
  }


  @Override public void setTwitterCallback(TwitterLoginButton twitterLoginButton) {

  }
}