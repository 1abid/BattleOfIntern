package abid.challenge.ritetag.com.ritetagchallenge.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by VutkaBilai on 7/28/17.
 * mail : la4508@gmail.com
 */

public class UserDbHelper extends SQLiteOpenHelper {

  public static final int DATABASE_VERSION = 1;

  public static final String DATABASE_NAME = "Tasks.db";

  private static final String TEXT_TYPE = " TEXT";

  private static final String TYPE_INT = " INTEGER";

  private static final String AUTO_INC = " AUTOINCREMENT";

  private static final String COMMA_SEP = ",";

  private static final String DROP_TABLE      = "DROP TABLE IF EXISTS ";

  private static final String SQL_CREATE_ENTRIES =
      "CREATE TABLE " + UserContract.UserEntry.TABLE_NAME + " (" +
          UserContract.UserEntry._ID + TYPE_INT + " PRIMARY KEY" +AUTO_INC +COMMA_SEP+
          UserContract.UserEntry.COLUMN_NAME_TWITTER_USER_NAME + TEXT_TYPE + COMMA_SEP +
          UserContract.UserEntry.COLUMN_NAME_TWITTER_USER_ID + TYPE_INT +
          " )";

  public UserDbHelper(Context context) {
    super(context, DATABASE_NAME, null, DATABASE_VERSION);
  }

  public void onCreate(SQLiteDatabase db) {
    db.execSQL(SQL_CREATE_ENTRIES);
  }

  public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    db.execSQL(DROP_TABLE + UserContract.UserEntry.TABLE_NAME);
  }

  public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    // Not required as at version 1
  }
}
