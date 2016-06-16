package hberumen.me.clientetuiter.images;

import com.twitter.sdk.android.core.models.MediaEntity;
import com.twitter.sdk.android.core.models.Tweet;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import hberumen.me.clientetuiter.api.CustomTweeterApiClient;
import hberumen.me.clientetuiter.entities.Image;
import hberumen.me.clientetuiter.images.events.ImagesEvent;
import hberumen.me.clientetuiter.lib.base.EventBus;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by hberumen on 15/06/16.
 */
public class ImagesRepositoryImpl implements ImagesRepository {

    private EventBus eventBus;
    private CustomTweeterApiClient client;
    private final static int TWEET_COUNT = 100;

    public ImagesRepositoryImpl(EventBus eventBus, CustomTweeterApiClient client) {
        this.eventBus = eventBus;
        this.client = client;
    }

    @Override
    public void getImages() {
        Callback<List<Tweet>> callback = new Callback<List<Tweet>>() {
            @Override
            public void success(List<Tweet> tweets, Response response) {
                List<Image> items = new ArrayList<Image>();
                for(Tweet tweet : tweets){
                    if(containsImages(tweet)){
                        Image tweetModel = new Image();
                        tweetModel.setId(tweet.idStr);
                        tweetModel.setFavoriteCount(tweet.favoriteCount);

                        String tweetText = tweet.text;
                        int index = tweetText.indexOf("http");
                        if(index > 0){
                            tweetText = tweetText.substring(0, index);
                        }

                        tweetModel.setTweetText(tweetText);

                        MediaEntity currentPhoto = tweet.entities.media.get(0);
                        String imageUrl = currentPhoto.mediaUrl;
                        tweetModel.setImageUrl(imageUrl);

                        items.add(tweetModel);
                    }
                }

                Collections.sort(items, new Comparator<Image>() {
                    @Override
                    public int compare(Image lhs, Image rhs) {
                        return rhs.getFavoriteCount() - lhs.getFavoriteCount();
                    }
                });

                post(items);
            }

            @Override
            public void failure(RetrofitError error) {
                post(error.getLocalizedMessage());
            }
        };

        client.getTimeLineServicer().homeTimeline(TWEET_COUNT, true, true, true, true, callback);
    }

    private boolean containsImages(Tweet tweet){
        return tweet.entities != null
                && tweet.entities.media != null
                && !tweet.entities.media.isEmpty();
    }

    private void post(List<Image> items){
        post(items, null);
    }

    private void post(String error){
        post(null, error);
    }

    private void post(List<Image> items, String error){
        ImagesEvent event = new ImagesEvent();
        event.setError(error);
        event.setImages(items);
        eventBus.post(event);
    }
}
