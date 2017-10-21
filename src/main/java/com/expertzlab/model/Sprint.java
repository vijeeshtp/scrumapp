package com.expertzlab.model;

import java.util.Date;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "sprints")

public class Sprint {
	

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "sprint_id")
	private int id;
	
	
	@Column(name = "sprintno")
	private int sprintNo;
	
	@Column(name="sprint_duration")
	private int noOfDays;
		
	@Column(name="burn_p_day")
	private int burnperDay;
	
	public int getBurnperDay() {
		return burnperDay;
	}


	public void setBurnperDay(int burnperDay) {
		this.burnperDay = burnperDay;
	}


	@Column(name="sprint_description")
	private String sprintDesc;
	
	  @DateTimeFormat(pattern = "dd/MM/yyyy")
	@Column(name="start_date")
	private Date startDate;
	
	  @DateTimeFormat(pattern = "dd/MM/yyyy")
	@Column(name="end_date")
	private Date endDate;
	
	@Column(name = "name")
	@NotEmpty
	@NotNull
	private String name;
	
	
	@OneToMany (mappedBy="sprint")
	Set<Story> stories;

	@ManyToOne
	@JoinColumn(name="project_id", nullable=false)
	private Project project;
	
	
	public Sprint() {
		super();
	}


	public Sprint(int id, int sprint_hrs, int start_date) {
		super();
		this.id = id;
	
		
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}



	public Set<Story> getStories() {
		return stories;
	}


	public void setStories(Set<Story> stories) {
		this.stories = stories;
	}


	public Project getProject() {
		return project;
	}


	public void setProject(Project project) {
		this.project = project;
	}


	public int getSprintNo() {
		return sprintNo;
	}


	public void setSprintNo(int sprintNo) {
		this.sprintNo = sprintNo;
	}


	public int getNoOfDays() {
		return noOfDays;
	}


	public void setNoOfDays(int noOfDays) {
		this.noOfDays = noOfDays;
	}


	public String getSprintDesc() {
		return sprintDesc;
	}


	public void setSprintDesc(String sprintDesc) {
		this.sprintDesc = sprintDesc;
	}


	public Date getStartDate() {
		return startDate;
	}


	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}


	public Date getEndDate() {
		return endDate;
	}


	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}
	
	

}
