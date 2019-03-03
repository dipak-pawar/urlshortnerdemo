package com.avpa.demo.urlshortner.minifyurl;

import static org.junit.Assert.assertSame;

import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MinifyServiceTest {

	@Autowired
	private MinifyService minifyService;
	
	@MockBean
    private MinifyRepository minifyRepository;
	
	@Before
	public void setUp() {
		// site found on get
	    UrlsModel urlsModel = new UrlsModel(1, "suexist", "www.phoronix.com");
	 	Mockito.when(minifyRepository.findByshorturl("suexist")).thenReturn(Optional.of(urlsModel));
	 	
	 	// site not found on get
	 	Mockito.when(minifyRepository.findByshorturl("notfound")).thenReturn(Optional.empty());
	 	
	 	// url not exist, post request
	 	Mockito.when(minifyRepository.findByrealurl("newurl")).thenReturn(Optional.empty());
	 	
	 	// url exist, post request
	 	Mockito.when(minifyRepository.findByrealurl("urlexists")).thenReturn(Optional.of(urlsModel));
	}
	
	@Test
	public void whenShortenedUrlPresentDuringGet() {
		GenericResponse response = minifyService.getRedirectUrl("suexist");
		assertSame("00", response.getReturnCode());
		assertSame("www.phoronix.com", response.getMessage());
	}
	
	@Test
	public void whenShortenedUrlNotPresentDuringGet() {
		GenericResponse response = minifyService.getRedirectUrl("notfound");
		assertSame("99", response.getReturnCode());
	}
	
	@Test
	public void whenRealUrlNotPresentDuringPost() {
		GenericResponse response = minifyService.addUrlToDatabase("newurl");
		assertSame("00", response.getReturnCode());
	}
	
	@Test
	public void whenRealUrlPresentDuringPost() {
		GenericResponse response = minifyService.addUrlToDatabase("urlexists");
		assertSame("04", response.getReturnCode());
	}
}
