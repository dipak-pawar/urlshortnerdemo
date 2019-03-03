package com.avpa.demo.urlshortner.minifyurl;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MinifyService {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private MinifyRepository minifyRepository;
	
	/**
	 * This method take the real url, and shorten it store in db 
	 * and return shorten url. If url is already present in db,
	 * it return saved value.
	 * @param url
	 * @return
	 */
	
	public GenericResponse addUrlToDatabase(String url) {
		// check if this url already exist, if so return existing value
		Optional<UrlsModel> shortUrl = minifyRepository.findByrealurl(url);
		if (shortUrl.isPresent()) {
			logger.info("shortened url already exist for {} , value {}", url, shortUrl.get().getShorturl());
			return new GenericResponse("04", "url_already_exist", shortUrl.get().getShorturl());
		}
		
		//create shortened url, store in db
		int nextId = 1; // first time entry
		String newMinifyUrl = "";
		//TODO: to avoid race condition, may be handle this in better way, or may be convert url to other base
		synchronized(this) {
			Optional<UrlsModel> latestRow = minifyRepository.findFirstByOrderByIdDesc();
			if (latestRow.isPresent()) {
				nextId = latestRow.get().getId() + 1;
			}
			newMinifyUrl = Integer.toString(nextId, 36);
			logger.info("saving new short url {}, for value {}", newMinifyUrl, url);
			minifyRepository.save(new UrlsModel(nextId, newMinifyUrl, url));
		}
		return new GenericResponse("00", "all_ok", newMinifyUrl);
	}
	
	/**
	 * This method take minifyUrl as input and fetch actual url value.
	 * @param shortUrl
	 * @return actualUrl
	 */
	public GenericResponse getRedirectUrl(String shortUrl) {
		Optional<UrlsModel> urlsModel = minifyRepository.findByshorturl(shortUrl);
		if (urlsModel.isPresent()) {
			logger.info("url found for {}", shortUrl);
			return new GenericResponse("00", "all_ok", urlsModel.get().getRealurl());
		} else {
			logger.info("url not found for {}", shortUrl);
			return new GenericResponse("99", "url_not_found", "requested url not presented in our db");
		}
	}
}
