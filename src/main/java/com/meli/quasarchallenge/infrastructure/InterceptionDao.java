package com.meli.quasarchallenge.infrastructure;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.meli.quasarchallenge.model.Satellite;

@Entity
@Table(name="interception")
public class InterceptionDao {

	@Id
	@Column(name="interceptionId")
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	Integer interceptionId;

	@Column(name="satelliteId", nullable= false,length=30)
	String satelliteId;

	@Column(name="distance")
	double distance;

	@Column(name="decoded")
	boolean decoded;
	
	@Column(name="deprecated")
	boolean deprecated;

	public Integer getInterceptionId() {
		return interceptionId;
	}

	public void setInterceptionId(Integer interceptionId) {
		this.interceptionId = interceptionId;
	}

	public String getSatelliteId() {
		return satelliteId;
	}

	public void setSatelliteId(String satelliteId) {
		this.satelliteId = satelliteId;
	}

	public double getDistance() {
		return distance;
	}

	public void setDistance(double distance) {
		this.distance = distance;
	}

	public boolean isDecoded() {
		return decoded;
	}

	public void setDecoded(boolean decoded) {
		this.decoded = decoded;
	}

	public boolean isDeprecated() {
		return deprecated;
	}

	public void setDeprecated(boolean deprecated) {
		this.deprecated = deprecated;
	}

	public InterceptionDao(Satellite satelliteId,double distance) {
		this.satelliteId = satelliteId.getName();
		this.distance = distance;
		this.decoded = false;

	}

	public InterceptionDao() {
	}
		
}
