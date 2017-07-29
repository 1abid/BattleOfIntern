package abid.challenge.ritetag.com.ritetagchallenge.hashTagInfluence;

import abid.challenge.ritetag.com.ritetagchallenge.R;
import abid.challenge.ritetag.com.ritetagchallenge.trendingHashTag.TrendingHashTagFragment;
import abid.challenge.ritetag.com.ritetagchallenge.util.ActivityUtils;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import static com.google.common.base.Preconditions.checkNotNull;

public class HashTagInfluenceActivity extends AppCompatActivity {

  private InfluencePresenter mInfluencePresenter;

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_hash_tag_influence);

    String tag = getIntent().getStringExtra("tag");

    // Set up the toolbar.
    Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
    setSupportActionBar(toolbar);
    ActionBar ab = getSupportActionBar();
    checkNotNull(tag);
    ab.setTitle("#"+tag +" "+getString(R.string.influence));
    ab.setDisplayHomeAsUpEnabled(true);
    ab.setDisplayShowHomeEnabled(true);

    InfluenceFragment influenceFragment =
        (InfluenceFragment) getSupportFragmentManager().findFragmentById(R.id.contentFrame);
    if (influenceFragment == null) {
      // Create the fragment
      influenceFragment = InfluenceFragment.newInstance();
      ActivityUtils.addFragmentToActivity(
          getSupportFragmentManager(), influenceFragment, R.id.contentFrame);
    }

    mInfluencePresenter = new InfluencePresenter(influenceFragment , tag);
  }


  @Override public boolean onSupportNavigateUp() {
    onBackPressed();
    return true;
  }
}
