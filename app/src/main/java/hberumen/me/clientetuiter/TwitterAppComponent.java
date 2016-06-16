package hberumen.me.clientetuiter;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by hberumen on 15/06/16.
 */
@Singleton @Component(modules = {TwitterAppModule.class})
public interface TwitterAppComponent {
}
