package com.skilldistillery.tracker.data;

import java.util.List;

import com.skilldistillery.tracker.entities.Miles;

public interface TrackerDAO {

	Boolean deleteMiles(int id);

	List<Miles> showMilesRan();

	Miles findMiles(int id);

	Miles replaceMiles(String json, int id);

	Miles createMiles(String json);

}
