package hberumen.me.clientetuiter.hashtags.events;

import java.util.List;

import hberumen.me.clientetuiter.entities.HashTag;

/**
 * Created by hberumen on 15/06/16.
 */
public class HashTagEvent {
    private String error;
    private List<HashTag> hashTags;

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public List<HashTag> getHashTags() {
        return hashTags;
    }

    public void setHashTags(List<HashTag> hashTags) {
        this.hashTags = hashTags;
    }
}
