package hberumen.me.clientetuiter.images;

import hberumen.me.clientetuiter.images.events.ImagesEvent;

/**
 * Created by hberumen on 14/06/16.
 */
public interface ImagesPresenter {
    void onResume();
    void onPause();
    void onDestroy();

    void getImageTweets();
    void onEventMainThread(ImagesEvent event);

}
