package abid.challenge.ritetag.com.ritetagchallenge.data;

import android.provider.BaseColumns;

/**
 * Created by VutkaBilai on 7/28/17.
 * mail : la4508@gmail.com
 */

public final class UserContract {

  private UserContract(){}

  public static abstract class UserEntry implements BaseColumns {
    public static final String TABLE_NAME = "user";
    public static final String COLUMN_NAME_TWITTER_USER_NAME = "twitter_user_name";
    public static final String COLUMN_NAME_TWITTER_USER_ID = "twitter_id";
  }
}
