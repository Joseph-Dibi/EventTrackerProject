package com.skilldistillery.tracker.services;

import java.util.List;

import com.skilldistillery.tracker.entities.Miles;

public interface TrackerService {

	List<Miles> showMilesRan();

	Miles findMiles(int id);

	Miles createMiles(Miles mile);

	Boolean deleteMiles(int id);

	List<Double> totalMilesRan();

	List<Double> averageMilesRan();

	Miles replaceMiles(Miles mile, int id);

	Miles updateMiles(Miles newRun, int id);

	List<Miles> findByWeek(int week);

	List<Double> findWeeklyAverage(int week);

	List<Double> findWeeklyTotal(int week);

	double totalRunTime(List<Miles> trackTime);

	double averageRunTime(List<Miles> trackTime);

}
