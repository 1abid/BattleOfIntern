package abid.challenge.ritetag.com.ritetagchallenge.login;

import abid.challenge.ritetag.com.ritetagchallenge.R;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import com.twitter.sdk.android.core.identity.TwitterLoginButton;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by VutkaBilai on 7/28/17.
 * mail : la4508@gmail.com
 */

public class TwitterLoginFragment extends Fragment implements TwitterLoginContract.view{

  private TwitterLoginContract.Presenter mPresenter ;

  private TwitterLoginButton twitterLoginButton ;

  public TwitterLoginFragment() {
  }

  public static TwitterLoginFragment newInstance(){
    return new TwitterLoginFragment();
  }

  @Nullable @Override
  public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
      @Nullable Bundle savedInstanceState) {
    View rootView = inflater.inflate(R.layout.twitter_log_in_frag , container , false);

    twitterLoginButton = (TwitterLoginButton) rootView.findViewById(R.id.login_button);

    mPresenter.setTwitterCallback(twitterLoginButton);

    return rootView ;
  }

  @Override public void onResume() {
    super.onResume();
    //mPresenter.start();
  }

  @Override public void setPresenter(TwitterLoginContract.Presenter presenter) {
    mPresenter = checkNotNull(presenter);
  }

  @Override public void goToNextActivity() {

  }

  @Override public void showToast(Snackbar snakbar) {
    snakbar.show();
  }

  @Override public void showToast(Toast toast) {
    toast.show();
  }

  @Override public TwitterLoginButton getLogInButton() {
    checkNotNull(twitterLoginButton);

    return twitterLoginButton;
  }

  @Override public Context shareContext() {
    return getContext();
  }
}
