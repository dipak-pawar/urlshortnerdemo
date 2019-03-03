package com.avpa.demo.urlshortner.minifyurl;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

public interface MinifyRepository extends CrudRepository<UrlsModel, String>{
	Optional<UrlsModel> findFirstByOrderByIdDesc();
	Optional<UrlsModel> findByrealurl(String realUrl);
	Optional<UrlsModel> findByshorturl(String shortUrl);
}
