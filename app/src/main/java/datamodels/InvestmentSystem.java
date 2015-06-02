package datamodels;

/**
 * Created by Shamyyoun on 3/31/2015.
 */
public class InvestmentSystem {
    private String name;
    private int imageResId;
    private String desc;

    private boolean expanded; // used for listview only

    public InvestmentSystem() {
    }

    public InvestmentSystem(String name, int imageResId, String desc) {
        this.name = name;
        this.imageResId = imageResId;
        this.desc = desc;
    }

    public InvestmentSystem setName(String name) {
        this.name = name;
        return this;
    }

    public String getName() {
        return name;
    }

    public int getImageResId() {
        return imageResId;
    }

    public InvestmentSystem setImageResId(int imageResId) {
        this.imageResId = imageResId;
        return this;
    }

    public String getDesc() {
        return desc;
    }

    public InvestmentSystem setDesc(String desc) {
        this.desc = desc;
        return this;
    }

    public InvestmentSystem setExpanded(boolean expanded) {
        this.expanded = expanded;
        return this;
    }

    public boolean isExpanded() {
        return expanded;
    }
}
