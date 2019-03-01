package com.avpa.demo.urlshortner.minifyurl;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.avpa.demo.urlshortner.utils.Helper;

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
			logger.info("shortened url already exist for " + url + ", value " + shortUrl.get().getShorturl());;
			return new GenericResponse("04", "url_already_exist", shortUrl.get().getShorturl());
		}
		
		//create shortened url, store in db
		// TODO: better logic here, may be we can generate shortened url sequentially in some manner
		
		boolean continueNewShortenUrlSearch =true;
		String newShortenedUrl = "";
		while (continueNewShortenUrlSearch) {
			newShortenedUrl = Helper.getRandomString(5); // TODO: move to constant or settings
			// check if this shortened url is not already present
			continueNewShortenUrlSearch = minifyRepository.existsById(newShortenedUrl);
			logger.debug("creating new shortened url for " + url + ", " + newShortenedUrl + " ,exist " + continueNewShortenUrlSearch);
		}
		minifyRepository.save(new UrlsModel(newShortenedUrl, url));
		return new GenericResponse("00", "all_ok", newShortenedUrl);
	}
	
	public GenericResponse getRedirectUrl(String shortUrl) {
		Optional<UrlsModel> urlsModel = minifyRepository.findById(shortUrl);
		if (urlsModel.isPresent()) {
			return new GenericResponse("00", "all_ok", urlsModel.get().getRealurl());
		} else {
			return new GenericResponse("99", "url_not_found", "requested url not presented in our db");
		}
	}
}
