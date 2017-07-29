package abid.challenge.ritetag.com.ritetagchallenge.rest.responseModels;

/**
 * Created by VutkaBilai on 7/29/17.
 * mail : la4508@gmail.com
 */

public class Influence {

  String username ;
  String photo ;
  int followers ;

  public Influence(String username, String photo, int followers) {
    this.username = username;
    this.photo = photo;
    this.followers = followers;
  }

  public String getUsername() {
    return username;
  }

  public String getPhoto() {
    return photo;
  }

  public int getFollowers() {
    return followers;
  }
}
