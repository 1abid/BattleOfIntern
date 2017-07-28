package abid.challenge.ritetag.com.ritetagchallenge.trendingHashTag;

import abid.challenge.ritetag.com.ritetagchallenge.Injection;
import abid.challenge.ritetag.com.ritetagchallenge.R;
import abid.challenge.ritetag.com.ritetagchallenge.util.ActivityUtils;
import abid.challenge.ritetag.com.ritetagchallenge.util.EspressoIdlingResource;
import android.content.Intent;
import android.support.annotation.VisibleForTesting;
import android.support.design.widget.NavigationView;
import android.support.test.espresso.IdlingResource;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

public class TrendingHashTagActivity extends AppCompatActivity {

  private DrawerLayout mDrawerLayout;

  private TrendingHashTagPresenter mHashTagPresenter;

  private Toolbar toolbar ;

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_trending_hash_tag);

    // Set up the toolbar.
    toolbar = (Toolbar) findViewById(R.id.toolbar);
    setSupportActionBar(toolbar);
    ActionBar ab = getSupportActionBar();
    ab.setHomeAsUpIndicator(R.drawable.ic_menu);
    ab.setDisplayHomeAsUpEnabled(true);

    // Set up the navigation drawer.
    mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
    mDrawerLayout.setStatusBarBackground(R.color.colorPrimaryDark);
    NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
    if (navigationView != null) {
      setupDrawerContent(navigationView);
    }


    TrendingHashTagFragment hashTagFragment =
        (TrendingHashTagFragment) getSupportFragmentManager().findFragmentById(R.id.contentFrame);
    if (hashTagFragment == null) {
      // Create the fragment
      hashTagFragment = TrendingHashTagFragment.newInstance();
      ActivityUtils.addFragmentToActivity(
          getSupportFragmentManager(), hashTagFragment, R.id.contentFrame);
    }

    // Create the presenter
    mHashTagPresenter = new TrendingHashTagPresenter(
        Injection.provideUserDataSource(getApplicationContext()) , hashTagFragment);

  }

  @Override
  public boolean onOptionsItemSelected(MenuItem item) {
    switch (item.getItemId()) {
      case android.R.id.home:
        // Open the navigation drawer when the home icon is selected from the toolbar.
        mDrawerLayout.openDrawer(GravityCompat.START);
        return true;
    }
    return super.onOptionsItemSelected(item);
  }



  private void setupDrawerContent(NavigationView navigationView) {
    navigationView.setNavigationItemSelectedListener(
        new NavigationView.OnNavigationItemSelectedListener() {
          @Override
          public boolean onNavigationItemSelected(MenuItem menuItem) {
            switch (menuItem.getItemId()) {
              case R.id.list_navigation_menu_item:
                // Do nothing, we're already on that screen
                break;
              case R.id.log_out_menu_item:
                mHashTagPresenter.logOutUser();
                break;
              default:
                break;
            }
            // Close the navigation drawer when an item is selected.
            menuItem.setChecked(true);
            mDrawerLayout.closeDrawers();
            return true;
          }
        });
  }

  @VisibleForTesting
  public IdlingResource getCountingIdlingResource() {
    return EspressoIdlingResource.getIdlingResource();
  }

}
