package abid.challenge.ritetag.com.ritetagchallenge.trendingHashTag;

import abid.challenge.ritetag.com.ritetagchallenge.R;
import abid.challenge.ritetag.com.ritetagchallenge.login.TwitterLogInActivity;
import abid.challenge.ritetag.com.ritetagchallenge.rest.responseModels.HashTag;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import java.util.ArrayList;
import java.util.List;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by VutkaBilai on 7/29/17.
 * mail : la4508@gmail.com
 */

public class TrendingHashTagFragment extends Fragment implements TrendingHashTagContract.view {

  private TrendingHashTagContract.Presenter mPresenter;

  private HashTagAdapter mHashTagAdapter;

  public TrendingHashTagFragment() {
  }

  public static TrendingHashTagFragment newInstance(){
    return new TrendingHashTagFragment();
  }

  @Override public void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    mHashTagAdapter = new HashTagAdapter(mItemListener , new ArrayList<HashTag>(0));
  }

  HashTagAdapter.TagItemListener mItemListener = new HashTagAdapter.TagItemListener() {
    @Override public void onItemClick(HashTag tag) {

    }
  };

  @Override public void onResume() {
    super.onResume();
    mPresenter.start();
  }

  @Nullable @Override
  public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
      @Nullable Bundle savedInstanceState) {
    View rootView = inflater.inflate(R.layout.trending_hash_fragment , container , false);

    RecyclerView recyclerView = (RecyclerView) rootView.findViewById(R.id.tags_list);
    recyclerView.setAdapter(mHashTagAdapter);

    int numColumns = getContext().getResources().getInteger(R.integer.num_notes_columns);

    recyclerView.setHasFixedSize(true);
    recyclerView.setLayoutManager(new GridLayoutManager(getContext(), numColumns));

    // Pull-to-refresh
    SwipeRefreshLayout swipeRefreshLayout =
        (SwipeRefreshLayout) rootView.findViewById(R.id.refresh_layout);
    swipeRefreshLayout.setColorSchemeColors(
        ContextCompat.getColor(getActivity(), R.color.colorPrimary),
        ContextCompat.getColor(getActivity(), R.color.colorAccent),
        ContextCompat.getColor(getActivity(), R.color.colorPrimaryDark));
    swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
      @Override
      public void onRefresh() {
        mPresenter.start();
      }
    });
    return rootView;
  }

  @Override public void setPresenter(TrendingHashTagContract.Presenter presenter) {
    mPresenter = checkNotNull(presenter);
  }

  @Override public Context shareContext() {
    return getContext();
  }

  @Override public void showInitialActivity() {
    getActivity().startActivity(new Intent(getContext() , TwitterLogInActivity.class));
    getActivity().finish();
  }

  @Override public void showProgress(final boolean show) {
    if(getView() == null)
      return;
    final SwipeRefreshLayout refreshlayout =
        (SwipeRefreshLayout) getView().findViewById(R.id.refresh_layout);

    refreshlayout.post(new Runnable() {
      @Override public void run() {
        refreshlayout.setRefreshing(show);
      }
    });
  }

  @Override public void showTags(List<HashTag> tags) {
    mHashTagAdapter.replaceData(tags);
  }
}
