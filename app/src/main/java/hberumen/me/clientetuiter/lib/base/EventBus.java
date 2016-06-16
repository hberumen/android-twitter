package hberumen.me.clientetuiter.lib.base;

/**
 * Created by hberumen on 14/06/16.
 */
public interface EventBus {
    void register(Object subscriber);
    void unregister(Object unsuscriber);
    void post(Object event);
}
