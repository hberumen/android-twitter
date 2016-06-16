package hberumen.me.clientetuiter.images.ui;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
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
import butterknife.Unbinder;
import hberumen.me.clientetuiter.R;
import hberumen.me.clientetuiter.entities.Image;
import hberumen.me.clientetuiter.images.ImagesPresenter;
import hberumen.me.clientetuiter.images.di.DaggerImagesComponent;
import hberumen.me.clientetuiter.images.di.ImagesModule;
import hberumen.me.clientetuiter.images.ui.adapters.ImagesAdapter;
import hberumen.me.clientetuiter.images.ui.adapters.OnItemClickListener;
import hberumen.me.clientetuiter.lib.di.LibsModule;


public class ImagesFragment extends Fragment implements ImagesView, OnItemClickListener {

    @BindView(R.id.progressBar)
    ProgressBar progresBar;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.container)
    FrameLayout container;

    @Inject
    public ImagesAdapter imagesAdapter;
    @Inject
    public ImagesPresenter imagesPresenter;
    private Unbinder butter;

    public ImagesFragment() {
        // Required empty public constructor
    }

    @Override
    public void onResume() {
        super.onResume();
        imagesPresenter.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        imagesPresenter.onPause();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_content, container, false);
        butter = ButterKnife.bind(this, view);
        setupInjection();
        setupRecyclerView();
        imagesPresenter.getImageTweets();
        return view;
    }

    private void setupRecyclerView() {
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        recyclerView.setAdapter(imagesAdapter);
    }

    public void setupInjection() {
        DaggerImagesComponent
                .builder()
                .libsModule(new LibsModule(this))
                .imagesModule(new ImagesModule(this, this))
                .build()
                .inject(this);
    }

    @Override
    public void onItemClick(Image image) {
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(image.getTweetUrl()));
        startActivity(intent);
    }

    @Override
    public void showImages() {
        recyclerView.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideImages() {
        recyclerView.setVisibility(View.GONE);
    }

    @Override
    public void showProgressBar() {
        progresBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgressBar() {
        progresBar.setVisibility(View.GONE);
    }

    @Override
    public void onError(String error) {
        Snackbar.make(container, error, Snackbar.LENGTH_SHORT).show();
    }

    @Override
    public void setContent(List<Image> items) {
        imagesAdapter.setItems(items);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        butter.unbind();
    }
}
