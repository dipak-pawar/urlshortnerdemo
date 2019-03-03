package com.avpa.demo.urlshortner.minifyurl;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class UrlsModel {

	@Id
	private int id;
	@Column(unique = true)
	private String shorturl;
	@Column(unique = true)
	private String realurl;

	public UrlsModel() {

	}

	public UrlsModel(int id, String shorturl, String realurl) {
		this.id = id;
		this.shorturl = shorturl;
		this.realurl = realurl;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getShorturl() {
		return shorturl;
	}

	public void setShorturl(String shorturl) {
		this.shorturl = shorturl;
	}

	public String getRealurl() {
		return realurl;
	}

	public void setRealurl(String realurl) {
		this.realurl = realurl;
	}
}
