package com.expertzlab.model;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.data.annotation.Transient;

@Entity
@Table(name = "project")
public class Project {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "project_id")
	private int id;
	
	@Column(name = "title")
	@NotEmpty(message = "*Please provide a title")
	private String title;
	
	
	@Column(name = "master")
	//@NotEmpty
	//@NotNull
	private String master;
	
	public String getMaster() {
		return master;
	}


	public void setMaster(String master) {
		this.master = master;
	}


	@NotEmpty(message = "*Please provide description about the project")
	@NotNull
	@Column(name = "description")
	private String description;
	
	//@NotNull
	//@Length(min = 4, max = 4)
	@Column(name="duration")
	private int duration;
	
	@Column(name = "active")
	private int active;
	
	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "project_user", joinColumns = @JoinColumn(name = "project_id"), inverseJoinColumns = @JoinColumn(name = "user_id"))
	private Set<User> users;
	
	/*@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "project_story", joinColumns = @JoinColumn(name = "project_id"), inverseJoinColumns = @JoinColumn(name = "story_id"))
	private Set<Story> stories;*/
	
	@OneToMany (mappedBy="project")
	Set<Story> stories;
	
	@OneToMany (mappedBy="project")
	Set<Sprint> sprints;
	
	long assignedUser;
	

	public long getAssignedUser() {
		return assignedUser;
	}


	public void setAssignedUser(long assignedUser) {
		this.assignedUser = assignedUser;
	}


	public Set<Sprint> getSprints() {
		return sprints;
	}


	public void setSprints(Set<Sprint> sprints) {
		this.sprints = sprints;
	}


	public Project(){
		
	}
	
	
	public Project(int id, String title,String master, String description,int duration, int active, Set<User> users,Set<Sprint> sprints) {
		super();
		this.id = id;
		this.title = title;
		this.description = description;
		this.duration = duration;
		this.active = active;
		this.users = users;
		this.sprints = sprints;
		this.master=master;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getActive() {
		return active;
	}

	public void setActive(int active) {
		this.active = active;
	}

	public int getDuration() {
		return duration;
	}


	public void setDuration(int duration) {
		this.duration = duration;
	}


	public Set<User> getUsers() {
		return users;
	}

	public void setUsers(Set<User> users) {
		this.users = users;
	}


	public Set<Story> getStories() {
		return stories;
	}


	public void setStories(Set<Story> stories) {
		this.stories = stories;
	}

}
