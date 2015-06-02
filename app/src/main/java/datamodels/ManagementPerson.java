package datamodels;

/**
 * Created by Shamyyoun on 3/31/2015.
 */
public class ManagementPerson {
    private String name;
    private int imageResId;
    private String desc;
    private String email;

    private boolean expanded; // used for listview only

    public ManagementPerson() {
    }

    public ManagementPerson(String name, int imageResId, String desc, String email) {
        this.name = name;
        this.imageResId = imageResId;
        this.desc = desc;
        this.email = email;
    }

    public ManagementPerson setName(String name) {
        this.name = name;
        return this;
    }

    public String getName() {
        return name;
    }

    public int getImageResId() {
        return imageResId;
    }

    public ManagementPerson setImageResId(int imageResId) {
        this.imageResId = imageResId;
        return this;
    }

    public String getDesc() {
        return desc;
    }

    public ManagementPerson setDesc(String desc) {
        this.desc = desc;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public ManagementPerson setEmail(String email) {
        this.email = email;
        return this;
    }

    public ManagementPerson setExpanded(boolean expanded) {
        this.expanded = expanded;
        return this;
    }

    public boolean isExpanded() {
        return expanded;
    }
}
