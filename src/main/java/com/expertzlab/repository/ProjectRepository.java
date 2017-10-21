package com.expertzlab.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.expertzlab.model.Project;
import com.expertzlab.model.User;

@Repository("projectRepository")
public interface ProjectRepository extends  JpaRepository<Project, Long> {
	 Project findByTitle(String email);
	 Project findById(int id);
}
