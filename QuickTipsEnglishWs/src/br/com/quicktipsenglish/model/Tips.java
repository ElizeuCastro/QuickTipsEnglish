package br.com.quicktipsenglish.model;

import java.util.List;

public class Tips {

	private Integer id;
	private String name;
	private List<TipsItem> tips;

	public Tips() {
	}

	public Tips(Integer id, String name, List<TipsItem> tips) {
		super();
		this.id = id;
		this.name = name;
		this.tips = tips;
	}

	public Tips(Integer id, String name) {
		super();
		this.id = id;
		this.name = name;
		this.tips = null;
	}

	public boolean isSaved() {
		return this.id > -1;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<TipsItem> getTips() {
		return tips;
	}

	public void setTips(List<TipsItem> tips) {
		this.tips = tips;
	}

	@Override
	public String toString() {
		return "Category [id=" + id + ", name=" + name + ", tips=" + tips + "]";
	}

}
