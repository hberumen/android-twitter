package hberumen.me.clientetuiter.images;

import org.greenrobot.eventbus.Subscribe;

import hberumen.me.clientetuiter.images.ImagesInteractor;
import hberumen.me.clientetuiter.images.ImagesPresenter;
import hberumen.me.clientetuiter.images.events.ImagesEvent;
import hberumen.me.clientetuiter.images.ui.ImagesView;
import hberumen.me.clientetuiter.lib.base.EventBus;

/**
 * Created by hberumen on 15/06/16.
 */
public class ImagesPresenterImpl implements ImagesPresenter {
    private ImagesView view;
    private EventBus eventBus;
    private ImagesInteractor interactor;

    public ImagesPresenterImpl(ImagesView view, EventBus eventBus, ImagesInteractor interactor) {
        this.view = view;
        this.eventBus = eventBus;
        this.interactor = interactor;
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
    public void getImageTweets() {
        if(view == null){
            return;
        }

        view.hideImages();
        view.showProgressBar();
        interactor.execute();
    }

    @Override
    @Subscribe
    public void onEventMainThread(ImagesEvent event) {
        String errorMsg = event.getError();
        if(view == null){
            return;
        }

        view.showImages();
        view.hideProgressBar();

        if(errorMsg == null){
            view.setContent(event.getImages());
        } else {
            view.onError(errorMsg);
        }

    }
}
