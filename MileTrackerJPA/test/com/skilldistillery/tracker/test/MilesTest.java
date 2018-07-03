package com.skilldistillery.tracker.test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.skilldistillery.tracker.entities.Miles;

public class MilesTest {
	private EntityManager em;
	private static EntityManagerFactory emf;
	
	@BeforeAll
	public static void setUpAll() {
		emf = Persistence.createEntityManagerFactory("MilesTracker");
	}
	
	@BeforeEach
	  public void setUp() throws Exception {
	    em = emf.createEntityManager();
	  }
	  
	@AfterEach
	public void tearDown() throws Exception {
		em.close();
	}

	  @AfterAll
	  public static void tearDownAll() {
		  emf.close();
	  }
	
	@Test
	public void test_address_mapping() {
		Miles milesRan = em.find(Miles.class, 1);
		assertEquals(1, milesRan.getMiles());
		
		System.out.println("da");
		// No memory leaks...
	}
}
