package hberumen.me.clientetuiter.images.di;

import javax.inject.Singleton;

import dagger.Component;
import hberumen.me.clientetuiter.images.ImagesPresenter;
import hberumen.me.clientetuiter.images.ui.ImagesFragment;
import hberumen.me.clientetuiter.lib.di.LibsModule;

/**
 * Created by hberumen on 15/06/16.
 */
@Singleton @Component(modules = {LibsModule.class, ImagesModule.class})
public interface ImagesComponent {
    void inject(ImagesFragment fragment);
    ImagesPresenter getPresenter();
}
