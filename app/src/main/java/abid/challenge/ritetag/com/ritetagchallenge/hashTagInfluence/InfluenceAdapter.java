package abid.challenge.ritetag.com.ritetagchallenge.hashTagInfluence;

import abid.challenge.ritetag.com.ritetagchallenge.R;
import abid.challenge.ritetag.com.ritetagchallenge.rest.responseModels.Influence;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import java.util.List;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by VutkaBilai on 7/29/17.
 * mail : la4508@gmail.com
 */

public class InfluenceAdapter extends RecyclerView.Adapter<InfluenceAdapter.InfluenceViewHolder>{

  Context mContext ;

  interface InfluenceItemListener {
    void onItemClick(Influence influnce);
  }

  private InfluenceItemListener itemListener ;
  private List<Influence> mListInfluences;

  public InfluenceAdapter(InfluenceItemListener itemListener, List<Influence> mListTags) {
    this.itemListener = itemListener;
    this.mListInfluences = mListTags;
  }

  @Override public InfluenceViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    mContext = parent.getContext() ;
    LayoutInflater inflater = LayoutInflater.from(mContext);
    View itemView = inflater.inflate(R.layout.item_influence , null);
    InfluenceViewHolder viewHolder = new InfluenceViewHolder(itemView , itemListener);
    return viewHolder;
  }

  @Override public void onBindViewHolder(InfluenceViewHolder holder, int position) {
    Influence influence = mListInfluences.get(position);

    Glide.with(mContext).load(influence.getPhoto()).into(holder.influenceIv);
    holder.influencer.setText(influence.getUsername());
    holder.influencer.setText(mContext.getString(R.string.follower)+String.valueOf(influence.getFollowers()));
  }

  public void replaceData(List<Influence> items){
    setList(items);
    notifyDataSetChanged();
  }

  private void setList(List<Influence> items) {
    checkNotNull(items);
    mListInfluences = items;
  }

  private Influence getItem(int position){
    checkArgument(position != -1);
    return mListInfluences.get(position);
  }

  @Override public int getItemCount() {
    return null == mListInfluences ? 0 : mListInfluences.size();
  }

  public class InfluenceViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    public ImageView influenceIv ;
    public TextView influencer ;
    public TextView follwer ;
    private InfluenceItemListener mItemListener;


    public InfluenceViewHolder( View itemView , InfluenceItemListener clickListener) {
      super(itemView);
      mItemListener = clickListener ;

      influenceIv = (ImageView) itemView.findViewById(R.id.influence_iv);
      influencer = (TextView) itemView.findViewById(R.id.user_name);
      follwer = (TextView) itemView.findViewById(R.id.followers);

      itemView.setOnClickListener(this);
    }

    @Override public void onClick(View v) {
      int position = getAdapterPosition();
      mItemListener.onItemClick(getItem(position));
    }
  }
}
