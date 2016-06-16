package hberumen.me.clientetuiter;

import android.content.Context;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by hberumen on 15/06/16.
 */
@Module
public class TwitterAppModule {
    Context context;

    public TwitterAppModule(Context context) {
        this.context = context;
    }

    @Provides
    @Singleton
    Context provideContext() {
        return this.context;
    }
}
