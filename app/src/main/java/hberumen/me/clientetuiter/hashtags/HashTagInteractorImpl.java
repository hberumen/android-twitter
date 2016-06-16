package hberumen.me.clientetuiter.hashtags;

/**
 * Created by hberumen on 15/06/16.
 */
public class HashTagInteractorImpl implements HashTagInteractor {

    private HashTagRepository hashTagRepository;

    public HashTagInteractorImpl(HashTagRepository hashTagRepository) {
        this.hashTagRepository = hashTagRepository;
    }

    @Override
    public void getHashTagItemList() {
        hashTagRepository.getHashTags();
    }
}
