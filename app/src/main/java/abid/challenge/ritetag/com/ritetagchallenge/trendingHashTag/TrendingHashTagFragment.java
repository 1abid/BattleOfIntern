package abid.challenge.ritetag.com.ritetagchallenge.trendingHashTag;

import abid.challenge.ritetag.com.ritetagchallenge.R;
import abid.challenge.ritetag.com.ritetagchallenge.login.TwitterLogInActivity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by VutkaBilai on 7/29/17.
 * mail : la4508@gmail.com
 */

public class TrendingHashTagFragment extends Fragment implements TrendingHashTagContract.view {

  private TrendingHashTagContract.Presenter mPresenter;

  public TrendingHashTagFragment() {
  }

  public static TrendingHashTagFragment newInstance(){
    return new TrendingHashTagFragment();
  }

  @Nullable @Override
  public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
      @Nullable Bundle savedInstanceState) {
    View rootView = inflater.inflate(R.layout.trending_hash_fragment , container , false);

    mPresenter.start();
    return rootView;
  }

  @Override public void setPresenter(TrendingHashTagContract.Presenter presenter) {
    mPresenter = checkNotNull(presenter);
  }

  @Override public void showInitialActivity() {
    getActivity().startActivity(new Intent(getContext() , TwitterLogInActivity.class));
    getActivity().finish();
  }
}
