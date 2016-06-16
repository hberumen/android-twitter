package hberumen.me.clientetuiter.lib.di;


import android.support.v4.app.Fragment;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestManager;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import hberumen.me.clientetuiter.lib.GlideImageLoader;
import hberumen.me.clientetuiter.lib.GreenRoborEventBus;
import hberumen.me.clientetuiter.lib.base.EventBus;
import hberumen.me.clientetuiter.lib.base.ImageLoader;

/**
 * Created by hberumen on 14/06/16.
 */
@Module
public class LibsModule {
    private Fragment fragment;

    public LibsModule(Fragment fragment) {
        this.fragment = fragment;
    }

    @Provides
    @Singleton
    ImageLoader providerImageLoader(RequestManager requestManager){
        return new GlideImageLoader(requestManager);
    }

    @Provides
    @Singleton
    RequestManager providesRequestManager(Fragment fragment){
        return Glide.with(fragment);
    }

    @Provides
    @Singleton
    Fragment providesFragment(){
        return this.fragment;
    }

    @Provides
    @Singleton
    EventBus providerEventBus(org.greenrobot.eventbus.EventBus eventBus){
        return new GreenRoborEventBus(eventBus);
    }

    @Provides
    @Singleton
    org.greenrobot.eventbus.EventBus providesLibraryEventBus(){
        return org.greenrobot.eventbus.EventBus.getDefault();
    }

}
