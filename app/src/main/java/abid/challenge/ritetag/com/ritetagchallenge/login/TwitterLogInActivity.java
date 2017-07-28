package abid.challenge.ritetag.com.ritetagchallenge.login;

import abid.challenge.ritetag.com.ritetagchallenge.R;
import abid.challenge.ritetag.com.ritetagchallenge.util.ActivityUtils;
import abid.challenge.ritetag.com.ritetagchallenge.util.EspressoIdlingResource;
import android.support.annotation.VisibleForTesting;
import android.support.test.espresso.IdlingResource;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class TwitterLogInActivity extends AppCompatActivity {

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_twitter_log_in);


    TwitterLoginFragment twitterLoginFragment =
        (TwitterLoginFragment) getSupportFragmentManager().findFragmentById(R.id.contentFrame);
    if (twitterLoginFragment == null) {
      // Create the fragment
      twitterLoginFragment = TwitterLoginFragment.newInstance();
      ActivityUtils.addFragmentToActivity(
          getSupportFragmentManager(), twitterLoginFragment, R.id.contentFrame);
    }
  }


  @VisibleForTesting
  public IdlingResource getCountingIdlingResource() {
    return EspressoIdlingResource.getIdlingResource();
  }
}
