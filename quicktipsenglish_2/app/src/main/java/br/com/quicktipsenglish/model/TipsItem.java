package br.com.quicktipsenglish.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by elizeu on 25/09/15.
 */
public class TipsItem {

    private Integer id;
    private Integer tipsId;
    private String descriptionUs;
    private String descriptionBr;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getTipsId() {
        return tipsId;
    }

    public void setTipsId(Integer tipsId) {
        this.tipsId = tipsId;
    }

    public String getDescriptionUs() {
        return descriptionUs;
    }

    public void setDescriptionUs(String descriptionUs) {
        this.descriptionUs = descriptionUs;
    }

    public String getDescriptionBr() {
        return descriptionBr;
    }

    public void setDescriptionBr(String descriptionBr) {
        this.descriptionBr = descriptionBr;
    }
}
