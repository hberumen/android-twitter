package hberumen.me.clientetuiter.hashtags.ui;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ProgressBar;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import hberumen.me.clientetuiter.R;
import hberumen.me.clientetuiter.TwitterAppModule;
import hberumen.me.clientetuiter.entities.HashTag;
import hberumen.me.clientetuiter.hashtags.HashTagPresenter;
import hberumen.me.clientetuiter.hashtags.di.DaggerHashTagComponent;
import hberumen.me.clientetuiter.hashtags.di.HashTagModule;
import hberumen.me.clientetuiter.hashtags.ui.adapters.HashTagAdapter;
import hberumen.me.clientetuiter.hashtags.ui.adapters.OnItemClickListener;
import hberumen.me.clientetuiter.lib.di.LibsModule;

/**
 * A simple {@link Fragment} subclass.
 */
public class HashTagFragment extends Fragment  implements HashTagView, OnItemClickListener{


    @Inject
    HashTagAdapter adapter;
    @Inject
    HashTagPresenter hashtagsPresenter;

    @BindView(R.id.container)
    FrameLayout container;
    @BindView(R.id.progressBar)
    ProgressBar progressBar;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    @Override
    public void onResume() {
        super.onResume();
        hashtagsPresenter.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        hashtagsPresenter.onPause();
    }

    @Override
    public void onDestroy() {
        hashtagsPresenter.onDestroy();
        super.onDestroy();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_content, container, false);
        ButterKnife.bind(this, view);

        setupInjection();
        setupRecyclerView();

        hashtagsPresenter.getHashtagTweets();
        return view;
    }

    private void setupInjection() {
        DaggerHashTagComponent
                .builder()
                .libsModule(new LibsModule(this))
                .twitterAppModule(new TwitterAppModule(getContext()))
                .hashTagModule(new HashTagModule(this, this))
                .build()
                .inject(this);
    }


    private void setupRecyclerView() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onError(String error) {
        Snackbar.make(container, error, Snackbar.LENGTH_SHORT).show();
    }

    @Override
    public void setHashTag(List<HashTag> items) {
        adapter.setItems(items);
    }

    @Override
    public void showList() {
        recyclerView.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideList() {
        recyclerView.setVisibility(View.GONE);
    }

    @Override
    public void showProgress() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void onItemClick(HashTag tweet) {
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(tweet.getTweetUrl()));
        startActivity(intent);
    }



}
