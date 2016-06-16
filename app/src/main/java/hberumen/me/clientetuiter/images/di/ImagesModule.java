package hberumen.me.clientetuiter.images.di;

import com.twitter.sdk.android.Twitter;
import com.twitter.sdk.android.core.Session;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Singleton;
import dagger.Module;
import dagger.Provides;
import hberumen.me.clientetuiter.api.CustomTweeterApiClient;
import hberumen.me.clientetuiter.entities.Image;
import hberumen.me.clientetuiter.images.ImagesInteractor;
import hberumen.me.clientetuiter.images.ImagesInteractorImpl;
import hberumen.me.clientetuiter.images.ImagesPresenter;
import hberumen.me.clientetuiter.images.ImagesPresenterImpl;
import hberumen.me.clientetuiter.images.ImagesRepository;
import hberumen.me.clientetuiter.images.ImagesRepositoryImpl;
import hberumen.me.clientetuiter.images.ui.ImagesView;
import hberumen.me.clientetuiter.images.ui.adapters.ImagesAdapter;
import hberumen.me.clientetuiter.images.ui.adapters.OnItemClickListener;
import hberumen.me.clientetuiter.lib.base.EventBus;
import hberumen.me.clientetuiter.lib.base.ImageLoader;

/**
 * Created by hberumen on 15/06/16.
 */
@Module
public class ImagesModule {

    private ImagesView view;
    private OnItemClickListener onItemClickListener;

    public ImagesModule(ImagesView view, OnItemClickListener onItemClickListener) {
        this.view = view;
        this.onItemClickListener = onItemClickListener;
    }

    @Provides
    @Singleton
    ImagesAdapter providesAdapter(List<Image> dataset, ImageLoader imageLoader, OnItemClickListener clickListener){
        return  new ImagesAdapter(dataset, clickListener, imageLoader);
    }

    @Provides
    @Singleton
    OnItemClickListener providesOnItemClicListener(){
        return this.onItemClickListener;
    }

    @Provides
    @Singleton
    List<Image> providesItemList(){
        return new ArrayList<Image>();
    }

    @Provides
    @Singleton
    ImagesPresenter providesImagesPresenter(ImagesView view, EventBus eventBus, ImagesInteractor interactor){
        return new ImagesPresenterImpl(view, eventBus, interactor);
    }

    @Provides
    @Singleton
    ImagesView providesImagesView(){
        return this.view;
    }

    @Provides
    @Singleton
    ImagesInteractor providesImagesInteractor(ImagesRepository repository){
        return new ImagesInteractorImpl(repository);
    }

    @Provides
    @Singleton
    ImagesRepository providesImagesRepository(EventBus eventBus, CustomTweeterApiClient client){
        return new ImagesRepositoryImpl(eventBus, client);
    }

    @Provides
    @Singleton
    CustomTweeterApiClient providesCustomTweeterApliClient(Session session){
        return new CustomTweeterApiClient(session);
    }

    @Provides
    @Singleton
    Session providesTwitter(){
        return Twitter.getSessionManager().getActiveSession();
    }

}
