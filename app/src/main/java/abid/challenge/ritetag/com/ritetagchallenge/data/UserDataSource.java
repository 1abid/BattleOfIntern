package abid.challenge.ritetag.com.ritetagchallenge.data;

import android.support.annotation.NonNull;

/**
 * Created by VutkaBilai on 7/28/17.
 * mail : la4508@gmail.com
 */

public interface UserDataSource {

  interface GetUserCallback {

    void onUserFound(User user);

    void onUserNotFound();
  }

  interface CheckUserExistsCallback{
    void onUserFound(boolean found);
  }

  interface UserLogoutCallback{
    void onUserLogout();
  }

  interface SaveUserCallback{
    void onSuccessFullUserSave();
  }

  void saveUser(@NonNull User user , @NonNull SaveUserCallback saveUserCallback);

  void getUser(@NonNull GetUserCallback getUserCallback);

  void logOut(@NonNull UserLogoutCallback userLogoutCallback);

  void isUserSaved(@NonNull CheckUserExistsCallback checkUserExistsCallback);
}
