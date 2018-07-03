package com.skilldistillery.tracker.data;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.skilldistillery.tracker.entities.Miles;

@Transactional
@Service
public class TrackerDAOImpl implements TrackerDAO {

	@PersistenceContext
	EntityManager em;

	@Override
	public Miles replaceMiles(String json, int id) {
		Miles replacedPost = em.find(Miles.class, id);

		ObjectMapper om = new ObjectMapper();
		try {
			if (replacedPost != null) {
				Miles newPost = om.readValue(json, Miles.class);
				replacedPost.setMiles(newPost.getMiles());

				em.flush();

			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return replacedPost;
	}

	@Override
	public List<Miles> showMilesRan() {
		List<Miles> posts = null;
		String query = "SELECT p FROM Post p";
		posts = em.createQuery(query, Miles.class).getResultList();
		return posts;
	}

	@Override
	public Miles findMiles(int id) {
		Miles post = em.find(Miles.class, id);
		return post;
	}

	@Override
	public Miles createMiles(String json) {
		Miles post = null;
		
		ObjectMapper om = new ObjectMapper();
		try {
			post = om.readValue(json, Miles.class);
			em.persist(post);
			em.flush();
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return post;
	}

	@Override
	public Boolean deleteMiles(int id) {
		Boolean check = false;

		try {
			Miles removeMiles = em.find(Miles.class, id);
			if (removeMiles != null) {
				em.remove(em.find(Miles.class, id));
			}
			check = true;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return check;
	}
}
