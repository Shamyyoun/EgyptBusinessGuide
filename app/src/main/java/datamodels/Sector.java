package datamodels;

import java.io.Serializable;

/**
 * Created by Shamyyoun on 3/19/2015.
 */
public class Sector extends SearchableItem {
    private String id;
    private String name;

    public Sector(String id) {
        this.id = id;
    }

    public Sector(String id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public String getId() {
        return id;
    }

    public Sector setId(String id) {
        this.id = id;
        return this;
    }

    @Override
    public String getName() {
        return name;
    }

    public Sector setName(String name) {
        this.name = name;
        return this;
    }

    @Override
    public String getThumbnail() {
        return null;
    }
}
