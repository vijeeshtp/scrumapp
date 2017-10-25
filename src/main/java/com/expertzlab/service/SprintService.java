package com.expertzlab.service;

import java.util.List;

import com.expertzlab.model.Project;
import com.expertzlab.model.Sprint;
import com.expertzlab.model.Story;
import com.expertzlab.model.User;

public interface SprintService {

	public List<Sprint> findAll();
	
	public void saveSprint(Sprint sprint,Project project);
	public Sprint findById(int Id);
	public void deleteProject (Sprint project);
	public void updateSprint(Sprint sprint) ;
	public void deleteSprint (Sprint sprint);
	public void releaseSprint(Sprint inSprint);
}
