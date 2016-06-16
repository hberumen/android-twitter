package hberumen.me.clientetuiter.hashtags.di;

import android.content.Context;

import com.twitter.sdk.android.Twitter;
import com.twitter.sdk.android.core.TwitterSession;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import hberumen.me.clientetuiter.api.CustomTweeterApiClient;
import hberumen.me.clientetuiter.entities.HashTag;
import hberumen.me.clientetuiter.hashtags.HashTagInteractor;
import hberumen.me.clientetuiter.hashtags.HashTagInteractorImpl;
import hberumen.me.clientetuiter.hashtags.HashTagPresenter;
import hberumen.me.clientetuiter.hashtags.HashTagPresenterImpl;
import hberumen.me.clientetuiter.hashtags.HashTagRepository;
import hberumen.me.clientetuiter.hashtags.HashTagRepositoryImpl;
import hberumen.me.clientetuiter.hashtags.ui.HashTagView;
import hberumen.me.clientetuiter.hashtags.ui.adapters.HashTagAdapter;
import hberumen.me.clientetuiter.hashtags.ui.adapters.OnItemClickListener;
import hberumen.me.clientetuiter.lib.base.EventBus;

/**
 * Created by hberumen on 15/06/16.
 */
@Module
public class HashTagModule {

    private HashTagView view;
    private OnItemClickListener clickListener;

    public HashTagModule(HashTagView view, OnItemClickListener clickListener) {
        this.view = view;
        this.clickListener = clickListener;
    }

    @Provides
    @Singleton
    List<HashTag> provideItems() {
        return new ArrayList<HashTag>();
    }

    @Provides
    @Singleton
    OnItemClickListener provideClickListener() {
        return this.clickListener;
    }

    @Provides
    HashTagAdapter provideAdapter(Context context, List<HashTag> items, OnItemClickListener clickListener) {
        return new HashTagAdapter(context, items, clickListener);
    }

    @Provides
    @Singleton
    HashTagView provideHashtagsView() {
        return this.view;
    }

    @Provides
    @Singleton
    HashTagPresenter provideHashtagsPresenter(HashTagView view, HashTagInteractor interactor, EventBus eventBus) {
        return new HashTagPresenterImpl(eventBus, view, interactor);
    }

    @Provides
    @Singleton
    HashTagInteractor provideHashtagsInteractor(HashTagRepository repository) {
        return new HashTagInteractorImpl(repository);
    }

    @Provides
    @Singleton
    HashTagRepository provideHashtagsRepository(CustomTweeterApiClient client, EventBus eventBus) {
        return new HashTagRepositoryImpl(eventBus, client);
    }

    @Provides
    @Singleton
    CustomTweeterApiClient provideTwitterApiClient(TwitterSession session) {
        return new CustomTweeterApiClient(session);
    }

    @Provides
    @Singleton
    TwitterSession provideTwitterSession() {
        return Twitter.getSessionManager().getActiveSession();
    }
}
