package hberumen.me.clientetuiter.images.ui;

import java.util.List;

import hberumen.me.clientetuiter.entities.Image;

/**
 * Created by hberumen on 14/06/16.
 */
public interface ImagesView {
    void showImages();
    void hideImages();
    void showProgressBar();
    void hideProgressBar();

    void onError(String error);
    void setContent(List<Image> items);

}
