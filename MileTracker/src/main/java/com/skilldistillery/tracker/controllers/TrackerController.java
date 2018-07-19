package com.skilldistillery.tracker.controllers;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.skilldistillery.tracker.entities.Miles;
import com.skilldistillery.tracker.services.TrackerService;

@RestController
@RequestMapping("api/")
public class TrackerController {

	@Autowired
	TrackerService serv;
	
	@RequestMapping(path="miles", method=RequestMethod.GET)
	public List<Miles> showMiles() {
		
		return serv.showMilesRan();
	}
	@RequestMapping(path="miles/{id}", method=RequestMethod.GET)
	public Miles showMilesSpecific(@PathVariable int id) {
		
		return serv.findMiles(id);
	}
	@RequestMapping(path="miles/{id}", method=RequestMethod.PATCH)
	public Miles updatePost(@RequestBody Miles run, @PathVariable int id) {
		return serv.updateMiles(run, id);
	}
	@RequestMapping(path="miles/{id}", method=RequestMethod.PUT)
	public Miles replacePost(@RequestBody Miles run, @PathVariable int id) {
		return serv.replaceMiles(run, id);
	}
	@RequestMapping(path="miles/{id}", method=RequestMethod.DELETE)
	public Boolean deleteMiles(@PathVariable int id, HttpServletResponse response) {
		Boolean deleted = serv.deleteMiles(id);
		if (deleted) {
			response.setStatus(200);
		}
		else {
			response.setStatus(400);

		}
		return deleted;
	}
	@RequestMapping(path="miles", method=RequestMethod.POST)
	public Miles createPost(@RequestBody Miles run, HttpServletResponse response) {
		Miles runCheck = serv.createMiles(run);
		if (runCheck != null) {
			response.setStatus(201);
		}
		else {
			response.setStatus(400);

		}
		return runCheck;
	}
	
	@RequestMapping(path = "ping", method = RequestMethod.GET)
	public String ping() {
	  return "pong";
	}
	@RequestMapping(path = "miles/total", method = RequestMethod.GET)
	public List<Double> totalRan() {
		return serv.totalMilesRan();
	}
	@RequestMapping(path = "miles/week/{week}/total", method = RequestMethod.GET)
	public List<Double> weeklyTotalRan(@PathVariable int week) {
		return serv.findWeeklyTotal(week);
	}
	@RequestMapping(path = "miles/average", method = RequestMethod.GET)
	public List<Double> averageRan() {
		return serv.averageMilesRan();
	}
	@RequestMapping(path = "miles/week/{week}/average", method = RequestMethod.GET)
	public List<Double> weeklyAverageRan(@PathVariable int week) {
		return serv.findWeeklyAverage(week);
	}
	@RequestMapping(path = "miles/week/{week}", method = RequestMethod.GET)
	public List<Miles> milesByWeek(@PathVariable int week) {
		return serv.findByWeek(week);
	}

	
}

