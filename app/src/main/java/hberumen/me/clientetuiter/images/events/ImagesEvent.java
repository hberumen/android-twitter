package hberumen.me.clientetuiter.images.events;

import java.util.List;

import hberumen.me.clientetuiter.entities.Image;

/**
 * Created by hberumen on 14/06/16.
 */
public class ImagesEvent {
    private String error;
    private List<Image> images;

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public List<Image> getImages() {
        return images;
    }

    public void setImages(List<Image> images) {
        this.images = images;
    }
}
