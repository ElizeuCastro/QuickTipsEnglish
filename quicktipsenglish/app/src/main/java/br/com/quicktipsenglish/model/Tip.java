package br.com.quicktipsenglish.model;

import java.util.List;

/**
 * Created by elizeu on 09/09/15.
 */
public class Tip {

    private int type;
    private String descriptionBr;
    private String descriptionUs;
    private List<TipItem> items;

    public int getType() {
        return type;
    }

    public String getDescriptionBr() {
        return descriptionBr;
    }

    public String getDescriptionUs() {
        return descriptionUs;
    }

    public List<TipItem> getItems() {
        return items;
    }
}
