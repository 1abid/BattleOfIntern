package abid.challenge.ritetag.com.ritetagchallenge.rest.responseModels;

import java.util.List;

/**
 * Created by VutkaBilai on 7/29/17.
 * mail : la4508@gmail.com
 */

public class HashTagResponse {

  Boolean result ;

  String message ;

  int code ;

  List<HashTag> tags ;

  public HashTagResponse(Boolean result, String message, int code, List<HashTag> tags) {
    this.result = result;
    this.message = message;
    this.code = code;
    this.tags = tags;
  }

  public Boolean getResult() {
    return result;
  }

  public String getMessage() {
    return message;
  }

  public int getCode() {
    return code;
  }

  public List<HashTag> getTags() {
    return tags;
  }
}
