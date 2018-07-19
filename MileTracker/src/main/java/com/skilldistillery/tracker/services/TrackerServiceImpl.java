package com.skilldistillery.tracker.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skilldistillery.tracker.entities.Miles;
import com.skilldistillery.tracker.repositories.TrackerRepository;

@Service
public class TrackerServiceImpl implements TrackerService {

	@Autowired
	TrackerRepository repo;

	@Override
	public Miles replaceMiles(Miles mile, int id) {
		mile.setId(id);
		repo.saveAndFlush(mile);
		return mile;

	}
	@Override
	public List<Miles> findByWeek(int week) {
		List<Miles> milesByWeek = repo.findByWeek(week);
		return milesByWeek;
		
	}
	@Override
	public List<Double> findWeeklyTotal(int week) {
		List<Miles> milesByWeek = repo.findByWeek(week);
		double totalMiles = 0;
		double runTime = 0;
		for (Miles miles : milesByWeek) {
			totalMiles += miles.getMiles();
			runTime += miles.getTime();
		}
		List<Double> milesAndTime = new ArrayList();
		milesAndTime.add(totalMiles);
		milesAndTime.add(runTime);
		return milesAndTime;
		
	}
	@Override
	public List<Double> findWeeklyAverage(int week) {
		List<Miles> avgMilesByWeek = repo.findByWeek(week);
		double totalMiles = 0;
		double runTime = 0;
		for (Miles miles : avgMilesByWeek) {
			totalMiles += miles.getMiles();
			runTime += miles.getTime();

		}
		double averageMiles = totalMiles / avgMilesByWeek.size();
		double averageTime = runTime / avgMilesByWeek.size();
		List<Double> milesAndTime = new ArrayList();
		milesAndTime.add(averageMiles);
		milesAndTime.add(averageTime);
		return milesAndTime;
		
	}
	@Override
	public Miles updateMiles(Miles newRun, int id) {
		Optional option = repo.findById(id);
		Miles oldRun = (Miles) option.get();
		if(oldRun.getMiles() != newRun.getMiles() && newRun.getMiles() > 0) {
			oldRun.setMiles(newRun.getMiles());
		}
		if(oldRun.getTime() != newRun.getMiles() && newRun.getTime() > 0) {
			oldRun.setTime(newRun.getTime());
		}
		if(oldRun.getWeek() != newRun.getWeek()) {
			oldRun.setWeek(newRun.getWeek());
		}
		if(oldRun.getComments() != newRun.getComments() && newRun.getComments() != null) {
			oldRun.setComments(newRun.getComments());
		}
		repo.saveAndFlush(oldRun);
		return oldRun;
		
	}

	@Override
	public List<Miles> showMilesRan() {
		return repo.findAll();
	}

	@Override
	public Miles findMiles(int id) {
		Optional option = repo.findById(id);
		Miles miles = (Miles) option.get();
		return miles;
	}

	@Override
	public Miles createMiles(Miles mile) {
		if(mile.getMiles() > 0 && mile.getWeek() > 0) {
			if (mile.getTime() < 0) {
				mile.setTime(0);
			}
			if(mile.getComments() == null) {
				mile.setComments("");
			}
			repo.saveAndFlush(mile);
		}
		else {
			mile = null;
		}
		return mile;
	}

	@Override
	public Boolean deleteMiles(int id) {
		Boolean deleted = false;
		try {
			repo.deleteById(id);
			deleted = true;

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return deleted;
	}

	@Override
	public List<Double> totalMilesRan() {
		List<Miles> averageMilesRan = repo.findAll();
		double totalMiles = 0;
		double runTime = 0;
		for (Miles miles : averageMilesRan) {
			totalMiles += miles.getMiles();
			runTime += miles.getTime();
		}
		List<Double> milesAndTime = new ArrayList();
		milesAndTime.add(totalMiles);
		milesAndTime.add(runTime);
		return milesAndTime;
	}
	@Override
	public double totalRunTime(List<Miles> trackTime) {
		double runTime = 0;
		for (Miles miles : trackTime) {
			runTime += miles.getTime();
		}
		return runTime;
	}
	@Override
	public double averageRunTime(List<Miles> trackTime) {
		double runTime = 0;
		for (Miles miles : trackTime) {
			runTime += miles.getTime();
		}
		runTime = runTime / trackTime.size();
		return runTime;
	}

	@Override
	public List<Double> averageMilesRan() {
		List<Miles> averageMilesRan = repo.findAll();
		double totalMiles = 0;
		double runTime = 0;
		for (Miles miles : averageMilesRan) {
			totalMiles += miles.getMiles();
			runTime += miles.getTime();

		}
		double averageMiles = totalMiles / averageMilesRan.size();
		double averageTime = runTime / averageMilesRan.size();
		List<Double> milesAndTime = new ArrayList();
		milesAndTime.add(averageMiles);
		milesAndTime.add(averageTime);
		return milesAndTime;
	}
}
