package com.avpa.demo.urlshortner.minifyurl;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class UrlsModel {

	@Id
	private String shorturl;
	@Column(unique = true)
	private String realurl;

	public UrlsModel() {

	}

	public UrlsModel(String shorturl, String realurl) {
		this.shorturl = shorturl;
		this.realurl = realurl;
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
