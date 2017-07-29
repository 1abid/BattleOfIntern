package abid.challenge.ritetag.com.ritetagchallenge.hashTagInfluence;

import abid.challenge.ritetag.com.ritetagchallenge.R;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import static com.google.common.base.Preconditions.checkNotNull;

public class HashTagInfluenceActivity extends AppCompatActivity {

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_hash_tag_influence);

    String tag = getIntent().getStringExtra("tag");

    // Set up the toolbar.
    Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
    setSupportActionBar(toolbar);
    ActionBar ab = getSupportActionBar();
    checkNotNull(tag);
    ab.setTitle("#"+tag +" Influences");
    ab.setDisplayHomeAsUpEnabled(true);
    ab.setDisplayShowHomeEnabled(true);
  }


  @Override public boolean onSupportNavigateUp() {
    onBackPressed();
    return true;
  }
}
