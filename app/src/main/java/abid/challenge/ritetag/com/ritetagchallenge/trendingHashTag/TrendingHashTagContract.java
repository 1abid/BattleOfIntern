package abid.challenge.ritetag.com.ritetagchallenge.trendingHashTag;

import abid.challenge.ritetag.com.ritetagchallenge.BasePresenter;
import abid.challenge.ritetag.com.ritetagchallenge.BaseView;
import abid.challenge.ritetag.com.ritetagchallenge.rest.responseModels.HashTag;
import android.content.Context;
import java.util.List;

/**
 * Created by VutkaBilai on 7/29/17.
 * mail : la4508@gmail.com
 */

public interface TrendingHashTagContract {

  interface view extends BaseView<Presenter>{
    void showInitialActivity();
    void showProgress(boolean show);

    void showTags(List<HashTag> tags);

  }


  interface Presenter extends BasePresenter{
    void logOutUser();
  }
}
