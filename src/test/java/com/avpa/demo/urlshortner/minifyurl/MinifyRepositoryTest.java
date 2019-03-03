package com.avpa.demo.urlshortner.minifyurl;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@DataJpaTest
public class MinifyRepositoryTest {
	@Autowired
	private TestEntityManager entityManager;

	@Autowired
	private MinifyRepository minifyRepository;
	
	
	@Before
	public void SetUp() {
		UrlsModel urlsModel1 = new UrlsModel(1, "a", "www.phoronix.com");
		UrlsModel urlsModel2 = new UrlsModel(2, "b", "www.distrowatch.com");
		entityManager.persist(urlsModel1);
		entityManager.persist(urlsModel2);
		entityManager.flush();
	}
	
	@Test
	public void findByRealUrlPresent() {
		Optional<UrlsModel> row = minifyRepository.findByrealurl("www.phoronix.com");
		assertTrue(row.isPresent());
		assertSame("a", row.get().getShorturl());
	}
	
	@Test
	public void findByShortUrlPresent() {
		Optional<UrlsModel> row = minifyRepository.findByshorturl("b");
		assertTrue(row.isPresent());
		assertSame("www.distrowatch.com", row.get().getRealurl());
	}
	
	@Test
	public void findByRealUrlNotPresent() {
		Optional<UrlsModel> row = minifyRepository.findByrealurl("www.phoronixnot.com");
		assertFalse(row.isPresent());
	}
	
	@Test
	public void findByShortUrlNotPresent() {
		Optional<UrlsModel> row = minifyRepository.findByshorturl("bnot");
		assertFalse(row.isPresent());
	}
	
	@Test
	public void getLastId() {
		Optional<UrlsModel> row = minifyRepository.findFirstByOrderByIdDesc();
		assertTrue(row.isPresent());
		assertSame(2, row.get().getId());
	}
}
