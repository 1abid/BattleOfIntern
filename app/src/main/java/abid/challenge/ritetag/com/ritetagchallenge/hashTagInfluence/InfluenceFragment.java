package abid.challenge.ritetag.com.ritetagchallenge.hashTagInfluence;

import abid.challenge.ritetag.com.ritetagchallenge.R;
import abid.challenge.ritetag.com.ritetagchallenge.rest.responseModels.Influence;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
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

public class InfluenceFragment extends Fragment implements InflueceContract.view {

  private InflueceContract.presenter mPresenter;
  private InfluenceAdapter mInfluenceAdapter;

  private View rootView ;

  public InfluenceFragment() {
  }

  public static InfluenceFragment newInstance(){
    return new InfluenceFragment();
  }

  @Override public void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    mInfluenceAdapter = new InfluenceAdapter(mItemListener , new ArrayList<Influence>(0));
  }

  @Nullable @Override
  public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
      @Nullable Bundle savedInstanceState) {
    rootView = inflater.inflate(R.layout.influence_fragment , container , false);

    RecyclerView recyclerView = (RecyclerView) rootView.findViewById(R.id.influence_list);
    recyclerView.setAdapter(mInfluenceAdapter);

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

  InfluenceAdapter.InfluenceItemListener mItemListener = new InfluenceAdapter.InfluenceItemListener() {
    @Override public void onItemClick(Influence tag) {

    }
  };

  @Override public void onResume() {
    super.onResume();
    mPresenter.start();
  }

  @Override public void setPresenter(InflueceContract.presenter presenter) {
    mPresenter  = presenter ;
  }

  @Override public Context shareContext() {
    return getContext();
  }

  @Override public void showInfluence(List<Influence> influenceList) {
    mInfluenceAdapter.replaceData(influenceList);
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

  @Override public void showToast(Snackbar snackbar) {
    snackbar.show();
  }

  @Override public View getRootView() {
    checkNotNull(rootView);
    return rootView ;
  }
}
