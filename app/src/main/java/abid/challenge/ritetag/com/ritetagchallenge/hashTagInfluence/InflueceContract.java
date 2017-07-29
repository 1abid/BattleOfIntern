package abid.challenge.ritetag.com.ritetagchallenge.hashTagInfluence;

import abid.challenge.ritetag.com.ritetagchallenge.BasePresenter;
import abid.challenge.ritetag.com.ritetagchallenge.BaseView;
import abid.challenge.ritetag.com.ritetagchallenge.rest.responseModels.Influence;
import android.support.design.widget.Snackbar;
import android.view.View;
import java.util.List;

/**
 * Created by VutkaBilai on 7/29/17.
 * mail : la4508@gmail.com
 */

public interface InflueceContract {

  interface view extends BaseView<presenter>{
    void showInfluence(List<Influence> influenceList);
    void showProgress(boolean show);
    void showToast(Snackbar snackbar);
    View getRootView();
  }

  interface presenter extends BasePresenter{

  }

}
