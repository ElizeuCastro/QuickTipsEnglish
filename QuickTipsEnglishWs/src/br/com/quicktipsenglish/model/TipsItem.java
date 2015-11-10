package br.com.quicktipsenglish.model;

public class TipsItem {

	private Integer id;
	private String descriptionBr;
	private String descriptionUs;
	private int tipsId;

	public TipsItem(int id, String descritionBr, String descritionUs, int tipsId) {
		super();
		this.id = id;
		this.descriptionBr = descritionBr;
		this.descriptionUs = descritionUs;
		this.tipsId = tipsId;
	}

}
