package abid.challenge.ritetag.com.ritetagchallenge.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.NonNull;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by VutkaBilai on 7/28/17.
 * mail : la4508@gmail.com
 */

public class UserDataSourceImp implements UserDataSource {

  private static UserDataSourceImp INSTANCE;

  private UserDbHelper mDbHelper;


  // Prevent direct instantiation.
  private UserDataSourceImp(@NonNull Context context) {
    checkNotNull(context);
    mDbHelper = new UserDbHelper(context);
  }

  public static UserDataSourceImp getInstance(@NonNull Context context) {
    if (INSTANCE == null) {
      INSTANCE = new UserDataSourceImp(context);
    }
    return INSTANCE;
  }


  @Override public void saveUser(@NonNull User user , @NonNull SaveUserCallback callback) {
    checkNotNull(user);
    SQLiteDatabase db = mDbHelper.getWritableDatabase();

    ContentValues values = new ContentValues();
    values.put(UserContract.UserEntry.COLUMN_NAME_TWITTER_USER_NAME, user.getUserTwitterName());
    values.put(UserContract.UserEntry.COLUMN_NAME_TWITTER_USER_ID, user.getUserTwitterId());

    db.insert(UserContract.UserEntry.TABLE_NAME, null, values);

    db.close();

    callback.onSuccessFullUserSave();
  }

  @Override public void getUser(@NonNull GetUserCallback getUserCallback) {

    User user = null;
    SQLiteDatabase db = mDbHelper.getReadableDatabase();

    String[] projection = {
        UserContract.UserEntry.COLUMN_NAME_TWITTER_USER_NAME,
        UserContract.UserEntry.COLUMN_NAME_TWITTER_USER_ID
    };

    Cursor c = db.query(
        UserContract.UserEntry.TABLE_NAME, projection, null, null, null, null, null);

    if(c != null && c.moveToFirst()){
      String userName = c.getString(c.getColumnIndexOrThrow(UserContract.UserEntry.COLUMN_NAME_TWITTER_USER_NAME));
      long userId = c.getLong(c.getColumnIndexOrThrow(UserContract.UserEntry.COLUMN_NAME_TWITTER_USER_ID));

      user = new User(userName , userId) ;
    }

    if (c != null) {
      c.close();
    }

    db.close();

    if(user != null)
      getUserCallback.onUserFound(user);
    else
      getUserCallback.onUserNotFound();
  }

  @Override public void logOut(UserLogoutCallback userLogoutCallback) {
    SQLiteDatabase db = mDbHelper.getWritableDatabase();

    db.delete(UserContract.UserEntry.TABLE_NAME, null, null);

    db.close();

    userLogoutCallback.onUserLogout();
  }

  @Override public void isUserSaved(@NonNull CheckUserExistsCallback checkUserExistsCallback) {
    User user = null;
    SQLiteDatabase db = mDbHelper.getReadableDatabase();

    String[] projection = {
        UserContract.UserEntry.COLUMN_NAME_TWITTER_USER_NAME,
        UserContract.UserEntry.COLUMN_NAME_TWITTER_USER_ID
    };

    Cursor c = db.query(
        UserContract.UserEntry.TABLE_NAME, projection, null, null, null, null, null);

    if(c != null && c.moveToFirst()){
      String userName = c.getString(c.getColumnIndexOrThrow(UserContract.UserEntry.COLUMN_NAME_TWITTER_USER_NAME));
      long userId = c.getLong(c.getColumnIndexOrThrow(UserContract.UserEntry.COLUMN_NAME_TWITTER_USER_ID));

      user = new User(userName , userId) ;
    }

    if (c != null) {
      c.close();
    }

    db.close();

    if(user != null)
       checkUserExistsCallback.onUserFound(true);
    else
       checkUserExistsCallback.onUserFound(false);

  }
}
