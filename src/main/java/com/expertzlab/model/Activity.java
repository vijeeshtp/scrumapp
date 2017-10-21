package com.expertzlab.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "activity")
public class Activity {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "activity_id")
	private int id;
	
	@ManyToOne
	@JoinColumn(name="story_id", nullable=false)
	private Story story;
	
	
	@Column(name="updated_date")
	private Date updatedDate;
	
	@Column(name = "hours_remaining")
	private int reamingHours;

	public Story getStory() {
		return story;
	}

	public void setStory(Story story) {
		this.story = story;
	}

	public Date getUpdatedDate() {
		return updatedDate;
	}

	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}

	public int getReamingHours() {
		return reamingHours;
	}

	public void setReamingHours(int reamingHours) {
		this.reamingHours = reamingHours;
	}
	
	
	
}
