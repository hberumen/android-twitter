package hberumen.me.clientetuiter.hashtags;

import com.twitter.sdk.android.core.models.HashtagEntity;
import com.twitter.sdk.android.core.models.Tweet;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import hberumen.me.clientetuiter.api.CustomTweeterApiClient;
import hberumen.me.clientetuiter.entities.HashTag;
import hberumen.me.clientetuiter.hashtags.events.HashTagEvent;
import hberumen.me.clientetuiter.lib.base.EventBus;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by hberumen on 15/06/16.
 */
public class HashTagRepositoryImpl implements HashTagRepository {

    private EventBus eventBus;
    private CustomTweeterApiClient client;
    private final static int TWEET_COUNT = 100;

    public HashTagRepositoryImpl(EventBus eventBus, CustomTweeterApiClient client) {
        this.eventBus = eventBus;
        this.client = client;
    }

    @Override
    public void getHashTags() {
        client.getTimeLineServicer().homeTimeline(TWEET_COUNT, true, true, true, true,
                new Callback<List<Tweet>>() {
                    @Override
                    public void success(List<Tweet> tweets, Response response) {
                        List<HashTag> items = new ArrayList<HashTag>();
                        for(Tweet tweet : tweets){
                            if (checkIfTweetHasHashtags(tweet)) {
                                HashTag tweetModel = new HashTag();

                                tweetModel.setId(tweet.idStr);
                                tweetModel.setTweetText(tweet.text);
                                tweetModel.setFavoriteCount(tweet.favoriteCount);

                                List<String> hashtags = new ArrayList<String>();
                                for (HashtagEntity hashtag : tweet.entities.hashtags) {
                                    hashtags.add(hashtag.text);
                                }
                                tweetModel.setHashtags(hashtags);

                                items.add(tweetModel);
                            }
                        }
                        Collections.sort(items, new Comparator<HashTag>() {
                            public int compare(HashTag t1, HashTag t2) {
                                return t2.getFavoriteCount() - t1.getFavoriteCount();
                            }
                        });
                        postEvent(items);
                    }

                    @Override
                    public void failure(RetrofitError error) {
                        postEvent(error.getMessage());
                    }
                });
    }

    private boolean checkIfTweetHasHashtags(Tweet tweet) {
        return  tweet.entities != null &&
                tweet.entities.hashtags != null &&
                !tweet.entities.hashtags.isEmpty();
    }

    private void postEvent(String error) {
        HashTagEvent event = new HashTagEvent();
        event.setError(error);
        eventBus.post(event);
    }

    private void postEvent(List<HashTag> items) {
        HashTagEvent event = new HashTagEvent();
        event.setHashTags(items);
        eventBus.post(event);
    }
}
