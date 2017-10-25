package com.expertzlab.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.expertzlab.model.Project;
import com.expertzlab.model.Sprint;

import com.expertzlab.repository.ProjectRepository;
import com.expertzlab.repository.SprintRepository;
import com.expertzlab.repository.StoryRepository;


@Service("sprintService")

public class SprintServiceImpl  implements SprintService {

	@Autowired
	private SprintRepository sprintRepository;
	
	@Autowired
	private ProjectRepository projectRepository;
	
	@Autowired
	StoryRepository storyRepository;
	
	@Override
	public List<Sprint> findAll() {
		// TODO Auto-generated method stub
		return sprintRepository.findAll();
	}

	@Override
	public void saveSprint(Sprint sprint, Project project)
	{
		sprint.setProject(project);
		sprintRepository.save(sprint);
		project.getSprints().add(sprint);
		projectRepository.save(project);
		
		// TODO Auto-generated method stub
		
	}

	@Override
	public Sprint findById(int Id) {
		// TODO Auto-generated method stub
		return sprintRepository.findOne(Id);
	}

	@Override
	public void deleteProject(Sprint project) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateSprint(Sprint inSprint) {
		Sprint sprint=findById(inSprint.getId());
		sprint.setName(inSprint.getName());
		sprint.setBurnperDay(inSprint.getBurnperDay());
		sprint.setSprintDesc(inSprint.getSprintDesc());
		sprint.setStartDate(inSprint.getStartDate());
		sprint.setEndDate(inSprint.getEndDate());
		sprintRepository.save(sprint);
		
	}

	@Override
	public void deleteSprint(Sprint sprint) {
		// TODO Auto-generated method stub
		
	}

}
