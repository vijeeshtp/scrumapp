		package com.expertzlab.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.expertzlab.model.Sprint;
import com.expertzlab.model.Story;
import com.expertzlab.model.User;


@Repository("sprintRepository")

public interface SprintRepository extends JpaRepository<Sprint, Integer>{
	Sprint findById(int id);
}
	