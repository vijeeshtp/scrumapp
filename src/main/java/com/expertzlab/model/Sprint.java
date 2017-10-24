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
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;
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
	//@NotEmpty(message = "*Please provide a valid number")
	private int sprintNo;

	//@Column(name = "sprint_duration")
	//@NotEmpty(message = "*Please provide the expected duration for the Sprint")
	//private int noOfDays;

	@Column(name = "burn_p_day")
	//@Length(min = 24, max = 40,  message = "*burn per day should be in a range of 24 to 40")
	@Min (value=1)
	@Max (value=10000)
	@NotNull(message = "Please provide value between 1 and 10000")
	private int burnperDay;

	public int getBurnperDay() {
		return burnperDay;
	}

	public void setBurnperDay(int burnperDay) {
		this.burnperDay = burnperDay;
	}

	@Column(name = "sprint_description")
	private String sprintDesc;
	
	
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Column(name = "status")
	private String status;

	@DateTimeFormat(pattern = "dd/MM/yyyy")
	@NotNull(message = "*Please provide the Starting Date (dd/MM/yyyy)Format")
	@Column(name = "start_date")
	private Date startDate;

	@DateTimeFormat(pattern = "dd/MM/yyyy")
	@Column(name = "end_date")
	@NotNull(message = "*Please provide the expected Ending Date  in (dd/MM/yyyy)Format")
	private Date endDate;

	@Column(name = "name")
	@NotEmpty(message = "*Please provide a Name for the Sprint")
	@NotNull
	private String name;

	@OneToMany(mappedBy = "sprint")
	Set<Story> stories;

	@ManyToOne
	@JoinColumn(name = "project_id", nullable = false)
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

	/*public int getNoOfDays() {
		return noOfDays;
	}

	public void setNoOfDays(int noOfDays) {
		this.noOfDays = noOfDays;
	}*/

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
