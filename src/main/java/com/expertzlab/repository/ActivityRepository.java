package com.expertzlab.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.expertzlab.model.Activity;
import com.expertzlab.model.Story;

@Repository("activityRepository")
public interface ActivityRepository  extends JpaRepository<Activity, Integer>{
	

}
