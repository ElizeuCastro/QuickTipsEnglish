package br.com.quicktipsenglish.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by elizeu on 09/09/15.
 */
public class Tips {

    int id;
    String name;
    @SerializedName("tips")
    List<TipsItem> items;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<TipsItem> getItems() {
        return items;
    }

    public void setItems(List<TipsItem> items) {
        this.items = items;
    }
}
