package hberumen.me.clientetuiter.hashtags;

import hberumen.me.clientetuiter.hashtags.events.HashTagEvent;

/**
 * Created by hberumen on 15/06/16.
 */
public interface HashTagPresenter {
    void onResume();
    void onPause();
    void onDestroy();
    void getHashtagTweets();
    void onEventMainThread(HashTagEvent event);
}
