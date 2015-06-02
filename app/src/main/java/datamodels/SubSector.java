package datamodels;

import java.io.Serializable;

/**
 * Created by Shamyyoun on 3/19/2015.
 */
public class SubSector extends SearchableItem {
    private String id;
    private String name;

    public SubSector(String id) {
        this.id = id;
    }

    public SubSector(String id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public String getId() {
        return id;
    }

    public SubSector setId(String id) {
        this.id = id;
        return this;
    }

    @Override
    public String getName() {
        return name;
    }

    public SubSector setName(String name) {
        this.name = name;
        return this;
    }

    @Override
    public String getThumbnail() {
        return null;
    }
}
