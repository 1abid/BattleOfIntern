package abid.challenge.ritetag.com.ritetagchallenge.data;

/**
 * Created by VutkaBilai on 7/28/17.
 * mail : la4508@gmail.com
 */

public class User {

  private String userTwitterName ;
  private String userTwitterId ;

  public User(String userTwitterName, String userTwitterId) {
    this.userTwitterName = userTwitterName;
    this.userTwitterId = userTwitterId;
  }

  public String getUserTwitterName() {
    return userTwitterName;
  }


  public String getUserTwitterId() {
    return userTwitterId;
  }


  @Override public String toString() {
    return this.userTwitterName ;
  }
}
