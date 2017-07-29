package abid.challenge.ritetag.com.ritetagchallenge;

import android.content.Context;

/**
 * Created by VutkaBilai on 7/28/17.
 * mail : la4508@gmail.com
 */

public interface BaseView<T> {
  void setPresenter(T presenter);

  Context shareContext();
}
