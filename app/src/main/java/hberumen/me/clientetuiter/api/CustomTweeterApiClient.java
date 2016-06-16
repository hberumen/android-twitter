package hberumen.me.clientetuiter.api;

import com.twitter.sdk.android.core.Session;
import com.twitter.sdk.android.core.TwitterApiClient;

/**
 * Created by hberumen on 14/06/16.
 */
public class CustomTweeterApiClient extends TwitterApiClient {

    public CustomTweeterApiClient(Session session) {
        super(session);
    }

    public TimeLineService getTimeLineServicer(){
        return getService(TimeLineService.class);
    }
}
