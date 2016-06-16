package hberumen.me.clientetuiter.hashtags.ui;

import java.util.List;

import hberumen.me.clientetuiter.entities.HashTag;

/**
 * Created by hberumen on 15/06/16.
 */
public interface HashTagView {

    void showList();
    void hideList();
    void showProgress();
    void hideProgress();

    void onError(String errror);
    void setHashTag(List<HashTag> items);
}
