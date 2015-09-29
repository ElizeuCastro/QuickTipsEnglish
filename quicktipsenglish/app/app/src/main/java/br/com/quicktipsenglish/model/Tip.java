package br.com.quicktipsenglish.model;

import java.util.List;

/**
 * Created by elizeu on 09/09/15.
 */
public class Tip {

    int type;
    String typeDescription;
    List<TipsItem> items;

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getTypeDescription() {
        return typeDescription;
    }

    public void setTypeDescription(String typeDescription) {
        this.typeDescription = typeDescription;
    }

    public List<TipsItem> getItems() {
        return items;
    }

    public void setItems(List<TipsItem> items) {
        this.items = items;
    }
}
