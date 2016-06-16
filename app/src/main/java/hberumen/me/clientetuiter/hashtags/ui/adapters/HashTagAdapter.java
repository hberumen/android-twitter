package hberumen.me.clientetuiter.hashtags.ui.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import hberumen.me.clientetuiter.R;
import hberumen.me.clientetuiter.entities.HashTag;
import hberumen.me.clientetuiter.hashtags.ui.CustomGridLayoutManager;

/**
 * Created by hberumen on 15/06/16.
 */
public class HashTagAdapter extends RecyclerView.Adapter<HashTagAdapter.ViewHolder>{

    private Context context;
    private List<HashTag> items;
    private OnItemClickListener clickListener;

    public HashTagAdapter(Context context, List<HashTag> items, OnItemClickListener clickListener) {
        this.context = context;
        this.items = items;
        this.clickListener = clickListener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.content_hashtag, parent, false);
        return new ViewHolder(parent.getContext(), view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        HashTag tweet = items.get(position);
        holder.setClickListener(tweet, clickListener);
        holder.txtTweet.setText(tweet.getTweetText());
        holder.setItems(tweet.getHashtags());
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public void setItems(List<HashTag> newItems) {
        items.addAll(newItems);
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        @BindView(R.id.txtTweet) TextView txtTweet;
        @BindView(R.id.recyclerView)  RecyclerView recyclerView;

        private View view;
        private ArrayList<String> items;
        private HashtagsListAdapter adapter;

        public ViewHolder(Context context, View view) {
            super(view);
            ButterKnife.bind(this, view);
            this.view = view;

            items = new ArrayList<String>();
            adapter = new HashtagsListAdapter(items);
            CustomGridLayoutManager layoutManager = new CustomGridLayoutManager(context, 3);
            recyclerView.setLayoutManager(layoutManager);
            recyclerView.setAdapter(adapter);
        }
        public void setClickListener(final HashTag tweet,
                                     final OnItemClickListener listener) {
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.onItemClick(tweet);
                }
            });

        }

        public void setItems(List<String> newItems){
            items.clear();
            items.addAll(newItems);
            adapter.notifyDataSetChanged();
        }
    }

}
