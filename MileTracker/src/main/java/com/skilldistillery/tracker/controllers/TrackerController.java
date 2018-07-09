package com.skilldistillery.tracker.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.skilldistillery.tracker.data.TrackerDAO;
import com.skilldistillery.tracker.entities.Miles;

@RestController
@RequestMapping("api/")
public class TrackerController {

	@Autowired
	TrackerDAO dao;
	
	@RequestMapping(path="miles", method=RequestMethod.GET)
	public List<Miles> showMiles() {
		
		return dao.showMilesRan();
	}
	@RequestMapping(path="miles/{id}", method=RequestMethod.GET)
	public Miles showMilesSpecific(@PathVariable int id) {
		
		return dao.findMiles(id);
	}
	@RequestMapping(path="miles/{id}", method=RequestMethod.PUT)
	public Miles updatePost(@RequestBody String json, @PathVariable int id) {
		return dao.replaceMiles(json, id);
	}
	@RequestMapping(path="miles/{id}", method=RequestMethod.DELETE)
	public Boolean deleteMiles(@PathVariable int id) {
		
		return dao.deleteMiles(id);
	}
	@RequestMapping(path="miles", method=RequestMethod.POST)
	public Miles createPost(@RequestBody String json) {
		return dao.createMiles(json);
	}
	
	@RequestMapping(path = "ping", method = RequestMethod.GET)
	public String ping() {
	  return "pong";
	}
	@RequestMapping(path = "miles/total", method = RequestMethod.GET)
	public double totalRan() {
		return dao.totalMilesRan();
	}
	@RequestMapping(path = "miles/average", method = RequestMethod.GET)
	public double averageRan() {
		return dao.averageMilesRan();
	}
	
}

