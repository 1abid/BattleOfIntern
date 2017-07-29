package abid.challenge.ritetag.com.ritetagchallenge.rest.responseModels;

/**
 * Created by VutkaBilai on 7/29/17.
 * mail : la4508@gmail.com
 */

public class HashTag {

  String tag ;

  int tweets ;

  int retweets ;

  public HashTag(String tag, int tweets, int retweets) {
    this.tag = tag;
    this.tweets = tweets;
    this.retweets = retweets;
  }

  public String getTag() {
    return tag;
  }

  public int getTweets() {
    return tweets;
  }

  public int getRetweets() {
    return retweets;
  }
}
