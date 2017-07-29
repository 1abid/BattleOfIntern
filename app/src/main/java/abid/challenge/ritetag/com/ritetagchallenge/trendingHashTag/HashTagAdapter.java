package abid.challenge.ritetag.com.ritetagchallenge.trendingHashTag;

import abid.challenge.ritetag.com.ritetagchallenge.R;
import abid.challenge.ritetag.com.ritetagchallenge.rest.responseModels.HashTag;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import java.util.List;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by VutkaBilai on 7/29/17.
 * mail : la4508@gmail.com
 */

public class HashTagAdapter extends RecyclerView.Adapter<HashTagAdapter.TagsViewHolder> {


  interface TagItemListener {
    void onItemClick(HashTag tag);
  }

  private TagItemListener itemListener ;
  private List<HashTag> mListTags;

  public HashTagAdapter(TagItemListener itemListener, List<HashTag> mListTags) {
    this.itemListener = itemListener;
    this.mListTags = mListTags;
  }

  @Override public TagsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    LayoutInflater inflater = LayoutInflater.from(parent.getContext());
    View itemView = inflater.inflate(R.layout.item_tag , null);
    TagsViewHolder viewHolder = new TagsViewHolder(itemView , itemListener);
    return viewHolder;
  }

  @Override public void onBindViewHolder(TagsViewHolder holder, int position) {
    HashTag tag = mListTags.get(position);

    holder.title.setText(tag.getTag());
    holder.tweets.setText(tag.getTweets());
    holder.reTweets.setText(tag.getRetweets());
  }

  public void replaceData(List<HashTag> tags){
    setList(tags);
    notifyDataSetChanged();
  }

  private void setList(List<HashTag> tags) {
    checkNotNull(tags);
    mListTags = tags;
  }

  private HashTag getItem(int position){
    checkArgument(position != -1);
    return mListTags.get(position);
  }

  @Override public int getItemCount() {
    return null == mListTags ? 0 : mListTags.size();
  }

  public class TagsViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    public TextView title;

    public TextView tweets;
    public TextView reTweets;
    private TagItemListener mItemListener;


    public TagsViewHolder( View itemView , TagItemListener clickListener) {
      super(itemView);
      mItemListener = clickListener ;

      title = (TextView) itemView.findViewById(R.id.tag_title);
      tweets = (TextView) itemView.findViewById(R.id.tag_tweets);
      reTweets = (TextView) itemView.findViewById(R.id.tag_retweets);

      itemView.setOnClickListener(this);
    }

    @Override public void onClick(View v) {
      int position = getAdapterPosition();
      mItemListener.onItemClick(getItem(position));
    }
  }
}
