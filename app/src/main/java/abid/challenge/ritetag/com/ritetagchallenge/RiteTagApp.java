package abid.challenge.ritetag.com.ritetagchallenge;

import android.app.Application;
import android.util.Log;
import com.twitter.sdk.android.core.DefaultLogger;
import com.twitter.sdk.android.core.Twitter;
import com.twitter.sdk.android.core.TwitterAuthConfig;
import com.twitter.sdk.android.core.TwitterConfig;

/**
 * Created by VutkaBilai on 7/28/17.
 * mail : la4508@gmail.com
 */

public class RiteTagApp extends Application {

  @Override public void onCreate() {
    super.onCreate();
    TwitterConfig config = new TwitterConfig.Builder(this)
        .logger(new DefaultLogger(Log.DEBUG))
        .twitterAuthConfig(new TwitterAuthConfig(getString(R.string.com_twitter_sdk_android_CONSUMER_KEY)
            , getString(R.string.com_twitter_sdk_android_CONSUMER_SECRET)))
        .debug(true)
        .build();
    Twitter.initialize(config);
  }
}
