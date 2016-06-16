package hberumen.me.clientetuiter.hashtags;

import org.greenrobot.eventbus.Subscribe;

import java.util.List;

import hberumen.me.clientetuiter.entities.HashTag;
import hberumen.me.clientetuiter.hashtags.events.HashTagEvent;
import hberumen.me.clientetuiter.hashtags.ui.HashTagView;
import hberumen.me.clientetuiter.lib.base.EventBus;

/**
 * Created by hberumen on 15/06/16.
 */
public class HashTagPresenterImpl implements HashTagPresenter {

    private EventBus eventBus;
    private HashTagView view;
    private HashTagInteractor hashTagInteractor;

    public HashTagPresenterImpl(EventBus eventBus, HashTagView view, HashTagInteractor hashTagInteractor) {
        this.eventBus = eventBus;
        this.view = view;
        this.hashTagInteractor = hashTagInteractor;
    }

    @Override
    public void onResume() {
        eventBus.register(this);
    }

    @Override
    public void onPause() {
        eventBus.unregister(this);
    }

    @Override
    public void onDestroy() {
        view = null;
    }

    @Override
    public void getHashtagTweets() {
        if(view != null){
            view.hideList();
            view.showProgress();
        }
        hashTagInteractor.getHashTagItemList();
    }

    @Override
    @Subscribe
    public void onEventMainThread(HashTagEvent event) {
        String errorMsg = event.getError();
        if(view != null){
            view.showList();
            view.hideProgress();
            if(errorMsg != null){
                view.onError(errorMsg);
            }else{
                List<HashTag> items = event.getHashTags();
                if(items != null && !items.isEmpty()){
                    view.setHashTag(items);
                }
            }
        }
    }
}
