package hberumen.me.clientetuiter.hashtags.di;

import javax.inject.Singleton;

import dagger.Component;
import hberumen.me.clientetuiter.TwitterAppModule;
import hberumen.me.clientetuiter.hashtags.ui.HashTagFragment;
import hberumen.me.clientetuiter.lib.di.LibsModule;

/**
 * Created by hberumen on 15/06/16.
 */
@Singleton @Component(modules = {HashTagModule.class, LibsModule.class, TwitterAppModule.class})
public interface HashTagComponent {
    void inject(HashTagFragment fragment);
}
