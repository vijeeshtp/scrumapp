package com.expertzlab.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.repository.CrudRepository;

import com.expertzlab.model.Project;
import com.expertzlab.model.Story;

@Repository("storyRepository")
public interface StoryRepository  extends JpaRepository<Story, Integer>{ 
	 Story findById(int id);
	

}
