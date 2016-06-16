package hberumen.me.clientetuiter.images.ui.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import hberumen.me.clientetuiter.R;
import hberumen.me.clientetuiter.entities.Image;
import hberumen.me.clientetuiter.lib.base.ImageLoader;

/**
 * Created by hberumen on 15/06/16.
 */
public class ImagesAdapter extends RecyclerView.Adapter<ImagesAdapter.ViewHolder> {
    private List<Image> items;
    private ImageLoader imageLoader;
    private OnItemClickListener clickListener;

    public ImagesAdapter(List<Image> items,
                         OnItemClickListener clickListener,
                         ImageLoader imageLoader) {
        this.items = items;
        this.clickListener = clickListener;
        this.imageLoader = imageLoader;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.content_images, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Image tweet = items.get(position);
        holder.setClickListener(tweet, clickListener);
        holder.txtTweet.setText(tweet.getTweetText());
        holder.txtFavs.setText(String.valueOf(tweet.getFavoriteCount()));
        imageLoader.load(holder.imgMedia, tweet.getImageUrl());
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public void setItems(List<Image> newItems) {
        items.addAll(newItems);
        notifyDataSetChanged();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.txtTweet) TextView txtTweet;
        @BindView(R.id.imgMedia) ImageView imgMedia;
        @BindView(R.id.txtFavs) TextView txtFavs;

        private View view;

        public ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
            this.view = view;
        }

        public void setClickListener(final Image tweet,
                                     final OnItemClickListener listener) {
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.onItemClick(tweet);
                }
            });

        }
    }
}