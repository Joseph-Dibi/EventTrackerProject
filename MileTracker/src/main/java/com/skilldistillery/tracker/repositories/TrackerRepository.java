package com.skilldistillery.tracker.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skilldistillery.tracker.entities.Miles;

public interface TrackerRepository extends JpaRepository<Miles, Integer>{
	List<Miles> findByWeek(int week);
}
