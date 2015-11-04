package br.com.quicktipsenglish.persistence;

import java.util.List;

public class Category {

	private Integer id;
	private String name;
	private List<Tips> tips;

	public Category(Integer id, String name, List<Tips> tips) {
		super();
		this.id = id;
		this.name = name;
		this.tips = tips;
	}

	public Category(Integer id, String name) {
		super();
		this.id = id;
		this.name = name;
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

	public List<Tips> getTips() {
		return tips;
	}

	public void setTips(List<Tips> tips) {
		this.tips = tips;
	}

	@Override
	public String toString() {
		return "Category [id=" + id + ", name=" + name + ", tips=" + tips + "]";
	}

}
