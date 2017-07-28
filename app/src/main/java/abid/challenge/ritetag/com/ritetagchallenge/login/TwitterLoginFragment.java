package abid.challenge.ritetag.com.ritetagchallenge.login;

import abid.challenge.ritetag.com.ritetagchallenge.R;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by VutkaBilai on 7/28/17.
 * mail : la4508@gmail.com
 */

public class TwitterLoginFragment extends Fragment implements TwitterLoginContract.view{

  public TwitterLoginFragment() {
  }

  public static TwitterLoginFragment newInstance(){
    return new TwitterLoginFragment();
  }

  @Nullable @Override
  public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
      @Nullable Bundle savedInstanceState) {
    View rootView = inflater.inflate(R.layout.twitter_log_in_frag , container , false);

    return rootView ;
  }

  @Override public void setPresenter(TwitterLoginContract.Presenter presenter) {

  }

  @Override public void goToNextActivity() {

  }
}
