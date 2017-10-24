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
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;




@Entity
@Table(name = "story")

public class Story {
	

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "story_id")
	private int id;
	
	@Column(name = "type")
	@NotEmpty(message = "*Please provide the type for the story")
	@NotNull
	private String type;
	
	
	@Column(name = "name")
	@NotEmpty(message = "*Please provide the Name for the story")
	@NotNull
	private String name;
	
	//@NotEmpty
	@Column(name="resolved_date")
	private Date resolvedDate;

	
	
	public Date getResolvedDate() {
		return resolvedDate;
	}

	public void setResolvedDate(Date resolvedDate) {
		this.resolvedDate = resolvedDate;
	}

	@Column(name = "duration")
	//@NotEmpty
	//@NotNull
	private int duration;
	

	@Column(name = "remaining")
	//@NotEmpty
	//@NotNull
	private int remaining;
	

	@Column(name = "actual_hours")
	//@NotEmpty
	//@NotNull
	private int actual;
	
	
	
	
	@Column(name = "status")
	@NotEmpty//(message = "*Please provide the status of the story")
	@NotNull
	private String status;
	

	@NotEmpty
	@NotNull
	@Column(name = "description")
	private String description;
	
	
	//@Length(min = 4, max = 4)
	@Column(name="est_hrs")
	private int est_hrs;
	
	@Column(name = "priority")
	@NotEmpty
	@NotNull
	private String priority;
	
	
	@Column(name = "assign_to")
	@NotEmpty//(message = "*Please Assign the Story to any Developer")
	@NotNull
	private String assigned;
	
	
	
	@ManyToOne
	@JoinColumn(name="project_id", nullable=false)
	private Project project;
	

	@ManyToOne
	@JoinColumn(name="user_id", nullable=false)
	private User user;
	
	

	@ManyToOne
	@JoinColumn(name="sprint_id", nullable=false)
	private Sprint sprint;
	

	@OneToMany (mappedBy="story")
	Set<Activity> activities;

	
	
	public Story() {
		super();
	}

	public Story(int id, int duration,String type, String name, String description, int est_hrs, String priority, String assigned,
			int sprint,String status) {
		super();
		this.id = id;
		this.duration = duration;
		this.type = type;
		this.name = name;
		//this.title = title;
		this.description = description;
		this.est_hrs = est_hrs;
		this.priority = priority;
		this.assigned = assigned;
		this.status = status;
		
		
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getDuration() {
		return duration;
	}

	public void setDuration(int duration) {
		this.duration = duration;
	}

	public int getRemaining() {
		return remaining;
	}

	public void setRemaining(int remaining) {
		this.remaining = remaining;
	}

	public int getActual() {
		return actual;
	}

	public void setActual(int actual) {
		this.actual = actual;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getEst_hrs() {
		return est_hrs;
	}

	public void setEst_hrs(int est_hrs) {
		this.est_hrs = est_hrs;
	}

	public String getPriority() {
		return priority;
	}

	public void setPriority(String priority) {
		this.priority = priority;
	}

	public String getAssigned() {
		return assigned;
	}

	public void setAssigned(String assigned) {
		this.assigned = assigned;
	}

	public Project getProject() {
		return project;
	}

	public void setProject(Project project) {
		this.project = project;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Sprint getSprint() {
		return sprint;
	}

	public void setSprint(Sprint sprint) {
		this.sprint = sprint;
	}

	public Set<Activity> getActivities() {
		return activities;
	}

	public void setActivities(Set<Activity> activities) {
		this.activities = activities;
	}

	
	
	
}
	
	

