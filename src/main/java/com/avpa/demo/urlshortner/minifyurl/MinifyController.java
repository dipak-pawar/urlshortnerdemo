package com.avpa.demo.urlshortner.minifyurl;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class MinifyController {
	
	@Autowired
	MinifyService minifyService;
	
	@RequestMapping(method = RequestMethod.GET, value = "/{label}")
	public ModelAndView redirectToRealUrl(@PathVariable(required = true) String label) {
		GenericResponse response = minifyService.getRedirectUrl(label);
		if (response.getReturnCode().equalsIgnoreCase("00")) {
			return new ModelAndView("redirect:" + "http://" + response.getMessage());
		} else {
			return new ModelAndView("redirect:" + "http://localhost:9000/urlnotfound");
		}
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/urlnotfound")
	public GenericResponse showErrorPage() {
		return new GenericResponse("99", "requested url not found", "short url requested by your not present in our database");
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public GenericResponse shortenTheUrl(@Valid @RequestBody ShortenUrlContract contract) {
		return minifyService.addUrlToDatabase(contract.getUrl());
	}
}
