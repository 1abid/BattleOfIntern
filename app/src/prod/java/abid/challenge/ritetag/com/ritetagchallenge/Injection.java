package abid.challenge.ritetag.com.ritetagchallenge;

import abid.challenge.ritetag.com.ritetagchallenge.data.UserDataSourceImp;
import android.content.Context;
import android.support.annotation.NonNull;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by VutkaBilai on 7/28/17.
 * mail : la4508@gmail.com
 */

public class Injection {
  public static UserDataSourceImp provideUserDataSource(@NonNull Context context){
    checkNotNull(context);
    return UserDataSourceImp.getInstance(context);
  }


}
