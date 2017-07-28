package abid.challenge.ritetag.com.ritetagchallenge.login;

import abid.challenge.ritetag.com.ritetagchallenge.Injection;
import abid.challenge.ritetag.com.ritetagchallenge.R;
import abid.challenge.ritetag.com.ritetagchallenge.util.ActivityUtils;
import abid.challenge.ritetag.com.ritetagchallenge.util.EspressoIdlingResource;
import android.content.Intent;
import android.support.annotation.VisibleForTesting;
import android.support.test.espresso.IdlingResource;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import com.twitter.sdk.android.core.Twitter;

public class TwitterLogInActivity extends AppCompatActivity {

  private TwitterLoginPresenter mTwitterPresenter;

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

    // Create the presenter
    mTwitterPresenter = new TwitterLoginPresenter(
        Injection.provideUserDataSource(getApplicationContext()) , twitterLoginFragment);


  }

  @Override protected void onActivityResult(int requestCode, int resultCode, Intent data) {
    super.onActivityResult(requestCode, resultCode, data);

    TwitterLoginFragment twitterLoginFragment = (TwitterLoginFragment) getSupportFragmentManager().findFragmentById(R.id.contentFrame);
    if (twitterLoginFragment != null) {
      twitterLoginFragment.onActivityResult(requestCode, resultCode, data);
    }
  }

  @VisibleForTesting
  public IdlingResource getCountingIdlingResource() {
    return EspressoIdlingResource.getIdlingResource();
  }
}
