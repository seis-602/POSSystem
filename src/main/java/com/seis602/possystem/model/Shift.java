package com.seis602.possystem.model;

import java.time.Duration;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


@Entity
@Table(name = "shifts")
public class Shift {
	
	private static int idCount = 1;
	
	@Id
    @Column(name = "id")
    private int id;
	
	@OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;
	
	@Column(name = "drawer_amount")
    private int drawerAmount;
	
	@Column(name = "start_time")
	@Temporal(TemporalType.TIMESTAMP)
	private Date startTime;
//	
	@Column(name = "end_time")
	@Temporal(TemporalType.TIMESTAMP)
	private Date endTime;

	public Shift() {
		
	}
	
	public Shift(User user) {
		super();
		this.id = this.idCount++;
		this.user = user;
		this.startTime = new Date();
		this.drawerAmount = 0;
	}
	
	public Shift(User user, Date startTime, Date endTime, int drawerAmount) {
		super();
		this.id = this.idCount++;
		this.user = user;
		this.startTime = startTime;
		this.endTime = endTime;
		this.drawerAmount = drawerAmount;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getDrawerAmount() {
		return drawerAmount;
	}

	public void setDrawerAmount(int drawerAmount) {
		this.drawerAmount = drawerAmount;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}
	
	public String getDuration() {
	
		long millis = getDurationInMillies();
		
		if (millis == 0) return null;
		
		long hours = TimeUnit.MILLISECONDS.toHours(millis);
        millis -= TimeUnit.HOURS.toMillis(hours);
        
        long minutes = TimeUnit.MILLISECONDS.toMinutes(millis);
        millis -= TimeUnit.MINUTES.toMillis(minutes);
        
        long seconds = TimeUnit.MILLISECONDS.toSeconds(millis);

		String str = String.format("%dmin, %dsec", minutes, seconds);
		
		if (hours != 0) {
			str = String.format("%dh, %dmin, %dsec", hours, minutes, seconds);
		}
		
		return str;
	}
	
	private long getDurationInMillies() {
		
		if (this.startTime == null || this.endTime == null) {
			return 0;
		}
		
	    long diffInMillies = this.endTime.getTime() - this.startTime.getTime();
	    return diffInMillies;
//	    return TimeUnit.SECONDS.convert(diffInMillies, TimeUnit.MILLISECONDS);
	}
	
}
