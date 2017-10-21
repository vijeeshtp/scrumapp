package com.expertzlab.model;

import java.util.Date;

public class ChartPoint {
	
	Date date;
	
	Integer standard;
	
	Integer done;

	
	
	
	public ChartPoint() {
		super();
	}




	public Date getDate() {
		return date;
	}




	public void setDate(Date date) {
		this.date = date;
	}




	public Integer getStandard() {
		return standard;
	}




	public void setStandard(Integer standard) {
		this.standard = standard;
	}




	public Integer getDone() {
		return done;
	}




	public void setDone(Integer done) {
		this.done = done;
	}




	@Override
	public String toString() {
		return "ChartPoint [date=" + date + ", standard=" + standard + ", done=" + done + "]";
	}

	
	

}
