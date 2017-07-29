package abid.challenge.ritetag.com.ritetagchallenge.rest.responseModels;

import java.util.List;

/**
 * Created by VutkaBilai on 7/29/17.
 * mail : la4508@gmail.com
 */

public class InfluenceResponse {

  boolean result ;

  int code ;

  String message ;

  String hashtag ;

  List<Influence> influencers ;

  public InfluenceResponse(boolean result, int code, String message, String hashtag,
      List<Influence> influencers) {
    this.result = result;
    this.code = code;
    this.message = message;
    this.hashtag = hashtag;
    this.influencers = influencers;
  }

  public boolean isResult() {
    return result;
  }

  public int getCode() {
    return code;
  }

  public String getMessage() {
    return message;
  }

  public String getHashtag() {
    return hashtag;
  }

  public List<Influence> getInfluencers() {
    return influencers;
  }
}
