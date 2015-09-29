package br.com.quicktipsenglish.model;

/**
 * Created by elizeu on 25/09/15.
 */
public class TipsItem {

    private String titleUs;
    private String titleBr;
    private String descriptionUs;
    private String descriptionBr;

    public String getTitleUs() {
        return titleUs;
    }

    public void setTitleUs(String titleUs) {
        this.titleUs = titleUs;
    }

    public String getTitleBr() {
        return titleBr;
    }

    public void setTitleBr(String titleBr) {
        this.titleBr = titleBr;
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
