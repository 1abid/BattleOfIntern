package abid.challenge.ritetag.com.ritetagchallenge.trendingHashTag;

import abid.challenge.ritetag.com.ritetagchallenge.data.User;
import abid.challenge.ritetag.com.ritetagchallenge.data.UserDataSource;
import android.support.annotation.NonNull;

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
  }

  @Override public void logOutUser() {
    mDataSource.logOut(new UserDataSource.UserLogoutCallback() {
      @Override public void onUserLogout() {
        mTrendHashTagView.showInitialActivity();
      }
    });
  }
}
