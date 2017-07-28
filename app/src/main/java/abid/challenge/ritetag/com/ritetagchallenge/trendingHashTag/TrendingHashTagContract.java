package abid.challenge.ritetag.com.ritetagchallenge.trendingHashTag;

import abid.challenge.ritetag.com.ritetagchallenge.BasePresenter;
import abid.challenge.ritetag.com.ritetagchallenge.BaseView;

/**
 * Created by VutkaBilai on 7/29/17.
 * mail : la4508@gmail.com
 */

public interface TrendingHashTagContract {

  interface view extends BaseView<Presenter>{
    void showInitialActivity();
  }


  interface Presenter extends BasePresenter{
    void logOutUser();
  }
}
